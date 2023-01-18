package Spielablauf;


import Main.SpielPanel;
import spieler.Spieler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SpielMapManager {
    public final Fliese[][] mapFliesen;
    SpielPanel sp;
    Graphics2D g2;

    public MapEingabeManager mapEingabeManager;
    public Stern stern;
    public Muenze muenze;

    public SpielMapManager(SpielPanel sp) {
        this.sp = sp;
        mapEingabeManager = new MapEingabeManager(this);
        this.mapFliesen = new Fliese[26][30];
        mapLaden();
        felderReihenfolgeFestlegen();
        this.stern = new Stern(this);
        this.muenze = new Muenze(sp);

    }
    public Feld feldEinrichten(int zeile, int spalte){
        switch(zeile){
            case 19:
                if(spalte == 11){
                    return new ViolettesFeld(this,zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,0);
                }
                else{
                    return null;
                }
            case 17:
                return switch (spalte) {
                    case 5 -> new RotesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,1);
                    case 8 -> new GruenesFeld(this,zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 2);
                    case 11 -> new GruenesFeld(this,zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 3);
                    case 14 -> new OrangesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 4);
                    case 17 -> new GelbesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 5);
                    default -> null;
                };
            case 15:
                return switch (spalte) {
                    case 5 -> new GruenesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,6);
                    case 8 -> new RotesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,7);
                    case 11  -> new BlauesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,8);
                    case 17 -> new BlauesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,9);
                    default -> null;
                };
            case 13:
                return switch (spalte) {
                    case 5 -> new ViolettesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,10);
                    case 8 -> new GruenesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,11);
                    case 14 -> new GruenesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,12);
                    case 11 -> new OrangesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,13);
                    case 17 -> new RotesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,14);
                    default -> null;
                };
            case 11:
                if(spalte == 8) {
                    return new OrangesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,15);
                }else{
                    return null;
                }
            case 10:
                if(spalte == 17){
                    return new BlauesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,16);
                }else{
                    return null;
                }
            case 9:
                if(spalte == 8) {
                    return new GelbesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,17);
                }else{
                    return null;
                }

            case 7:
                return switch (spalte) {
                    case 8 -> new GruenesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 18);
                    case 19 -> new GruenesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 19);
                    case 15 -> new OrangesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 20);
                    case 17 -> new RotesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 21);
                    case 23 -> new RotesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 22);
                    case 21 -> new BlauesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 23);
                    case 25 -> new GelbesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 24);
                    default -> null;
                };
            case 6:
                return switch (spalte) {
                    case 9 -> new RotesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,25);
                    case 26 -> new RotesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse,26);
                    case 11 -> new OrangesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 27);
                    case 14 -> new ViolettesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 28);
                    default -> null;
                };
            case 4:
                return switch (spalte) {
                    case 14 -> new GruenesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 29);
                    case 26 -> new GruenesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 30);
                    case 16 -> new RotesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 31);
                    case 24 -> new BlauesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 32);
                    default -> null;
                };
            case 3:
                return switch (spalte) {
                    case 17 -> new GruenesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 33);
                    case 21 -> new GruenesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 34);
                    case 19 -> new OrangesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 35);
                    case 23 -> new RotesFeld(this, zeile * sp.vergroesserteFliesenGroesse, spalte * sp.vergroesserteFliesenGroesse, 36);
                    default -> null;
                };
            default:
                return null;
        }
    }
    
    private void mapLaden(){
        String[][] mapFelder = new String[26][30];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("src/source/mapBilder/mapStructure.txt"));
            String line;
            int zeilenIndex = 0;
            while ((line = reader.readLine()) != null) {
                String[] zeile = line.split("\t");

                System.arraycopy(zeile, 0, mapFelder[zeilenIndex], 0, zeile.length);
                zeilenIndex++;
            }
            for(int zeile = 0; zeile < 25; zeile++){
                for(int spalte = 0; spalte < 30; spalte++){
                    try{
                        Fliese fliese = new Fliese();
                        fliese.flieseImage = ImageIO.read(new File("src/source/mapBilder/tileset" + mapFelder[zeile][spalte] + ".png"));

                        fliese.feld = feldEinrichten(zeile, spalte);

                        mapFliesen[zeile][spalte] = fliese;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void felderReihenfolgeFestlegen(){
        mapFliesen[19][11].feld.nordFeld = mapFliesen[17][11].feld;

        mapFliesen[17][11].feld.ostFeld = mapFliesen[17][14].feld;
        mapFliesen[17][11].feld.westFeld = mapFliesen[17][8].feld;
        mapFliesen[17][11].feld.nordFeld = mapFliesen[15][11].feld;

        mapFliesen[17][14].feld.ostFeld = mapFliesen[17][17].feld;
        mapFliesen[17][14].feld.westFeld = mapFliesen[17][11].feld;

        mapFliesen[17][8].feld.westFeld = mapFliesen[17][5].feld;
        mapFliesen[17][8].feld.ostFeld = mapFliesen[17][11].feld;

        mapFliesen[15][11].feld.nordFeld = mapFliesen[13][11].feld;
        mapFliesen[15][11].feld.westFeld = mapFliesen[15][8].feld;
        mapFliesen[15][11].feld.suedFeld = mapFliesen[17][11].feld;

        mapFliesen[17][17].feld.nordFeld = mapFliesen[15][17].feld;
        mapFliesen[17][17].feld.westFeld = mapFliesen[17][14].feld;

        mapFliesen[17][5].feld.nordFeld = mapFliesen[15][5].feld;
        mapFliesen[17][5].feld.ostFeld = mapFliesen[17][8].feld;

        mapFliesen[15][5].feld.nordFeld = mapFliesen[13][5].feld;
        mapFliesen[15][5].feld.ostFeld = mapFliesen[15][8].feld;
        mapFliesen[15][5].feld.suedFeld = mapFliesen[17][5].feld;

        mapFliesen[15][17].feld.nordFeld = mapFliesen[13][17].feld;
        mapFliesen[15][17].feld.suedFeld = mapFliesen[17][17].feld;

        mapFliesen[15][8].feld.westFeld = mapFliesen[15][5].feld;
        mapFliesen[15][8].feld.ostFeld = mapFliesen[15][11].feld;

        mapFliesen[13][5].feld.ostFeld = mapFliesen[13][8].feld;
        mapFliesen[13][5].feld.suedFeld = mapFliesen[15][5].feld;

        mapFliesen[13][8].feld.westFeld = mapFliesen[13][5].feld;
        mapFliesen[13][8].feld.ostFeld = mapFliesen[13][11].feld;
        mapFliesen[13][8].feld.nordFeld = mapFliesen[11][8].feld;

        mapFliesen[13][11].feld.westFeld = mapFliesen[13][8].feld;
        mapFliesen[13][11].feld.ostFeld = mapFliesen[13][14].feld;
        mapFliesen[13][11].feld.suedFeld = mapFliesen[15][11].feld;

        mapFliesen[13][14].feld.ostFeld = mapFliesen[13][17].feld;
        mapFliesen[13][14].feld.westFeld = mapFliesen[13][11].feld;

        mapFliesen[13][17].feld.westFeld = mapFliesen[13][14].feld;
        mapFliesen[13][17].feld.nordFeld = mapFliesen[10][17].feld;
        mapFliesen[13][17].feld.suedFeld = mapFliesen[15][17].feld;

        mapFliesen[11][8].feld.nordFeld = mapFliesen[9][8].feld;
        mapFliesen[11][8].feld.suedFeld = mapFliesen[13][8].feld;

        mapFliesen[10][17].feld.nordFeld = mapFliesen[7][17].feld;
        mapFliesen[10][17].feld.suedFeld = mapFliesen[13][17].feld;

        mapFliesen[9][8].feld.nordFeld = mapFliesen[7][8].feld;
        mapFliesen[9][8].feld.suedFeld = mapFliesen[11][8].feld;

        mapFliesen[7][8].feld.nordFeld = mapFliesen[6][9].feld;
        mapFliesen[7][8].feld.suedFeld = mapFliesen[9][8].feld;

        mapFliesen[7][15].feld.ostFeld = mapFliesen[7][17].feld;
        mapFliesen[7][15].feld.nordFeld = mapFliesen[6][14].feld;

        mapFliesen[7][17].feld.suedFeld = mapFliesen[10][17].feld;
        mapFliesen[7][17].feld.westFeld = mapFliesen[7][15].feld;
        mapFliesen[7][17].feld.ostFeld = mapFliesen[7][19].feld;

        mapFliesen[7][19].feld.ostFeld = mapFliesen[7][21].feld;
        mapFliesen[7][19].feld.westFeld = mapFliesen[7][17].feld;

        mapFliesen[7][21].feld.westFeld = mapFliesen[7][19].feld;
        mapFliesen[7][21].feld.ostFeld = mapFliesen[7][23].feld;

        mapFliesen[7][23].feld.westFeld = mapFliesen[7][21].feld;
        mapFliesen[7][23].feld.ostFeld = mapFliesen[7][25].feld;

        mapFliesen[7][25].feld.nordFeld = mapFliesen[6][26].feld;
        mapFliesen[7][25].feld.westFeld = mapFliesen[7][23].feld;

        mapFliesen[6][26].feld.nordFeld = mapFliesen[4][26].feld;
        mapFliesen[6][26].feld.suedFeld = mapFliesen[7][25].feld;

        mapFliesen[4][26].feld.westFeld = mapFliesen[4][24].feld;
        mapFliesen[4][26].feld.suedFeld = mapFliesen[6][26].feld;

        mapFliesen[4][24].feld.ostFeld = mapFliesen[4][26].feld;
        mapFliesen[4][24].feld.nordFeld = mapFliesen[3][23].feld;

        mapFliesen[3][23].feld.suedFeld = mapFliesen[4][24].feld;
        mapFliesen[3][23].feld.westFeld = mapFliesen[3][21].feld;

        mapFliesen[3][21].feld.ostFeld = mapFliesen[3][23].feld;
        mapFliesen[3][21].feld.westFeld = mapFliesen[3][19].feld;

        mapFliesen[3][19].feld.ostFeld = mapFliesen[3][21].feld;
        mapFliesen[3][19].feld.westFeld = mapFliesen[3][17].feld;

        mapFliesen[3][17].feld.ostFeld = mapFliesen[3][19].feld;
        mapFliesen[3][17].feld.suedFeld = mapFliesen[4][16].feld;

        mapFliesen[4][16].feld.nordFeld = mapFliesen[3][17].feld;
        mapFliesen[4][16].feld.westFeld = mapFliesen[4][14].feld;

        mapFliesen[4][14].feld.ostFeld = mapFliesen[4][16].feld;
        mapFliesen[4][14].feld.suedFeld = mapFliesen[6][14].feld;

        mapFliesen[6][14].feld.nordFeld = mapFliesen[4][14].feld;
        mapFliesen[6][14].feld.westFeld = mapFliesen[6][11].feld;
        mapFliesen[6][14].feld.suedFeld = mapFliesen[7][15].feld;

        mapFliesen[6][11].feld.ostFeld = mapFliesen[6][14].feld;
        mapFliesen[6][11].feld.westFeld = mapFliesen[6][9].feld;

        mapFliesen[6][9].feld.ostFeld = mapFliesen[6][11].feld;
        mapFliesen[6][9].feld.suedFeld = mapFliesen[7][8].feld;


    }
    public void update(){
        stern.update();
    }
    public void malen(Graphics2D g2){
        this.g2 = g2;
        mapMalen();
        spielerStatusBoxMalen();
        spielerStatusMalen();
    }
    public void spielerStatusMalen(){
        g2.drawImage(sp.mainSpieler.spielfigur.profile, 10, 10, sp.vergroesserteFliesenGroesse+30, sp.vergroesserteFliesenGroesse+30, null);
        g2.drawImage(sp.mapManager.muenze.muenze1, 127, 15, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(sp.mapManager.stern.stern, 105, 50, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        g2.drawString("X",180,58);
        g2.drawString("X",180,115);
        String text = Integer.toString(sp.mainSpieler.konto.muenzen);
        g2.setColor(Color.yellow);
        g2.drawString(text,215, 57);
        text = Integer.toString(sp.mainSpieler.konto.sterne);
        g2.drawString(text,215, 115);
    }
    public void spielerStatusBoxMalen(){
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(0, 0, 285, 140, 35, 35);
        c = new Color(255,255,255,200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(5,5,275, 130, 25, 25);
    }
    private void mapMalen(){
        int weltSpalte = 0;
        int weltZeile = 0;

        while(weltSpalte < sp.maxWeltSpalte && weltZeile < sp.maxWeltZeile) {
            Fliese vorlauefigeFliese = mapFliesen[weltZeile][weltSpalte];

            int weltX = weltSpalte * sp.vergroesserteFliesenGroesse;
            int weltY = weltZeile * sp.vergroesserteFliesenGroesse;
            int bildschirmX = weltX - sp.mainSpieler.weltX + sp.mainSpieler.bildschirmX;
            int bildschirmY = weltY - sp.mainSpieler.weltY + sp.mainSpieler.bildschirmY;

            g2.drawImage(vorlauefigeFliese.getFlieseImage(), bildschirmX, bildschirmY, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
            if(mapFliesen[weltZeile][weltSpalte].feld != null){
                g2.drawImage(mapFliesen[weltZeile][weltSpalte].feld.getFeldImage(), bildschirmX, bildschirmY, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
                if(mapFliesen[weltZeile][weltSpalte].feld.hatStern){
                    BufferedImage image = null;
                    if(stern.spriteNum == 0){
                        image = stern.stern1;
                    }else if(stern.spriteNum == 1 || stern.spriteNum == 7){
                        image = stern.stern2;
                    }else if(stern.spriteNum == 2 || stern.spriteNum == 6){
                        image = stern.stern3;
                    }else if(stern.spriteNum == 3 || stern.spriteNum == 5){
                        image = stern.stern4;
                    }else if(stern.spriteNum == 4){
                        image = stern.stern5;
                    }
                    int xSternPosition = bildschirmX , ySternPosition = bildschirmY;
                    if(stern.sternPostition.equals("unten")){
                        ySternPosition -= sp.vergroesserteFliesenGroesse;
                    }else if(stern.sternPostition.equals("oben")){
                        ySternPosition += sp.vergroesserteFliesenGroesse;
                    }else if(stern.sternPostition.equals("rechts")){
                        xSternPosition += sp.vergroesserteFliesenGroesse;
                    }else if(stern.sternPostition.equals("links")){
                        xSternPosition -= sp.vergroesserteFliesenGroesse;
                    }
                    g2.drawImage(image, xSternPosition, ySternPosition, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);                }
            }

            weltSpalte++;
            if (weltSpalte == sp.maxWeltSpalte) {
                weltSpalte = 0;
                weltZeile++;
            }
        }
    }
}
