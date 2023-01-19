package Networking.Server;

import Networking.Pakete.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.util.Map;

import static Networking.Server.SpielServer.*;

public class ServerListener extends Listener {

    private int maxAnzahlDerMitspielerHost;
    private int anzahlDerRunden;
    private Connection[] connections;
    private int naechsterClient;
    private final int menueZustand = 0;
    private int zustand = 0;

    @Override
    public synchronized void connected(Connection connection) {
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

        } else{
            zug.istDran = false;


        }
        server.sendToTCP(connection.getID(), host);
        server.sendToTCP(connection.getID(), zug);
        System.out.println(alleClients.size());
        AnzahlClients anzahl = new AnzahlClients();
        anzahl.anzahlVerbundeneClients = alleClients.size();
        server.sendToAllTCP(anzahl);
        alleClients.get(connection).istDran = zug.istDran;

        naechsterClient = alleClients.size() - 2;

    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("Client ist nicht mehr verbunden.");
        connection.close();
        alleClients.remove(connection, alleClients.get(connection));
        AnzahlClients anzahl = new AnzahlClients();
        anzahl.anzahlVerbundeneClients = alleClients.size();
        server.sendToAllTCP(anzahl);
        if(alleClients.size()<2){
            System.exit(0);
            SpielServer.start();
        }
    }

    @Override
    public void received(Connection connection, Object object) {

        if (object instanceof Bescheid signal) {
            if (signal.fertig) {
                alleClients.get(connection).istDran = false;
                ClientsZug zug = new ClientsZug();
                zug.istDran = false;

              // if(zustand == menueZustand){
              //     zug.wartung = true;
              // }

                server.sendToTCP(connection.getID(), zug);
                System.out.println("nächste client " + naechsterClient);


          //    if(zustand == menueZustand && alleClients.get(connection).clientIndex == alleClients.size()-1){
          //        zustand = 1;
          //        zug.wartung = false;
          //        server.sendToTCP(server.getConnections()[2].getID(), zug);
          //        server.sendToTCP(server.getConnections()[1].getID(), zug);
          //        server.sendToTCP(server.getConnections()[0].getID(), zug);

          //    }
                alleClients.get(server.getConnections()[naechsterClient]).istDran = true;
                zug.istDran = true;
                server.sendToTCP(server.getConnections()[naechsterClient].getID(), zug);



                for (Map.Entry<Connection, SpielerAuskuenfte> entry : alleClients.entrySet()) {
                    System.out.println(entry.getKey() + " is : " + entry.getValue().clientIndex
                            + " and the turn is:" + entry.getValue().istDran);
                }


                naechsterClient--;
                if (naechsterClient < 0) {
                    naechsterClient = alleClients.size() - 1;
                }
            }
        } else if (object instanceof AnzahlMitspieler anzahlMitspieler) {
            this.maxAnzahlDerMitspielerHost = anzahlMitspieler.anzahlDerMitspielerHost;
            System.out.println("maximum darf " + maxAnzahlDerMitspielerHost + " Spieler das spiel spielen");
            int index = 0;
            while (alleClients.size() > maxAnzahlDerMitspielerHost) {
                ClientsZug zug = new ClientsZug();
                zug.istDran = false;
                zug.dispose = true;
                server.sendToTCP(server.getConnections()[index].getID(), zug);
                alleClients.remove(server.getConnections()[index]);
                server.getConnections()[index].close();
                naechsterClient = alleClients.size() - 2;

                System.out.println("Server: Verbindung ist untersagt, da die Anzahl der Spieler ueberschritten wird.");
            }
            System.out.println("anzahl der spieler jetzt ist " + alleClients.size());


        } else if (object instanceof Rundenzahl rundenzahl) {
            this.anzahlDerRunden = rundenzahl.anzahlDerRunden;
            server.sendToAllExceptTCP(connection.getID(), rundenzahl);
            System.out.println("Server: ich habe die anzahl der Runden erhalten");

        } else if (object instanceof SpielfigurAuswahl spielfigurAuswahl) {
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
            sternPosition.sternFeldnummer = generator.sternFeldnummer;
            server.sendToTCP(connection.getID(), sternPosition);
            System.out.println("Server spielFigur ich habe die position stern geschickt " + sternPosition.sternFeldnummer);


        } else if (object instanceof SpielerPosition spielerPosition) {
            spielerPosition.clientIndex = alleClients.get(connection).clientIndex;
            server.sendToAllExceptTCP(connection.getID(), spielerPosition);
        } else if (object instanceof Muenzenzahl muenzenzahl){
            System.out.println("Server ich habe munzen bekommen ");
            if(alleClients.get(connection).clientIndex == muenzenzahl.clientIndex){
                if(muenzenzahl.anzahlDerMuenzen > 0){
                    alleClients.get(connection).muenzenzahl += muenzenzahl.anzahlDerMuenzen;
                }else if(muenzenzahl.anzahlDerMuenzen < 0){
                    alleClients.get(connection).muenzenzahl += muenzenzahl.anzahlDerMuenzen;
                }
                server.sendToAllExceptTCP(connection.getID(), muenzenzahl);
            }


        } else if (object instanceof Sternzahl sternzahl) {
            System.out.println("Server ich habe sterne bekommen ");

            if (alleClients.get(connection).clientIndex == sternzahl.clientIndex) {
                if (sternzahl.anzahlDerSterne > 0) {
                    alleClients.get(connection).sternzahl += sternzahl.anzahlDerSterne;
                } else if (sternzahl.anzahlDerSterne < 0) {
                    alleClients.get(connection).sternzahl += sternzahl.anzahlDerSterne;
                }
                server.sendToAllExceptTCP(connection.getID(), sternzahl);
            }

        } else if (object instanceof SternKaufen sternKaufen){
            if(sternKaufen.sternGekauft){
                generator.sternFeldnummer = positionGenerator.nextInt(1, 36);
                SternPosition sternPosition = new SternPosition();
                sternPosition.sternFeldnummer = generator.sternFeldnummer;
                server.sendToAllTCP(sternPosition);
                System.out.println("Server ich habe die position stern geschickt " + sternPosition.sternFeldnummer);
            }
        } else if(object instanceof Schritte schritte){
            schritte.clientIndex = alleClients.get(connection).clientIndex;
            server.sendToAllExceptTCP(connection.getID(), schritte);
        } else if(object instanceof Bewegung bewegung){
            bewegung.clientIndex = alleClients.get(connection).clientIndex;
            server.sendToAllExceptTCP(connection.getID(), bewegung);
        } else if(object instanceof GegenstandInfo gegenstandInfo){
            gegenstandInfo.clientIndex = alleClients.get(connection).clientIndex;
            server.sendToAllExceptTCP(connection.getID(), gegenstandInfo);
        } else if(object instanceof Blocken){
            naechsterClient--;
            if (naechsterClient < 0) {
                naechsterClient = alleClients.size() - 1;
            }

        }

    }
}
