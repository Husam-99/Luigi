package spieler;

import Main.SpielPanel;
import Spielablauf.Feld;

import java.awt.*;

public class Spieler {
    SpielPanel sp;
    Graphics2D g2;
    public Spielfigur spielfigur;
    public Wuerfel wuerfel;
    public final int bildschirmX, bildschirmY;
    public int weltX, weltY;
    public int geschwindigkeit, schritteAnzahl;
    public Feld naechstesFeld, aktuellesFeld, tempFeld, aktuellFeld; //tempFeld ist zu prüfen, ob das nächste Feld nicht gleich wie das vorherige Feld ist
    public String richtung;
    public Konto konto;
    public Inventar inventar;
    public boolean bewegung = false, wuerfelZustand = false, inventarZustand = false;
    public Spieler(SpielPanel sp) {
        this.sp = sp;
        konto = new Konto();
        inventar = new Inventar(this);
        bildschirmX = sp.bildschirmBreite/2 - (sp.vergroesserteFliesenGroesse/2);
        bildschirmY = sp.bildschirmHoehe/2 - (sp.vergroesserteFliesenGroesse/2);
        sp.mapManager.hinzufuegeSpieler(this);
        setzeWerte();
    }

    public void setzeWerte(){
        weltX = sp.vergroesserteFliesenGroesse * 11;
        weltY = sp.vergroesserteFliesenGroesse * 21;
        geschwindigkeit = 3;
        richtung = "nord";
        naechstesFeld = sp.mapManager.mapFliesen[19][11].feld;
        aktuellesFeld = null;
    }

    public void spielfigurAuswaehlen() {
        if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 0){
            spielfigur = new Abdo(this);
            wuerfel = new AbdoWuerfel(this);
        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 1){
            spielfigur = new Husam(this);
            wuerfel = new HusamWuerfel(this);
        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 2){
            spielfigur = new Taha(this);
            wuerfel = new TahaWuerfel(this);
        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 3){
            spielfigur = new Yousef(this);
            wuerfel = new YousefWuerfel(this);
        }
    }
    public void getSpielerPositionX() {}
    public void getSpielerPositionY() {}

    public void update(){
        spielfigur.update();
        if(wuerfelZustand) {
            wuerfel.update();
        }
    }
    public void malen(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(sp.marioPartyFont);
        spielfigur.malen(g2);
        spielerStatusBoxMalen();
        spielerStatusMalen();
        if(wuerfelZustand){
            wuerfel.malen(g2);
        }else if(schritteAnzahl > 0){
            schritteMalen();
        }if(inventarZustand){
            inventar.malen(g2);
        }
    }
    public void schritteMalen(){
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
            g2.fillRoundRect(650, 80, 220, 160, 35, 35);
            c = new Color(255, 255, 255, 200);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(655, 85, 210, 150, 25, 25);
        }
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,150F));
        String schritte = Integer.toString(schritteAnzahl);
        int x = getXfuerCenter(schritte);
        g2.drawString(schritte, x, 200);
    }
    public void spielerStatusMalen(){
        g2.drawImage(spielfigur.profile, 10, 10, sp.vergroesserteFliesenGroesse+30, sp.vergroesserteFliesenGroesse+30, null);
        g2.drawImage(sp.mapManager.muenze.muenze1, 127, 15, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(sp.mapManager.stern.stern, 105, 50, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        g2.drawString("X",180,58);
        g2.drawString("X",180,115);
        String text = Integer.toString(konto.muenzen);
        g2.setColor(Color.yellow);
        g2.drawString(text,215, 57);
        text = Integer.toString(konto.sterne);
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
    public int getXfuerCenter(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = sp.bildschirmBreite/2 - length/2;
        return x;
    }
}