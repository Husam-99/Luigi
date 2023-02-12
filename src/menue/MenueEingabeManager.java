package menue;

import Networking.Client.SpielClient;
import Networking.Pakete.AnzahlMitspieler;
import Networking.Pakete.Bescheid;
import Networking.Pakete.Rundenzahl;
import Networking.Pakete.SpielfigurAuswahl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MenueEingabeManager implements KeyListener {

    MenueManager menueManager;

    public ArrayList<Integer> ausgewaehlteSpielfiguren;

    public MenueEingabeManager(MenueManager menueManager){
        this.menueManager = menueManager;
        ausgewaehlteSpielfiguren = new ArrayList<>(4);

        //temporäre Werte addieren in die Arraylist, es wird später aktualisiert
        ausgewaehlteSpielfiguren.add(-1);
        ausgewaehlteSpielfiguren.add(-1);
        ausgewaehlteSpielfiguren.add(-1);
        ausgewaehlteSpielfiguren.add(-1);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //die Tasten dürfen gedruckt werden, nur wenn der Client dran ist
        if(menueManager.sp.client.istDran()) {

            //Das Menü Verlauf für den Host
            if (menueManager.sp.client.isIstHost()) {
                if (menueManager.menueZustand == menueManager.hauptmenueZustand) {
                    hauptmenueZustandHost(code);
                } else if (menueManager.menueZustand == menueManager.SpielerUndRundenAnzahlZustand) {
                    SpielerUndRundenAnzahlZustandHost(code);
                } else if (menueManager.menueZustand == menueManager.spielfigurAuswahlZustand) {
                    spielfigurAuswahlZustandHost(code);
                }
            }

            //Das Menü Verlauf für den Client
            else{
                if (menueManager.menueZustand == menueManager.hauptmenueZustand) {
                    hauptmenueZustandClient(code);
                }else if (menueManager.menueZustand == menueManager.spielfigurAuswahlZustand) {
                    spielfigureAuswaehlenZustandClient(code);
                }
            }
        }
    }

    private void spielfigurAuswahlZustandHost(int code){

        //bewegung oben und unten in SpielfigurAuswahl Menü (Host)
        if (code == KeyEvent.VK_W) {
            menueManager.spielfigurAuswahl.befehlNum1--;
            if (menueManager.spielfigurAuswahl.befehlNum1 < 0) {
                menueManager.spielfigurAuswahl.befehlNum1 = 3;
            }
        }else if (code == KeyEvent.VK_S) {
            menueManager.spielfigurAuswahl.befehlNum1++;
            if (menueManager.spielfigurAuswahl.befehlNum1 > 3) {
                menueManager.spielfigurAuswahl.befehlNum1 = 0;
            }
        }

        //Spielfigur festlegen (Host)
        else if (code == KeyEvent.VK_ENTER) {
            SpielfigurAuswahl spielfigurAuswahl = new SpielfigurAuswahl();
            if (menueManager.spielfigurAuswahl.befehlNum1 == 0) {
                menueManager.spielfigurAuswahl.enterZustand = 1;
                spielfigurAuswahl.spielfigurIndex = 0;
                spielfigurAuswahl.spielfigurMenueIndex = 0;
            }
            else if (menueManager.spielfigurAuswahl.befehlNum1 == 1) {
                menueManager.spielfigurAuswahl.enterZustand = 1;
                spielfigurAuswahl.spielfigurIndex = 1;
                spielfigurAuswahl.spielfigurMenueIndex = 1;
            }
            else if (menueManager.spielfigurAuswahl.befehlNum1 == 2) {
                menueManager.spielfigurAuswahl.enterZustand = 1;
                spielfigurAuswahl.spielfigurIndex = 2;
                spielfigurAuswahl.spielfigurMenueIndex = 2;
            }
            else if (menueManager.spielfigurAuswahl.befehlNum1 == 3) {
                menueManager.spielfigurAuswahl.enterZustand = 1;
                spielfigurAuswahl.spielfigurIndex = 3;
                spielfigurAuswahl.spielfigurMenueIndex = 3;
            }

            //Anzahl der Spieler an allen Clients senden
            AnzahlMitspieler anzahlMitspieler = new AnzahlMitspieler();
            anzahlMitspieler.anzahlDerMitspielerHost = menueManager.spielerAnzahl;
            menueManager.sp.client.send(anzahlMitspieler);

            //Anzahl der Runden an allen Clients senden
            Rundenzahl rundenzahl = new Rundenzahl();
            rundenzahl.anzahlDerRunden = menueManager.sp.ausgewaehlteRundenAnzahl;
            menueManager.sp.client.send(rundenzahl);

            //Spielfigur in spieler erstellen
            menueManager.sp.spielablaufManager.mainSpieler.spielfigurErstellen();

            //Spieler zug ist fertig an allen Clients senden
            Bescheid bescheid = new Bescheid();
            bescheid.fertig = true;
            menueManager.sp.client.send(bescheid);

            //ausgewählte Spielfigur an allen Clients senden
            menueManager.sp.client.send(spielfigurAuswahl);

            //wechsel der Spielzustand an Spielbrettzustand
            menueManager.sp.setzeZustand(menueManager.sp.spielBrettZustand, -1);

            //Der Sound beenden
            menueManager.sp.soundClip.close();
        }

        //zurück zur Rundenanzahl
        else if(code == KeyEvent.VK_ESCAPE){
            menueManager.menueZustand = menueManager.SpielerUndRundenAnzahlZustand;
            menueManager.hauptmenue.enterZustand = 1;
            menueManager.hauptmenue.befehlNum2 = 0;
        }
    }

    private void SpielerUndRundenAnzahlZustandHost(int code){
        if(menueManager.hauptmenue.enterZustand == 0) {

            //bewegung rechts und links zwischen Spieleranzahl
            if (code == KeyEvent.VK_D) {
                menueManager.hauptmenue.befehlNum3++;
                if (menueManager.hauptmenue.befehlNum3 > 2) {
                    menueManager.hauptmenue.befehlNum3 = 0;
                }
            } else if (code == KeyEvent.VK_A) {
                menueManager.hauptmenue.befehlNum3--;
                if (menueManager.hauptmenue.befehlNum3 < 0) {
                    menueManager.hauptmenue.befehlNum3 = 2;
                }
            }

            //Spieleranzahl festlegen
            else if (code == KeyEvent.VK_ENTER) {
                if (menueManager.hauptmenue.befehlNum3 == 0) {
                    menueManager.spielerAnzahl = 2;
                    menueManager.hauptmenue.enterZustand = 1;
                    menueManager.hauptmenue.befehlNum2 = 0;
                }
                if (menueManager.hauptmenue.befehlNum3 == 1) {
                    menueManager.spielerAnzahl = 3;
                    menueManager.hauptmenue.enterZustand = 1;
                    menueManager.hauptmenue.befehlNum2 = 0;
                }
                if (menueManager.hauptmenue.befehlNum3 == 2) {
                    menueManager.spielerAnzahl = 4;
                    menueManager.hauptmenue.enterZustand = 1;
                    menueManager.hauptmenue.befehlNum2 = 0;
                }
            }

            //zurück zum Hauptmenü
            else if(code == KeyEvent.VK_ESCAPE){
                menueManager.menueZustand = menueManager.hauptmenueZustand;
            }
        } else if (menueManager.hauptmenue.enterZustand == 1) {

            //bewegung rechts und links zwischen Rundenanzahl
            if (code == KeyEvent.VK_D) {
                menueManager.hauptmenue.befehlNum2++;
                if (menueManager.hauptmenue.befehlNum2 > 4) {
                    menueManager.hauptmenue.befehlNum2 = 0;
                }
            }else if (code == KeyEvent.VK_A) {
                menueManager.hauptmenue.befehlNum2--;
                if (menueManager.hauptmenue.befehlNum2 < 0) {
                    menueManager.hauptmenue.befehlNum2 = 4;
                }
            }

            //Rundenanzahl festlegen
            else if (code == KeyEvent.VK_ENTER) {
                if (menueManager.hauptmenue.befehlNum2 == 0) {
                    menueManager.sp.ausgewaehlteRundenAnzahl = 6;
                    menueManager.menueZustand = menueManager.spielfigurAuswahlZustand;
                } else if (menueManager.hauptmenue.befehlNum2 == 1) {
                    menueManager.sp.ausgewaehlteRundenAnzahl = 7;
                    menueManager.menueZustand = menueManager.spielfigurAuswahlZustand;
                }else if (menueManager.hauptmenue.befehlNum2 == 2) {
                    menueManager.sp.ausgewaehlteRundenAnzahl = 8;
                    menueManager.menueZustand = menueManager.spielfigurAuswahlZustand;
                } else if (menueManager.hauptmenue.befehlNum2 == 3) {
                    menueManager.sp.ausgewaehlteRundenAnzahl = 9;
                    menueManager.menueZustand = menueManager.spielfigurAuswahlZustand;
                } else if (menueManager.hauptmenue.befehlNum2 == 4) {
                    menueManager.sp.ausgewaehlteRundenAnzahl = 10;
                    menueManager.menueZustand = menueManager.spielfigurAuswahlZustand;
                }
            }

            //zurück zur Spieleranzahl
            else if(code == KeyEvent.VK_ESCAPE){
                menueManager.hauptmenue.enterZustand = 0;
                menueManager.hauptmenue.befehlNum2 = -1;
            }
        }
    }

    private void hauptmenueZustandHost(int code){
        //bewegung oben und unten in Hauptmenü
        if (code == KeyEvent.VK_W) {
            menueManager.hauptmenue.befehlNum1-=2;
            if (menueManager.hauptmenue.befehlNum1 < 0) {
                menueManager.hauptmenue.befehlNum1 = 2;
            }
        }else if (code == KeyEvent.VK_S) {
            menueManager.hauptmenue.befehlNum1+=2;
            if (menueManager.hauptmenue.befehlNum1 > 2) {
                menueManager.hauptmenue.befehlNum1 = 0;
            }
        }else if (code == KeyEvent.VK_ENTER) {

            //Spiel erstellen
            if (menueManager.hauptmenue.befehlNum1 == 0) {
                menueManager.menueZustand = menueManager.SpielerUndRundenAnzahlZustand;
            }

            //Spiel verlassen
            else if (menueManager.hauptmenue.befehlNum1 == 2) {
                menueManager.sp.client.close();
                System.exit(0);
            }
        }
    }

    private void spielfigureAuswaehlenZustandClient(int code){

        //bewegung oben und unten in SpielfigurAuswahl Menü (Client)
        //es hängt von vorherige Spielfigur Auswahl und das Client-index ab
        if (code == KeyEvent.VK_W) {
            if(SpielClient.clientIndex == 1) {
                if (ausgewaehlteSpielfiguren.contains(0)) {
                    menueManager.spielfigurAuswahl.befehlNum1--;
                    if (menueManager.spielfigurAuswahl.befehlNum1 < 1) {
                        menueManager.spielfigurAuswahl.befehlNum1 = 3;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(1)){
                    if(menueManager.spielfigurAuswahl.befehlNum1 == 2){
                        menueManager.spielfigurAuswahl.befehlNum1 -= 2;
                    }else {
                        menueManager.spielfigurAuswahl.befehlNum1--;
                    }
                    if(menueManager.spielfigurAuswahl.befehlNum1 < 0){
                        menueManager.spielfigurAuswahl.befehlNum1 = 3;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(2)){
                    if(menueManager.spielfigurAuswahl.befehlNum1 == 3){
                        menueManager.spielfigurAuswahl.befehlNum1 -= 2;
                    }else {
                        menueManager.spielfigurAuswahl.befehlNum1--;
                    }
                    if(menueManager.spielfigurAuswahl.befehlNum1 < 0){
                        menueManager.spielfigurAuswahl.befehlNum1 = 3;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(3)){
                    menueManager.spielfigurAuswahl.befehlNum1--;
                    if(menueManager.spielfigurAuswahl.befehlNum1 < 0){
                        menueManager.spielfigurAuswahl.befehlNum1 = 2;
                    }
                }
            }else if(SpielClient.clientIndex == 2){
                if(ausgewaehlteSpielfiguren.contains(0) && ausgewaehlteSpielfiguren.contains(1)){
                    menueManager.spielfigurAuswahl.befehlNum1--;
                    if (menueManager.spielfigurAuswahl.befehlNum1 < 2) {
                        menueManager.spielfigurAuswahl.befehlNum1 = 3;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(0) && ausgewaehlteSpielfiguren.contains(2)){
                    menueManager.spielfigurAuswahl.befehlNum1-=2;
                    if (menueManager.spielfigurAuswahl.befehlNum1 < 1) {
                        menueManager.spielfigurAuswahl.befehlNum1 = 3;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(0) && ausgewaehlteSpielfiguren.contains(3)){
                    menueManager.spielfigurAuswahl.befehlNum1--;
                    if (menueManager.spielfigurAuswahl.befehlNum1 < 1) {
                        menueManager.spielfigurAuswahl.befehlNum1 = 2;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(1) && ausgewaehlteSpielfiguren.contains(2)){
                    menueManager.spielfigurAuswahl.befehlNum1-=3;
                    if(menueManager.spielfigurAuswahl.befehlNum1 < 0){
                        menueManager.spielfigurAuswahl.befehlNum1 = 3;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(1) && ausgewaehlteSpielfiguren.contains(3)){
                    menueManager.spielfigurAuswahl.befehlNum1-=2;
                    if(menueManager.spielfigurAuswahl.befehlNum1 < 0){
                        menueManager.spielfigurAuswahl.befehlNum1 = 3;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(2) && ausgewaehlteSpielfiguren.contains(3)){
                    menueManager.spielfigurAuswahl.befehlNum1--;
                    if(menueManager.spielfigurAuswahl.befehlNum1 < 0){
                        menueManager.spielfigurAuswahl.befehlNum1 = 1;
                    }
                }
            }
        } else if (code == KeyEvent.VK_S) {
            if(SpielClient.clientIndex == 1) {
                if(ausgewaehlteSpielfiguren.contains(0)) {
                    menueManager.spielfigurAuswahl.befehlNum1++;
                    if (menueManager.spielfigurAuswahl.befehlNum1 > 3) {
                        menueManager.spielfigurAuswahl.befehlNum1 = 1;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(1)){
                    if(menueManager.spielfigurAuswahl.befehlNum1 == 0){
                        menueManager.spielfigurAuswahl.befehlNum1 += 2;
                    }else {
                        menueManager.spielfigurAuswahl.befehlNum1++;
                    }
                    if(menueManager.spielfigurAuswahl.befehlNum1 > 3){
                        menueManager.spielfigurAuswahl.befehlNum1 = 0;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(2)){
                    if(menueManager.spielfigurAuswahl.befehlNum1 == 1){
                        menueManager.spielfigurAuswahl.befehlNum1 += 2;
                    }else {
                        menueManager.spielfigurAuswahl.befehlNum1++;
                    }
                    if(menueManager.spielfigurAuswahl.befehlNum1 > 3){
                        menueManager.spielfigurAuswahl.befehlNum1 = 0;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(3)) {
                    menueManager.spielfigurAuswahl.befehlNum1++;
                    if (menueManager.spielfigurAuswahl.befehlNum1 > 2) {
                        menueManager.spielfigurAuswahl.befehlNum1 = 0;
                    }
                }
            }else if(SpielClient.clientIndex == 2){
                if(ausgewaehlteSpielfiguren.contains(0) && ausgewaehlteSpielfiguren.contains(1)){
                    menueManager.spielfigurAuswahl.befehlNum1++;
                    if (menueManager.spielfigurAuswahl.befehlNum1 > 3) {
                        menueManager.spielfigurAuswahl.befehlNum1 = 2;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(0) && ausgewaehlteSpielfiguren.contains(2)){
                    menueManager.spielfigurAuswahl.befehlNum1+=2;
                    if (menueManager.spielfigurAuswahl.befehlNum1 > 3) {
                        menueManager.spielfigurAuswahl.befehlNum1 = 1;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(0) && ausgewaehlteSpielfiguren.contains(3)){
                    menueManager.spielfigurAuswahl.befehlNum1++;
                    if (menueManager.spielfigurAuswahl.befehlNum1 > 2) {
                        menueManager.spielfigurAuswahl.befehlNum1 = 1;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(1) && ausgewaehlteSpielfiguren.contains(2)){
                    menueManager.spielfigurAuswahl.befehlNum1+=3;
                    if(menueManager.spielfigurAuswahl.befehlNum1 > 3){
                        menueManager.spielfigurAuswahl.befehlNum1 = 0;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(1) && ausgewaehlteSpielfiguren.contains(3)){
                    menueManager.spielfigurAuswahl.befehlNum1+=2;
                    if(menueManager.spielfigurAuswahl.befehlNum1 > 3){
                        menueManager.spielfigurAuswahl.befehlNum1 = 0;
                    }
                }else if(ausgewaehlteSpielfiguren.contains(2) && ausgewaehlteSpielfiguren.contains(3)){
                    menueManager.spielfigurAuswahl.befehlNum1++;
                    if(menueManager.spielfigurAuswahl.befehlNum1 > 1){
                        menueManager.spielfigurAuswahl.befehlNum1 = 0;
                    }
                }
            }
        }

        //Spielfigur festlegen
        else if (code == KeyEvent.VK_ENTER) {
            SpielfigurAuswahl spielfigurAuswahl = new SpielfigurAuswahl();
            if (menueManager.spielfigurAuswahl.befehlNum1 == 0) {
                menueManager.spielfigurAuswahl.enterZustand = 1;
                spielfigurAuswahl.spielfigurIndex = 0;
            }
            else if (menueManager.spielfigurAuswahl.befehlNum1 == 1) {
                menueManager.spielfigurAuswahl.enterZustand = 1;
                spielfigurAuswahl.spielfigurIndex = 1;
            }
            else if (menueManager.spielfigurAuswahl.befehlNum1 == 2) {
                menueManager.spielfigurAuswahl.enterZustand = 1;
                spielfigurAuswahl.spielfigurIndex = 2;
            }
            else if (menueManager.spielfigurAuswahl.befehlNum1 == 3) {
                menueManager.spielfigurAuswahl.enterZustand = 1;
                spielfigurAuswahl.spielfigurIndex = 3;
            }

            //Spielfigur in spieler erstellen.
            menueManager.sp.spielablaufManager.mainSpieler.spielfigurErstellen();

            //Spieler zug ist fertig an allen Clients senden
            Bescheid bescheid = new Bescheid();
            bescheid.fertig = true;
            menueManager.sp.client.send(bescheid);

            //ausgewählte Spielfigur an allen Clients senden
            menueManager.sp.client.send(spielfigurAuswahl);

            //wechsel der Spielzustand an Spielbrettzustand
            menueManager.sp.setzeZustand(menueManager.sp.spielBrettZustand, -1);

            //Der Sound beenden
            menueManager.sp.soundClip.close();
        }
    }

    private void hauptmenueZustandClient(int code){

        //bewegung oben und unten in Hauptmenü
        if (code == KeyEvent.VK_W) {
            menueManager.hauptmenue.befehlNum1--;
            if (menueManager.hauptmenue.befehlNum1 < 1) {
                menueManager.hauptmenue.befehlNum1 = 2;
            }
        }else if (code == KeyEvent.VK_S) {
            menueManager.hauptmenue.befehlNum1++;
            if (menueManager.hauptmenue.befehlNum1 > 2) {
                menueManager.hauptmenue.befehlNum1 = 1;
            }
        }else if (code == KeyEvent.VK_ENTER) {

            //Spiel beitreten
            if (menueManager.hauptmenue.befehlNum1 == 1) {
                menueManager.menueZustand = menueManager.spielfigurAuswahlZustand;
            }

            //Spiel verlassen
            if (menueManager.hauptmenue.befehlNum1 == 2) {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
