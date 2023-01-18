package spieler;

import Main.SpielPanel;
import Spielablauf.Feld;

import java.awt.*;

public class Spieler {
    public SpielPanel sp;
    Graphics2D g2;
    public Spielfigur spielfigur;
    public Wuerfel wuerfel;
    public int bildschirmX, bildschirmY;
    public int weltX, weltY;
    public int geschwindigkeit, schritteAnzahl;
    public Feld naechstesFeld, aktuellesFeld, tempFeld, aktuellFeld; //tempFeld ist zu prüfen, ob das nächste Feld nicht gleich wie das vorherige Feld ist
    public Konto konto;
    public Inventar inventar;
    public boolean bewegung = false, wuerfelZustand = false, inventarZustand = false;

    public Spieler(){


    }
    public boolean amSpiel = true, bewegung = false, wuerfelZustand = false, inventarZustand = false, shopGeoeffnet = false,
            normaleWuerfelZustand = true, megaWuerfelZustand = false, miniWuerfelZustand = false;
    public GegenstandWuerfel megaWuerfel = new MegaWuerfel( this), miniWuerfel = new MiniWuerfel(this);
    public Shop shop;
    public Spieler(SpielPanel sp) {
        this.sp = sp;
        konto = new Konto(this);
        inventar = new Inventar(this);
        shop = new Shop(this);
        setzeWerte();
    }
    public void setzeWerte(){
        this.bildschirmX = sp.bildschirmBreite / 2 - (sp.vergroesserteFliesenGroesse / 2);
        this.bildschirmY = sp.bildschirmHoehe / 2 - (sp.vergroesserteFliesenGroesse / 2);
        this.weltY = (int) (sp.vergroesserteFliesenGroesse * 21.5);

        geschwindigkeit = 3;

        naechstesFeld = sp.mapManager.mapFliesen[19][11].feld;
        aktuellesFeld = null;
    }
    public void spielfigurAuswaehlen() {
        if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 0){
            spielfigur = new Abdo(this);
            wuerfel = new AbdoWuerfel(this);
            this.weltX = (int) (sp.vergroesserteFliesenGroesse * 9.5);

        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 1){
            spielfigur = new Husam(this);
            wuerfel = new HusamWuerfel(this);
            this.weltX = (int) (sp.vergroesserteFliesenGroesse * 10.5);

        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 2){
            spielfigur = new Taha(this);
            wuerfel = new TahaWuerfel(this);
            this.weltX = (int) (sp.vergroesserteFliesenGroesse * 11.5);
        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 3){
            spielfigur = new Yousef(this);
            wuerfel = new YousefWuerfel(this);
            this.weltX = (int) (sp.vergroesserteFliesenGroesse * 12.5);

        }
    }
    public void spielfigurAuswaehlen(int spielfigurIndex) {
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
        spielfigur.update();
        if(wuerfelZustand){
            if(normaleWuerfelZustand) {
                wuerfel.update();
            }else if(megaWuerfelZustand){
                megaWuerfel.update();
            }else if(miniWuerfelZustand){
                miniWuerfel.update();
            }
        }
    }
    public void malen(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(sp.marioPartyFont);
        if(sp.mainSpieler.spielfigur!=null){
            spielfigur.malen(g2);
            konto.malen(g2);
        }
        if(wuerfelZustand){
            if(normaleWuerfelZustand) {
                wuerfel.malen(g2);
            }else if(megaWuerfelZustand){
                megaWuerfel.malen(g2);
            }else if(miniWuerfelZustand){
                miniWuerfel.malen(g2);
            }
        }else if(schritteAnzahl > 0){
            schritteMalen();
        }
        if(inventarZustand){
            inventar.malen(g2);
        } else if(shopGeoeffnet){
            shop.malen(g2);
        }
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
        int x = sp.bildschirmBreite/2 - length/2;
        return x;
    }
}