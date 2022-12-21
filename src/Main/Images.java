package Main;

import javax.swing.*;
import java.util.ArrayList;

public interface Images {
    ArrayList<ImageIcon> images = new ArrayList<>();

    default void insertImages(){
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 2; j++) {
                images.add(new ImageIcon(i + "-" + j + ".png"));
            }
    }

    }
}
