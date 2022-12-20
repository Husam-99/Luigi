package Networking.Server;

import Networking.Pakete.GeklickteKarte;
import Networking.Pakete.SpielerAuskuenfte;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import static Networking.Server.SpielServer.server;

public class ServerListener extends Listener {

    @Override
    public void connected(Connection connection) {
        System.out.println("Client ist verbunden.");
        SpielServer.anzahlClients++;
        System.out.println(SpielServer.anzahlClients);


    }
    @Override
    public void disconnected(Connection connection){
        System.out.println("Client ist nicht mehr verbunden.");

    }

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof SpielerAuskuenfte){
            SpielerAuskuenfte spielerAuskuenfte = (SpielerAuskuenfte) object;
            System.out.println("Server: ich werde den Namen weiterleiten.");
            server.sendToAllExceptTCP(connection.getID(), spielerAuskuenfte);

        }
        else if(object instanceof GeklickteKarte){
            GeklickteKarte geklickteKarte = (GeklickteKarte) object;
            System.out.println("Server: ich werde den geklickteKartenIndex weiterleiten.");
            server.sendToAllExceptTCP(connection.getID(), geklickteKarte);

        }


    }
}
