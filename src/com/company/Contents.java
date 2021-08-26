package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Contents extends JPanel implements ActionListener {

    private Image image, image2;
    private int x = 100, y = 200;
    public static int count = 0;

    private static Engine engine = new Engine("V8", 8);
    public static Car car = new Car("Ford", "Focus", engine, 4);

    static boolean isMoving = false, isForward = true;

    private Timer t;

    public Contents(){
        super.setDoubleBuffered(true);
        t = new Timer(7, this);
        t.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("car.png"));
        image = ii.getImage();
        ImageIcon ii2 = new ImageIcon(this.getClass().getResource("carreversed.png"));
        image2 = ii2.getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("CONTROLS:                  (CAPSLOCK MUST BE ON)", 50, 25);
        g2d.drawString("A : Add Passenger", 50, 45);
        g2d.drawString("R : Remove Passenger", 50, 65);
        g2d.drawString("S : Start Engine", 50, 85);
        g2d.drawString("X : Turn Off Engine", 50, 105);
        g2d.drawString("M : Drive Forward", 50, 125);
        g2d.drawString("N : Drive Backwards", 50, 145);

        g2d.drawString(car.toString(), 375, 25);
        if(isForward){
            g2d.drawImage(image, x, y, this);
        }
        if (!isForward){
            g2d.drawImage(image2, x, y, this);
        }
        for(int i = 0; i < car.numberOfPassengers; i++){
            g2d.fillOval(x + ((i + 2) * 15), y + 45, 10, 10);
        }
    }

    public static int xV = 0;
    public static int yV = 0;

    public void move(){
        x = x + xV;
        y = y + yV;
    }

    public void actionPerformed(ActionEvent e) {
        move();

        if(isMoving)
        count++;

        repaint();
    }
}
