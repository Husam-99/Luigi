package menue;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenueEingabeManager implements KeyListener {
    MenueManager mn;

    public MenueEingabeManager(MenueManager mn){
        this.mn = mn;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(mn.hauptmenueZustand == 0) {
            if (code == KeyEvent.VK_W) {
                mn.hauptmenue.befehlNum1--;
                if (mn.hauptmenue.befehlNum1 < 0) {
                    mn.hauptmenue.befehlNum1 = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                mn.hauptmenue.befehlNum1++;
                if (mn.hauptmenue.befehlNum1 > 2) {
                    mn.hauptmenue.befehlNum1 = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (mn.hauptmenue.befehlNum1 == 0) {
                    mn.sp.Zustand = mn.sp.spielZustand;
                    mn.hauptmenueZustand = 1;
                }
                if (mn.hauptmenue.befehlNum1 == 1) {

                }
                if (mn.hauptmenue.befehlNum1 == 2) {
                    System.exit(0);
                }
            }
        } else if(mn.hauptmenueZustand == 1) {
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
                    }
                    if (mn.hauptmenue.befehlNum2 == 1) {
                        mn.rundenAnzahl = 7;
                    }
                    if (mn.hauptmenue.befehlNum2 == 2) {
                        mn.rundenAnzahl = 8;
                    }
                    if (mn.hauptmenue.befehlNum2 == 3) {
                        mn.rundenAnzahl = 9;
                    }
                    if (mn.hauptmenue.befehlNum2 == 4) {
                        mn.rundenAnzahl = 10;
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
