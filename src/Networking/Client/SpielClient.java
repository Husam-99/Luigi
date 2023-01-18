package Networking.Client;

import Main.SpielPanel;
import Networking.Pakete.*;
import Spielablauf.Stern;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import spieler.Spieler;

import java.io.IOException;
import java.util.Random;

public class SpielClient {
    private boolean istDran = false;
    private boolean istHost = false;
    static int tcp_Port = 54777;
    static int udp_Port = 54555;
    private static String ipAdresse = "127.0.0.1";
    private final Client client;
    private SpielPanel sp;
    public int anzahlSpieler;
    public int clientIndex;
    public SpielClient(SpielPanel sp){
        this.sp = sp;
        client = new Client();

    }

    public void start(){
        try {
            Register.register(client.getKryo());
            client.start();
            client.connect(5000, ipAdresse, tcp_Port, udp_Port);
            client.addListener(new Listener(){
                @Override
                public void received(Connection connection, Object object) {

                    if (object instanceof ClientsZug zug) {
                        if(zug.dispose){
                            sp.window.dispose();
                            System.exit(0);
                        }
                        istDran = zug.istDran;
                        System.out.println("Clinet: dran " + istDran);
                    } else if (object instanceof HostClient hostAuskuefte) {
                        istHost = hostAuskuefte.istHost;
                        clientIndex = hostAuskuefte.clientIndex;
                        System.out.println("Client: ich bin der Host " + istHost);
                        System.out.println("Client: mein Index " + clientIndex);
                    } else if (object instanceof AnzahlClients anzahl) {
                        anzahlSpieler = anzahl.anzahlVerbundeneClients;

                    } else if (object instanceof Rundenzahl rundenzahl) {
                        sp.menueManager.rundenAnzahl = rundenzahl.anzahlDerRunden;

                    } else if (object instanceof SpielfigurAuswahl spielfigurAuswahl) {
                        Spieler andererSpieler = new Spieler(sp);
                        if (spielfigurAuswahl.spielfigurIndex == 0) {
                            andererSpieler.spielfigurAuswaehlen(0);
                            System.out.println("at recieve spielfigur the client index" + spielfigurAuswahl.clientIndex);
                            sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                        } else if (spielfigurAuswahl.spielfigurIndex == 1) {
                            andererSpieler.spielfigurAuswaehlen(1);
                            System.out.println("at recieve spielfigur the client index" + spielfigurAuswahl.clientIndex);
                            sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                        } else if (spielfigurAuswahl.spielfigurIndex == 2) {
                            andererSpieler.spielfigurAuswaehlen(2);
                            System.out.println("at recieve spielfigur the client index" + spielfigurAuswahl.clientIndex);
                            sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                        } else if (spielfigurAuswahl.spielfigurIndex == 3) {
                            andererSpieler.spielfigurAuswaehlen(3);
                            System.out.println("at recieve spielfigur the client index" + spielfigurAuswahl.clientIndex);
                            sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                        }
                    } else if(object instanceof SternPosition sternPosition){
                        sp.mapManager.stern.setFeldNum(sternPosition.sternFeldnummer);
                        System.out.println("Client: ich habe die feldnummer fuer stern erhalten.");


                    } else if(object instanceof SpielerPosition spielerPosition){
                        sp.alleSpieler.get(spielerPosition.clientIndex).bildschirmX = spielerPosition.weltX - sp.mainSpieler.weltX + sp.mainSpieler.bildschirmX;
                        sp.alleSpieler.get(spielerPosition.clientIndex).bildschirmY = spielerPosition.weltY - sp.mainSpieler.weltY + sp.mainSpieler.bildschirmY;

                    } else if(object instanceof Muenzenzahl muenzenzahl){
                        if(muenzenzahl.anzahlDerMuenzen > 0){
                            sp.alleSpieler.get(muenzenzahl.clientIndex).konto.muenzen += muenzenzahl.anzahlDerMuenzen;
                            System.out.println("spieler " + muenzenzahl.clientIndex + " hat " + muenzenzahl.anzahlDerMuenzen + " muenze erhalten.");
                        }else if(muenzenzahl.anzahlDerMuenzen < 0){
                            sp.alleSpieler.get(muenzenzahl.clientIndex).konto.muenzen -= muenzenzahl.anzahlDerMuenzen;
                            System.out.println("spieler " + muenzenzahl.clientIndex + " hat " + muenzenzahl.anzahlDerMuenzen + " muenze verloren.");
                        }
                    } else if(object instanceof Sternzahl sternzahl){
                        if(sternzahl.anzahlDerSterne > 0){
                            sp.alleSpieler.get(sternzahl.clientIndex).konto.sterne += sternzahl.anzahlDerSterne;
                            System.out.println("spieler " + sternzahl.clientIndex + " hat " + sternzahl.anzahlDerSterne + " sterne erhalten.");

                        }else if(sternzahl.anzahlDerSterne < 0){
                            sp.alleSpieler.get(sternzahl.clientIndex).konto.sterne -= sternzahl.anzahlDerSterne;
                            System.out.println("spieler " + sternzahl.clientIndex + " hat " + sternzahl.anzahlDerSterne + "sterne verloren.");
                        }
                    } else if(object instanceof Schritte schritte){
                        sp.alleSpieler.get(schritte.clientIndex).schritteAnzahl = schritte.schritteAnzahl;
                    } else if(object instanceof Bewegung bewegung){
                        if(bewegung.bewegungLinks){
                            sp.alleSpieler.get(bewegung.clientIndex).sp.mapManager.mapEingabeManager.bewegungLinks = true;
                        }else if(bewegung.bewegungRechts){
                            sp.alleSpieler.get(bewegung.clientIndex).sp.mapManager.mapEingabeManager.bewegungLinks = true;
                        }else if(bewegung.bewegungUnten){
                            sp.alleSpieler.get(bewegung.clientIndex).sp.mapManager.mapEingabeManager.bewegungUnten = true;
                        }else if(bewegung.bewegungOben){
                            sp.alleSpieler.get(bewegung.clientIndex).sp.mapManager.mapEingabeManager.bewegungOben = true;
                        }
                    }


                }
            });
            System.out.println("ich bin der Client und bin am Start");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isIstHost() {
        return istHost;
    }

    public boolean istDran() {
        return istDran;
    }

    public void close(){
        client.close();
    }

    public void send(Object object){
        if(object instanceof Bescheid bescheid){
            client.sendTCP(bescheid);
        } else if(object instanceof AnzahlMitspieler anzahlMitspieler){
            client.sendTCP(anzahlMitspieler);
        } else if (object instanceof  Rundenzahl rundenzahl) {
            client.sendTCP(rundenzahl);
        } else if(object instanceof SpielfigurAuswahl spielfigurAuswahl){
            client.sendTCP(spielfigurAuswahl);
        } else if(object instanceof SpielerPosition spielerPosition){
            client.sendTCP(spielerPosition);
        } else if(object instanceof Muenzenzahl muenzenzahl){
            muenzenzahl.clientIndex = this.clientIndex;
            client.sendTCP(muenzenzahl);
        } else if(object instanceof Sternzahl sternzahl){
            sternzahl.clientIndex = this.clientIndex;
            client.sendTCP(sternzahl);
        } else if(object instanceof SternKaufen sternKaufen){
            client.sendTCP(sternKaufen);
        } else if(object instanceof Schritte schritte){
            schritte.clientIndex = this.clientIndex;
            client.sendTCP(schritte);
        } else if(object instanceof Bewegung bewegung){
            bewegung.clientIndex = this.clientIndex;
            client.sendTCP(bewegung);
        }

    }


}
