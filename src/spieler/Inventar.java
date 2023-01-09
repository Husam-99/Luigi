package spieler;

public class Inventar {

    Spieler s;
    int blockNum = 1, bubeNum = 2, megaWuerfelNum = 3, miniWuerfelNum = 4, reposNum = 5, sternTaxiNum = 6;
    public Gegenstand[] inventar;
    public Inventar(Spieler s){
        this.s = s;
        inventar = new Gegenstand[4];
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

}
