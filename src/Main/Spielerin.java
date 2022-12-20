package Main;



public class Spielerin {
    public String name;
    private int punkte = 0;
    private boolean istDran;

    public Spielerin() {
        name = "";

    }

    public void setName(String name){
        this.name = name.toUpperCase();
    }

    public String gibNamen() {
        return name;
    }

    public boolean IstDran() {
        return istDran;
    }

    public void setzeDran(boolean Dran) {
        this.istDran = Dran;
    }

    public int gibPunkte() {
        return punkte;
    }

    public void erhoehePunkte() {
        this.punkte++;
    }
}
