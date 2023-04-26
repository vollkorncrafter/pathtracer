package de.oskarschulz;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class display {
    JFrame frame = new JFrame("Render");
    BufferedImage image ;

    public void paint(){


    }
    public void createWindow(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
