package spieler;

import Networking.Pakete.SternKaufen;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Repos extends Gegenstand{

    public Repos(Spieler spieler) {
        super(spieler);
        preis = 13;
        nummer = 5;
        getGegenstandBilder();
    }

    @Override
    public void getGegenstandBilder(){
        try {
            icon= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gegenstaende/Repos.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void effeckteAnwenden(){

        //sende, dass der Stern gekauft ist, um neue Position von der Server zu bekommen
        SternKaufen sternKaufen = new SternKaufen();
        sternKaufen.sternGekauft = true;
        spieler.spielablaufManager.sp.client.send(sternKaufen);
        spieler.inventarZustand = false;
        spieler.spielablaufManager.mapManager.mapEingabeManager.iGedrueckt = false;
    }

}
