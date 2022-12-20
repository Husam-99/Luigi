package Networking.Client;

import Networking.Pakete.GeklickteKarte;
import Networking.Pakete.Register;
import Networking.Pakete.SpielerAuskuenfte;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public class SpielClient {
    static int tcp_Port = 54777;
    static int udp_Port = 54555;
    public static String ipAdresse = "192.168.56.1";
    public Client client;
    public SpielClient(){
        client = new Client();
    }

    public void start(){
        try {
            Register.register(client.getKryo());
            client.start();
            client.connect(5000, ipAdresse, tcp_Port, udp_Port);
            client.addListener(new ClientListener());
            System.out.println("ich bin der Client und bin am Start");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        client.close();
    }

    public void send(Object object){
        if(object instanceof SpielerAuskuenfte){
            SpielerAuskuenfte spielerAuskuenfte = (SpielerAuskuenfte) object;
            client.sendTCP(spielerAuskuenfte);
            System.out.println("Client: ich habe meinen Namen verschickt.");
        }
        else if(object instanceof GeklickteKarte){
            GeklickteKarte geklickteKarte = (GeklickteKarte) object;
            client.sendTCP(geklickteKarte);
            System.out.println("Client: ich habe die geklickte Karte verschickt.");
        }

    }


}
