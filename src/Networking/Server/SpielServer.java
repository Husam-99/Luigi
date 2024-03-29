package Networking.Server;

import Networking.Pakete.Register;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class SpielServer  {

    static int tcp_Port = 54777;
    static int udp_Port = 54555;
    static Server server;
    static LinkedHashMap<Connection, SpielerAuskuenfte> alleClients; // speichert die wichtigsten Infos über alle Clients im Server, wie ClientIndex im Server, spielfigurIndex usw.
    static Generator sternGenerator;
    static Random generator;
    static ArrayList<Integer> spielerReihenfolge; // speichert die Reihenfolge der Spieler. Also in welcher Reihenfolge die ClientIndexe in dieser Liste eingespeichert werden,
                                                  // entscheidet, wer von den Clients als nächstes spielen darf.

    static ArrayList<Integer> schritteArray; // für die Neuordnung der ArrayListe "spielerReihenfolge".

    public static void start(){

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String hostAddress = localHost.getHostAddress();
            System.out.println("Server IP address: " + hostAddress);
            server = new Server();
            Register.register(server.getKryo());
            server.bind(tcp_Port, udp_Port);
            server.start();
            server.addListener(new ServerListener());
            System.out.println("Server gestartet");
            alleClients = new LinkedHashMap<>();
            spielerReihenfolge = new ArrayList<>();
            schritteArray = new ArrayList<>();
            generator = new Random();
            sternGenerator = new Generator();
            sternGenerator.sternFeldnummer = generator.nextInt(1, 36);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SpielServer.start();
    }

}
