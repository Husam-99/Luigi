package Networking.Client;

import Main.Memory;
import Networking.Pakete.*;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class SpielClient {
    private boolean istDran = false;
    private boolean istHost = false;
    static int tcp_Port = 54777;
    static int udp_Port = 54555;
    public static String ipAdresse = "127.0.0.1";
    public Client client;
    public int anzahlVerbundeneClients = 0;
    public SpielClient(){
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
                    if(object instanceof ClientsZug){
                        ClientsZug zug = (ClientsZug) object;
                        istDran = zug.istDran;
                        System.out.println("Clinet: dran " + istDran);
                    }
                    else if(object instanceof HostClient){
                        HostClient host = (HostClient) object;
                        istHost = host.istHost;
                        System.out.println("Client: ich bin der Host " + istHost);
                    }
                    else if(object instanceof AnzahlClients){
                        AnzahlClients anzahl = (AnzahlClients) object;
                        anzahlVerbundeneClients = anzahl.anzahlVerbundeneClients;
                        System.out.println("Client: ich habe die Anzahl der Clients erhalten.");

                    }
                    else if(object instanceof SpielerAuskuenfte){
                        SpielerAuskuenfte y = (SpielerAuskuenfte) object;
                        if(Memory.spielerin1.gibNamen().isEmpty()){
                            Memory.spielerin1.setName(y.name);
                        }else{
                            Memory.spielerin2.setName(y.name);
                        }
                        System.out.println("Client: ich habe den Namen der anderen Spieler erhalten! " + y.name);
                    }
                    else if(object instanceof GeklickteKarte){
                        GeklickteKarte y = (GeklickteKarte) object;
                        if (Memory.karten.get(y.kartenIndex).istOffen()) {
                            Memory.karten.get(y.kartenIndex).deckeAuf();
                        }
                        Memory.geklickteKarte.add(Memory.karten.get(y.kartenIndex));
                        System.out.println("Client: ich habe geklickte Karte der anderen Spieler erhalten! ");
                        Memory.update();
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

    public void setIstHost(boolean istHost) {
        this.istHost = istHost;
    }

    public void setzeIstDran(boolean istDran) {
        this.istDran = istDran;
    }

    public boolean istDran() {
        return istDran;
    }

    public void close(){
        client.close();
    }

    public void send(Object object){
        if(object instanceof BescheidSagen){
            BescheidSagen bescheidSagen = (BescheidSagen) object;
            client.sendTCP(bescheidSagen);
            System.out.println("Client: ich habe meine Auskuenfte verschickt.");
        }
        else if(object instanceof GeklickteKarte){
            GeklickteKarte geklickteKarte = (GeklickteKarte) object;
            client.sendTCP(geklickteKarte);
            System.out.println("Client: ich habe die geklickte Karte verschickt.");
        }

    }


}
