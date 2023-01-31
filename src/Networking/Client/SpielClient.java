package Networking.Client;

import Main.SpielPanel;
import Minispiele.SammlerElement;
import Networking.Pakete.*;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import spieler.Spieler;

import java.io.IOException;
import java.util.ArrayList;

public class SpielClient {
    static boolean istDran = false;
    static boolean istHost = false;
    static int tcp_Port = 54777;
    static int udp_Port = 54555;
    private static String ipAdresse = "127.0.0.1";
    Client client;
    static SpielPanel sp;
    public static int anzahlSpieler;
    public static int clientIndex;
    public boolean wartung = false;
    public SpielClient(SpielPanel sp){
        this.sp = sp;
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
    public boolean isIstHost() {
        return istHost;
    }

    public boolean istDran() {
        return istDran;
    }

    public void close(){
        client.close();
    }

    public void send(Object object){
        if(object instanceof Bescheid bescheid){
            client.sendTCP(bescheid);
        } else if(object instanceof AnzahlMitspieler anzahlMitspieler){
            client.sendTCP(anzahlMitspieler);
        } else if (object instanceof  Rundenzahl rundenzahl) {
            client.sendTCP(rundenzahl);
        } else if(object instanceof SpielfigurAuswahl spielfigurAuswahl){
            client.sendTCP(spielfigurAuswahl);
        } else if(object instanceof SpielerPosition spielerPosition){
            client.sendTCP(spielerPosition);
        } else if(object instanceof Muenzenzahl muenzenzahl){
            muenzenzahl.clientIndex = this.clientIndex;
            client.sendTCP(muenzenzahl);
        } else if(object instanceof Sternzahl sternzahl){
            sternzahl.clientIndex = this.clientIndex;
            client.sendTCP(sternzahl);
        } else if(object instanceof SternKaufen sternKaufen){
            client.sendTCP(sternKaufen);
        } else if(object instanceof Schritte schritte){
            schritte.clientIndex = this.clientIndex;
            client.sendTCP(schritte);
        } else if(object instanceof Bewegung bewegung){
            bewegung.clientIndex = this.clientIndex;
            client.sendTCP(bewegung);
        } else if(object instanceof GegenstandInfo gegenstandInfo){
            gegenstandInfo.clientIndex = this.clientIndex;
            client.sendTCP(gegenstandInfo);
        } else if(object instanceof Blocken block){
            client.sendTCP(block);
        } else if(object instanceof SammlerGegenstaende sammlerGegenstaende){
            client.sendTCP(sammlerGegenstaende);
        } else if(object instanceof SammlerPunkte sammlerPunkte){
            sammlerPunkte.clientIndex = clientIndex;
            client.sendTCP(sammlerPunkte);
        } else if(object instanceof SquidGamePunkte squidGamePunkte){
            squidGamePunkte.clientIndex = clientIndex;
            client.sendTCP(squidGamePunkte);
        } else if(object instanceof SquidGamePosition squidGamePosition){
            squidGamePosition.clientIndex = clientIndex;
            client.sendTCP(squidGamePosition);
        }
    }

}
