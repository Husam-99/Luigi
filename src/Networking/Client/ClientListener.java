package Networking.Client;

import Main.Memory;
import Networking.Pakete.GeklickteKarte;
import Networking.Pakete.SpielerAuskuenfte;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


public class ClientListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
       if(object instanceof SpielerAuskuenfte){
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


}