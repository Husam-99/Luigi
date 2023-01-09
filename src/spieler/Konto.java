package spieler;

public class Konto {

    public int muenzen, sterne;

    public Konto(){
        muenzen = 5;
        sterne = 0;
    }

    public int getSterneAnzahl(){
        return sterne;
    }

    public int getMuenzenAnzahl(){
        return muenzen;
    }

    public void muenzenErhalten(int muenzenAnzahl){
        muenzen += muenzenAnzahl;
    }

    public void muenzenVerlieren(int muenzenAnzahl){
        muenzen -= muenzenAnzahl;
    }

    public void sterneErhalten(int sterneAnzahl){
        muenzen += sterneAnzahl;
    }

    public void sterneVerlieren(int sterneAnzahl){
        muenzen -= sterneAnzahl;
    }


}
