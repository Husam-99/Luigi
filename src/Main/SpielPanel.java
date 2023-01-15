package Main;

import Networking.Client.SpielClient;
import Spielablauf.SpielMapManager;
import spieler.*;
import menue.MenueManager;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SpielPanel extends JPanel implements Runnable{

    public int zustand;
    public final int menueZustand = 0, spielZustand = 1;
    public final int fliesenGroesse = 32;
    public final int skalaMenue = 5,     skala = 3,
            vergroesserteFliesenGroesseMenue = fliesenGroesse * skalaMenue, vergroesserteFliesenGroesse = fliesenGroesse * skala;
    public final double maxBildschirmSpalteMenue = 9,     maxBildschirmSpalte = 22.5,
            maxBildschirmZeileMenue = 5, maxBildschirmZeile = 12.5;
    public final int bildschirmHoeheMenue = (int) (vergroesserteFliesenGroesseMenue * maxBildschirmZeileMenue),
            bildschirmHoehe = (int) (fliesenGroesse * 2 * maxBildschirmZeile),
            bildschirmBreiteMenue = (int) (vergroesserteFliesenGroesseMenue * maxBildschirmSpalteMenue),
            bildschirmBreite = (int)(fliesenGroesse * 2 * maxBildschirmSpalte);

    // Welt
    public final int maxWeltSpalte = 30;
    public final int maxWeltZeile = 25;
    public final int weltBreite = vergroesserteFliesenGroesse * maxWeltSpalte;
    public final int weltHoehe = vergroesserteFliesenGroesse * maxWeltZeile;


    int FPS = 60;
    public Font marioPartyFont;
    public Clip soundClip;
    public FloatControl floatControl;
    Thread spielThread;
    public SpielMapManager mapManager= new SpielMapManager(this);
    public Spieler mainSpieler;
    public ArrayList<Spieler> alleSpieler;
    public SpielClient client;
    public MenueManager menueManager;


    public SpielPanel(){
        client = new SpielClient(this);
        client.start();
        this.zustand = 0;
        this.setPreferredSize(new Dimension(bildschirmBreite, bildschirmHoehe));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        menueManager = new MenueManager(this);
        mainSpieler = new Spieler(this);
        alleSpieler = new ArrayList<>(4);
        for(int i = 0; i < 4; i++){
            alleSpieler.add(i, new Spieler(this));
        }
        this.addKeyListener(menueManager.menueEingabeManager);
        this.setFocusable(true);

        try {
            InputStream is = getClass().getResourceAsStream("/font/Mario-Party-Hudson-Font.ttf");
            marioPartyFont = Font.createFont(Font.TRUETYPE_FONT, is);

            File soundFile = new File("src/source/sound/platformer.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            soundClip = AudioSystem.getClip();
            soundClip.open(audioInputStream);
            floatControl = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(-20f);
            soundClip.start();

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void setClient(SpielClient client) {
        this.client = client;
    }
    public void hinzufuegeSpieler(Spieler spieler, int clientIndex){
        System.out.println("hier is the clientIndex " + clientIndex);
        alleSpieler.set(clientIndex, spieler);
        for(Spieler spieler1 : alleSpieler){

            if (spieler1.spielfigur instanceof Abdo) {
                spieler1.weltY = (int) (vergroesserteFliesenGroesse * 21.5);
                spieler1.weltX = (int) (vergroesserteFliesenGroesse * 9.5);
                spieler1.bildschirmX = spieler1.weltX - mainSpieler.weltX + mainSpieler.bildschirmX;
                spieler1.bildschirmY = spieler1.weltY - mainSpieler.weltY + mainSpieler.bildschirmY;
            } else if (spieler1.spielfigur instanceof Husam) {
                spieler1.weltY = (int) (vergroesserteFliesenGroesse * 21.5);
                spieler1.weltX = (int) (vergroesserteFliesenGroesse * 10.5);
                spieler1.bildschirmX = spieler1.weltX - mainSpieler.weltX + mainSpieler.bildschirmX;
                spieler1.bildschirmY = spieler1.weltY - mainSpieler.weltY + mainSpieler.bildschirmY;
            } else if (spieler1.spielfigur instanceof Taha) {
                spieler1.weltY = (int) (vergroesserteFliesenGroesse * 21.5);
                spieler1.weltX = (int) (vergroesserteFliesenGroesse * 11.5);
                spieler1.bildschirmX = spieler1.weltX - mainSpieler.weltX + mainSpieler.bildschirmX;
                spieler1.bildschirmY = spieler1.weltY - mainSpieler.weltY + mainSpieler.bildschirmY;
            } else if (spieler1.spielfigur instanceof Yousef) {
                spieler1.weltY = (int) (vergroesserteFliesenGroesse * 21.5);
                spieler1.weltX = (int) (vergroesserteFliesenGroesse * 12.5);
                spieler1.bildschirmX = spieler1.weltX - mainSpieler.weltX + mainSpieler.bildschirmX;
                spieler1.bildschirmY = spieler1.weltY - mainSpieler.weltY + mainSpieler.bildschirmY;
            }

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
        if(zustand == menueZustand){
            menueManager.menueHintergrund.update();
            menueManager.update();
        }else if(zustand == spielZustand){
            mapManager.update();
            mainSpieler.update();
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if(zustand == menueZustand){
            menueManager.menueHintergrund.malen(g2);
            menueManager.malen(g2);
            g2.dispose();
        }else if(zustand == spielZustand){
            this.setBackground(new Color(19, 250, 19));
            this.removeKeyListener(this.getKeyListeners()[0]);
            this.addKeyListener(mapManager.mapEingabeManager);
            mapManager.malen(g2);
            if(mainSpieler.spielfigur!=null){
                if(!alleSpieler.isEmpty())
                    for(Spieler spieler : alleSpieler) {
                        if (spieler.spielfigur != null) {
                            spieler.malen(g2);
                        }
                    }
                mainSpieler.malen(g2);


            }
        }
        g2.dispose();
    }

}
