package spieler;

public abstract class Gegenstand {
    Spieler s;
    int positionX ;
    int positionY ;

    public Gegenstand(Spieler s){
        this.s = s;
    }

    public void getGegenstandBilder(){}
}
