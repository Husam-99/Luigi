package spieler;

import java.awt.*;

public class Shop {
    Spieler spieler;
    Graphics2D g2;
    Gegenstand sternTaxi, repos, bube, block, megaWuerfel, miniWuerfel;
    private int slot1Messungen,  slot2Messungen, slot3Messungen,  slot4Messungen, slot5Messungen, slot6Messungen;
    public int befehlNum = 0;
    public boolean genugMuenzen = true;
    public Shop(Spieler spieler){
        this.spieler = spieler;
        sternTaxi = new SternTaxi(spieler);
        repos = new Repos(spieler);
        bube = new Bube(spieler);
        block = new Block(spieler);
        megaWuerfel = new MegaWuerfel(spieler);
        miniWuerfel = new MiniWuerfel(spieler);
    }
    public void gegenstandKaufen(){
        if(befehlNum == 0){
            if(sternTaxi.preis <= spieler.konto.muenzen){
                spieler.inventar.gegenstandBekommen(6);
                spieler.konto.muenzenVerlieren(15);
                spieler.shopGeoeffnet = false;
                spieler.amSpiel = false;
            }else{
                genugMuenzen = false;
            }
        }else if(befehlNum == 1){
            if(repos.preis <= spieler.konto.muenzen){
                spieler.inventar.gegenstandBekommen(5);
                spieler.konto.muenzenVerlieren(13);
                spieler.shopGeoeffnet = false;
                spieler.amSpiel = false;
            }else{
                genugMuenzen = false;
            }
        }else if(befehlNum == 2){
            if(bube.preis <= spieler.konto.muenzen){
                spieler.inventar.gegenstandBekommen(2);
                spieler.konto.muenzenVerlieren(11);
                spieler.shopGeoeffnet = false;
                spieler.amSpiel = false;
            }else{
                genugMuenzen = false;
            }
        }else if(befehlNum == 3){
            if(block.preis <= spieler.konto.muenzen){
                spieler.inventar.gegenstandBekommen(1);
                spieler.konto.muenzenVerlieren(7);
                spieler.shopGeoeffnet = false;
                spieler.amSpiel = false;
            }else{
                genugMuenzen = false;
            }
        }else if(befehlNum == 4){
            if(megaWuerfel.preis <= spieler.konto.muenzen){
                spieler.inventar.gegenstandBekommen(3);
                spieler.konto.muenzenVerlieren(5);
                spieler.shopGeoeffnet = false;
                spieler.amSpiel = false;
            }else{
                genugMuenzen = false;
            }
        }else if(befehlNum == 5){
            if(miniWuerfel.preis <= spieler.konto.muenzen){
                spieler.inventar.gegenstandBekommen(4);
                spieler.konto.muenzenVerlieren(3);
                spieler.shopGeoeffnet = false;
                spieler.amSpiel = false;
            }else{
                genugMuenzen = false;
            }
        }
    }
    public void malen(Graphics2D g2){
        this.g2 = g2;
        shopBoxMalen();
        gegenstaendeMalen();
        preisMalen();
        if(!genugMuenzen){
            rueckMeldungMalen();
        }
    }
    private void rueckMeldungMalen(){
        Color c = new Color(0,0,0,100);
        g2.setColor(c);
        g2.fillRect(0, 0, 1440, 800);
        g2.setColor(Color.black);
        g2.fillRoundRect(330, 400, 780, 100, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(335,405,770, 90, 25, 25);
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
        String meldung = "nicht genung Muenzen!";
        g2.drawString(meldung,382, 463);
    }
    private void preisMalen(){
        g2.drawImage(spieler.sp.mapManager.muenze.muenze1, 350, 430, spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(spieler.sp.mapManager.muenze.muenze1, 650, 430, spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(spieler.sp.mapManager.muenze.muenze1, 950, 430, spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(spieler.sp.mapManager.muenze.muenze1, 350, 630, spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(spieler.sp.mapManager.muenze.muenze1, 650, 630, spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(spieler.sp.mapManager.muenze.muenze1, 950, 630, spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        Color c = new Color(255,255,255,200);
        g2.setColor(c);
        g2.drawString("X",405,467);
        g2.drawString("X",705,467);
        g2.drawString("X",1005,467);
        g2.drawString("X",405,667);
        g2.drawString("X",705,667);
        g2.drawString("X",1005,667);
        g2.setColor(Color.yellow);
        g2.drawString("15",440,467);
        g2.drawString("13",740,467);
        g2.drawString("11",1040,467);
        g2.drawString("7",440,667);
        g2.drawString("5",740,667);
        g2.drawString("3",1040,667);
    }
    private void groesseAnpssen() {
        if(befehlNum == 0) {
            slot1Messungen = spieler.sp.vergroesserteFliesenGroesse * 2 + 40;
            slot2Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot5Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot6Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 1){
            slot1Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen = spieler.sp.vergroesserteFliesenGroesse * 2 + 40;
            slot3Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot5Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot6Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 2){
            slot1Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen = spieler.sp.vergroesserteFliesenGroesse * 2 + 40;
            slot4Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot5Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot6Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 3){
            slot1Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen = spieler.sp.vergroesserteFliesenGroesse * 2 + 40;
            slot5Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot6Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 4){
            slot1Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot5Messungen = spieler.sp.vergroesserteFliesenGroesse * 2 + 40;
            slot6Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 5){
            slot1Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot5Messungen = spieler.sp.vergroesserteFliesenGroesse * 2;
            slot6Messungen = spieler.sp.vergroesserteFliesenGroesse * 2 + 40;
        }
    }
    private void gegenstaendeMalen(){
        groesseAnpssen();
        g2.drawImage(sternTaxi.icon, 320, 250, slot1Messungen, slot1Messungen, null);
        g2.drawImage(repos.icon, 620, 250, slot2Messungen, slot2Messungen, null);
        g2.drawImage(bube.icon, 920, 250, slot3Messungen, slot3Messungen, null);
        g2.drawImage(block.icon, 320, 450, slot4Messungen, slot4Messungen, null);
        g2.drawImage(megaWuerfel.icon, 620, 450, slot5Messungen, slot5Messungen, null);
        g2.drawImage(miniWuerfel.icon, 920, 450, slot6Messungen, slot6Messungen, null);
    }
    private void shopBoxMalen(){
        Color c = new Color(0,0,0,100);
        g2.setColor(c);
        g2.fillRect(0, 0, 1440, 864);
        c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(300, 200, 840, 510, 35, 35);
        c = new Color(255,255,255,200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(305,205,830, 500, 25, 25);
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
        g2.drawString("Shop",600, 280);
    }

}
