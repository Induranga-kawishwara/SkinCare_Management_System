package GUI;

import javax.swing.*;
import java.awt.*;

public abstract class GUI_table extends JFrame {
    public abstract void button();
    public void makeFrame(String name, int width, int height){

        //CREATING THE FRAMES

        ImageIcon img = new ImageIcon("src/GUI/ico.png");
        setIconImage(img.getImage());
        setSize(width,height);
        setTitle(name);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE or 3
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setVisible(true);


    }

}
