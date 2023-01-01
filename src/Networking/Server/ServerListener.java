package Networking.Server;

import Networking.Pakete.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import static Networking.Server.SpielServer.alleClients;
import static Networking.Server.SpielServer.server;

public class ServerListener extends Listener {

    private int naechsterClient;
    @Override
    public void connected(Connection connection) {
        System.out.println("Client ist verbunden.");
        ClientsZug zug = new ClientsZug();
        HostClient host = new HostClient();
        host.istHost = false;
        alleClients.put(connection, false);
        if(alleClients.size()== 1){
            alleClients.put(connection, true);
            host.istHost = true;
        }
        System.out.println(alleClients.size());
        AnzahlClients anzahl = new AnzahlClients();
        anzahl.anzahlVerbundeneClients = alleClients.size();
        zug.istDran = alleClients.get(connection);

        server.sendToTCP(connection.getID(), host);
        server.sendToTCP(connection.getID(), zug);
        server.sendToAllTCP(anzahl);
        naechsterClient = alleClients.size()-2;


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

       if(object instanceof BescheidSagen signal){
            if(signal.fertig){
                alleClients.put(connection, false);
                ClientsZug zug = new ClientsZug();
                zug.istDran = false;
                server.sendToTCP(connection.getID(), zug);
                System.out.println("nächste client " + naechsterClient);

                alleClients.put(server.getConnections()[naechsterClient], true);
                System.out.println(alleClients);
                zug.istDran = true;
                server.sendToTCP(server.getConnections()[naechsterClient].getID(), zug);


                naechsterClient--;
                if(naechsterClient < 0){
                    naechsterClient = alleClients.size()-1;
                }



            }
        }

    }
}
