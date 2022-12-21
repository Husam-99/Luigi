package Networking.Server;

import Networking.Pakete.Register;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class SpielServer  {
    static int tcp_Port = 54777;
    static int udp_Port = 54555;
    static Server server;
    static boolean serverStatus = false;
    static int anzahlClients = 0;

    public static void start(){

        try {
            server = new Server();
            Register.register(server.getKryo());
            server.bind(tcp_Port, udp_Port);
            server.start();
            server.addListener(new ServerListener());
            serverStatus = true;
            System.out.println("Server gestartet");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
