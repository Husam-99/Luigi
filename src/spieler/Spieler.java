package spieler;

import Spielablauf.Feld;
import Spielablauf.SpielablaufManager;

import java.awt.*;

public class Spieler {

    public SpielablaufManager spielablaufManager;
    Graphics2D g2;

    public Spielfigur spielfigur;
    public Wuerfel wuerfel;

    public int bildschirmX, bildschirmY;
    public int weltX, weltY;

    public int schritteAnzahl, geschwindigkeit, position;
    public Feld aktuellesFeld,vorherigesFeld;
    public Konto konto;
    public Inventar inventar;

    public boolean amSpiel, bewegung, inventarZustand, zuStern;

    public boolean wuerfelZustand, normaleWuerfelZustand, megaWuerfelZustand, miniWuerfelZustand;

    public GegenstandWuerfel megaWuerfel, miniWuerfel;


    public Spieler(){}

    public Spieler(SpielablaufManager spielablaufManager) {
        this.spielablaufManager = spielablaufManager;
        konto = new Konto(this);
        inventar = new Inventar(this);
        setzeWerte();
    }

    private void setzeWerte(){

        //default Werte
        this.bildschirmX = spielablaufManager.sp.bildschirmBreite / 2 - (spielablaufManager.sp.vergroesserteFliesenGroesse / 2);
        this.bildschirmY = spielablaufManager.sp.bildschirmHoehe / 2 - (spielablaufManager.sp.vergroesserteFliesenGroesse / 2);
        this.weltY = (int) (spielablaufManager.sp.vergroesserteFliesenGroesse * 21.5);
        geschwindigkeit = 3;
        position = 0;
        aktuellesFeld = spielablaufManager.mapManager.mapFliesen[19][11].feld;
        vorherigesFeld = null;

        amSpiel = true;
        bewegung = false;
        inventarZustand = false;
        zuStern = false;

        wuerfelZustand = true;
        normaleWuerfelZustand = true;
        megaWuerfelZustand = false;
        miniWuerfelZustand = false;

        megaWuerfel = new MegaWuerfel( this);
        miniWuerfel = new MiniWuerfel(this);
    }

    //main Spieler erstellen
    public void spielfigurErstellen(){
        if(spielablaufManager.sp.menueManager.spielfigurAuswahl.befehlNum1 == 0){
            spielfigur = new Abdo(this);
            wuerfel = new AbdoWuerfel(this);
            this.weltX = (int) (spielablaufManager.sp.vergroesserteFliesenGroesse * 9.50);
        } else if(spielablaufManager.sp.menueManager.spielfigurAuswahl.befehlNum1 == 1){
            spielfigur = new Husam(this);
            wuerfel = new HusamWuerfel(this);
            this.weltX = (int) (spielablaufManager.sp.vergroesserteFliesenGroesse * 10.5);
        } else if(spielablaufManager.sp.menueManager.spielfigurAuswahl.befehlNum1 == 2){
            spielfigur = new Taha(this);
            wuerfel = new TahaWuerfel(this);
            this.weltX = (int) (spielablaufManager.sp.vergroesserteFliesenGroesse * 11.5);
        } else if(spielablaufManager.sp.menueManager.spielfigurAuswahl.befehlNum1 == 3){
            spielfigur = new Yousef(this);
            wuerfel = new YousefWuerfel(this);
            this.weltX = (int) (spielablaufManager.sp.vergroesserteFliesenGroesse * 12.5);
        }
    }

    //alle Spieler erstellen
    public void spielfigurErstellen(int spielfigurIndex) {
        if(spielfigurIndex == 0){
            spielfigur = new Abdo(this);
            wuerfel = new AbdoWuerfel(this);
        } else if(spielfigurIndex == 1){
            spielfigur = new Husam(this);
            wuerfel = new HusamWuerfel(this);
        } else if(spielfigurIndex == 2){
            spielfigur = new Taha(this);
            wuerfel = new TahaWuerfel(this);
        } else if(spielfigurIndex == 3){
            spielfigur = new Yousef(this);
            wuerfel = new YousefWuerfel(this);
        }
    }

    public void update(){
      if(spielfigur != null && wuerfel!=null) {
            spielfigur.update();
            if (wuerfelZustand) {
                if (normaleWuerfelZustand) {
                    wuerfel.update();
                } else if (megaWuerfelZustand) {
                    megaWuerfel.update();
                } else if (miniWuerfelZustand) {
                    miniWuerfel.update();
                }
            }
        }
    }

    public void malen(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(spielablaufManager.sp.marioPartyFont);

        if(spielablaufManager.mainSpieler.spielfigur!=null){
            spielfigur.malen(g2);
        }

        if(spielablaufManager.sp.client.istDran()) {
            if (this == spielablaufManager.mainSpieler) {
                if (wuerfelZustand) {
                    if (normaleWuerfelZustand) {
                        wuerfel.malen(g2);
                    } else if (megaWuerfelZustand) {
                        megaWuerfel.malen(g2);
                    } else if (miniWuerfelZustand) {
                        miniWuerfel.malen(g2);
                    }
                } else if (schritteAnzahl > 0) {
                    schritteMalen();
                }
            }
        }

        //Speziell fall für beginn des Spiels
        if (spielablaufManager.sp.aktuelleRundenAnzahl == 0) {
            if (this == spielablaufManager.mainSpieler) {
                if (schritteAnzahl > 0) {
                    schritteMalen();
                }
            } else {
                if (schritteAnzahl > 0) {
                    andereSpielerSchritteMalen();
                }
            }
        }
    }

    private void andereSpielerSchritteMalen(){
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
        String schritte = Integer.toString(schritteAnzahl);
        g2.drawString(schritte, bildschirmX + 25, bildschirmY - 20);
    }

    private void schritteMalen(){
        if(schritteAnzahl < 10) {
            Color c = new Color(0, 0, 0, 200);
            g2.setColor(c);
            g2.fillRoundRect(650, 80, 140, 150, 35, 35);
            c = new Color(255, 255, 255, 200);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(655, 85, 130, 140, 25, 25);
        }else{
            Color c = new Color(0, 0, 0, 200);
            g2.setColor(c);
            g2.fillRoundRect(610, 80, 220, 160, 35, 35);
            c = new Color(255, 255, 255, 200);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(615, 85, 210, 150, 25, 25);
        }

        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,150F));
        String schritte = Integer.toString(schritteAnzahl);
        int x = getXfuerCenter(schritte);
        g2.drawString(schritte, x, 200);
    }

    private int getXfuerCenter(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return spielablaufManager.sp.bildschirmBreite/2 - length/2;
    }

}