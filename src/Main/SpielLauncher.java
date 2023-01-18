package Main;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SpielLauncher {
    public static void main (String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Luigi Party");


        SpielPanel spielPanel = new SpielPanel(window);
        window.add(spielPanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        spielPanel.startSpielThread();

    }

}
