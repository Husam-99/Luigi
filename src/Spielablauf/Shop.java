package Spielablauf;

import spieler.*;

import java.awt.*;

public class Shop {
    SpielablaufManager spielablaufManager;
    Graphics2D g2;
    Gegenstand sternTaxi, repos, bube, block, megaWuerfel, miniWuerfel;
    private int slot1Messungen,  slot2Messungen, slot3Messungen,  slot4Messungen, slot5Messungen, slot6Messungen, fehlendeMuenzen;
    public int befehlNum = 0;


    public Shop(SpielablaufManager spielablaufManager){
        this.spielablaufManager = spielablaufManager;
        sternTaxi = new SternTaxi(spielablaufManager.mainSpieler);
        repos = new Repos(spielablaufManager.mainSpieler);
        bube = new Bube(spielablaufManager.mainSpieler);
        block = new Block(spielablaufManager.mainSpieler);
        megaWuerfel = new MegaWuerfel(spielablaufManager.mainSpieler);
        miniWuerfel = new MiniWuerfel(spielablaufManager.mainSpieler);
    }
    public void gegenstandKaufen(){
        if(befehlNum == 0){
            if(sternTaxi.preis <= spielablaufManager.mainSpieler.konto.muenzen){
                spielablaufManager.mainSpieler.inventar.gegenstandBekommen(6);
                spielablaufManager.mainSpieler.konto.muenzenVerlieren(15);
                spielablaufManager.shopGeoeffnet = false;
                if(!spielablaufManager.mainSpieler.aktuellFeld.hatStern) {
                    spielablaufManager.mainSpieler.amSpiel = false;
                }else{
                    spielablaufManager.mapManager.stern.sternKaufen = true;
                }
            }else{
                spielablaufManager.mainSpieler.konto.genugMuenzen = false;
                fehlendeMuenzen = sternTaxi.preis - spielablaufManager.mainSpieler.konto.muenzen;
            }
        }else if(befehlNum == 1){
            if(repos.preis <= spielablaufManager.mainSpieler.konto.muenzen){
                spielablaufManager.mainSpieler.inventar.gegenstandBekommen(5);
                spielablaufManager.mainSpieler.konto.muenzenVerlieren(13);
                spielablaufManager.shopGeoeffnet = false;
                if(!spielablaufManager.mainSpieler.aktuellFeld.hatStern) {
                    spielablaufManager.mainSpieler.amSpiel = false;
                }else{
                    spielablaufManager.mapManager.stern.sternKaufen = true;
                }
            }else{
                spielablaufManager.mainSpieler.konto.genugMuenzen = false;
                fehlendeMuenzen = repos.preis - spielablaufManager.mainSpieler.konto.muenzen;
            }
        }else if(befehlNum == 2){
            if(bube.preis <= spielablaufManager.mainSpieler.konto.muenzen){
                spielablaufManager.mainSpieler.inventar.gegenstandBekommen(2);
                spielablaufManager.mainSpieler.konto.muenzenVerlieren(11);
                spielablaufManager.shopGeoeffnet = false;
                if(!spielablaufManager.mainSpieler.aktuellFeld.hatStern) {
                    spielablaufManager.mainSpieler.amSpiel = false;
                }else{
                    spielablaufManager.mapManager.stern.sternKaufen = true;
                }
            }else{
                spielablaufManager.mainSpieler.konto.genugMuenzen = false;
                fehlendeMuenzen = bube.preis - spielablaufManager.mainSpieler.konto.muenzen;
            }
        }else if(befehlNum == 3){
            if(block.preis <= spielablaufManager.mainSpieler.konto.muenzen){
                spielablaufManager.mainSpieler.inventar.gegenstandBekommen(1);
                spielablaufManager.mainSpieler.konto.muenzenVerlieren(7);
                spielablaufManager.shopGeoeffnet = false;
                if(!spielablaufManager.mainSpieler.aktuellFeld.hatStern) {
                    spielablaufManager.mainSpieler.amSpiel = false;
                }else{
                    spielablaufManager.mapManager.stern.sternKaufen = true;
                }
            }else{
                spielablaufManager.mainSpieler.konto.genugMuenzen = false;
                fehlendeMuenzen = block.preis - spielablaufManager.mainSpieler.konto.muenzen;
            }
        }else if(befehlNum == 4){
            if(megaWuerfel.preis <= spielablaufManager.mainSpieler.konto.muenzen){
                spielablaufManager.mainSpieler.inventar.gegenstandBekommen(3);
                spielablaufManager.mainSpieler.konto.muenzenVerlieren(5);
                spielablaufManager.shopGeoeffnet = false;
                if(!spielablaufManager.mainSpieler.aktuellFeld.hatStern) {
                    spielablaufManager.mainSpieler.amSpiel = false;
                }else{
                    spielablaufManager.mapManager.stern.sternKaufen = true;
                }
            }else{
                spielablaufManager.mainSpieler.konto.genugMuenzen = false;
                fehlendeMuenzen = megaWuerfel.preis - spielablaufManager.mainSpieler.konto.muenzen;
            }
        }else if(befehlNum == 5){
            if(miniWuerfel.preis <= spielablaufManager.mainSpieler.konto.muenzen){
                spielablaufManager.mainSpieler.inventar.gegenstandBekommen(4);
                spielablaufManager.mainSpieler.konto.muenzenVerlieren(3);
                spielablaufManager.shopGeoeffnet = false;
                if(!spielablaufManager.mainSpieler.aktuellFeld.hatStern) {
                    spielablaufManager.mainSpieler.amSpiel = false;
                }else{
                    spielablaufManager.mapManager.stern.sternKaufen = true;
                }
            }else{
                spielablaufManager.mainSpieler.konto.genugMuenzen = false;
                fehlendeMuenzen = miniWuerfel.preis - spielablaufManager.mainSpieler.konto.muenzen;
            }
        }
    }
    public void malen(Graphics2D g2){
        this.g2 = g2;
        shopBoxMalen();
        gegenstaendeMalen();
        preisMalen();
        if(!spielablaufManager.mainSpieler.konto.genugMuenzen){
            spielablaufManager.mainSpieler.konto.rueckMeldungMalen(fehlendeMuenzen);
        }
    }

    private void preisMalen(){
        g2.drawImage(spielablaufManager.mapManager.muenze.muenze1, 350, 430, spielablaufManager.sp.vergroesserteFliesenGroesse, spielablaufManager.sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(spielablaufManager.mapManager.muenze.muenze1, 650, 430, spielablaufManager.sp.vergroesserteFliesenGroesse, spielablaufManager.sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(spielablaufManager.mapManager.muenze.muenze1, 950, 430, spielablaufManager.sp.vergroesserteFliesenGroesse, spielablaufManager.sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(spielablaufManager.mapManager.muenze.muenze1, 350, 630, spielablaufManager.sp.vergroesserteFliesenGroesse, spielablaufManager.sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(spielablaufManager.mapManager.muenze.muenze1, 650, 630, spielablaufManager.sp.vergroesserteFliesenGroesse, spielablaufManager.sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(spielablaufManager.mapManager.muenze.muenze1, 950, 630, spielablaufManager.sp.vergroesserteFliesenGroesse, spielablaufManager.sp.vergroesserteFliesenGroesse, null);
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
            slot1Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2 + 40;
            slot2Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot5Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot6Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 1){
            slot1Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2 + 40;
            slot3Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot5Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot6Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 2){
            slot1Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2 + 40;
            slot4Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot5Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot6Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 3){
            slot1Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2 + 40;
            slot5Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot6Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 4){
            slot1Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot5Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2 + 40;
            slot6Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
        }else if(befehlNum == 5){
            slot1Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot2Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot3Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot4Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot5Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
            slot6Messungen = spielablaufManager.sp.vergroesserteFliesenGroesse * 2 + 40;
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
