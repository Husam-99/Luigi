/*package Networking;

import Networking.Server.SpielServer;
import Networking.Pakete.SpielerAuskuenfte;

import javax.swing.*;

public class Kram {
    String name1 = null;
    String name2 = null;
        if(istHost){
        do {
            name1 = JOptionPane.showInputDialog("Geben Sie den Namen des ersten Spielers ein:");
            if (name1.isEmpty())
                JOptionPane.showMessageDialog(null, "Bitte die Namen erneut eingeben!", "Meldung", JOptionPane.WARNING_MESSAGE);
            SpielerAuskuenfte auskunft = new SpielerAuskuenfte(name1);
            SpielServer.send(auskunft);
        } while (name1.isEmpty());
    }else{
        do {
            name2 = JOptionPane.showInputDialog("Geben Sie den Namen des zweiten Spielers ein:");
            if (name2.isEmpty())
                JOptionPane.showMessageDialog(null, "Bitte die Namen erneut eingeben!", "Meldung", JOptionPane.WARNING_MESSAGE);
            SpielerAuskuenfte auskunft = new SpielerAuskuenfte(name2);
            client.send(auskunft);
        } while (name2.isEmpty());
    }

}
*/