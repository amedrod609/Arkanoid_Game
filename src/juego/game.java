package juego;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class game extends Canvas {

    public static final int HEIGHT = 800;
    public static final int WIDTH = 500;
    private static game instance = null;
    


    public game() {
        JFrame window = new JFrame("Arkanoid");
        JPanel panel  = (JPanel) window.getContentPane();
        setBounds(0, 0, WIDTH, HEIGHT);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);
        panel.add(this);
        window.setBounds(0, 0, WIDTH, HEIGHT);
        window.setVisible(true);


    }

    //Patrón singleton
    public static game getInstance(){
        if (instance == null) {
            instance = new game();
        }
        return instance;
    }
    

    public static void main(String[] args) {
        game.getInstance();
    }

    
    
}