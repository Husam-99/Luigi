package Main;

import javax.swing.*;

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
