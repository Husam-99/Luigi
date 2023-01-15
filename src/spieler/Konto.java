package spieler;

import Networking.Pakete.Muenzenzahl;
import Networking.Pakete.Sternzahl;

public class Konto {

    public int muenzen, sterne;
    public Spieler spieler;

    public Konto(Spieler spieler){
        this.spieler = spieler;
        muenzen = 10;
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
        Muenzenzahl muenzenzahl = new Muenzenzahl();
        muenzenzahl.anzahlDerMuenzen = muenzenAnzahl;
        spieler.sp.client.send(muenzenzahl);

    }

    public void muenzenVerlieren(int muenzenAnzahl){
        muenzen -= muenzenAnzahl;
        Muenzenzahl muenzenzahl = new Muenzenzahl();
        muenzenzahl.anzahlDerMuenzen = - muenzenAnzahl;
        spieler.sp.client.send(muenzenzahl);


    }

    public void sterneErhalten(int sterneAnzahl){
        sterne += sterneAnzahl;
        Sternzahl sternzahl = new Sternzahl();
        sternzahl.anzahlDerSterne = sterneAnzahl;
        spieler.sp.client.send(sternzahl);
    }

    public void sterneVerlieren(int sterneAnzahl){
        sterne -= sterneAnzahl;
        Sternzahl sternzahl = new Sternzahl();
        sternzahl.anzahlDerSterne = - sterneAnzahl;
        spieler.sp.client.send(sternzahl);
    }


}
