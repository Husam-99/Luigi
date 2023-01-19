package spieler;

import Networking.Pakete.SternKaufen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
            icon= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Repos.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void effeckteAnwenden(){
        SternKaufen sternKaufen = new SternKaufen();
        sternKaufen.sternGekauft = true;
        spieler.spielablaufManager.sp.client.send(sternKaufen);
    }
}
