package Main;

import Networking.Client.SpielClient;
import Networking.Pakete.GeklickteKarte;
import Networking.Pakete.SpielerAuskuenfte;
import Networking.Server.SpielServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Memory extends JFrame implements ActionListener {
    public static Memory memory;

    private static boolean alleKartenOffen = false;
    public static Spielerin spielerin1 = new Spielerin();
    public static Spielerin spielerin2 = new Spielerin();
    private static final SpielClient client = new SpielClient();


    public static ArrayList<Karte> karten;
    public static Map<Karte, Karte> zuordnung;
    public static ArrayList<Karte> geklickteKarte = new ArrayList<>();
    public static ArrayList<JButton> buttons = new ArrayList<>();


    public Memory() {

        String[] connectionTypen = {"Server", "Client"};
        int wahl = JOptionPane.showOptionDialog(
                memory,
                "Wer bist du?",
                "Willkommen!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                connectionTypen,
                0);
        if (wahl == 0) {
            SpielServer.start();
        } else {
            client.start();

            setTitle("Memory");
            setSize(600, 600);
            setVisible(true);
            setResizable(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            getContentPane().setBackground(Color.DARK_GRAY);

            JMenuBar fensterMenue = new JMenuBar();
            JMenu memoryFensterMenue = new JMenu("Memory");

            JMenuItem starten = new JMenuItem("Starten");
            JMenuItem abbrechen = new JMenuItem("Abbrechen");

            starten.addActionListener(e -> {
                if (e.getSource() == starten) {
                    starteSpiel();
                }
            });
            abbrechen.addActionListener(e -> {
                if (e.getSource() == abbrechen) {
                    beenden();
                }
            });

            memoryFensterMenue.add(starten);
            memoryFensterMenue.add(abbrechen);
            fensterMenue.add(memoryFensterMenue);
            setJMenuBar(fensterMenue);


            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            starteSpiel();

            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int anzahlKarten = 16;

            GridLayout layout = new GridLayout(4, 4, 5, 5);
            this.setLayout(layout);

            karten = new ArrayList<>();
            String kartenZeichen = "karte ";

            for (int i = 0; i < anzahlKarten; i++) {
                karten.add(new Karte(kartenZeichen + i, i));
            }
            zuordnung = new HashMap<>();
            for (int i = 0; i < anzahlKarten; i += 2) {
                zuordnung.put(karten.get(i), karten.get(i + 1));
                zuordnung.put(karten.get(i + 1), karten.get(i));
            }
            for (Karte karte : karten) {
                buttons.add(karte.getButton());
            }
            for (JButton button : buttons) {
                button.addActionListener(this);

            }
            for(int i = 0; i < 16; i += 2){
                for(int j = 15; j >= 0; j--){
                    Collections.swap(karten, i, j);

                }
            }
            while (anzahlKarten > 0) {
                anzahlKarten--;
                this.add(karten.get(anzahlKarten));
                karten.get(anzahlKarten).setBounds(0, 0, 200, 200);
            }

            pack();

            while(getAlleKartenZustand()){
                update();
            }
        }
    }


    private void starteSpiel() {
        String name;
        do {
            name = JOptionPane.showInputDialog(memory,"Geben Sie den Namen des Spielers ein:");
            if (name.isEmpty())
                JOptionPane.showMessageDialog(memory, "Bitte den Namen erneut eingeben!", "Meldung", JOptionPane.WARNING_MESSAGE);
            if(spielerin1.gibNamen().isEmpty()){
                Memory.spielerin1.setName(name);
            }else{
                Memory.spielerin2.setName(name);
            }
            SpielerAuskuenfte auskunft = new SpielerAuskuenfte();
            auskunft.name = name;
            client.send(auskunft);
        } while (name.isEmpty());

        while(Memory.spielerin1.gibNamen().isEmpty() || Memory.spielerin2.gibNamen().isEmpty()){
            System.out.printf("Fo");
        }


        System.out.println(spielerin1.gibNamen());
        System.out.println(spielerin2.gibNamen());

        //Random rand = new Random();
        if (spielerin1.gibNamen() == null) {
            spielerin1.setzeDran(true);
            spielerin2.setzeDran(false);
            JOptionPane.showMessageDialog(memory, spielerin1.gibNamen() + " ist dran!", "Meldung", JOptionPane.INFORMATION_MESSAGE);
        } else {
            spielerin2.setzeDran(true);
            spielerin1.setzeDran(false);
            JOptionPane.showMessageDialog(memory, spielerin2.gibNamen() + " ist dran!", "Meldung", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static boolean pruefePaarGefunden() {
        return zuordnung.get(geklickteKarte.get(0)).equals(geklickteKarte.get(1));
    }

    private static void pruefeGewonnen() {
        if (spielerin1.gibPunkte() > spielerin2.gibPunkte()) {
            JOptionPane.showMessageDialog(memory, spielerin1.gibNamen() + " hat das Spiel gewonnen!", "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
            beenden();
        } else {
            JOptionPane.showMessageDialog(memory, spielerin2.gibNamen() + " hat das Spiel gewonnen!", "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
            beenden();
        }

    }

    private static void wechsleSpielerin() {
        if (spielerin1.IstDran()) {
            spielerin1.setzeDran(false);
            spielerin2.setzeDran(true);
        } else {
            spielerin2.setzeDran(false);
            spielerin1.setzeDran(true);
        }
    }

    private static void beenden() {
        String[] antwort = {"Neues Spiel", "Das Spiel beenden"};
        int wahl = JOptionPane.showOptionDialog(
                memory,
                gibSpielStand(),
                "Meldung",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                antwort,
                0);
        if (wahl == 0) {
            memory.dispose();
            SwingUtilities.invokeLater(Memory::new);
        } else {
            System.exit(0);
        }

    }

    private static String gibSpielStand() {
        return "Aktueller Spielstand:\n\n" + spielerin1.gibNamen() + " - " + spielerin1.gibPunkte()
                + " | " + spielerin2.gibNamen() + " - " + spielerin2.gibPunkte();
    }

    public static void main(String[] args) {
        memory = new Memory();
    }

    public static boolean getAlleKartenZustand() {
        pruefeAlleKartenOffen();
        return alleKartenOffen;
    }

    public static void pruefeAlleKartenOffen() {
        alleKartenOffen = true;
        for (Karte karte : karten) {
            if (karte.istOffen()) {
                alleKartenOffen = false;
                break;
            }
        }
    }

    public static void update() {
        if (geklickteKarte.size() == 2) {

            if (pruefePaarGefunden()) {
                geklickteKarte.get(0).setzeAnklickbar(false);
                geklickteKarte.get(1).setzeAnklickbar(false);

                if (spielerin1.IstDran()) {
                    spielerin1.erhoehePunkte();
                } else {
                    spielerin2.erhoehePunkte();
                }
                JOptionPane.showMessageDialog(memory, gibSpielStand(), "Meldung", JOptionPane.INFORMATION_MESSAGE);

            } else {
                wechsleSpielerin();
                if (spielerin1.IstDran()) {
                    JOptionPane.showMessageDialog(memory, "Leider kein Pärchen \n" + spielerin1.gibNamen() +
                            " ist dran!", "Meldung", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(memory, "Leider kein Pärchen \n" + spielerin2.gibNamen() +
                            " ist dran!", "Meldung", JOptionPane.INFORMATION_MESSAGE);
                }

                geklickteKarte.get(0).deckeZu();
                geklickteKarte.get(1).deckeZu();

            }
            geklickteKarte.clear();

        }


        if (getAlleKartenZustand()) {
            pruefeGewonnen();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Karte karte : karten) {
            if (e.getSource() == karte.getButton()) {
                if (karte.istOffen()) {
                    karte.deckeAuf();
                }
                geklickteKarte.add(karte);
                GeklickteKarte karte1 = new GeklickteKarte();
                karte1.kartenIndex = karten.indexOf(karte);
                client.send(karte1);

                update();

            }
        }
    }

}

