package Spielablauf;

import Main.SpielPanel;
import spieler.Spieler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SiegerKueren {
    SpielPanel sp;
    Graphics2D g2;
    BufferedImage goldeneKrone, silberneKrone, bronzeneKrone;
    ArrayList<Spieler> alleSpieler, alleSpielerSortiert;
    int spriteZaehler = 0, spriteNum = 1 ;
    int spielerY = 250, spieler1X = 1210, spieler2X = 1210, spieler3X = 1210, spieler4X = 1210;
    int ziel1X, ziel2X, ziel3X, ziel4X;
    String richtungSpieler1, richtungSpieler2, richtungSpieler3, richtungSpieler4;


    public SiegerKueren(SpielPanel sp){
        this.sp = sp;
        alleSpielerSortiert = new ArrayList<>();
        alleSpieler = new ArrayList<>();
        getKroneBilder();
        this.ziel1X = 410;
        this.ziel2X = ziel1X + 310;
        if (sp.client.anzahlSpieler == 3) {
            this.ziel1X = 255;
            this.ziel2X = ziel1X + 310;
         }else if (sp.client.anzahlSpieler == 4) {
            this.ziel1X = 100;
            this.ziel2X = ziel1X + 310;
            this.ziel3X = ziel2X + 310;
            this.ziel4X = ziel3X + 310;
        }
        this.richtungSpieler1 = "links";
        this.richtungSpieler2 = "links";
        this.richtungSpieler3 = "links";
        this.richtungSpieler4 = "links";
        for(Spieler spieler : sp.alleSpieler){
            if(spieler.spielfigur != null) {
                    alleSpieler.add(spieler);
            }
        }
        alleSpieler.add(sp.spielablaufManager.mainSpieler);
        positionierung();
        alleSpielerSortieren();
    }


    private void getKroneBilder(){
        try {
            goldeneKrone = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/Golden_crown.png")));
            silberneKrone = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/Silver_crown.png")));
            bronzeneKrone = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/Bronze_crown.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    private void alleSpielerSortieren(){
        for(int position = 1; position <= sp.client.anzahlSpieler; position++){
            for(Spieler spieler : alleSpieler) {
                if (spieler.position == position) {
                    alleSpielerSortiert.add(spieler);
                }
            }
        }
    }

    private void positionierung() {
        int position = 1;
        for(int temp = 0; temp < sp.client.anzahlSpieler; temp++) {
            int maxSterneAnzahl = -1;
            int maxMuenzenAnzahl = -1;
            Spieler ersteSiegerSpieler = null;
            Spieler zweiteSiegerSpieler = null;
            Spieler dritteSiegerSpieler = null;
            Spieler vierteSigerSpieler = null;
            for (Spieler spieler : alleSpieler) {
                if (spieler.position == 0) {
                    if (maxSterneAnzahl < spieler.konto.sterne) {
                        maxSterneAnzahl = spieler.konto.sterne;
                        maxMuenzenAnzahl = spieler.konto.muenzen;
                        ersteSiegerSpieler = spieler;
                        zweiteSiegerSpieler = null;
                        dritteSiegerSpieler = null;
                        vierteSigerSpieler = null;
                    } else if (maxSterneAnzahl == spieler.konto.sterne) {
                        if (maxMuenzenAnzahl < spieler.konto.muenzen) {
                            maxMuenzenAnzahl = spieler.konto.muenzen;
                            ersteSiegerSpieler = spieler;
                            zweiteSiegerSpieler = null;
                            dritteSiegerSpieler = null;
                            vierteSigerSpieler = null;
                        } else if (maxMuenzenAnzahl == spieler.konto.muenzen) {
                            if (zweiteSiegerSpieler == null) {
                                zweiteSiegerSpieler = spieler;
                                dritteSiegerSpieler = null;
                                vierteSigerSpieler = null;
                            } else if (dritteSiegerSpieler == null) {
                                dritteSiegerSpieler = spieler;
                                vierteSigerSpieler = null;
                            } else if (vierteSigerSpieler == null) {
                                vierteSigerSpieler = spieler;
                            }
                        }
                    }
                }
            }
            if (ersteSiegerSpieler != null) {
                ersteSiegerSpieler.position = position;
            }
            if (zweiteSiegerSpieler != null) {
                zweiteSiegerSpieler.position = position;
            }
            if (dritteSiegerSpieler != null) {
                dritteSiegerSpieler.position = position;
            }
            if (vierteSigerSpieler != null) {
                vierteSigerSpieler.position = position;
            }
            position++;
            if(zweiteSiegerSpieler != null){
                position++;
            }
            if(dritteSiegerSpieler != null){
                position++;
            }
            if(vierteSigerSpieler != null){
                position++;
            }
        }
    }

    public void update() {
        if (spieler1X > ziel1X) {
            richtungSpieler1 = "links";
            spieler1X -= 5;
        } else if (spieler1X == ziel1X) {
            richtungSpieler1 = "stehen";
        }
        if (spieler1X < 810) {
            if (spieler2X > ziel2X) {
                richtungSpieler2 = "links";
                spieler2X -= 5;
            } else if (spieler2X == ziel2X) {
                richtungSpieler2 = "stehen";
            }
        }
        if (spieler2X < 810) {
            if (spieler3X > ziel3X) {
                richtungSpieler3 = "links";
                spieler3X -= 5;
            } else if (spieler3X == ziel3X) {
                richtungSpieler3 = "stehen";
            }
        }
        if (spieler3X < 810) {
            if (spieler4X > ziel4X) {
                richtungSpieler4 = "links";
                spieler4X -= 5;
            } else if (spieler4X == ziel4X) {
                richtungSpieler4 = "stehen";
            }
        }
        bilderUpdate();
    }

    private void bilderUpdate(){
        spriteZaehler++;
        if (spriteZaehler > 20) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 1;
            }
            spriteZaehler = 0;
        }
    }
    public void malen(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(sp.marioPartyFont);
        hinteBoxMalen();
        spielerMalen();
    }

    private void spielerMalen() {
        BufferedImage image1 = null, image2 = null, image3 = null, image4 = null;
        int temp2 = 0;
        for (Spieler spieler : alleSpielerSortiert) {
            if (temp2 == 0) {
                if (Objects.equals(richtungSpieler1, "links")) {
                    if (spriteNum == 1 || spriteNum == 3) {
                        image1 = spieler.spielfigur.left1;
                    } else if (spriteNum == 2) {
                        image1 = spieler.spielfigur.left2;
                    } else if (spriteNum == 4) {
                        image1 = spieler.spielfigur.left3;
                    }
                }else if (Objects.equals(richtungSpieler1, "stehen")) {
                    image1 = spieler.spielfigur.down1;
                }
                g2.drawImage(image1, spieler1X, spielerY, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10, null);
                if(spieler1X == ziel1X) {
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
                    g2.setColor(Color.yellow);
                    String positionString = Integer.toString(spieler.position);
                    g2.drawString(positionString, ziel1X + 90, 700);
                    if (spieler.position == 1) {
                        g2.drawString("st", ziel1X + 145, 700);
                        g2.drawImage(goldeneKrone, spieler1X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                    } else if (spieler.position == 2) {
                        g2.drawString("nd", ziel1X + 150, 700);
                        g2.drawImage(silberneKrone, spieler1X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                    } else if (spieler.position == 3) {
                        g2.drawString("rd", ziel1X + 150, 700);
                        g2.drawImage(bronzeneKrone, spieler1X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                    } else if (spieler.position == 4) {
                        g2.drawString("th", ziel1X + 150, 700);
                    }
                }
            } else if (temp2 == 1) {
                if(spieler1X < 810) {
                    if (Objects.equals(richtungSpieler2, "links")) {
                        if (spriteNum == 1 || spriteNum == 3) {
                            image2 = spieler.spielfigur.left1;
                        } else if (spriteNum == 2) {
                            image2 = spieler.spielfigur.left2;
                        } else if (spriteNum == 4) {
                            image2 = spieler.spielfigur.left3;
                        }
                    }else if (Objects.equals(richtungSpieler2, "stehen")) {
                        image2 = spieler.spielfigur.down1;
                    }
                    g2.drawImage(image2, spieler2X, spielerY, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10, null);
                    if(spieler2X == ziel2X) {
                        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
                        g2.setColor(Color.yellow);
                        String positionString = Integer.toString(spieler.position);
                        g2.drawString(positionString, ziel2X + 90, 700);
                        if (spieler.position == 1) {
                            g2.drawString("st", ziel2X + 145, 700);
                            g2.drawImage(goldeneKrone, spieler2X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                        } else if (spieler.position == 2) {
                            g2.drawString("nd", ziel2X + 150, 700);
                            g2.drawImage(silberneKrone, spieler2X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                        } else if (spieler.position == 3) {
                            g2.drawString("rd", ziel2X + 150, 700);
                            g2.drawImage(bronzeneKrone, spieler2X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                        } else if (spieler.position == 4) {
                            g2.drawString("th", ziel2X + 150, 700);
                        }
                    }
                }
            } else if (temp2 == 2) {
                if (spieler2X < 810) {
                    if (Objects.equals(richtungSpieler3, "links")) {
                        if (spriteNum == 1 || spriteNum == 3) {
                            image3 = spieler.spielfigur.left1;
                        } else if (spriteNum == 2) {
                            image3 = spieler.spielfigur.left2;
                        } else if (spriteNum == 4) {
                            image3 = spieler.spielfigur.left3;
                        }
                    }else if (Objects.equals(richtungSpieler3, "stehen")) {
                        image3 = spieler.spielfigur.down1;
                    }
                    g2.drawImage(image3, spieler3X, spielerY, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10, null);
                    if(spieler3X == ziel3X) {
                        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
                        g2.setColor(Color.yellow);
                        String positionString = Integer.toString(spieler.position);
                        g2.drawString(positionString, ziel3X + 90, 700);
                        if (spieler.position == 1) {
                            g2.drawString("st", ziel3X + 145, 700);
                            g2.drawImage(goldeneKrone, spieler3X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                        } else if (spieler.position == 2) {
                            g2.drawString("nd", ziel3X + 150, 700);
                            g2.drawImage(silberneKrone, spieler3X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                        } else if (spieler.position == 3) {
                            g2.drawString("rd", ziel3X + 150, 700);
                            g2.drawImage(bronzeneKrone, spieler3X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                        } else if (spieler.position == 4) {
                            g2.drawString("th", ziel3X + 150, 700);
                        }
                    }
                }
            } else if (temp2 == 3) {
                if (spieler3X < 810) {
                    if (Objects.equals(richtungSpieler4, "links")) {
                        if (spriteNum == 1 || spriteNum == 3) {
                            image4 = spieler.spielfigur.left1;
                        } else if (spriteNum == 2) {
                            image4 = spieler.spielfigur.left2;
                        } else if (spriteNum == 4) {
                            image4 = spieler.spielfigur.left3;
                        }
                    }else if (Objects.equals(richtungSpieler4, "stehen")) {
                        image4 = spieler.spielfigur.down1;
                    }
                    g2.drawImage(image4, spieler4X, spielerY, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10, null);
                    if(spieler4X == ziel4X) {
                        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
                        g2.setColor(Color.yellow);
                        String positionString = Integer.toString(spieler.position);
                        g2.drawString(positionString, ziel4X + 90, 700);
                        if (spieler.position == 1) {
                            g2.drawString("st", ziel4X + 145, 700);
                            g2.drawImage(goldeneKrone, spieler4X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                        } else if (spieler.position == 2) {
                            g2.drawString("nd", ziel4X + 150, 700);
                            g2.drawImage(silberneKrone, spieler4X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                        } else if (spieler.position == 3) {
                            g2.drawString("rd", ziel4X + 150, 700);
                            g2.drawImage(bronzeneKrone, spieler4X, spielerY - 120, sp.fliesenGroesse * 10, sp.fliesenGroesse * 10,null);
                        } else if (spieler.position == 4) {
                            g2.drawString("th", ziel4X + 150, 700);
                        }
                    }
                }
            }
            temp2++;
        }
    }

    private void hinteBoxMalen(){
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(100, 100, 1240, 664, 35, 35);
        c = new Color(255,255,255,200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(105,105,1230, 654, 25, 25);
    }

}
