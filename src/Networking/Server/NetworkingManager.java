package Networking.Server;

import Networking.Client.SpielClient;

import java.util.ArrayList;

public class NetworkingManager implements Runnable{

    public static NetworkingManager instance;
    static SpielClient hostSpieler;
    static ArrayList<SpielClient> alleClients;
    private Thread networkingManagerThread;


    public NetworkingManager(){
        alleClients = new ArrayList<>();
        SpielServer.start();

    }
    public void verbindungVerwalten(){


    }
    public void verbindeClient(SpielClient client){

    }
    public void hinzufuegeClient(SpielClient spielClient){
        alleClients.add(spielClient);
        if (alleClients.size() == 1){
            setHostSpieler(spielClient);
            spielClient.start();
        }else{
            spielClient.start();
        }


    }
    public void loescheClient(SpielClient spielClient){
        spielClient.close();
        alleClients.remove(spielClient);

    }

    public static void setHostSpieler(SpielClient spielClient){
        hostSpieler = spielClient;
    }
    public ArrayList<SpielClient> getVerbundeneClients(){
        return alleClients;
    }

    @Override
    public void run() {

    }
}
