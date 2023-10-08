package Networking.Server;

import Networking.Pakete.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.util.*;

import static Networking.Server.SpielServer.*;

public class ServerListener extends Listener {

    private int anzahlDerRunden; // vom Host-Spieler bekommen
    private int runde = 0;
    private int zeit = 3;

    private int naechsterClient; // Client, der als nächstes am Zug ist
    private int minispielDauer = 64; // Sekunden

    private enum zustand {
        MENUE_ZUSTAND,
        WUERFEL_ZUSTAND,
        SPIELBRETT_ZUSTAND,
        MINISPIEL_ZUSTAND,

    }

    public int minispielWahl = 0; // für Sammler: 0, für SquidGame: 1

    private zustand spielZustand = zustand.MENUE_ZUSTAND;
    private boolean blockBenutzt = false; // für den Fall, dass der Block vom vorletzten Spieler in spielerReihenfolge ArrayList benutzt wird.

    @Override // wenn ein Client verbunden ist.
    public void connected(Connection connection) {
        System.out.println("Client ist verbunden.");
        ClientsZug zug = new ClientsZug();
        HostClient host = new HostClient();
        host.istHost = false;
        host.clientIndex = alleClients.size();
        SpielerAuskuenfte auskuenfte = new SpielerAuskuenfte();
        auskuenfte.clientIndex = alleClients.size();
        auskuenfte.istDran = false;
        alleClients.put(connection, auskuenfte);
        if (alleClients.size() == 1) {
            auskuenfte.istDran = true;
            alleClients.put(connection, auskuenfte);
            host.istHost = true;
            zug.istDran = true;

        } else {
            zug.istDran = false;
        }
        System.out.println("clientIndex: " + host.clientIndex + " und istHost: " + host.istHost);
        server.sendToTCP(connection.getID(), host);
        server.sendToTCP(connection.getID(), zug);

        AnzahlClients anzahl = new AnzahlClients();
        anzahl.anzahlVerbundeneClients = alleClients.size();
        server.sendToAllTCP(anzahl);
        alleClients.get(connection).istDran = zug.istDran;


        spielerReihenfolge.add(alleClients.get(connection).clientIndex);
        schritteArray.add(-1);
        spielerReihenfolge.sort(Collections.reverseOrder());
        if (alleClients.size() > 1) {
            naechsterClient = spielerReihenfolge.get(1);
            System.out.println(spielerReihenfolge);

        }


    }

    @Override // wenn ein Client nicht mehr verbunden ist, wird diese Methode aufgerufen.
    public void disconnected(Connection connection) {
        System.out.println("Client " + alleClients.get(connection).clientIndex + " ist nicht mehr verbunden.");

        SpielerConnection spielerConnection = new SpielerConnection();
        spielerConnection.clientIndex = alleClients.get(connection).clientIndex;
        server.sendToAllExceptTCP(connection.getID(), spielerConnection);

        spielerReihenfolge.remove((Object) alleClients.get(connection).clientIndex);
        for (int i = 0; i < spielerReihenfolge.size(); i++) {
            if (spielerReihenfolge.get(i) != 0 && spielerReihenfolge.get(i) != 1) {
                spielerReihenfolge.set(i, spielerReihenfolge.get(i) - 1);
            }
        }
        alleClients.remove(connection, alleClients.get(connection));
        connection.close();


        AnzahlClients anzahl = new AnzahlClients();
        anzahl.anzahlVerbundeneClients = alleClients.size();
        server.sendToAllTCP(anzahl);
        if (alleClients.size() < 2) {
            System.exit(0);
            SpielServer.start();
        }
    }
    // alle Clients bekommen erneut ihre Indexe, falls es bei der "connected" Methode aus irgendeinem Grund (vor allem Netzwerkverbindung) nicht geklappt hat.
    public void clientIndexenFuerAlleSchicken() {
        for (Map.Entry<Connection, SpielerAuskuenfte> entry : alleClients.entrySet()) {
            HostClient hostClient = new HostClient();
            hostClient.clientIndex = entry.getValue().clientIndex;
            if (hostClient.clientIndex == 0) {
                hostClient.istHost = true;
            }
            server.sendToTCP(entry.getKey().getID(), hostClient);
        }
    }
    // vom SpielbrettZustand auf MinispielZustand zu wechseln.
    public void zustandWechsel() {
        ClientsZug zug = new ClientsZug();

        if (spielZustand == zustand.SPIELBRETT_ZUSTAND) {
            spielZustand = zustand.MINISPIEL_ZUSTAND;
            zug.istDran = true;
            zug.zustand = 3;

            if (minispielWahl == 0) {
                zug.minispielIndex = 0;
                minispielWahl = 1;
            } else {
                zug.minispielIndex = 1;
                minispielWahl = 0;
            }
            server.sendToAllTCP(zug);
            if (zug.minispielIndex == 1) {
                PalettenFalle palettenFalle = new PalettenFalle();
                palettenFalle.palette0 = generator.nextBoolean();
                palettenFalle.palette2 = generator.nextBoolean();
                palettenFalle.palette4 = generator.nextBoolean();
                palettenFalle.palette6 = generator.nextBoolean();
                palettenFalle.palette8 = generator.nextBoolean();
                palettenFalle.palette10 = generator.nextBoolean();
                palettenFalle.palette12 = generator.nextBoolean();
                server.sendToAllTCP(palettenFalle);
            }

            Timer timer = new Timer();
            TimerTask minispielTask = new TimerTask() {
                @Override
                public void run() {
                    if (minispielDauer >= 0) {
                        minispielDauer--;
                        if (zug.minispielIndex == 0) {
                            if (minispielDauer == 60) {

                                SammlerGegenstaende muenze1 = new SammlerGegenstaende();
                                muenze1.elementIndex = 1;
                                muenze1.elementX = generator.nextInt(100, 1250);
                                muenze1.elementY = generator.nextInt(100, 668);
                                server.sendToAllTCP(muenze1);


                                SammlerGegenstaende muenze2 = new SammlerGegenstaende();
                                muenze2.elementIndex = 2;
                                muenze2.elementX = generator.nextInt(100, 1250);
                                muenze2.elementY = generator.nextInt(100, 668);
                                server.sendToAllTCP(muenze2);

                                SammlerGegenstaende spider1 = new SammlerGegenstaende();
                                spider1.elementIndex = 3;
                                spider1.elementX = generator.nextInt(100, 318);
                                spider1.elementY = generator.nextInt(100, 575);
                                server.sendToAllTCP(spider1);

                                SammlerGegenstaende spider2 = new SammlerGegenstaende();
                                spider2.elementIndex = 4;
                                spider2.elementX = generator.nextInt(510, 698);
                                spider2.elementY = generator.nextInt(100, 575);
                                server.sendToAllTCP(spider2);

                                SammlerGegenstaende spider3 = new SammlerGegenstaende();
                                spider3.elementIndex = 5;
                                spider3.elementX = generator.nextInt(890, 1152);
                                spider3.elementY = generator.nextInt(100, 575);
                                server.sendToAllTCP(spider3);

                            } else if (minispielDauer % 10 == 0 && minispielDauer < 60 && minispielDauer > 0) {
                                SammlerGegenstaende mushroom = new SammlerGegenstaende();
                                mushroom.elementIndex = 7;
                                mushroom.elementX = generator.nextInt(100, 1250);
                                mushroom.elementY = generator.nextInt(100, 668);
                                server.sendToAllTCP(mushroom);

                            } else if (minispielDauer == 51 || minispielDauer == 33 || minispielDauer == 15) {
                                SammlerGegenstaende diamond = new SammlerGegenstaende();
                                diamond.elementIndex = 6;
                                diamond.elementX = generator.nextInt(100, 1216);
                                diamond.elementY = generator.nextInt(100, 636);
                                server.sendToAllTCP(diamond);

                            }
                        }
                    } else if (minispielDauer > -7) {
                        minispielDauer--;

                    } else {
                        timer.cancel();
                        runde++;
                        minispielDauer = 64;

                        if (runde <= anzahlDerRunden) {
                            zug.zustand = 2;
                        } else {
                            zug.zustand = 4;
                        }

                        zug.istDran = false;
                        server.sendToAllExceptTCP(server.getConnections()[naechsterClient].getID(), zug);
                        zug.istDran = true;
                        alleClients.get(server.getConnections()[naechsterClient]).istDran = true;
                        server.sendToTCP(server.getConnections()[naechsterClient].getID(), zug);
                        spielZustand = zustand.SPIELBRETT_ZUSTAND;


                        if (spielerReihenfolge.indexOf(naechsterClient) < spielerReihenfolge.size() - 1) {
                            naechsterClient = spielerReihenfolge.get(spielerReihenfolge.indexOf(naechsterClient) + 1);
                        } else {
                            naechsterClient = spielerReihenfolge.get(0);
                        }

                    }
                }
            };
            timer.schedule(minispielTask, 3000, 1000);

        }

    }

    @Override
    public void received(Connection connection, Object object) {

        if (object instanceof Bescheid signal) { // wenn der Client diesen objekt zuschickt, heisst es das er fertig und nicht mehr dran ist. Anschließend wird der nächste Client dran.
            if (signal.fertig) {

                alleClients.get(connection).istDran = false;
                ClientsZug zug = new ClientsZug();
                zug.istDran = false;

                server.sendToTCP(connection.getID(), zug);

                if (spielZustand != zustand.WUERFEL_ZUSTAND) {
                    // für den Fall, der letzte Client in spielerReihenfolge Bescheid
                    // zuschickt.(fürs auf Minispiel Wechseln)
                    if (connection == server.getConnections()[spielerReihenfolge.get(spielerReihenfolge.size() - 1)]) {
                        if (spielZustand == zustand.MENUE_ZUSTAND) {
                            alleClients.get(connection).istDran = true;
                            zug.istDran = true;
                            zug.zustand = 1;
                            server.sendToAllTCP(zug);
                            spielZustand = zustand.WUERFEL_ZUSTAND;

                        } else if (spielZustand == zustand.SPIELBRETT_ZUSTAND) {

                            zustandWechsel();

                        }
                    } else if (blockBenutzt) { // wenn Block benutzt wurde vom vorletzten Client.

                        zustandWechsel();
                        blockBenutzt = false;

                    } else {
                        zug.istDran = true;
                        server.sendToTCP(server.getConnections()[naechsterClient].getID(), zug);
                        if (spielerReihenfolge.indexOf(naechsterClient) < spielerReihenfolge.size() - 1) {
                            naechsterClient = spielerReihenfolge.get(spielerReihenfolge.indexOf(naechsterClient) + 1);
                        } else {
                            naechsterClient = spielerReihenfolge.get(0);
                        }

                    }
                }

            }
        } // Schritte := die Zahl auf dem Würfel
        else if (object instanceof Schritte schritte) {
            schritte.clientIndex = alleClients.get(connection).clientIndex;
            server.sendToAllExceptTCP(connection.getID(), schritte);

            // Nur für die erste Runde, wenn alle Spieler würfeln müssen, um die Reihenfolge festzulegen
            if (spielZustand == zustand.WUERFEL_ZUSTAND) {
                schritteArray.set(schritte.clientIndex, schritte.schritteAnzahl);

                int count = 0;
                for (Integer o : schritteArray) {
                    if (o != -1) {
                        count++;
                    }
                }
                if (count == spielerReihenfolge.size()) {
                    schritteArray.removeIf(o -> o == -1);

                    int stelle = 0;
                    Collections.reverse(schritteArray);
                    while (!schritteArray.isEmpty()) {
                        spielerReihenfolge.set(stelle, schritteArray.indexOf(Collections.max(schritteArray)));
                        schritteArray.set(schritteArray.indexOf(Collections.max(schritteArray)), -1);
                        stelle++;
                        if (stelle == spielerReihenfolge.size()) {
                            schritteArray.clear();
                        }
                    }
                    if (schritteArray.isEmpty()) {
                        Timer timer = new Timer();
                        TimerTask timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                if (zeit > 0) {
                                    zeit--;
                                } else {
                                    ClientsZug zug = new ClientsZug();
                                    zug.zustand = 2;
                                    zug.istDran = false;
                                    zug.ersteRunde = true;
                                    runde++;
                                    server.sendToAllTCP(zug);
                                    naechsterClient = spielerReihenfolge.get(0);
                                    zug.ersteRunde = false;
                                    zug.istDran = true;
                                    server.sendToTCP(server.getConnections()[naechsterClient].getID(), zug);
                                    if (spielerReihenfolge.indexOf(naechsterClient) < spielerReihenfolge.size() - 1) {
                                        naechsterClient = spielerReihenfolge.get(spielerReihenfolge.indexOf(naechsterClient) + 1);
                                    } else {
                                        naechsterClient = spielerReihenfolge.get(0);
                                    }
                                    spielZustand = zustand.SPIELBRETT_ZUSTAND;
                                    timer.cancel();
                                }
                            }
                        };
                        timer.schedule(timerTask, 0, 1000);
                    }

                }
            }
        }
        else if (object instanceof AnzahlMitspieler anzahlMitspieler) {
            int maxAnzahlDerMitspielerHost = anzahlMitspieler.anzahlDerMitspielerHost;
            int index = 0;
            while (alleClients.size() > maxAnzahlDerMitspielerHost) {
                ClientsZug zug = new ClientsZug();
                zug.istDran = false;
                zug.dispose = true;
                server.sendToTCP(server.getConnections()[index].getID(), zug);
                spielerReihenfolge.remove((Integer) (index));
                alleClients.remove(server.getConnections()[index]);

                System.out.println("Server: Verbindung ist untersagt, da die Anzahl der Spieler ueberschritten wird.");
            }
            if (server.getConnections().length == maxAnzahlDerMitspielerHost) {
                clientIndexenFuerAlleSchicken();
            }


        }
        else if (object instanceof Rundenzahl rundenzahl) {
            this.anzahlDerRunden = rundenzahl.anzahlDerRunden;
            server.sendToAllExceptTCP(connection.getID(), rundenzahl);

        } // ein Client schickt seine Spielfigurauswahl und das wird im Server eingespeichert und an andere Clients weitergeleitet.
        else if (object instanceof SpielfigurAuswahl spielfigurAuswahl) {
            if (spielfigurAuswahl.spielfigurIndex == 0) {
                spielfigurAuswahl.clientIndex = alleClients.get(connection).clientIndex;
                alleClients.get(connection).spielfigurIndex = spielfigurAuswahl.spielfigurIndex;

            } else if (spielfigurAuswahl.spielfigurIndex == 1) {
                spielfigurAuswahl.clientIndex = alleClients.get(connection).clientIndex;
                alleClients.get(connection).spielfigurIndex = spielfigurAuswahl.spielfigurIndex;

            } else if (spielfigurAuswahl.spielfigurIndex == 2) {
                spielfigurAuswahl.clientIndex = alleClients.get(connection).clientIndex;
                alleClients.get(connection).spielfigurIndex = spielfigurAuswahl.spielfigurIndex;

            } else if (spielfigurAuswahl.spielfigurIndex == 3) {
                spielfigurAuswahl.clientIndex = alleClients.get(connection).clientIndex;
                alleClients.get(connection).spielfigurIndex = spielfigurAuswahl.spielfigurIndex;
            }
            SpielfigurAuswahl auswahl = new SpielfigurAuswahl();
            auswahl.clientIndex = alleClients.get(connection).clientIndex;
            auswahl.spielfigurMenueIndex = alleClients.get(connection).spielfigurIndex;
            server.sendToAllExceptTCP(connection.getID(), auswahl);

            for (Connection con : alleClients.keySet()) {
                if (alleClients.get(con).spielfigurIndex != -1 && con != connection) {
                    auswahl = new SpielfigurAuswahl();
                    auswahl.clientIndex = alleClients.get(con).clientIndex;
                    auswahl.spielfigurIndex = alleClients.get(con).spielfigurIndex;
                    server.sendToTCP(connection.getID(), auswahl);
                    if (alleClients.get(con).clientIndex < alleClients.get(connection).clientIndex) {
                        server.sendToTCP(con.getID(), spielfigurAuswahl);
                    }
                }
            }
            SternPosition sternPosition = new SternPosition();
            sternPosition.sternFeldnummer = sternGenerator.sternFeldnummer;
            server.sendToTCP(connection.getID(), sternPosition);


        }
        else if (object instanceof SpielerPosition spielerPosition) {
            spielerPosition.clientIndex = alleClients.get(connection).clientIndex;
            server.sendToAllExceptUDP(connection.getID(), spielerPosition);
        }
        else if (object instanceof Muenzenzahl muenzenzahl) {

            if (alleClients.get(connection).clientIndex == muenzenzahl.clientIndex) {
                if (muenzenzahl.anzahlDerMuenzen > 0) {
                    alleClients.get(connection).muenzenzahl += muenzenzahl.anzahlDerMuenzen;
                } else if (muenzenzahl.anzahlDerMuenzen < 0) {
                    alleClients.get(connection).muenzenzahl += muenzenzahl.anzahlDerMuenzen;
                }
                server.sendToAllExceptTCP(connection.getID(), muenzenzahl);
            }
        }
        else if (object instanceof Sternzahl sternzahl) {

            if (alleClients.get(connection).clientIndex == sternzahl.clientIndex) {
                if (sternzahl.anzahlDerSterne > 0) {
                    alleClients.get(connection).sternzahl += sternzahl.anzahlDerSterne;
                } else if (sternzahl.anzahlDerSterne < 0) {
                    alleClients.get(connection).sternzahl += sternzahl.anzahlDerSterne;
                }
                server.sendToAllExceptTCP(connection.getID(), sternzahl);
            }

        }
        else if (object instanceof SternKaufen sternKaufen) {
            if (sternKaufen.sternGekauft) {
                sternGenerator.sternFeldnummer = generator.nextInt(1, 36);
                SternPosition sternPosition = new SternPosition();
                sternPosition.sternFeldnummer = sternGenerator.sternFeldnummer;
                server.sendToAllTCP(sternPosition);
            }
        }
        else if (object instanceof Bewegung bewegung) {

            bewegung.clientIndex = alleClients.get(connection).clientIndex;
            server.sendToAllExceptUDP(connection.getID(), bewegung);

        }
        else if (object instanceof GegenstandInfo gegenstandInfo) {

            gegenstandInfo.clientIndex = alleClients.get(connection).clientIndex;
            server.sendToAllExceptTCP(connection.getID(), gegenstandInfo);

        }
        else if (object instanceof Blocken) {

            if (spielerReihenfolge.indexOf(naechsterClient) < spielerReihenfolge.size() - 1) {
                naechsterClient = spielerReihenfolge.get(spielerReihenfolge.indexOf(naechsterClient) + 1);
            } else {
                naechsterClient = spielerReihenfolge.get(0);
            }
            if (spielerReihenfolge.indexOf(naechsterClient) == 0) {
                blockBenutzt = true;
            }
        }
        else if (object instanceof SammlerGegenstaende sammlerGegenstaende) {

            if (sammlerGegenstaende.elementIndex == 1 || sammlerGegenstaende.elementIndex == 2) {
                sammlerGegenstaende.elementX = generator.nextInt(100, 1290);
                sammlerGegenstaende.elementY = generator.nextInt(100, 668);
                server.sendToAllTCP(sammlerGegenstaende);
            }

        }
        else if (object instanceof SammlerPunkte sammlerPunkte) {

            server.sendToAllExceptTCP(connection.getID(), sammlerPunkte);

        }
        else if (object instanceof SquidGamePunkte squidGamePunkte) {

            server.sendToAllExceptTCP(connection.getID(), squidGamePunkte);

        }
        else if (object instanceof SquidGamePosition squidGamePosition) {

            server.sendToAllExceptUDP(connection.getID(), squidGamePosition);

        }
    }
}
