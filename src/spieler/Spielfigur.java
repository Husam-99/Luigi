package spieler;

public abstract class Spielfigur {
    Spieler s;
    int positionX ;
    int positionY ;

    public Spielfigur(Spieler s){
        this.s = s;
    }
    public void getSpielFigurBilder() {}
}
