package spieler;

import java.awt.*;

public class Inventar {

    Spieler spieler;
    Graphics2D g2;
    private int slot1Messungen,  slot2Messungen, slot3Messungen,  slot4Messungen;
    public Gegenstand[] inventar;
    public int befehlNum = 0, gegenstaendeAnzahl;
    public Inventar(Spieler spieler){
        this.spieler = spieler;
        inventar = new Gegenstand[4];
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
    }
    public void gegenstandVerwenden(int befehlNum){
        if(befehlNum == 0) {
            inventar[0].effeckteAnwenden();
            inventar[0] = null;
        }else if(befehlNum == 1){
            inventar[1].effeckteAnwenden();
            inventar[1] = null;
        }else if(befehlNum == 2){
            inventar[2].effeckteAnwenden();
            inventar[2] = null;
        }else if(befehlNum == 3){
            inventar[3].effeckteAnwenden();
            inventar[3] = null;
        }
    }
    public void anzahlGegenstaendeInInventar(){
        gegenstaendeAnzahl = 0;
        for(int slot = 0; slot < 4; slot++){
            if (inventar[slot] != null) {
                gegenstaendeAnzahl++;
            }
        }
    }
    public void malen(Graphics2D g2){
        this.g2 = g2;
        inventarBoxMalen();
        gegenstaendeMalen();
    }
    private void inventarBoxMalen(){
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
}
