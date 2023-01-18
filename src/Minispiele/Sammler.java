package Minispiele;

import Main.SpielPanel;
import Spielablauf.Fliese;
import Spielablauf.Muenze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Sammler extends Minispiel {
    private Muenze muenze1;
    private Muenze muenze2;
    Rectangle[] wandRechteck;


    public Sammler(SpielPanel sp, ArrayList<MinispielSpieler> alleMinispielSpieler) {
        super(sp, alleMinispielSpieler);
        this.minispielFliesen = new Fliese[9];
        this.minispielMap = new int[15][9];
        muenze1 = new Muenze(this.sp);
        muenze2 = new Muenze(this.sp);
        this.getFlieseImage();
        this.mapLaden();
    }

    public void update(){
        for(MinispielSpieler spieler: alleMinispielSpieler){
            if(spieler!= null){
                spieler.update();
            }
        }
        muenze1.update();
        muenze2.update();

    }
    public void kollisionChecken(MinispielSpieler spieler) {

        if (spieler.minispielSpielerRechteck.intersects(muenze1.muenzeRechteck)) {
            muenze1 = null;
            muenze1 = new Muenze(this.sp);
            spieler.punktzahl++;
        }
        else if (spieler.minispielSpielerRechteck.intersects(muenze2.muenzeRechteck)) {
            muenze2 = null;
            muenze2 = new Muenze(this.sp);
            spieler.punktzahl++;

        }

        //if (this.playerRect.intersects(this.wandRechteck)) {
        //    keyH.upPressed =false;
        //    keyH.downPressed = false;
        //    keyH.leftPressed = false;
        //    keyH.rightPressed = false;
        //}
    }


    @Override
    public void getFlieseImage() {
        try{
            minispielFliesen[0] = new Fliese();
            minispielFliesen[0].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice0.png")));

            minispielFliesen[1] = new Fliese();
            minispielFliesen[1].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice1.png")));

            minispielFliesen[2] = new Fliese();
            minispielFliesen[2].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice3.png")));

            minispielFliesen[3] = new Fliese();
            minispielFliesen[3].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice4.png")));

            minispielFliesen[4] = new Fliese();
            minispielFliesen[4].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice5.png")));

            minispielFliesen[5] = new Fliese();
            minispielFliesen[5].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice6.png")));

            minispielFliesen[6] = new Fliese();
            minispielFliesen[6].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice7.png")));

            minispielFliesen[7] = new Fliese();
            minispielFliesen[7].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice8.png")));

            minispielFliesen[8] = new Fliese();
            minispielFliesen[8].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice9.png")));

           
        }catch(IOException e){
            e.printStackTrace();
        }    }

    @Override
    public void mapLaden() {
        try{
            InputStream is= getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int spalte = 0;
            int zeile = 0;

            while(spalte < 15 && zeile < 9){
                String line = br.readLine();
                while(spalte < 15){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[spalte]);

                    minispielMap[spalte][zeile] = num;
                    spalte++;
                }
                if(spalte == 15){
                    spalte = 0;
                    zeile++;
                }
            }
            br.close();

        }catch(Exception e){

        }
        wandRechteck = new Rectangle[4];
        wandRechteck[0] = new Rectangle(0,0,1440,96);
        wandRechteck[1] = new Rectangle(0,800-96,1440,96);
        wandRechteck[2] = new Rectangle(0,0,96,800);
        wandRechteck[3] = new Rectangle(1440-96,0,96,800);

    }

    @Override
    public void malen(Graphics2D g2) {
        int spalte = 0;
        int zeile = 0;
        int x = 0;
        int y = 0;
        while(spalte < 15 && zeile < 9){

            int flieseIndex = minispielMap[spalte][zeile];

            if(zeile == 8){
                g2.drawImage(minispielFliesen[flieseIndex].flieseImage, x, y-(sp.fliesenGroesse*2), sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
            } else{
                g2.drawImage(minispielFliesen[flieseIndex].flieseImage, x, y, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
            }

            spalte++;
            x += sp.vergroesserteFliesenGroesse;

            if(spalte == 15){
                spalte = 0;
                x = 0;
                zeile ++;
                y += sp.vergroesserteFliesenGroesse;
            }
        }


        muenze1.malen(g2);
        muenze2.malen(g2);
    }
}
