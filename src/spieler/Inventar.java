package spieler;

import Networking.Pakete.GegenstandInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Inventar {

    Spieler spieler;
    Graphics2D g2;
    public BufferedImage icon;

    public Gegenstand[] inventar;
    public int befehlNum = 0, gegenstaendeAnzahl;
    private int slot1Messungen,  slot2Messungen, slot3Messungen,  slot4Messungen;

    //Inventar zustand
    public Boolean inventarLeer, inventarVoll;

    public Inventar(Spieler spieler) {
        this.spieler = spieler;
        inventar = new Gegenstand[4];

        inventarLeer = true;
        inventarVoll = false;

        // Inventar Icon
        try {
            icon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gegenstaende/Inventar.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gegenstandBekommen(int gegenstandNum){
        if(gegenstandNum == 1){
            if (inventar[0] == null) {
                inventar[0] = new Block(spieler);
            }else if(inventar[1] == null){
                inventar[1] = new Block(spieler);
            }else if(inventar[2] == null){
                inventar[2] = new Block(spieler);
            }else if(inventar[3] == null){
                inventar[3] = new Block(spieler);
            }
        }else if(gegenstandNum == 2){
            if (inventar[0] == null) {
                inventar[0] = new Bube(spieler);
            }else if(inventar[1] == null){
                inventar[1] = new Bube(spieler);
            }else if(inventar[2] == null){
                inventar[2] = new Bube(spieler);
            }else if(inventar[3] == null){
                inventar[3] = new Bube(spieler);
            }
        }else if(gegenstandNum == 3){
            if (inventar[0] == null) {
                inventar[0] = new MegaWuerfel(spieler);
            }else if(inventar[1] == null){
                inventar[1] = new MegaWuerfel(spieler);
            }else if(inventar[2] == null){
                inventar[2] = new MegaWuerfel(spieler);
            }else if(inventar[3] == null){
                inventar[3] = new MegaWuerfel(spieler);
            }
        }else if(gegenstandNum == 4){
            if (inventar[0] == null) {
                inventar[0] = new MiniWuerfel(spieler);
            }else if(inventar[1] == null){
                inventar[1] = new MiniWuerfel(spieler);
            }else if(inventar[2] == null){
                inventar[2] = new MiniWuerfel(spieler);
            }else if(inventar[3] == null){
                inventar[3] = new MiniWuerfel(spieler);
            }
        }else if(gegenstandNum == 5){
            if (inventar[0] == null) {
                inventar[0] = new Repos(spieler);
            }else if(inventar[1] == null){
                inventar[1] = new Repos(spieler);
            }else if(inventar[2] == null){
                inventar[2] = new Repos(spieler);
            }else if(inventar[3] == null){
                inventar[3] = new Repos(spieler);
            }
        }else if(gegenstandNum == 6){
            if (inventar[0] == null) {
                inventar[0] = new SternTaxi(spieler);
            }else if(inventar[1] == null){
                inventar[1] = new SternTaxi(spieler);
            }else if(inventar[2] == null){
                inventar[2] = new SternTaxi(spieler);
            }else if(inventar[3] == null){
                inventar[3] = new SternTaxi(spieler);
            }
        }

        //bestimmen, ob das Inventar voll oder leer ist
        inventarLeerOderVoll();

        if(spieler.spielablaufManager.sp.client.istDran()){

            //sende an alle Clients die Gegenstandnummer
            GegenstandInfo gegenstandInfo = new GegenstandInfo();
            gegenstandInfo.gegenstandNum = gegenstandNum;
            spieler.spielablaufManager.sp.client.send(gegenstandInfo);
        }
    }

    public void gegenstandVerwenden(int befehlNum){
        if(spieler.spielablaufManager.sp.client.istDran()){
            inventar[befehlNum].effeckteAnwenden();

            //sende an alle Clients welche slot ist verwendet
            GegenstandInfo gegenstandInfo = new GegenstandInfo();
            gegenstandInfo.befehlNum = befehlNum;
            spieler.spielablaufManager.sp.client.send(gegenstandInfo);
        }
        inventar[befehlNum] = null;

        //bestimmen, ob das Inventar voll oder leer ist
        inventarLeerOderVoll();
    }

    public void inventarLeerOderVoll(){
        gegenstaendeAnzahl = 0;
        for(int slot = 0; slot < 4; slot++){
            if (inventar[slot] != null) {
                gegenstaendeAnzahl++;
            }
        }
        inventarLeer = gegenstaendeAnzahl <= 0;
        inventarVoll = gegenstaendeAnzahl == 4;
    }

    public void malen(Graphics2D g2){
        this.g2 = g2;
        inventarBoxMalen();
        gegenstaendeMalen();
    }

    private void inventarBoxMalen(){

        //hinter bildschirm dunkler malen
        Color c = new Color(0,0,0,100);
        g2.setColor(c);
        g2.fillRect(0, 0, 1440, 864);

        c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(1000, 20, 420, 400, 35, 35);
        c = new Color(255, 255, 255, 200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(1005, 25, 410, 390, 25, 25);
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
        g2.drawString("Inventar",1035,400);
    }

    private void groesseAnpssen(){
        if (befehlNum == 0){
            slot1Messungen = spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2 +40;
            slot2Messungen =  spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen =  spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen =  spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 1){
            slot1Messungen = spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen =  spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen = spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2 +40;
            slot4Messungen =  spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 2){
            slot1Messungen = spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen = spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2 +40;
            slot3Messungen =  spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen =  spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 3){
            slot1Messungen = spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen =  spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen =  spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen = spieler.spielablaufManager.sp.vergroesserteFliesenGroesse * 2 +40;
        }
    }

    private void gegenstaendeMalen() {
        groesseAnpssen();

        if (inventar[0] != null) {
            g2.drawImage(inventar[0].icon, 1000, 20, slot1Messungen, slot1Messungen, null);
        }
        if (inventar[1] != null) {
            g2.drawImage(inventar[1].icon, 1200, 20, slot2Messungen, slot2Messungen, null);
        }
        if (inventar[2] != null) {
            g2.drawImage(inventar[2].icon, 1000, 170, slot3Messungen, slot3Messungen, null);
        }
        if (inventar[3] != null) {
            g2.drawImage(inventar[3].icon, 1200, 170, slot4Messungen, slot4Messungen, null);
        }
    }

    public void inventarVollRueckMeldungMalen(Graphics2D g2) {
        Color c = new Color(0, 0, 0, 100);
        g2.setColor(c);
        g2.fillRect(0, 0, 1440, 864);
        g2.setColor(Color.black);
        g2.fillRoundRect(348, 350, 750, 100, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(353, 355, 740, 90, 25, 25);
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
        g2.drawString("Inventar ist voll!", 425, 420);
    }

}
