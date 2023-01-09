package Main;

import Networking.Client.SpielClient;
import Spielablauf.SpielMapManager;
import spieler.Spieler;
import menue.MenueManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class SpielPanel extends JPanel implements Runnable{

    public int zustand = 1 ;
    public final int menueZustand = 0;
    public final int spielZustand = 1;
    public final int fliesenGroesse = 32;
    public int skala;
    public int doppelteFliesenGroesse = fliesenGroesse * skala;
    public double maxBildschirmSpalte, maxBildschirmZeile;
    public int bildschirmHoehe, bildschirmBreite;

    // Welt
    public final int maxWeltSpalte = 30;
    public final int maxWeltZeile = 25;
    public final int weltBreite = doppelteFliesenGroesse * maxWeltSpalte;
    public final int weltHoehe = doppelteFliesenGroesse * maxWeltZeile;


    int FPS = 60;
    public Font marioPartyFont;
    Thread spielThread;

    public Spieler spieler = new Spieler(this);
    public MenueManager menueManager = new MenueManager(this);
    public SpielMapManager mapManager= new SpielMapManager(this);
    public SpielClient client;


    public SpielPanel(){
        client = new SpielClient();
        //client.start();
        this.zustand = 1;
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

    public void setBildschirmgroesse(){
        if(zustand == menueZustand){
            skala = 5;
            maxBildschirmSpalte = 9;
            maxBildschirmZeile = 5;
            bildschirmHoehe = (int) (fliesenGroesse * maxBildschirmZeile);
            bildschirmBreite = (int) (fliesenGroesse * maxBildschirmSpalte);
        }else if(zustand == spielZustand){
            skala = 3;
            maxBildschirmSpalte = 22.5;
            maxBildschirmZeile = 12.5;
            bildschirmHoehe = (int) (fliesenGroesse * 2 * maxBildschirmZeile);
            bildschirmBreite = (int)(fliesenGroesse * 2 * maxBildschirmSpalte);
        }
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
        menueManager.menueHintergrund.update();
        menueManager.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if(zustand == menueZustand){
            setBildschirmgroesse();
            menueManager.menueHintergrund.malen(g2);
            menueManager.malen(g2);
        }
        else if(zustand == spielZustand){
            setBildschirmgroesse();
            this.setBackground(new Color(39, 105, 195));
            this.removeKeyListener(this.getKeyListeners()[0]);
            this.addKeyListener(mapManager.mapEingabeManager);
            mapManager.malen(g2);
        }
        g2.dispose();
    }

}
