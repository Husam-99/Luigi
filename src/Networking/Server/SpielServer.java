package Networking.Server;

import Networking.Pakete.Register;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.LinkedHashMap;

public class SpielServer  {

    static int tcp_Port = 54777;
    static int udp_Port = 54555;
    static Server server;
    static LinkedHashMap<Connection, SpielerAuskuenfte> alleClients = new LinkedHashMap<>();


    public static void start(){

        try {
            server = new Server();
            Register.register(server.getKryo());
            server.bind(tcp_Port, udp_Port);
            server.start();
            server.addListener(new ServerListener());
            System.out.println("Server gestartet");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SpielServer.start();
    }

}
