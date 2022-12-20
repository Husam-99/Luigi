package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serializable;


public class Karte extends JPanel implements Images, Serializable {
    private int wert;
    private boolean offen;
    private ImageIcon motiv;
    private final ImageIcon rueckseite = new ImageIcon("rueckseite.png");
    private JPanel panel;
    private JButton button;

    public Karte(String pfad, int wert) {


        insertImages();

        String[] pfadZeichen = pfad.split(" ");
        Integer number = Integer.valueOf(pfadZeichen[1]);


        panel = new JPanel();
        motiv = images.get(number);
        button = new JButton();


        button.setForeground(Color.darkGray);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setIcon(motiv);

        panel.add(button);
        this.add(panel);
        this.deckeZu();
        this.setzeAnklickbar(true);

    }
    public JButton getButton() {
        return this.button;
    }

    public void deckeAuf(){
        button.setIcon(motiv);
        this.offen = true;
    }
    public void deckeZu(){
        button.setIcon(rueckseite);
        this.offen = false;
    }
    public void entferne(){
        this.setEnabled(false);

    }

    public boolean istOffen(){
        return !this.offen;
    }
    public void setzeAnklickbar(boolean a){
        button.setEnabled(a);

    }

    public int getWert() {
        return wert;
    }
    public Karte getKarte(int wert){
        if(this.wert == wert)
            return this;
        return null;
    }
}
