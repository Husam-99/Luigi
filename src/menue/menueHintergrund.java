package menue;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class menueHintergrund {

    MenueManager mn;
    BufferedImage[] fliese;
    int hintergrundFlieseNum[][];
    int menueHintergrundSpalte = 15;
    int menueHintergrundZeile = 5;
    Graphics2D g2;
    int x = 0;
    int spriteZaehler = 0;
    int hintergrundSplate, hintergrundZeile;
    int hin = 0, zueruck = 1;
    int zustand = hin;


    public menueHintergrund(MenueManager mn){
        this.mn = mn;
        fliese = new BufferedImage[75];
        hintergrundFlieseNum = new int[15][5];
        getFlieseBilder();
        flieseLesen();
    }

    public void getFlieseBilder(){
        try{
            for(int temp = 0; temp < 75; temp++){
                    fliese[temp] = ImageIO.read(getClass().getResourceAsStream("/menueHintergrund/tileset (" + temp +").png"));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void flieseLesen(){
        try{
            InputStream is= getClass().getResourceAsStream("/menueHintergrund/menueHintergrund.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int spalte = 0;
            int zeile = 0;

            while(spalte < 15 && zeile < 5){
                String line = reader.readLine();
                while(spalte < 15){
                    String numbers[] = line.split(" ");
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
        }catch(Exception e){
        }
    }
    public void update(){
        if(spriteZaehler > 3){
            if(zustand == hin){
                x-=1;
                if(x == -960){
                    zustand = zueruck;
                }
            }else if(zustand == zueruck){
                x+=1;
                if(x == 0){
                    zustand = hin;
                }
            }
            spriteZaehler = 0;
        }
        spriteZaehler++;
    }

    public void malen(Graphics2D g2){
        hintergrundSplate = 0;
        hintergrundZeile = 0;
        this.g2 = g2;
        int x = 0;
        int y = 0;

        while(hintergrundSplate < menueHintergrundSpalte && hintergrundZeile < menueHintergrundZeile){
            int flieseNum =  hintergrundFlieseNum[hintergrundSplate][hintergrundZeile];
            if(hintergrundZeile == 0 && hintergrundSplate == 0){
                x =this.x;
            }
            g2.drawImage(fliese[flieseNum], x, y, mn.sp.flieseGroesse, mn.sp.flieseGroesse, null);
            hintergrundSplate++;
            x += mn.sp.flieseGroesse;

            if(hintergrundSplate == menueHintergrundSpalte){
                hintergrundSplate = 0;
                x = this.x;
                hintergrundZeile++;
                y += mn.sp.flieseGroesse;
            }
        }
    }





}
