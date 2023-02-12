package Spielablauf;

import spieler.Spieler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MiniMap {

    SpielablaufManager spielablaufManager;
    BufferedImage weltMap;

    public MiniMap(SpielablaufManager spielablaufManager){
        this.spielablaufManager = spielablaufManager;
        weltMapLaden();
    }

    private void weltMapLaden(){
        int weltMapBereite = spielablaufManager.sp.fliesenGroesse * spielablaufManager.sp.maxWeltSpalte;
        int weltMapHoehe = spielablaufManager.sp.fliesenGroesse * spielablaufManager.sp.maxWeltZeile;

        //die gesamte map wird in einem Bild gespeichert
        weltMap = new BufferedImage(weltMapBereite, weltMapHoehe, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = weltMap.createGraphics();
        int spalte = 0;
        int zeile = 0;
        int xSternPosition = 0;
        int ySternPosition = 0;

        while(spalte < spielablaufManager.sp.maxWeltSpalte && zeile < spielablaufManager.sp.maxWeltZeile){

            Fliese tileNum = spielablaufManager.mapManager.mapFliesen[zeile][spalte];
            int x = spielablaufManager.sp.fliesenGroesse * spalte;
            int y = spielablaufManager.sp.fliesenGroesse * zeile;
            g2.drawImage(tileNum.getFlieseImage(), x, y, null);


            //wenn auf diese Fliese ein Feld gibt, dann speicher es
            if(tileNum.feld != null){
                g2.drawImage(tileNum.feld.getFeldImage(), x, y, null);

                //wenn das Feld ein Stern hat, dann lege die Position des Sterns fest
                if(tileNum.feld.hatStern){
                    xSternPosition = x;
                    ySternPosition = y;
                    switch (spielablaufManager.mapManager.stern.sternPostition) {
                        case "unten" -> ySternPosition += spielablaufManager.sp.fliesenGroesse/2;
                        case "oben" -> ySternPosition -= spielablaufManager.sp.fliesenGroesse;
                        case "rechts" -> xSternPosition += spielablaufManager.sp.fliesenGroesse;
                        case "links" -> xSternPosition -= spielablaufManager.sp.fliesenGroesse;
                    }
                }
            }

            spalte++;
            if(spalte == spielablaufManager.sp.maxWeltSpalte){
                spalte = 0;
                zeile++;
            }
        }

        //der Stern wird nach Ende des Maps Malen gespeichert
        g2.drawImage(spielablaufManager.mapManager.stern.stern1, xSternPosition, ySternPosition,null);
        g2.dispose();
    }

    public void malen(Graphics2D g2){

        //Der Box hinter das Minimap malen
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(370, 132, 700, 600, 35, 35);
        c = new Color(255,255,255,200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(375,137,690, 590, 25, 25);

        //das gesamte Minimap mit Felder und Stern malen
        int bereite = 600;
        int hoehe = 500;
        int x = spielablaufManager.sp.bildschirmBreite/2 - bereite/2;
        int y = spielablaufManager.sp.bildschirmHoehe/2 - hoehe/2;
        g2.drawImage(weltMap, x, y, bereite, hoehe, null);

        //alle Spieler mit dem Minimap Größe anpassen und malen
        double skala = (double) (spielablaufManager.sp.vergroesserteFliesenGroesse * spielablaufManager.sp.maxWeltSpalte)/bereite;
        int spielerX = (int) (x +  spielablaufManager.mainSpieler.weltX/skala);
        int spielerY = (int) (y + spielablaufManager.mainSpieler.weltY/skala);
        int spielerGroesse = (int) (spielablaufManager.sp.vergroesserteFliesenGroesse/skala);
        g2.drawImage(spielablaufManager.mainSpieler.spielfigur.down1, spielerX, spielerY, spielerGroesse, spielerGroesse, null);
        for(Spieler spieler : spielablaufManager.sp.alleSpieler){
            if(spieler.spielfigur != null){
                spielerX = (int) (x + spieler.weltX/skala);
                spielerY = (int) (y + spieler.weltY/skala);
                g2.drawImage(spieler.spielfigur.down1, spielerX, spielerY, spielerGroesse, spielerGroesse, null);
            }
        }
    }

}
