package Main;

import Networking.Client.SpielClient;
import spieler.Spieler;
import menue.MenueManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class SpielPanel extends JPanel implements Runnable{
    final int originalFlieseGroesse = 32;
    final int skala = 5;
    public final int flieseGroesse = originalFlieseGroesse * skala;
    final int maxBildschirmReihe = 9;
    final int maxBildschirmSpalte = 5;

    public final int bildschirmHoehe = flieseGroesse * maxBildschirmSpalte;
    public final int bildschirmBreite = flieseGroesse * maxBildschirmReihe;

    int FPS = 60;
    public Font marioPartyFont;
    Thread spielThread;

    public Spieler spieler = new Spieler(this);
    public MenueManager menueManager = new MenueManager(this);
    public SpielClient client;


    public int Zustand;
    public final int menueZustand = 0;
    public final int spielZustand = 1;

    public SpielPanel(){
        client = new SpielClient();
        client.start();
        this.setPreferredSize(new Dimension(bildschirmBreite, bildschirmHoehe));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(menueManager.menueEingabeManager);
        this.setFocusable(true);

        try {
            InputStream is = getClass().getResourceAsStream("/font/Mario-Party-Hudson-Font.ttf");
            marioPartyFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void setClient(SpielClient client) {

        this.client = client;
    }

    public void startSpielThread(){
        spielThread = new Thread(this);
        spielThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(spielThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta > 0){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        menueManager.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        menueManager.malen(g2);

        g2.dispose();
    }

}
