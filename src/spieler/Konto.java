package spieler;

import java.awt.*;

import Networking.Pakete.Muenzenzahl;
import Networking.Pakete.Sternzahl;

public class Konto {

    public int muenzen, sterne;
    Graphics2D g2;
    public Spieler spieler;
    int boxHeight = 0, stautsHeight = 10;
    public Konto(Spieler spieler){
        this.spieler = spieler;
        muenzen = 10;
        sterne = 0;
    }
    public void muenzenErhalten(int muenzenAnzahl){
        muenzen += muenzenAnzahl;
        Muenzenzahl muenzenzahl = new Muenzenzahl();
        muenzenzahl.anzahlDerMuenzen = muenzenAnzahl;
        spieler.sp.client.send(muenzenzahl);

    }

    public void muenzenVerlieren(int muenzenAnzahl){
        muenzen -= muenzenAnzahl;
        Muenzenzahl muenzenzahl = new Muenzenzahl();
        muenzenzahl.anzahlDerMuenzen = - muenzenAnzahl;
        spieler.sp.client.send(muenzenzahl);


    }

    public void sterneErhalten(int sterneAnzahl){
        sterne += sterneAnzahl;
        Sternzahl sternzahl = new Sternzahl();
        sternzahl.anzahlDerSterne = sterneAnzahl;
        spieler.sp.client.send(sternzahl);
    }

    public void sterneVerlieren(int sterneAnzahl){
        sterne -= sterneAnzahl;
        Sternzahl sternzahl = new Sternzahl();
        sternzahl.anzahlDerSterne = - sterneAnzahl;
        spieler.sp.client.send(sternzahl);
    }

    public void malen(Graphics2D g2){
        this.g2 = g2;
        if(spieler == spieler.sp.mainSpieler){
            mainSpielerBoxMalen();
            mainSpielerStatusMalen();
        }else if(spieler.sp.spielerNum == 1){
            spielerStatusBoxMalen();
            spielerStatusMalen();
        }else if(spieler.sp.spielerNum == 2){
            boxHeight = 212;
            spielerStatusBoxMalen();
            stautsHeight = 225;
            spielerStatusMalen();
        }else if(spieler.sp.spielerNum == 3){
            boxHeight = 212*2;
            spielerStatusBoxMalen();
            stautsHeight = 225 + 210;
            spielerStatusMalen();
        }
    }
    private void mainSpielerStatusMalen(){
        g2.drawImage(spieler.spielfigur.profile, 1050, 584, spieler.sp.vergroesserteFliesenGroesse * 2 , spieler.sp.vergroesserteFliesenGroesse * 2, null);
        g2.drawImage(spieler.sp.mapManager.muenze.muenze1, 1235, 610, spieler.sp.vergroesserteFliesenGroesse +30, spieler.sp.vergroesserteFliesenGroesse+ 30, null);
        g2.drawImage(spieler.sp.mapManager.stern.stern, 1205, 655, spieler.sp.vergroesserteFliesenGroesse + 30, spieler.sp.vergroesserteFliesenGroesse +30, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,60F));
        g2.drawString("X",1310,660);
        g2.drawString("X",1310,737);
        String text = Integer.toString(spieler.konto.muenzen);
        g2.setColor(Color.yellow);
        g2.drawString(text,1353, 660);
        text = Integer.toString(spieler.konto.sterne);
        g2.drawString(text,1353, 737);
        if (spieler.inventar.inventar[0] != null) {
            g2.drawImage(spieler.inventar.inventar[0].icon, 1050, 758, spieler.sp.vergroesserteFliesenGroesse + 25, spieler.sp.vergroesserteFliesenGroesse+25, null);
        }
        if (spieler.inventar.inventar[1] != null) {
            g2.drawImage(spieler.inventar.inventar[1].icon, 1137, 758, spieler.sp.vergroesserteFliesenGroesse+25, spieler.sp.vergroesserteFliesenGroesse+25, null);
        }
        if (spieler.inventar.inventar[2] != null) {
            g2.drawImage(spieler.inventar.inventar[2].icon, 1230, 758, spieler.sp.vergroesserteFliesenGroesse+25, spieler.sp.vergroesserteFliesenGroesse+25, null);
        }
        if (spieler.inventar.inventar[3] != null) {
            g2.drawImage(spieler.inventar.inventar[3].icon, 1326, 758, spieler.sp.vergroesserteFliesenGroesse+25, spieler.sp.vergroesserteFliesenGroesse+25, null);
        }
    }

    private void mainSpielerBoxMalen(){
        Color c = new Color(0,0,0,150);
        g2.setColor(c);
        g2.fillRoundRect(1050, 584, 390, 190, 35, 35);
        g2.fillRoundRect(1050, 774, 390, 90, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(1055,589,380, 180, 25, 25);
        g2.drawRoundRect(1055,779,380, 80, 25, 25);
    }
    private void spielerStatusMalen(){
        g2.drawImage(spieler.spielfigur.profile, 10, stautsHeight, spieler.sp.vergroesserteFliesenGroesse+30, spieler.sp.vergroesserteFliesenGroesse+30, null);
        g2.drawImage(spieler.sp.mapManager.muenze.muenze1, 127, stautsHeight + 5, spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        g2.drawImage(spieler.sp.mapManager.stern.stern, 105, stautsHeight + 40 , spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        g2.drawString("X",180,stautsHeight + 48);
        g2.drawString("X",180,stautsHeight + 105);
        String text = Integer.toString(spieler.konto.muenzen);
        g2.setColor(Color.yellow);
        g2.drawString(text,215, stautsHeight + 47);
        text = Integer.toString(spieler.konto.sterne);
        g2.drawString(text,215, stautsHeight + 105);
        if (spieler.inventar.inventar[0] != null) {
            g2.drawImage(spieler.inventar.inventar[0].icon, 0, stautsHeight + 110, spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        }
        if (spieler.inventar.inventar[1] != null) {
            g2.drawImage(spieler.inventar.inventar[1].icon, 65, stautsHeight + 110, spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        }
        if (spieler.inventar.inventar[2] != null) {
            g2.drawImage(spieler.inventar.inventar[2].icon, 130, stautsHeight + 110, spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        }
        if (spieler.inventar.inventar[3] != null) {
            g2.drawImage(spieler.inventar.inventar[3].icon, 195, stautsHeight + 110, spieler.sp.vergroesserteFliesenGroesse, spieler.sp.vergroesserteFliesenGroesse, null);
        }
    }

    private void spielerStatusBoxMalen(){
        Color c = new Color(0,0,0,150);
        g2.setColor(c);
        g2.fillRoundRect(0, boxHeight, 285, 140, 35, 35);
        g2.fillRoundRect(0, boxHeight + 130, 285, 82, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(5,boxHeight + 5,275, 130, 25, 25);
        g2.drawRoundRect(5,boxHeight + 135,275, 72, 25, 25);
    }


}
