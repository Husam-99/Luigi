package spieler;

import java.awt.*;

public class Inventar {

    Spieler s;
    int blockNum = 1, bubeNum = 2, megaWuerfelNum = 3, miniWuerfelNum = 4, reposNum = 5, sternTaxiNum = 6;
    public Gegenstand[] inventar;
    public int befehlNum = 0;
    Gegenstand sterntaxi;
    Gegenstand bube;
    Gegenstand block;
    Gegenstand repos;


    public Inventar(Spieler s){
        this.s = s;
        inventar = new Gegenstand[4];
        sterntaxi = new SternTaxi(s);
        bube = new Bube(s);
        block = new Block(s);
        repos = new Repos(s);
    }
    public void ersteSlot(int gegenstandNum){
        if(gegenstandNum == blockNum){
            inventar[0] = new Block(s);
        }else if(gegenstandNum == bubeNum){
            inventar[0] = new Bube(s);
        }else if(gegenstandNum == megaWuerfelNum){
            inventar[0] = new MegaWuerfel(s);
        }else if(gegenstandNum == miniWuerfelNum){
            inventar[0] = new MiniWuerfel(s);
        }else if(gegenstandNum == reposNum){
            inventar[0] = new Repos(s);
        }else if(gegenstandNum == sternTaxiNum){
            inventar[0] = new SternTaxi(s);
        }
    }
    public void zweiteSlot(int gegenstandNum){
        if(gegenstandNum == blockNum){
            inventar[1] = new Block(s);
        }else if(gegenstandNum == bubeNum){
            inventar[1] = new Bube(s);
        }else if(gegenstandNum == megaWuerfelNum){
            inventar[1] = new MegaWuerfel(s);
        }else if(gegenstandNum == miniWuerfelNum){
            inventar[1] = new MiniWuerfel(s);
        }else if(gegenstandNum == reposNum){
            inventar[1] = new Repos(s);
        }else if(gegenstandNum == sternTaxiNum){
            inventar[1] = new SternTaxi(s);
        }
    }
    public void dritteSlot(int gegenstandNum){
        if(gegenstandNum == blockNum){
            inventar[2] = new Block(s);
        }else if(gegenstandNum == bubeNum){
            inventar[2] = new Bube(s);
        }else if(gegenstandNum == megaWuerfelNum){
            inventar[2] = new MegaWuerfel(s);
        }else if(gegenstandNum == miniWuerfelNum){
            inventar[2] = new MiniWuerfel(s);
        }else if(gegenstandNum == reposNum){
            inventar[2] = new Repos(s);
        }else if(gegenstandNum == sternTaxiNum){
            inventar[2] = new SternTaxi(s);
        }
    }
    public void vierteSlot(int gegenstandNum){
        if(gegenstandNum == blockNum){
            inventar[3] = new Block(s);
        }else if(gegenstandNum == bubeNum){
            inventar[3] = new Bube(s);
        }else if(gegenstandNum == megaWuerfelNum){
            inventar[3] = new MegaWuerfel(s);
        }else if(gegenstandNum == miniWuerfelNum){
            inventar[3] = new MiniWuerfel(s);
        }else if(gegenstandNum == reposNum){
            inventar[3] = new Repos(s);
        }else if(gegenstandNum == sternTaxiNum){
            inventar[3] = new SternTaxi(s);
        }
    }
    public void verwenden(int slotNum){
        inventar[slotNum].effeckteAnwenden();
        inventar[slotNum] = null;
    }

    public void malen(Graphics2D g2){
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(1000, 20, 420, 400, 35, 35);
        c = new Color(255, 255, 255, 200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(1005, 25, 410, 390, 25, 25);

        if(befehlNum == 0){
            g2.drawImage(sterntaxi.icon, 1000, 20, s.sp.vergroesserteFliesenGroesse*2+40,s.sp.vergroesserteFliesenGroesse*2+40,null);
            g2.drawImage(block.icon, 1200, 20, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);
            g2.drawImage(bube.icon, 1000, 170, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);
            g2.drawImage(repos.icon, 1200, 170, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);
        }else if(befehlNum == 1){
            g2.drawImage(bube.icon, 1000, 170, s.sp.vergroesserteFliesenGroesse*2+40,s.sp.vergroesserteFliesenGroesse*2+40,null);
            g2.drawImage(sterntaxi.icon, 1000, 20, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);
            g2.drawImage(repos.icon, 1200, 170, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);
            g2.drawImage(block.icon, 1200, 20, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);
        }else if(befehlNum == 2){
            g2.drawImage(block.icon, 1200, 20, s.sp.vergroesserteFliesenGroesse*2+40,s.sp.vergroesserteFliesenGroesse*2+40,null);
            g2.drawImage(repos.icon, 1200, 170, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);
            g2.drawImage(bube.icon, 1000, 170, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);
            g2.drawImage(sterntaxi.icon, 1000, 20, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);

        } else if (befehlNum == 3) {
            g2.drawImage(repos.icon, 1200, 170, s.sp.vergroesserteFliesenGroesse*2+40,s.sp.vergroesserteFliesenGroesse*2+40,null);
            g2.drawImage(sterntaxi.icon, 1000, 20, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);
            g2.drawImage(block.icon, 1200, 20, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);
            g2.drawImage(bube.icon, 1000, 170, s.sp.vergroesserteFliesenGroesse*2,s.sp.vergroesserteFliesenGroesse*2,null);

        }
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
        g2.drawString("Inventar",1035,400);
    }

}
