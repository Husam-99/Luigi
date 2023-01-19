package Networking.Server;

import Networking.Pakete.Register;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Random;

public class SpielServer  {

    static int tcp_Port = 54777;
    static int udp_Port = 54555;
    static Server server;
    static LinkedHashMap<Connection, SpielerAuskuenfte> alleClients = new LinkedHashMap<>();
    static Generator generator = new Generator();
    static Random positionGenerator = new Random();

    public static void start(){

        try {
            server = new Server();
            Register.register(server.getKryo());
            server.bind(tcp_Port, udp_Port);
            server.start();
            server.addListener(new ServerListener());
            System.out.println("Server gestartet");
            generator.sternFeldnummer = 11;
            //generator.sternFeldnummer = positionGenerator.nextInt(1, 36);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SpielServer.start();
    }

}
