package Main;

import Minispiele.MinispielManager;
import Networking.Client.SpielClient;

import Spielablauf.SpielablaufManager;
import spieler.*;
import menue.MenueManager;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

public class SpielPanel extends JPanel implements Runnable{

    public JFrame window;
    public int zustand;
    public final int menueZustand = 0, spielBrettZustand = 1, minispielZustand = 2;
    public final int fliesenGroesse = 32;
    public int skalaMenue = 5,     skala = 3,
            vergroesserteFliesenGroesseMenue = fliesenGroesse * skalaMenue, vergroesserteFliesenGroesse = fliesenGroesse * skala;
    public final int maxBildschirmSpalteMenue = 9,     maxBildschirmSpalte = 15,
            maxBildschirmZeileMenue = 5, maxBildschirmZeile = 9;
    public final int bildschirmHoeheMenue = vergroesserteFliesenGroesseMenue * maxBildschirmZeileMenue,
            bildschirmHoehe = vergroesserteFliesenGroesse * maxBildschirmZeile,
            bildschirmBreiteMenue = vergroesserteFliesenGroesseMenue * maxBildschirmSpalteMenue,
            bildschirmBreite = vergroesserteFliesenGroesse * maxBildschirmSpalte;

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
    public ArrayList<Spieler> alleSpieler;
    public SpielClient client;
    public MenueManager menueManager;
    public SpielablaufManager spielablaufManager;
    public MinispielManager minispielManager;



    public SpielPanel(JFrame window){
        this.window = window;
        client = new SpielClient(this);
        client.start();
        this.zustand = 0;
        this.setPreferredSize(new Dimension(bildschirmBreite, bildschirmHoehe));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        menueManager = new MenueManager(this);
        spielablaufManager = new SpielablaufManager(this);
        alleSpieler = new ArrayList<>(4);
        for(int i = 0; i < 4; i++){
            alleSpieler.add(i, new Spieler(spielablaufManager));
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
    public void hinzufuegeSpieler(Spieler spieler, int clientIndex){
        System.out.println("hier is the clientIndex " + clientIndex);
        alleSpieler.set(clientIndex, spieler);
        for(Spieler spieler1 : alleSpieler){

            if (spieler1.spielfigur instanceof Abdo) {
                spieler1.weltY = (int) (vergroesserteFliesenGroesse * 21.5);
                spieler1.weltX = (int) (vergroesserteFliesenGroesse * 9.5);
                spieler1.bildschirmX = spieler1.weltX - spielablaufManager.mainSpieler.weltX + spielablaufManager.mainSpieler.bildschirmX;
                spieler1.bildschirmY = spieler1.weltY - spielablaufManager.mainSpieler.weltY + spielablaufManager.mainSpieler.bildschirmY;
            } else if (spieler1.spielfigur instanceof Husam) {
                spieler1.weltY = (int) (vergroesserteFliesenGroesse * 21.5);
                spieler1.weltX = (int) (vergroesserteFliesenGroesse * 10.5);
                spieler1.bildschirmX = spieler1.weltX - spielablaufManager.mainSpieler.weltX + spielablaufManager.mainSpieler.bildschirmX;
                spieler1.bildschirmY = spieler1.weltY - spielablaufManager.mainSpieler.weltY + spielablaufManager.mainSpieler.bildschirmY;
            } else if (spieler1.spielfigur instanceof Taha) {
                spieler1.weltY = (int) (vergroesserteFliesenGroesse * 21.5);
                spieler1.weltX = (int) (vergroesserteFliesenGroesse * 11.5);
                spieler1.bildschirmX = spieler1.weltX - spielablaufManager.mainSpieler.weltX + spielablaufManager.mainSpieler.bildschirmX;
                spieler1.bildschirmY = spieler1.weltY - spielablaufManager.mainSpieler.weltY + spielablaufManager.mainSpieler.bildschirmY;
            } else if (spieler1.spielfigur instanceof Yousef) {
                spieler1.weltY = (int) (vergroesserteFliesenGroesse * 21.5);
                spieler1.weltX = (int) (vergroesserteFliesenGroesse * 12.5);
                spieler1.bildschirmX = spieler1.weltX - spielablaufManager.mainSpieler.weltX + spielablaufManager.mainSpieler.bildschirmX;
                spieler1.bildschirmY = spieler1.weltY - spielablaufManager.mainSpieler.weltY + spielablaufManager.mainSpieler.bildschirmY;
            }

        }
    }
    public void setzeZustand(int neueZustand){
        if(neueZustand == spielBrettZustand){
            this.setBackground(new Color(39, 105, 195));
            this.removeKeyListener(this.getKeyListeners()[0]);
            this.addKeyListener(spielablaufManager.mapManager.mapEingabeManager);
            this.zustand = spielBrettZustand;
        } else if(neueZustand == minispielZustand){
            this.minispielManager = new MinispielManager(this, 1);
            this.removeKeyListener(this.getKeyListeners()[0]);
            this.addKeyListener(minispielManager.squidGameEingabeManger);

            this.zustand = minispielZustand;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if(minispielManager.gesamtSekundenAnzahl > 0){
                        minispielManager.gesamtSekundenAnzahl--;
                        if(minispielManager.gesamtSekundenAnzahl == 60){
                            minispielManager.mainMinispielSpieler.amSpielen = true;
                        }
                        minispielManager.size = 200F;
                        minispielManager.yPosition = 465;

                    } else if(minispielManager.gesamtSekundenAnzahl == 0){
                        minispielManager.mainMinispielSpieler.amSpielen = false;
                        timer.cancel();
                        setzeZustand(spielBrettZustand);
                    }
                }
            };
            timer.schedule(task, 3000, 1000);

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
        if (zustand == menueZustand) {
            menueManager.menueHintergrund.update();
            menueManager.update();
        } else if (zustand == spielBrettZustand) {
            spielablaufManager.update();
        } else if (zustand == minispielZustand) {
            minispielManager.update();
            if (spielablaufManager.mainSpieler.spielfigur != null) {
                if (!alleSpieler.isEmpty())
                    for (Spieler spieler : alleSpieler) {
                        if (spieler.spielfigur != null) {
                            spieler.update();
                        }
                    }
                spielablaufManager.mainSpieler.update();
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if(zustand == menueZustand){
            menueManager.menueHintergrund.malen(g2);
            menueManager.malen(g2);
        }else if(zustand == spielBrettZustand){
            spielablaufManager.malen(g2);
        } else if(zustand == minispielZustand){
            minispielManager.malen(g2);

        }
        g2.dispose();
    }

}
