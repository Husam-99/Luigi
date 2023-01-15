package Networking.Client;

import Main.SpielPanel;
import Networking.Pakete.*;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import spieler.Spieler;

import java.io.IOException;

public class SpielClient {
    private boolean istDran = false;
    private boolean istHost = false;
    static int tcp_Port = 54777;
    static int udp_Port = 54555;
    private static String ipAdresse = "127.0.0.1";
    private Client client;
    private SpielPanel sp;
    public int anzahlVerbundeneClients = 0;
    public SpielClient(SpielPanel sp){
        this.sp = sp;
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
                    if (object instanceof ClientsZug zug) {
                        istDran = zug.istDran;
                        System.out.println("Clinet: dran " + istDran);
                    } else if (object instanceof HostClient host) {
                        istHost = host.istHost;
                        System.out.println("Client: ich bin der Host " + istHost);
                    } else if (object instanceof AnzahlClients anzahl) {
                        anzahlVerbundeneClients = anzahl.anzahlVerbundeneClients;
                        System.out.println("Client: ich habe die Anzahl der Clients erhalten.");

                    } else if (object instanceof AnzahlMitspieler anzahlMitspieler) {
                        sp.menueManager.spielerAnzahl = anzahlMitspieler.anzahlDerMitspielerHost;
                        System.out.println("Client: ich habe die Anzahl der MitSpieler erhalten." + sp.menueManager.spielerAnzahl);

                    } else if (object instanceof Rundenzahl rundenzahl) {
                        sp.menueManager.rundenAnzahl = rundenzahl.anzahlDerRunden;
                        System.out.println("Client: ich habe die Anzahl der Runden erhalten. " + sp.menueManager.rundenAnzahl);

                    }
                    else if(object instanceof SpielfigurAuswahl spielfigurAuswahl){
                        if(spielfigurAuswahl.spielfigurIndex == 0){
                            Spieler andererSpieler = new Spieler(sp);
                            andererSpieler.spielfigurAuswaehlen(0);
                            System.out.println("at recieve spielfigur the client index"+ spielfigurAuswahl.clientIndex);
                            sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                        }
                        else if(spielfigurAuswahl.spielfigurIndex == 1){
                            Spieler andererSpieler = new Spieler(sp);
                            andererSpieler.spielfigurAuswaehlen(1);
                            System.out.println("at recieve spielfigur the client index"+ spielfigurAuswahl.clientIndex);
                            sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                        }
                        else if(spielfigurAuswahl.spielfigurIndex == 2){
                            Spieler andererSpieler = new Spieler(sp);
                            andererSpieler.spielfigurAuswaehlen(2);
                            System.out.println("at recieve spielfigur the client index"+ spielfigurAuswahl.clientIndex);
                            sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                        }
                        else if(spielfigurAuswahl.spielfigurIndex == 3){
                            Spieler andererSpieler = new Spieler(sp);
                            andererSpieler.spielfigurAuswaehlen(3);
                            System.out.println("at recieve spielfigur the client index"+ spielfigurAuswahl.clientIndex);
                            sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                        }
                    }else if(object instanceof SpielerPosition spielerPosition){
                        System.out.println("ich habe die coord der anderen spieler erhalten.");
                        sp.alleSpieler.get(spielerPosition.clientIndex).bildschirmX = spielerPosition.weltX - sp.mainSpieler.weltX + sp.mainSpieler.bildschirmX;
                        sp.alleSpieler.get(spielerPosition.clientIndex).bildschirmY = spielerPosition.weltY - sp.mainSpieler.weltY + sp.mainSpieler.bildschirmY;

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

    public boolean istDran() {
        return istDran;
    }

    public void close(){
        client.close();
    }

    public void send(Object object){
        if(object instanceof Bescheid bescheid){
            client.sendTCP(bescheid);
            System.out.println("Client: ich habe meine Auskuenfte verschickt.");
        }
        else if(object instanceof AnzahlMitspieler anzahlMitspieler){
            client.sendTCP(anzahlMitspieler);
            System.out.println("Client: ich habe die Anzahl der Mitspieler verschickt.");
        } else if (object instanceof  Rundenzahl rundenzahl) {
            client.sendTCP(rundenzahl);
            System.out.println("Client: ich habe die Anzahl der Runden verschickt");

        } else if(object instanceof SpielfigurAuswahl spielfigurAuswahl){
            client.sendTCP(spielfigurAuswahl);
            System.out.println("Client: ich habe meine SpielfigurAuswahl verschickt.");
        }else if(object instanceof SpielerPosition spielerPosition){
            client.sendTCP(spielerPosition);
            System.out.println("Client: ich habe meine Position verschickt.");
        }

    }


}
