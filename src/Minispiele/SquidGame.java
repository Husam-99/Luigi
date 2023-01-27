package Minispiele;

import Main.SpielPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class SquidGame extends Minispiel {
    /*
          squidgameManger.wasserMalen(g2);
        squidgameManger.malen(g2);
        squidgameManger.spieler.spielerMalen(g2);

     */
    Palette[] paletten;
    BufferedImage[][] minispielFliesen;
    SquidGame(SpielPanel sp, MinispielSpieler mainMinispielSpieler, ArrayList<MinispielSpieler> alleMinispielSpieler) {
        super(sp, mainMinispielSpieler, alleMinispielSpieler);
        minispielFliesen=new BufferedImage[20][20];
        paletten = new Palette[14];
        mapLaden();
        setzePaletten();
        paletteVerbinden();
        falleFestlegen();
    }



    @Override
    public void getFlieseImage() {
    }

    @Override
    public void mapLaden() {
        String[][] mapFelder = new String[20][20];

        try {
            InputStream is = getClass().getResourceAsStream("/miniSpiele/Squidgamemap/squidbilder.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int zeilenIndex = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] zeile = line.split(" ");
                System.arraycopy(zeile, 0, mapFelder[zeilenIndex], 0, zeile.length);
                zeilenIndex++;
            }
            for(int zeile = 0; zeile < 20; zeile++){
                System.out.println();
                for(int spalte = 0; spalte < 20; spalte++){
                    try{
                        minispielFliesen[zeile][spalte] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/Squidgamemap/SquidgameBilder/tileset" + mapFelder[zeile][spalte] + ".png")));
                        System.out.printf(mapFelder[zeile][spalte]);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        

    }

    public void setzePaletten() {
        paletten[0] = new Palette(sp.vergroesserteFliesenGroesse * 6, sp.vergroesserteFliesenGroesse * 15-sp.vergroesserteFliesenGroesse/3, 0,true);
        paletten[1] = new Palette(sp.vergroesserteFliesenGroesse  * 9, sp.vergroesserteFliesenGroesse  * 15-sp.vergroesserteFliesenGroesse/3, 1,true);

        paletten[2] = new Palette(sp.vergroesserteFliesenGroesse  * 6, sp.vergroesserteFliesenGroesse  * 13-sp.vergroesserteFliesenGroesse/3, 2,true);
        paletten[3] = new Palette(sp.vergroesserteFliesenGroesse * 9, sp.vergroesserteFliesenGroesse * 13-sp.vergroesserteFliesenGroesse/3, 3,true);

        paletten[4] = new Palette(sp.vergroesserteFliesenGroesse * 6, sp.vergroesserteFliesenGroesse* 11-sp.vergroesserteFliesenGroesse/3, 4,true);
        paletten[5] = new Palette(sp.vergroesserteFliesenGroesse * 9, sp.vergroesserteFliesenGroesse* 11-sp.vergroesserteFliesenGroesse/3, 5,true);

        paletten[6] = new Palette(sp.vergroesserteFliesenGroesse * 6, sp.vergroesserteFliesenGroesse * 9-sp.vergroesserteFliesenGroesse/3, 6,true);
        paletten[7] = new Palette(sp.vergroesserteFliesenGroesse * 9, sp.vergroesserteFliesenGroesse * 9 - sp.vergroesserteFliesenGroesse/3 , 7,true);

        paletten[8] = new Palette(sp.vergroesserteFliesenGroesse* 6, sp.vergroesserteFliesenGroesse * 7-sp.vergroesserteFliesenGroesse/3, 8,true);
        paletten[9] = new Palette(sp.vergroesserteFliesenGroesse * 9, sp.vergroesserteFliesenGroesse * 7-sp.vergroesserteFliesenGroesse/3, 9,true);

        paletten[10] = new Palette(sp.vergroesserteFliesenGroesse * 6, sp.vergroesserteFliesenGroesse * 5-sp.vergroesserteFliesenGroesse/3, 10,true);
        paletten[11] = new Palette(sp.vergroesserteFliesenGroesse * 9, sp.vergroesserteFliesenGroesse* 5-sp.vergroesserteFliesenGroesse/3, 11,true);

        paletten[12] = new Palette(sp.vergroesserteFliesenGroesse * 6, sp.vergroesserteFliesenGroesse * 3-sp.vergroesserteFliesenGroesse/3, 12,true);
        paletten[13] = new Palette(sp.vergroesserteFliesenGroesse * 9, sp.vergroesserteFliesenGroesse* 3-sp.vergroesserteFliesenGroesse/3, 13,true);
    }
    
    public void paletteVerbinden(){
        for (int i = 0; i < 12; i++) {
            if (i % 2 == 0) {
                paletten[i].naechsteRechts = paletten[i + 3];
                paletten[i].naechsteLinks = paletten[i + 2];
            } else {
                paletten[i].naechsteRechts = paletten[i + 2];
                paletten[i].naechsteLinks = paletten[i + 1];
            }
        }
    }
    public void falleFestlegen(){
        for (int i = 0; i < 13; i++) {
            Random random=new Random();
            paletten[i].hatFalle=random.nextBoolean();
        }
        for (int i =0 ;i<13 ; i+=2){
            if ( paletten[i].hatFalle== paletten[i+1].hatFalle){
                paletten[i].hatFalle=!paletten[i+1].hatFalle;
            }
        }
    }

    public void siegerKueren() {
        mainMinispielSpieler.minispielXPosition = sp.vergroesserteFliesenGroesse * 8;
        mainMinispielSpieler.minispielYPosition = 0;
    }

    @Override
    public void malen(Graphics2D g2) {
        int spalte = 0;
        int zeile = 0;

        while (spalte < 20 && zeile < 20) {
            int worldX = spalte * sp.vergroesserteFliesenGroesse;
            int worldY = zeile * sp.vergroesserteFliesenGroesse;
            int bildschirmX = worldX - mainMinispielSpieler.minispielXPosition + mainMinispielSpieler.bildschirmX;
            int bildschirmY = worldY - mainMinispielSpieler.minispielYPosition + mainMinispielSpieler.bildschirmY;

            g2.drawImage(minispielFliesen[zeile][spalte], bildschirmX, bildschirmY, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
            spalte++;
            if (spalte == 20) {
                spalte = 0;
                zeile++;
            }
        }
    }
}