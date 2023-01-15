package menue;

import Networking.Pakete.AnzahlMitspieler;
import Networking.Pakete.Bescheid;
import Networking.Pakete.Rundenzahl;
import Networking.Pakete.SpielfigurAuswahl;

import javax.sound.sampled.FloatControl;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenueEingabeManager implements KeyListener {

    MenueManager mn;
    public MenueEingabeManager(MenueManager mn){
        this.mn = mn;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(mn.sp.client.isIstHost()){
            if(mn.menueZustand == mn.hauptmenueZustand1) {
                hauptmenueZustand1Host(code);
            } else if(mn.menueZustand == mn.hauptmenueZustand2) {
                hauptmenueZustand2Host(code);
            } else if(mn.menueZustand == mn.spielfigurAuswaehlenZustand){
                spielfigureAuswaehlenZustandHost(code);
            }
        }else{
            if(mn.menueZustand == mn.hauptmenueZustand1) {
                hauptmenueZustand1Client(code);
            } else if(mn.menueZustand == mn.hauptmenueZustand2) {
                hauptmenueZustand2Client(code);
            } else if(mn.menueZustand == mn.spielfigurAuswaehlenZustand){
                spielfigureAuswaehlenZustandClient(code);
            }
        }
    }

    public void spielfigureAuswaehlenZustandHost(int code){
        if (code == KeyEvent.VK_W) {
            mn.spielfigurAuswaehlen.befehlNum1--;
            if (mn.spielfigurAuswaehlen.befehlNum1 < 0) {
                mn.spielfigurAuswaehlen.befehlNum1 = 3;
            }
        }
        if (code == KeyEvent.VK_S) {
            mn.spielfigurAuswaehlen.befehlNum1++;
            if (mn.spielfigurAuswaehlen.befehlNum1 > 3) {
                mn.spielfigurAuswaehlen.befehlNum1 = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (mn.spielfigurAuswaehlen.befehlNum1 == 0) {
                mn.spielfigurAuswaehlen.enterZustand = 1;
                SpielfigurAuswahl spielfigurAuswahl = new SpielfigurAuswahl();
                spielfigurAuswahl.spielfigurIndex = 0;
                mn.sp.client.send(spielfigurAuswahl);
                Bescheid bescheid = new Bescheid();
                bescheid.fertig = true;
                mn.sp.client.send(bescheid);
                mn.sp.mainSpieler.spielfigurAuswaehlen();
                mn.sp.zustand = mn.sp.spielZustand;
            }
            else if (mn.spielfigurAuswaehlen.befehlNum1 == 1) {
                mn.spielfigurAuswaehlen.enterZustand = 1;
                SpielfigurAuswahl spielfigurAuswahl = new SpielfigurAuswahl();
                spielfigurAuswahl.spielfigurIndex = 1;
                mn.sp.client.send(spielfigurAuswahl);
                Bescheid bescheid = new Bescheid();
                bescheid.fertig = true;
                mn.sp.client.send(bescheid);
                mn.sp.mainSpieler.spielfigurAuswaehlen();
                mn.sp.zustand = mn.sp.spielZustand;
            }
            else if (mn.spielfigurAuswaehlen.befehlNum1 == 2) {
                mn.spielfigurAuswaehlen.enterZustand = 1;
                SpielfigurAuswahl spielfigurAuswahl = new SpielfigurAuswahl();
                spielfigurAuswahl.spielfigurIndex = 2;
                mn.sp.client.send(spielfigurAuswahl);
                Bescheid bescheid = new Bescheid();
                bescheid.fertig = true;
                mn.sp.client.send(bescheid);
                mn.sp.mainSpieler.spielfigurAuswaehlen();
                mn.sp.zustand = mn.sp.spielZustand;
            }
            else if (mn.spielfigurAuswaehlen.befehlNum1 == 3) {
                mn.spielfigurAuswaehlen.enterZustand = 1;
                SpielfigurAuswahl spielfigurAuswahl = new SpielfigurAuswahl();
                spielfigurAuswahl.spielfigurIndex = 3;
                mn.sp.client.send(spielfigurAuswahl);
                Bescheid bescheid = new Bescheid();
                bescheid.fertig = true;
                mn.sp.client.send(bescheid);
                mn.sp.mainSpieler.spielfigurAuswaehlen();
                mn.sp.zustand = mn.sp.spielZustand;
            }
            mn.sp.soundClip.close();
        }
    }
    public void hauptmenueZustand2Host(int code){
        if(mn.hauptmenue.enterZustand == 0) {
            if (code == KeyEvent.VK_D) {
                mn.hauptmenue.befehlNum3++;
                if (mn.hauptmenue.befehlNum3 > 2) {
                    mn.hauptmenue.befehlNum3 = 0;
                }
            }
            else if (code == KeyEvent.VK_A) {
                mn.hauptmenue.befehlNum3--;
                if (mn.hauptmenue.befehlNum3 < 0) {
                    mn.hauptmenue.befehlNum3 = 2;
                }
            }
            else if (code == KeyEvent.VK_ENTER) {
                if (mn.hauptmenue.befehlNum3 == 0) {
                    mn.spielerAnzahl = 2;
                    mn.hauptmenue.enterZustand = 1;
                    mn.hauptmenue.befehlNum2 = 0;
                }
                if (mn.hauptmenue.befehlNum3 == 1) {
                    mn.spielerAnzahl = 3;
                    mn.hauptmenue.enterZustand = 1;
                    mn.hauptmenue.befehlNum2 = 0;
                }
                if (mn.hauptmenue.befehlNum3 == 2) {
                    mn.spielerAnzahl = 4;
                    mn.hauptmenue.enterZustand = 1;
                    mn.hauptmenue.befehlNum2 = 0;
                }
                AnzahlMitspieler anzahlMitspieler = new AnzahlMitspieler();
                anzahlMitspieler.anzahlDerMitspielerHost = mn.spielerAnzahl;
                mn.sp.client.send(anzahlMitspieler);
            }
        } else if (mn.hauptmenue.enterZustand == 1) {
            if (code == KeyEvent.VK_D) {
                mn.hauptmenue.befehlNum2++;
                if (mn.hauptmenue.befehlNum2 > 4) {
                    mn.hauptmenue.befehlNum2 = 0;
                }
            }
            if (code == KeyEvent.VK_A) {
                mn.hauptmenue.befehlNum2--;
                if (mn.hauptmenue.befehlNum2 < 0) {
                    mn.hauptmenue.befehlNum2 = 4;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (mn.hauptmenue.befehlNum2 == 0) {
                    mn.rundenAnzahl = 6;
                    mn.menueZustand = mn.spielfigurAuswaehlenZustand;
                }
                if (mn.hauptmenue.befehlNum2 == 1) {
                    mn.rundenAnzahl = 7;
                    mn.menueZustand = mn.spielfigurAuswaehlenZustand;
                }
                if (mn.hauptmenue.befehlNum2 == 2) {
                    mn.rundenAnzahl = 8;
                    mn.menueZustand = mn.spielfigurAuswaehlenZustand;
                }
                if (mn.hauptmenue.befehlNum2 == 3) {
                    mn.rundenAnzahl = 9;
                    mn.menueZustand = mn.spielfigurAuswaehlenZustand;
                }
                if (mn.hauptmenue.befehlNum2 == 4) {
                    mn.rundenAnzahl = 10;
                    mn.menueZustand = mn.spielfigurAuswaehlenZustand;
                }
                Rundenzahl rundenzahl = new Rundenzahl();
                rundenzahl.anzahlDerRunden = mn.rundenAnzahl;
                mn.sp.client.send(rundenzahl);
            }
        }
    }
    public void hauptmenueZustand1Host(int code){
        if (code == KeyEvent.VK_W) {
            mn.hauptmenue.befehlNum1-=2;
            if (mn.hauptmenue.befehlNum1 < 0) {
                mn.hauptmenue.befehlNum1 = 2;
            }
        }
        if (code == KeyEvent.VK_S) {
            mn.hauptmenue.befehlNum1+=2;
            if (mn.hauptmenue.befehlNum1 > 2) {
                mn.hauptmenue.befehlNum1 = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (mn.hauptmenue.befehlNum1 == 0) {
                mn.menueZustand = mn.hauptmenueZustand2;
                System.out.println("HostSpieler ist da");
            }
            if (mn.hauptmenue.befehlNum1 == 2) {
                mn.sp.client.close();
                System.exit(0);
            }
        }
    }
    public void spielfigureAuswaehlenZustandClient(int code){
        if (code == KeyEvent.VK_W) {
            mn.spielfigurAuswaehlen.befehlNum1--;
            if (mn.spielfigurAuswaehlen.befehlNum1 < 0) {
                mn.spielfigurAuswaehlen.befehlNum1 = 3;
            }
        }
        if (code == KeyEvent.VK_S) {
            mn.spielfigurAuswaehlen.befehlNum1++;
            if (mn.spielfigurAuswaehlen.befehlNum1 > 3) {
                mn.spielfigurAuswaehlen.befehlNum1 = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (mn.spielfigurAuswaehlen.befehlNum1 == 0) {
                mn.spielfigurAuswaehlen.enterZustand = 1;
                SpielfigurAuswahl spielfigurAuswahl = new SpielfigurAuswahl();
                spielfigurAuswahl.spielfigurIndex = 0;
                mn.sp.client.send(spielfigurAuswahl);
                Bescheid bescheid = new Bescheid();
                bescheid.fertig = true;
                mn.sp.client.send(bescheid);
                mn.sp.mainSpieler.spielfigurAuswaehlen();
                mn.sp.zustand = mn.sp.spielZustand;
            }
            else if (mn.spielfigurAuswaehlen.befehlNum1 == 1) {
                mn.spielfigurAuswaehlen.enterZustand = 1;
                SpielfigurAuswahl spielfigurAuswahl = new SpielfigurAuswahl();
                spielfigurAuswahl.spielfigurIndex = 1;
                mn.sp.client.send(spielfigurAuswahl);
                Bescheid bescheid = new Bescheid();
                bescheid.fertig = true;
                mn.sp.client.send(bescheid);
                mn.sp.mainSpieler.spielfigurAuswaehlen();
                mn.sp.zustand = mn.sp.spielZustand;
            }
            else if (mn.spielfigurAuswaehlen.befehlNum1 == 2) {
                mn.spielfigurAuswaehlen.enterZustand = 1;
                SpielfigurAuswahl spielfigurAuswahl = new SpielfigurAuswahl();
                spielfigurAuswahl.spielfigurIndex = 2;
                mn.sp.client.send(spielfigurAuswahl);
                Bescheid bescheid = new Bescheid();
                bescheid.fertig = true;
                mn.sp.client.send(bescheid);
                mn.sp.mainSpieler.spielfigurAuswaehlen();
                mn.sp.zustand = mn.sp.spielZustand;
            }
            else if (mn.spielfigurAuswaehlen.befehlNum1 == 3) {
                mn.spielfigurAuswaehlen.enterZustand = 1;
                SpielfigurAuswahl spielfigurAuswahl = new SpielfigurAuswahl();
                spielfigurAuswahl.spielfigurIndex = 3;
                mn.sp.client.send(spielfigurAuswahl);
                Bescheid bescheid = new Bescheid();
                bescheid.fertig = true;
                mn.sp.client.send(bescheid);
                mn.sp.mainSpieler.spielfigurAuswaehlen();
                mn.sp.zustand = mn.sp.spielZustand;
            }
            mn.sp.soundClip.close();
        }
    }
    public void hauptmenueZustand2Client(int code){
        if(mn.hauptmenue.enterZustand == 0) {
            if (code == KeyEvent.VK_D) {
                mn.hauptmenue.befehlNum3++;
                if (mn.hauptmenue.befehlNum3 > 2) {
                    mn.hauptmenue.befehlNum3 = 0;
                }
            }
            if (code == KeyEvent.VK_A) {
                mn.hauptmenue.befehlNum3--;
                if (mn.hauptmenue.befehlNum3 < 0) {
                    mn.hauptmenue.befehlNum3 = 2;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (mn.hauptmenue.befehlNum3 == 0) {
                    mn.spielerAnzahl = 2;
                    mn.hauptmenue.enterZustand = 1;
                    mn.hauptmenue.befehlNum2 = 0;
                }
                if (mn.hauptmenue.befehlNum3 == 1) {
                    mn.spielerAnzahl = 3;
                    mn.hauptmenue.enterZustand = 1;
                    mn.hauptmenue.befehlNum2 = 0;
                }
                if (mn.hauptmenue.befehlNum3 == 2) {
                    mn.spielerAnzahl = 4;
                    mn.hauptmenue.enterZustand = 1;
                    mn.hauptmenue.befehlNum2 = 0;
                }
                AnzahlMitspieler anzahl = new AnzahlMitspieler();
                anzahl.anzahlDerMitspielerHost = mn.spielerAnzahl;
                mn.sp.client.send(anzahl);
            }
        } else if (mn.hauptmenue.enterZustand == 1) {
            if (code == KeyEvent.VK_D) {
                mn.hauptmenue.befehlNum2++;
                if (mn.hauptmenue.befehlNum2 > 4) {
                    mn.hauptmenue.befehlNum2 = 0;
                }
            }
            if (code == KeyEvent.VK_A) {
                mn.hauptmenue.befehlNum2--;
                if (mn.hauptmenue.befehlNum2 < 0) {
                    mn.hauptmenue.befehlNum2 = 4;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (mn.hauptmenue.befehlNum2 == 0) {
                    mn.rundenAnzahl = 6;
                    mn.menueZustand = mn.spielfigurAuswaehlenZustand;
                }
                if (mn.hauptmenue.befehlNum2 == 1) {
                    mn.rundenAnzahl = 7;
                    mn.menueZustand = mn.spielfigurAuswaehlenZustand;
                }
                if (mn.hauptmenue.befehlNum2 == 2) {
                    mn.rundenAnzahl = 8;
                    mn.menueZustand = mn.spielfigurAuswaehlenZustand;
                }
                if (mn.hauptmenue.befehlNum2 == 3) {
                    mn.rundenAnzahl = 9;
                    mn.menueZustand = mn.spielfigurAuswaehlenZustand;
                }
                if (mn.hauptmenue.befehlNum2 == 4) {
                    mn.rundenAnzahl = 10;
                    mn.menueZustand = mn.spielfigurAuswaehlenZustand;
                }
                Rundenzahl rundenzahl = new Rundenzahl();
                rundenzahl.anzahlDerRunden = mn.rundenAnzahl;
                mn.sp.client.send(rundenzahl);
            }
        }
    }
    public void hauptmenueZustand1Client(int code){
        if (code == KeyEvent.VK_W) {
            mn.hauptmenue.befehlNum1--;
            if (mn.hauptmenue.befehlNum1 < 1) {
                mn.hauptmenue.befehlNum1 = 2;
            }
        }
        if (code == KeyEvent.VK_S) {
            mn.hauptmenue.befehlNum1++;
            if (mn.hauptmenue.befehlNum1 > 2) {
                mn.hauptmenue.befehlNum1 = 1;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (mn.hauptmenue.befehlNum1 == 1) {
                System.out.println("Spieler beigetreten");
                mn.menueZustand = mn.spielfigurAuswaehlenZustand;
            }
            if (mn.hauptmenue.befehlNum1 == 2) {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
