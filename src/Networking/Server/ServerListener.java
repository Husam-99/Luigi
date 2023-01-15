package Networking.Server;

import Networking.Pakete.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.util.Iterator;
import java.util.Map;

import static Networking.Server.SpielServer.alleClients;
import static Networking.Server.SpielServer.server;

public class ServerListener extends Listener {

    private int maxAnzahlDerMitspielerHost;
    private int anzahlDerRunden;
    private Connection[] connections;
    private int naechsterClient;

    @Override
    public void connected(Connection connection) {
        System.out.println("Client ist verbunden.");
        ClientsZug zug = new ClientsZug();
        HostClient host = new HostClient();
        host.istHost = false;
        SpielerAuskuenfte auskuenfte = new SpielerAuskuenfte();
        auskuenfte.clientIndex = alleClients.size();
        auskuenfte.istDran = false;
        alleClients.put(connection, auskuenfte);
        if(alleClients.size()== 1){
            auskuenfte.istDran = true;
            alleClients.put(connection, auskuenfte);
            host.istHost = true;
        }
        System.out.println(alleClients.size());
        AnzahlClients anzahl = new AnzahlClients();
        anzahl.anzahlVerbundeneClients = alleClients.size();
        zug.istDran = alleClients.get(connection).istDran;

        server.sendToTCP(connection.getID(), host);
        server.sendToTCP(connection.getID(), zug);
        server.sendToAllTCP(anzahl);
        naechsterClient = alleClients.size()-2;

        /*System.out.println("Client ist verbunden.");
        ClientsZug zug = new ClientsZug();
        HostClient host = new HostClient();
        host.istHost = false;
        SpielerAuskuenfte spielerAuskuenfte = new SpielerAuskuenfte();
        spielerAuskuenfte.istDran = false;
        alleClients.put(connection, spielerAuskuenfte);

        if(alleClients.size()== 1){
            alleClients.get(connection).istDran = true;
            host.istHost = true;
        }
        System.out.println(alleClients.size());
        AnzahlClients anzahl = new AnzahlClients();
        anzahl.anzahlVerbundeneClients = alleClients.size();
        zug.istDran = alleClients.get(connection).istDran;


        connections = new Connection[alleClients.size()];
        Connection[] connections = alleClients.keySet().toArray(new Connection[0]);
        for(int i = 0; i < connections.length-1; i++){
            alleClients.get(connections[i]).naechsterClient = connections[i++];
        }alleClients.get(connections[connections.length-1]).naechsterClient = connections[0];

        server.sendToTCP(connection.getID(), host);
        server.sendToTCP(connection.getID(), zug);
        server.sendToAllTCP(anzahl);



        //}else{
        //    System.out.println("Server: Verbindung ist untersagt, da die Anzahl der Spieler ueberschritten wird.");
        //    connection.close();
        //}*/
    }
    @Override
    public void disconnected(Connection connection){
        System.out.println("Client ist nicht mehr verbunden.");
        alleClients.remove(connection);
        AnzahlClients anzahl = new AnzahlClients();
        anzahl.anzahlVerbundeneClients = alleClients.size();
        server.sendToAllTCP(anzahl);
        connection.close();
    }
    @Override
    public void received(Connection connection, Object object) {

       if(object instanceof Bescheid signal){
            if(signal.fertig){
                alleClients.get(connection).istDran = false;
                ClientsZug zug = new ClientsZug();
                zug.istDran = false;
                server.sendToTCP(connection.getID(), zug);
                System.out.println("nächste client " + naechsterClient);

                zug.istDran = true;
                alleClients.get(server.getConnections()[naechsterClient]).istDran = true;

                for (Map.Entry<Connection, SpielerAuskuenfte> entry : alleClients.entrySet()) {
                    System.out.println(entry.getKey()+ " is : " + entry.getValue().clientIndex
                    + " and the turn is:" + entry.getValue().istDran);
                }
                server.sendToTCP(server.getConnections()[naechsterClient].getID(), zug);

                naechsterClient--;
                if(naechsterClient < 0){
                    naechsterClient = alleClients.size()-1;
                }


            }
       }
       else if(object instanceof AnzahlMitspieler anzahlMitspieler){
           this.maxAnzahlDerMitspielerHost = anzahlMitspieler.anzahlDerMitspielerHost;
           System.out.println("maximum darf " + maxAnzahlDerMitspielerHost + " Spieler das spiel spielen");
           if(alleClients.size()>maxAnzahlDerMitspielerHost){
               alleClients.remove(server.getConnections()[0]);
               server.getConnections()[0].close();
               naechsterClient = alleClients.size()-2;

               System.out.println("Server: Verbindung ist untersagt, da die Anzahl der Spieler ueberschritten wird.");
           }
           System.out.println("anzahl der spieler jetzt ist " + alleClients.size());


       }
       else if (object instanceof Rundenzahl rundenzahl) {
           this.anzahlDerRunden = rundenzahl.anzahlDerRunden;
           server.sendToAllExceptTCP(connection.getID(), rundenzahl);
           System.out.println("Server: ich habe die anzahl der Runden erhalten");

       } else if(object instanceof SpielfigurAuswahl spielfigurAuswahl){
           if(spielfigurAuswahl.spielfigurIndex == 0){
               spielfigurAuswahl.clientIndex = alleClients.get(connection).clientIndex;
               alleClients.get(connection).spielfigurIndex = spielfigurAuswahl.spielfigurIndex;
               for(Connection con: alleClients.keySet()) {
                   if (alleClients.get(con).spielfigurIndex != -1 && con != connection) {
                       SpielfigurAuswahl auswahl = new SpielfigurAuswahl();
                       auswahl.clientIndex = alleClients.get(con).clientIndex;
                       auswahl.spielfigurIndex = alleClients.get(con).spielfigurIndex;
                       server.sendToTCP(connection.getID(), auswahl);
                       if (alleClients.get(con).clientIndex < alleClients.get(connection).clientIndex) {
                           server.sendToTCP(con.getID(), spielfigurAuswahl);
                       }
                   }
               }
           }
           else if(spielfigurAuswahl.spielfigurIndex == 1){
               spielfigurAuswahl.clientIndex = alleClients.get(connection).clientIndex;
               alleClients.get(connection).spielfigurIndex = spielfigurAuswahl.spielfigurIndex;
               for(Connection con: alleClients.keySet()){
                   if(alleClients.get(con).spielfigurIndex != -1 && con!=connection){
                       SpielfigurAuswahl auswahl = new SpielfigurAuswahl();
                       auswahl.clientIndex = alleClients.get(con).clientIndex;
                       auswahl.spielfigurIndex = alleClients.get(con).spielfigurIndex;
                       server.sendToTCP(connection.getID(), auswahl);
                       if(alleClients.get(con).clientIndex< alleClients.get(connection).clientIndex){
                           server.sendToTCP(con.getID(), spielfigurAuswahl);
                       }
                   }
               }
           }
           else if(spielfigurAuswahl.spielfigurIndex == 2){
               spielfigurAuswahl.clientIndex = alleClients.get(connection).clientIndex;
               alleClients.get(connection).spielfigurIndex = spielfigurAuswahl.spielfigurIndex;
               for(Connection con: alleClients.keySet()){
                   if(alleClients.get(con).spielfigurIndex != -1 && con!=connection){
                       SpielfigurAuswahl auswahl = new SpielfigurAuswahl();
                       auswahl.clientIndex = alleClients.get(con).clientIndex;
                       auswahl.spielfigurIndex = alleClients.get(con).spielfigurIndex;
                       server.sendToTCP(connection.getID(), auswahl);
                       if(alleClients.get(con).clientIndex< alleClients.get(connection).clientIndex){
                           server.sendToTCP(con.getID(), spielfigurAuswahl);
                       }
                   }
               }
           }
           else if(spielfigurAuswahl.spielfigurIndex == 3){
               spielfigurAuswahl.clientIndex = alleClients.get(connection).clientIndex;
               alleClients.get(connection).spielfigurIndex = spielfigurAuswahl.spielfigurIndex;
               for(Connection con: alleClients.keySet()){
                   if(alleClients.get(con).spielfigurIndex != -1 && con!=connection){
                       SpielfigurAuswahl auswahl = new SpielfigurAuswahl();
                       auswahl.clientIndex = alleClients.get(con).clientIndex;
                       auswahl.spielfigurIndex = alleClients.get(con).spielfigurIndex;
                       server.sendToTCP(connection.getID(), auswahl);
                       if(alleClients.get(con).clientIndex< alleClients.get(connection).clientIndex){
                           server.sendToTCP(con.getID(), spielfigurAuswahl);
                       }
                   }
               }
           }
       }
       else if(object instanceof SpielerPosition spielerPosition){
            spielerPosition.clientIndex = alleClients.get(connection).clientIndex;
            server.sendToAllExceptUDP(connection.getID(), spielerPosition);
       }

    }
}
