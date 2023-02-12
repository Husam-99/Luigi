package menue;

import Main.SpielPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class MenueHintergrund {

    SpielPanel sp;

    //in Fliese wird alle Menü Hintergrund Bilder gespeichert
    BufferedImage[] fliese;

    //in dieser Matrix wird welche Bilder an welche Stelle, also welche Spalte und welche Zeile, gespeichert
    int[][] hintergrundFlieseNum;

    int menueHintergrundSpalte, menueHintergrundZeile;

    //die x ist zu wissen, an welche Stelle das Menü Hintergrund gemalt werden soll
    int x;

    //das Variable ist zu wissen, wie viel mal die Update-Methode aufgerufen wird
    //es wird benutzt, um das Update in dieser Stelle langsamer zu haben
    int zaehler;

    //Zustande für das Menü Hintergrund Bewegung
    int zustand;
    int hin, zurueck;


    public MenueHintergrund(SpielPanel sp){
        this.sp = sp;
        fliese = new BufferedImage[90];
        hintergrundFlieseNum = new int[15][6];
        menueHintergrundSpalte = 15;
        menueHintergrundZeile = 6;
        x = 0;
        zaehler = 0;
        hin = 0;
        zurueck = 1;
        getFlieseBilder();
        flieseLesen();
    }

    //in dieser Methode wird alle Menü Hintergrund Bilder von dem Source in die Fliese Variable gespeichert
    private void getFlieseBilder(){
        try{
            for(int temp = 0; temp < 90; temp++){
                    fliese[temp] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menueHintergrund/tileset" + temp + ".png")));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //in dieser Methode werden Bilder Zahlen von txt Datei gelesen, und die werden für das Bestimmen, welche Bilder an welche Stelle gemalt werden soll, benutzt
    private void flieseLesen(){
        try{
            InputStream is= getClass().getResourceAsStream("/menueHintergrund/menueHintergrund.txt");
            assert is != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int spalte = 0;
            int zeile = 0;

            while(spalte < 15 && zeile < 6){
                String line = reader.readLine();
                while(spalte < 15){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[spalte]);
                    hintergrundFlieseNum[spalte][zeile] = num;
                    spalte++;
                }
                if(spalte == 15){
                    spalte = 0;
                    zeile++;
                }
            }
            reader.close();
        }catch(Exception ignored){
        }
    }


    public void update(){
        if(zaehler > 3){
            //in dieser if-Bedingung wird bestimmen, ob das Ende des Menüs Hintergrund erreicht wird
            if(zustand == hin){
                x-=1;
                if(x == -960){
                    zustand = zurueck;
                }
            }else if(zustand == zurueck){
                x+=1;
                if(x == 0){
                    zustand = hin;
                }
            }
            zaehler = 0;
        }
        zaehler++;
    }

    public void malen(Graphics2D g2){
        int hintergrundSpalte = 0;
        int hintergrundZeile = 0;
        int x = 0;
        int y = 0;

        //in dieser while-Schleife wird das gesamte Menü hintergrund gemalt wird
        while(hintergrundSpalte < menueHintergrundSpalte && hintergrundZeile < menueHintergrundZeile){
            int flieseNum =  hintergrundFlieseNum[hintergrundSpalte][hintergrundZeile];

            if(hintergrundZeile == 0 && hintergrundSpalte == 0){
                x =this.x;
            }

            g2.drawImage(fliese[flieseNum], x, y, sp.vergroesserteFliesenGroesseMenue, sp.vergroesserteFliesenGroesseMenue, null);
            hintergrundSpalte++;
            x += sp.vergroesserteFliesenGroesseMenue;

            //Die Zeile ist zum Ende gekommen
            if(hintergrundSpalte == menueHintergrundSpalte){
                hintergrundSpalte = 0;
                x = this.x;
                hintergrundZeile++;
                y += sp.vergroesserteFliesenGroesseMenue;
            }
        }
    }

}
