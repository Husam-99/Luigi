package spieler;

import Networking.Pakete.Blocken;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Block extends Gegenstand{

    public Block(Spieler spieler) {
        super(spieler);
        preis = 7;
        nummer = 1;
        getGegenstandBilder();
    }

    @Override
    public void getGegenstandBilder(){
        try {
            icon= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gegenstaende/Block.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void effeckteAnwenden(){

        //sende an Server, dass der nächste Spieler geblockt ist
        Blocken block = new Blocken();
        spieler.spielablaufManager.sp.client.send(block);
        spieler.inventarZustand = false;
        spieler.spielablaufManager.mapManager.mapEingabeManager.iGedrueckt = false;
    }
}
