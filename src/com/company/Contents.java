package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Contents extends JPanel implements ActionListener {

    private final int vehiclePositionY = 200;
    private static int  round = 1, count = 0, vehiclePositionX = 50, frogPositionX = 200, frogPositionY = -150;
    private static Vehicle vehicle;

    private static final CarEngine engine = new CarEngine("V8", 8);
    private static final InlineEngine inlineEngine = new InlineEngine("4 Cylinder", 4);
    private static final Car car = new Car("Ford", "Focus", engine, 4, 5, 4, "HONK!");
    private static final Boat boat = new Boat("Bayliner", "f500", inlineEngine, 4, 6);
    private static boolean hornActive = false, engineSelected = false, vehicleSelected = false, isMoving = false, isForward = true, isGameActive = false, goalReached = false, isWinning = false, wasHit = false;

    private Timer t;

    public Contents(){
        super.setDoubleBuffered(true);
        t = new Timer(7, this);
        t.start();

    }

    static KeyListener listener = new KeyListener() {
        public void keyPressed(KeyEvent event) {
            if(event.getKeyChar()==KeyEvent.VK_A) {                 //ADDS PASSENGERS ONE AT A TIME UNTIL CAR IS FULL
                if(vehicle.numberOfPassengers < vehicle.maxPassengers)
                    vehicle.addPassengers(1);
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_R) {                 //REMOVES PASSENGERS ONE AT A TIME UNTIL CAR IS EMPTY
                if(vehicle.numberOfPassengers > 0)
                    vehicle.removePassengers(1);
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_S && vehicle.numberOfPassengers > 0) {   //STARTS ENGINE AS LONG AS THERE IS AT LEAST ONE PASSENGER
                vehicle.start();
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_X && vehicle.numberOfPassengers > 0) {   //STOPS ENGINE AS LONG AS THERE IS AT LEAST ONE PASSENGER
                vehicle.stop();
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_M && vehicle.engine.isOn && vehicle.numberOfPassengers > 0){  //DRIVES CAR FORWARD IF ENGINE IS ON AND THERE IS AT LEAST ONE PASSENGER
                if(xV > 0)
                    isForward = true;
                isMoving = true;
                if(xV <= vehicle.maxSpeed)
                    xV += 1;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_N && vehicle.engine.isOn && vehicle.numberOfPassengers > 0){   //DRIVES CAR BACKWARD IF ENGINE IS ON AND THERE IS AT LEAST ONE PASSENGER
                if(xV < 0)
                    isForward = false;
                isMoving = true;
                if(xV > -vehicle.maxSpeed)
                    xV -= 1;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_ENTER) {         //STARTS GAME
                wasHit = false;
                isGameActive = true;
                goalReached = false;
                isWinning = true;
                fYV = (round + 2);
                frogPositionY = -150;
                frogPositionX = (int) ((Math.random() * 350) + 200);
                vehiclePositionX = 5;
                xV = 0;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_C && !vehicleSelected){       // SELECTS CAR
                vehicle = car;
                vehicleSelected = true;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_B && !vehicleSelected){       // SELECTS BOAT
                vehicle = boat;
                vehicleSelected = true;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_I && !engineSelected){        // SELECTS INLINE ENGINE
                vehicle.engine = inlineEngine;
                engineSelected = true;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_V && !engineSelected){        // SELECTS V8 ENGINE
                vehicle.engine = engine;
                engineSelected = true;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_H){      // ACTIVATES HORN
                hornActive = true;
                event.consume();
            }
        }

        public void keyReleased(KeyEvent event) {
            if(event.getKeyChar()==KeyEvent.VK_M){
                isMoving = false;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_N){
                isMoving = false;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_H){    // RELEASES HORN
                hornActive = false;
                event.consume();
            }
        }

        public void keyTyped(KeyEvent event) {
        }

    };

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("car.png"));
        Image carForward = ii.getImage();
        ImageIcon ii2 = new ImageIcon(this.getClass().getResource("carreversed.png"));
        Image carBackwards = ii2.getImage();
        ImageIcon ii3 = new ImageIcon(this.getClass().getResource("finishLine.png"));
        Image finishLine = ii3.getImage();
        ImageIcon ii4 = new ImageIcon(this.getClass().getResource("angryFrog.png"));
        Image frog = ii4.getImage();
        ImageIcon ii5 = new ImageIcon(this.getClass().getResource("boat.png"));
        Image boatForward = ii5.getImage();
        ImageIcon ii6 = new ImageIcon(this.getClass().getResource("boatreversed.png"));
        Image boatReversed = ii6.getImage();
        Graphics2D g2d = (Graphics2D) g;

        if (!vehicleSelected) {
            g2d.drawString("Select a Vehicle", 400, 400);
            g2d.drawString("C: Car", 400, 425);
            g2d.drawString("B: Boat", 400, 450);
        }

        if (vehicleSelected && !engineSelected) {
            g2d.drawString("Select an Engine", 400, 400);
            g2d.drawString("V: V8", 400, 425);
            g2d.drawString("I: Inline", 400, 450);
        }
        if(vehicleSelected && engineSelected){
        if (isGameActive)
            g2d.drawImage(frog, frogPositionX, frogPositionY, this);

        if (wasHit)
            g2d.drawString("YOU LOSE!!! :(", 400, 400);

        g2d.drawString("CONTROLS:  (CAPSLOCK MUST BE ON)", 50, 25);
        g2d.drawString("A : Add Passenger", 50, 45);
        g2d.drawString("R : Remove Passenger", 50, 65);
        g2d.drawString("S : Start Engine", 50, 85);
        g2d.drawString("X : Turn Off Engine", 50, 105);
        g2d.drawString("M : Accelerate Forward / Slow Down If Driving Backwards", 50, 125);
        g2d.drawString("N : Accelerate Backward / Slow Down If Driving Forwards", 50, 145);
        g2d.drawString("H : Honk Horn", 50, 165);
        g2d.drawString("ENTER : Start Game", 50, 185);

        if (goalReached)
            g2d.drawString("SUCCESS!!!", 400, 400);


        g2d.drawString(vehicle.toString(), 300, 25);
        g2d.drawString("Distance remaining: " + (750 - vehiclePositionX) / 100, 750, 50);
        g2d.drawString("Round: " + round, 750, 75);


        if (isForward) {
            if(vehicle == car){
                g2d.drawImage(carForward, vehiclePositionX, vehiclePositionY, this);
                for (int i = 0; i < vehicle.numberOfPassengers; i++) {
                    g2d.fillOval((vehiclePositionX + 135) - ((i + 4) * 15), vehiclePositionY + 45, 10, 10);
                }
            }
            if(vehicle == boat) {
                g2d.drawImage(boatForward, vehiclePositionX, vehiclePositionY, this);
                for (int i = 0; i < vehicle.numberOfPassengers; i++) {
                    g2d.fillOval((vehiclePositionX + 135) - ((i + 2) * 15), vehiclePositionY + 55, 10, 10);
                }
            }
        }
        if (!isForward) {
            if(vehicle == car){
                g2d.drawImage(carBackwards, vehiclePositionX, vehiclePositionY, this);
                for (int i = vehicle.numberOfPassengers; i > 0; i--) {
                    g2d.fillOval(vehiclePositionX + ((i + 2) * 15), vehiclePositionY + 45, 10, 10);
                }
            }
            if(vehicle == boat) {
                g2d.drawImage(boatReversed, vehiclePositionX, vehiclePositionY, this);
                for (int i = vehicle.numberOfPassengers; i > 0; i--) {
                    g2d.fillOval(vehiclePositionX + (i * 15), vehiclePositionY + 55, 10, 10);
                }
            }
        }

        g2d.drawString("Engine Type: " + vehicle.engine.type, 700,500);

        g2d.drawImage(finishLine, 750, 175, this);

        if(hornActive && vehicle == car)
            g2d.drawString(car.hornSound, vehiclePositionX + 100, vehiclePositionY + 50);

    }
    }

    public static int xV = 0;
    public static int fYV = 0;

    public void move(){
        vehiclePositionX = vehiclePositionX + xV;
        frogPositionY = frogPositionY + fYV;
    }

    public void actionPerformed(ActionEvent e) {
        move();

        if(vehiclePositionY + 64 >= frogPositionY && vehiclePositionY + 64 <= frogPositionY + 100 && vehiclePositionX + 64 >= frogPositionX && vehiclePositionX + 64 <= frogPositionX + 170){
            xV = 0;
            frogPositionY = -150;
            fYV = 0;
            isWinning = false;
            isGameActive = false;
            wasHit = true;
            round = 1;
        }

        if(frogPositionY >= 600){
            frogPositionY = -150;
            frogPositionX = (int) ((Math.random() * 350) + 200);
        }

        if(vehiclePositionX >= 750 && isWinning){
            round++;
            fYV = 0;
            frogPositionY = -150;
            goalReached = true;
            isGameActive = false;
            isWinning = false;
        }

        if(vehiclePositionX % 75 == 0)
            vehicle.drive(1);

        if(isMoving)
            count++;

        repaint();
    }
}
