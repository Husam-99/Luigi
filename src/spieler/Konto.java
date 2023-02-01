package spieler;

import java.awt.*;

import Networking.Pakete.Muenzenzahl;
import Networking.Pakete.Sternzahl;

public class Konto {
    public int muenzen, sterne;
    Graphics2D g2;
    public Spieler spieler;
    int boxHeight = 0, stautsHeight = 10;
    public boolean genugMuenzen = true, genugSterne = true;
    public Konto(Spieler spieler){
        this.spieler = spieler;
        muenzen = 0;
        sterne = 0;
    }
    public void muenzenErhalten(int muenzenAnzahl){
        muenzen += muenzenAnzahl;
        Muenzenzahl muenzenzahl = new Muenzenzahl();
        muenzenzahl.anzahlDerMuenzen = muenzenAnzahl;
        spieler.spielablaufManager.sp.client.send(muenzenzahl);

    }

    public void muenzenVerlieren(int muenzenAnzahl){
        if(muenzen >= muenzenAnzahl) {
            muenzen -= muenzenAnzahl;
            Muenzenzahl muenzenzahl = new Muenzenzahl();
            muenzenzahl.anzahlDerMuenzen = -muenzenAnzahl;
            spieler.spielablaufManager.sp.client.send(muenzenzahl);
        }
    }

    public void sterneErhalten(int sterneAnzahl){
        sterne += sterneAnzahl;
        Sternzahl sternzahl = new Sternzahl();
        sternzahl.anzahlDerSterne = sterneAnzahl;
        spieler.spielablaufManager.sp.client.send(sternzahl);
    }

    public void sterneVerlieren(int sterneAnzahl){
        if(sterne >= sterneAnzahl) {
            sterne -= sterneAnzahl;
            Sternzahl sternzahl = new Sternzahl();
            sternzahl.anzahlDerSterne = -sterneAnzahl;
            spieler.spielablaufManager.sp.client.send(sternzahl);
        }
    }

    public void malen(Graphics2D g2){
        this.g2 = g2;
        if(spieler == spieler.spielablaufManager.mainSpieler){
            mainSpielerBoxMalen();
            mainSpielerStatusMalen();
        }else if(spieler.spielablaufManager.spielerNum == 1){
            spielerStatusBoxMalen();
            spielerStatusMalen();
        }else if(spieler.spielablaufManager.spielerNum == 2){
            boxHeight = 212;
            spielerStatusBoxMalen();
            stautsHeight = 225;
            spielerStatusMalen();
        }else if(spieler.spielablaufManager.spielerNum == 3){
            boxHeight = 212*2;
            spielerStatusBoxMalen();
            stautsHeight = 225 + 210;
            spielerStatusMalen();
        }
    }
    private void mainSpielerStatusMalen(){
        g2.drawImage(spieler.spielfigur.profile, 1050, 584, 96 * 2 , 96 * 2, null);
        g2.drawImage(spieler.spielablaufManager.mapManager.muenze.muenze1, 1235, 610, 96 +30, 96+ 30, null);
        g2.drawImage(spieler.spielablaufManager.mapManager.stern.stern, 1205, 655, 96 + 30, 96 +30, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,60F));
        g2.drawString("X",1310,660);
        g2.drawString("X",1310,737);
        String text = Integer.toString(spieler.konto.muenzen);
        g2.setColor(Color.yellow);
        g2.drawString(text,1353, 660);
        text = Integer.toString(spieler.konto.sterne);
        g2.drawString(text,1353, 737);
        if (spieler.inventar.inventar[0] != null) {
            g2.drawImage(spieler.inventar.inventar[0].icon, 1050, 758, 96 + 25, 96+25, null);
        }
        if (spieler.inventar.inventar[1] != null) {
            g2.drawImage(spieler.inventar.inventar[1].icon, 1137, 758, 96+25, 96+25, null);
        }
        if (spieler.inventar.inventar[2] != null) {
            g2.drawImage(spieler.inventar.inventar[2].icon, 1230, 758, 96+25, 96+25, null);
        }
        if (spieler.inventar.inventar[3] != null) {
            g2.drawImage(spieler.inventar.inventar[3].icon, 1326, 758, 96+25, 96+25, null);
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
        g2.drawImage(spieler.spielfigur.profile, 10, stautsHeight, 96+30, 96+30, null);
        g2.drawImage(spieler.spielablaufManager.mapManager.muenze.muenze1, 127, stautsHeight + 5, 96, 96, null);
        g2.drawImage(spieler.spielablaufManager.mapManager.stern.stern, 105, stautsHeight + 40 , 96, 96, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        g2.drawString("X",180,stautsHeight + 48);
        g2.drawString("X",180,stautsHeight + 105);
        String text = Integer.toString(spieler.konto.muenzen);
        g2.setColor(Color.yellow);
        g2.drawString(text,215, stautsHeight + 47);
        text = Integer.toString(spieler.konto.sterne);
        g2.drawString(text,215, stautsHeight + 105);
        if (spieler.inventar.inventar[0] != null) {
            g2.drawImage(spieler.inventar.inventar[0].icon, 0, stautsHeight + 110, 96, 96, null);
        }
        if (spieler.inventar.inventar[1] != null) {
            g2.drawImage(spieler.inventar.inventar[1].icon, 65, stautsHeight + 110, 96, 96, null);
        }
        if (spieler.inventar.inventar[2] != null) {
            g2.drawImage(spieler.inventar.inventar[2].icon, 130, stautsHeight + 110, 96, 96, null);
        }
        if (spieler.inventar.inventar[3] != null) {
            g2.drawImage(spieler.inventar.inventar[3].icon, 195, stautsHeight + 110, 96, 96, null);
        }
    }

    private void spielerStatusBoxMalen(){
        Color c = new Color(0,0,0,150);
        g2.setColor(c);
        g2.fillRoundRect(0, boxHeight, 290, 140, 35, 35);
        g2.fillRoundRect(0, boxHeight + 130, 290, 82, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(5,boxHeight + 5,280, 130, 25, 25);
        g2.drawRoundRect(5,boxHeight + 135,280, 72, 25, 25);
    }

    public void muenzeRueckMeldungMalen(int fehlendeMuenzen){
        Color c = new Color(0,0,0,100);
        g2.setColor(c);
        g2.fillRect(0, 0, 1440, 864);
        g2.setColor(Color.black);
        g2.fillRoundRect(330, 400, 780, 100, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(335,405,770, 90, 25, 25);
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
        g2.drawString("noch",370, 470);
        g2.setColor(Color.red);
        String fehlendeMuenzenStr = Integer.toString(fehlendeMuenzen);
        if(fehlendeMuenzen >= 10) {
            g2.drawString(fehlendeMuenzenStr, 520, 470);
        }else{
            g2.drawString(fehlendeMuenzenStr, 535, 470);
        }
        g2.setColor(Color.yellow);
        g2.drawString("Münzen fehlen!",620, 470);

    }

    public void baluesFeldRueckMeldungMalen(){
        Color c = new Color(0,0,0,100);
        g2.setColor(c);
        g2.fillRect(0, 0, 1440, 864);
        g2.setColor(Color.black);
        g2.fillRoundRect(348, 350, 750, 100, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(353,355,740, 90, 25, 25);
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
        if(!spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne) {
            g2.drawString("nicht genug Sterne!", 420, 420);
        }else if(!spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen){
            g2.drawString("nicht genug Meunzen!", 415, 420);
        }else if(spieler.spielablaufManager.tempBlauesFeld.inventarLeer){
            g2.drawString("Inventar ist Leer!", 425, 420);
        }
    }


}
