package Spielablauf;


import Main.SpielPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SpielMapManager {
    private final Fliese[][] mapFliesen;
    SpielPanel sp;

    Graphics2D g2;
    ArrayList<Spieler> alleSpieler = new ArrayList<>();
    public MapEingabeManager mapEingabeManager;



    public SpielMapManager(SpielPanel sp) {
        this.sp = sp;
        mapEingabeManager = new MapEingabeManager(this);
        String[][] mapFelder = new String[25][30];
        this.mapFliesen = new Fliese[25][30];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("src/tileStructure.txt"));
            String line;
            int zeilenIndex = 0;
            while ((line = reader.readLine()) != null) {
                String[] zeile = line.split("\t");

                System.arraycopy(zeile, 0, mapFelder[zeilenIndex], 0, zeile.length);
                zeilenIndex++;

            }
            for(int zeile = 0; zeile < 25; zeile++){
                System.out.println();
                for(int spalte = 0; spalte < 30; spalte++){
                    try{
                        Fliese fliese = new Fliese();
                        fliese.flieseImage = ImageIO.read(new File("src/source/mapBilder/tileset (" + mapFelder[zeile][spalte] + ").png"));

                        fliese.feld = feldEinrichten(zeile, spalte);

                        mapFliesen[zeile][spalte] = fliese;
                        System.out.printf("%s\t", mapFelder[zeile][spalte]);
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
        felderReihenfolgeFestlegen();

    }
    public Feld feldEinrichten(int zeile, int spalte){
        switch(zeile){
            case 19:
                if(spalte == 11){
                    return new ViolettesFeld(zeile, spalte);
                }
                else{
                    return null;
                }
            case 17:
                return switch (spalte) {
                    case 5 -> new RotesFeld(zeile, spalte);
                    case 8, 11 -> new GruenesFeld(zeile, spalte);
                    case 14 -> new OrangesFeld(zeile, spalte);
                    case 17 -> new GelbesFeld(zeile, spalte);
                    default -> null;
                };
            case 15:
                return switch (spalte) {
                    case 5 -> new GruenesFeld(zeile, spalte);
                    case 8 -> new RotesFeld(zeile, spalte);
                    case 11, 17 -> new BlauesFeld(zeile, spalte);
                    default -> null;
                };
            case 13:
                return switch (spalte) {
                    case 5 -> new ViolettesFeld(zeile, spalte);
                    case 8, 14 -> new GruenesFeld(zeile, spalte);
                    case 11 -> new OrangesFeld(zeile, spalte);
                    case 17 -> new RotesFeld(zeile, spalte);
                    default -> null;
                };
            case 11:
                if(spalte == 8) {
                    return new OrangesFeld(zeile, spalte);
                }else{
                    return null;
                }
            case 10:
                if(spalte == 17){
                    return new BlauesFeld(zeile, spalte);
                }else{
                    return null;
                }
            case 9:
                if(spalte == 8) {
                    return new GelbesFeld(zeile, spalte);
                }else{
                    return null;
                }

            case 7:
                return switch (spalte) {
                    case 8, 19 -> new GruenesFeld(zeile, spalte);
                    case 15 -> new OrangesFeld(zeile, spalte);
                    case 17, 23 -> new RotesFeld(zeile, spalte);
                    case 21 -> new BlauesFeld(zeile, spalte);
                    case 25 -> new GelbesFeld(zeile, spalte);
                    default -> null;
                };
            case 6:
                return switch (spalte) {
                    case 9, 26 -> new RotesFeld(zeile, spalte);
                    case 11 -> new OrangesFeld(zeile, spalte);
                    case 14 -> new ViolettesFeld(zeile, spalte);
                    default -> null;
                };
            case 4:
                return switch (spalte) {
                    case 14, 26 -> new GruenesFeld(zeile, spalte);
                    case 16 -> new RotesFeld(zeile, spalte);
                    case 24 -> new BlauesFeld(zeile, spalte);
                    default -> null;
                };
            case 3:
                return switch (spalte) {
                    case 17, 21 -> new GruenesFeld(zeile, spalte);
                    case 19 -> new OrangesFeld(zeile, spalte);
                    case 23 -> new RotesFeld(zeile, spalte);
                    default -> null;
                };
            default:
                return null;


        }

    }


    public void hinzufuegeSpieler(Spieler spieler){
        alleSpieler.add(spieler);
    }
    public void update(){

    }
    public void malen(Graphics2D g2){

        int weltSpalte = 0;
        int weltZeile = 0;

        while(weltSpalte < sp.maxWeltSpalte && weltZeile < sp.maxWeltZeile) {
            Fliese vorlauefigeFliese = mapFliesen[weltZeile][weltSpalte];

            int weltX = weltSpalte * sp.doppelteFliesenGroesse;
            int weltY = weltZeile * sp.doppelteFliesenGroesse;
            int bildschirmX = weltX - sp.husam.weltX + sp.husam.bildschirmX;
            int bildschirmY = weltY - sp.husam.weltY + sp.husam.bildschirmY;

            g2.drawImage(vorlauefigeFliese.getFlieseImage(), bildschirmX, bildschirmY, sp.doppelteFliesenGroesse, sp.doppelteFliesenGroesse, null);
            if(mapFliesen[weltZeile][weltSpalte].feld != null){
                g2.drawImage(mapFliesen[weltZeile][weltSpalte].feld.getFeldImage(), bildschirmX, bildschirmY, sp.doppelteFliesenGroesse, sp.doppelteFliesenGroesse, null);
            }

            weltSpalte++;
            if (weltSpalte == sp.maxWeltSpalte) {
                weltSpalte = 0;
                weltZeile++;
            }
        }


    }
    private void felderReihenfolgeFestlegen(){
        mapFliesen[19][11].feld.addNaechstesFeld(mapFliesen[17][11].feld);
        mapFliesen[19][11].feld.addVorherigesFeld(mapFliesen[17][11].feld);

        mapFliesen[17][11].feld.addNaechstesFeld(mapFliesen[17][14].feld);
        mapFliesen[17][11].feld.addNaechstesFeld(mapFliesen[17][8].feld);
        mapFliesen[17][11].feld.addNaechstesFeld(mapFliesen[15][11].feld);
        mapFliesen[17][11].feld.addVorherigesFeld(mapFliesen[17][14].feld);
        mapFliesen[17][11].feld.addVorherigesFeld(mapFliesen[17][8].feld);
        mapFliesen[17][11].feld.addVorherigesFeld(mapFliesen[19][11].feld);
        mapFliesen[17][11].feld.addVorherigesFeld(mapFliesen[15][11].feld);

        mapFliesen[17][14].feld.addNaechstesFeld(mapFliesen[17][17].feld);
        mapFliesen[17][14].feld.addVorherigesFeld(mapFliesen[17][11].feld);

        mapFliesen[17][8].feld.addNaechstesFeld(mapFliesen[17][5].feld);
        mapFliesen[17][8].feld.addVorherigesFeld(mapFliesen[17][11].feld);

        mapFliesen[15][11].feld.addNaechstesFeld(mapFliesen[13][11].feld);
        mapFliesen[15][11].feld.addNaechstesFeld(mapFliesen[15][8].feld);
        mapFliesen[15][11].feld.addVorherigesFeld(mapFliesen[17][11].feld);
        mapFliesen[15][11].feld.addVorherigesFeld(mapFliesen[13][11].feld);
        mapFliesen[15][11].feld.addVorherigesFeld(mapFliesen[15][8].feld);

        mapFliesen[17][17].feld.addNaechstesFeld(mapFliesen[15][17].feld);
        mapFliesen[17][17].feld.addVorherigesFeld(mapFliesen[17][14].feld);

        mapFliesen[17][5].feld.addNaechstesFeld(mapFliesen[15][5].feld);
        mapFliesen[17][5].feld.addVorherigesFeld(mapFliesen[17][8].feld);

        mapFliesen[15][5].feld.addNaechstesFeld(mapFliesen[13][5].feld);
        mapFliesen[15][5].feld.addNaechstesFeld(mapFliesen[15][8].feld);
        mapFliesen[15][5].feld.addVorherigesFeld(mapFliesen[13][5].feld);
        mapFliesen[15][5].feld.addVorherigesFeld(mapFliesen[15][8].feld);
        mapFliesen[15][5].feld.addVorherigesFeld(mapFliesen[17][5].feld);

        mapFliesen[15][17].feld.addNaechstesFeld(mapFliesen[13][17].feld);
        mapFliesen[15][17].feld.addVorherigesFeld(mapFliesen[17][17].feld);

        mapFliesen[15][8].feld.addNaechstesFeld(mapFliesen[15][5].feld);
        mapFliesen[15][8].feld.addNaechstesFeld(mapFliesen[15][11].feld);
        mapFliesen[15][8].feld.addVorherigesFeld(mapFliesen[15][5].feld);
        mapFliesen[15][8].feld.addVorherigesFeld(mapFliesen[15][11].feld);

        mapFliesen[13][5].feld.addNaechstesFeld(mapFliesen[13][8].feld);
        mapFliesen[13][5].feld.addVorherigesFeld(mapFliesen[15][5].feld);

        mapFliesen[13][8].feld.addNaechstesFeld(mapFliesen[13][5].feld);
        mapFliesen[13][8].feld.addNaechstesFeld(mapFliesen[13][11].feld);
        mapFliesen[13][8].feld.addNaechstesFeld(mapFliesen[11][8].feld);
        mapFliesen[13][8].feld.addVorherigesFeld(mapFliesen[13][5].feld);
        mapFliesen[13][8].feld.addVorherigesFeld(mapFliesen[13][11].feld);

        mapFliesen[13][11].feld.addNaechstesFeld(mapFliesen[13][8].feld);
        mapFliesen[13][11].feld.addNaechstesFeld(mapFliesen[13][14].feld);
        mapFliesen[13][11].feld.addVorherigesFeld(mapFliesen[13][11].feld);
        mapFliesen[13][11].feld.addVorherigesFeld(mapFliesen[13][11].feld);

        mapFliesen[13][14].feld.addNaechstesFeld(mapFliesen[13][17].feld);
        mapFliesen[13][14].feld.addVorherigesFeld(mapFliesen[13][11].feld);

        mapFliesen[13][17].feld.addNaechstesFeld(mapFliesen[13][14].feld);
        mapFliesen[13][17].feld.addNaechstesFeld(mapFliesen[10][17].feld);
        mapFliesen[13][17].feld.addVorherigesFeld(mapFliesen[13][14].feld);
        mapFliesen[13][17].feld.addVorherigesFeld(mapFliesen[15][17].feld);

        mapFliesen[11][8].feld.addNaechstesFeld(mapFliesen[9][8].feld);
        mapFliesen[11][8].feld.addVorherigesFeld(mapFliesen[13][8].feld);

        mapFliesen[10][17].feld.addNaechstesFeld(mapFliesen[7][17].feld);
        mapFliesen[10][17].feld.addVorherigesFeld(mapFliesen[13][17].feld);

        mapFliesen[9][8].feld.addNaechstesFeld(mapFliesen[7][8].feld);
        mapFliesen[9][8].feld.addVorherigesFeld(mapFliesen[11][8].feld);

        mapFliesen[7][8].feld.addNaechstesFeld(mapFliesen[6][9].feld);
        mapFliesen[7][8].feld.addVorherigesFeld(mapFliesen[9][8].feld);



    }




}
