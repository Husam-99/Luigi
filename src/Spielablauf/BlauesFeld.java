package Spielablauf;

import Networking.Pakete.Muenzenzahl;
import Networking.Pakete.Sternzahl;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BlauesFeld extends Feld{
    int blauesFeldClientIndex = -1;
    public int befehlNum1 = 0, befehlNum2 = 0, befehlNum3 = 0;
    public boolean inventarLeer = true, inventarKlauen = false;
    public BlauesFeld(SpielMapManager mapManager, int weltY, int weltX, int feldNum) {
        super(mapManager, weltY, weltX, feldNum);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Blue_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void effeckteAnwenden(){
        blauesFeldClientIndex = 1;
        mapManager.spielablaufManager.clientAuswaehlen = true;
        if(befehlNum2 == 0) {
            if(mapManager.spielablaufManager.sp.alleSpieler.get(blauesFeldClientIndex).konto.muenzen >= 5) {
                mapManager.spielablaufManager.mainSpieler.konto.muenzenErhalten(5);
                Muenzenzahl muenzenzahl = new Muenzenzahl();
                muenzenzahl.blauesFeldClientIndex = blauesFeldClientIndex;
                mapManager.spielablaufManager.sp.client.send(muenzenzahl);
            }else{
                mapManager.spielablaufManager.mainSpieler.konto.genugMuenzen = false;
            }
        }else if(befehlNum2 == 1) {
            if (mapManager.spielablaufManager.sp.alleSpieler.get(blauesFeldClientIndex).konto.sterne >= 1) {
                mapManager.spielablaufManager.mainSpieler.konto.sterneErhalten(1);
                Sternzahl sternzahl = new Sternzahl();
                sternzahl.blauesFeldClientIndex = blauesFeldClientIndex;
                mapManager.spielablaufManager.sp.client.send(sternzahl);
            }else {
                mapManager.spielablaufManager.mainSpieler.konto.genugMuenzen = false;
            }
        }else if(befehlNum2 == 3){
            for(int temp = 0; temp < 4; temp++) {
                if (mapManager.spielablaufManager.sp.alleSpieler.get(blauesFeldClientIndex).inventar.inventar[temp] != null) {
                    inventarLeer = false;
                }
            }
            if(!inventarLeer){
                inventarKlauen = true;
            }
        }


    }



}
