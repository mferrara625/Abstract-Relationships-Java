package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {


    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        KeyListener listener = new KeyListener() {
            public void keyPressed(KeyEvent event) {
                if(event.getKeyChar()==KeyEvent.VK_A) {                 //ADDS PASSENGERS ONE AT A TIME UNTIL CAR IS FULL
                    if(Contents.car.numberOfPassengers < 4)
                        Contents.car.addPassengers(1);
                    event.consume();
                }
                if(event.getKeyChar()==KeyEvent.VK_R) {                 //REMOVES PASSENGERS ONE AT A TIME UNTIL CAR IS EMPTY
                    if(Contents.car.numberOfPassengers > 0)
                        Contents.car.removePassengers(1);
                    event.consume();
                }
                if(event.getKeyChar()==KeyEvent.VK_S && Contents.car.numberOfPassengers > 0) {   //STARTS ENGINE AS LONG AS THERE IS AT LEAST ONE PASSENGER
                    Contents.car.start();
                }
                if(event.getKeyChar()==KeyEvent.VK_X && Contents.car.numberOfPassengers > 0) {   //STOPS ENGINE AS LONG AS THERE IS AT LEAST ONE PASSENGER
                    Contents.car.stop();
                }
                if(event.getKeyChar()==KeyEvent.VK_M && Contents.car.isOn && Contents.car.numberOfPassengers > 0){  //DRIVES CAR FORWARD IF ENGINE IS ON AND THERE IS AT LEAST ONE PASSENGER
                    Contents.isForward = true;
                    Contents.isMoving = true;
                    Contents.xV = 2;
                    event.consume();
                }
                if(event.getKeyChar()==KeyEvent.VK_N && Contents.car.isOn && Contents.car.numberOfPassengers > 0){   //DRIVES CAR BACKWARD IF ENGINE IS ON AND THERE IS AT LEAST ONE PASSENGER
                    Contents.isForward = false;
                    Contents.isMoving = true;
                    Contents.xV = -2;
                    event.consume();
                }
            }

            public void keyReleased(KeyEvent event) {
                if(event.getKeyChar()==KeyEvent.VK_M){
                    Contents.isMoving = false;
                    Contents.xV = 0;
                    Contents.car.drive(Contents.count/10);
                    Contents.count = 0;
                    event.consume();
                }
                if(event.getKeyChar()==KeyEvent.VK_N){
                    Contents.isMoving = false;
                    Contents.xV = 0;
                    Contents.car.drive(Contents.count/10);
                    Contents.count = 0;
                    event.consume();
                }
            }

            public void keyTyped(KeyEvent event) {
            }

        };
        JFrame frame = new JFrame();
        frame.setTitle("Abstract Relationships - Car Example");
        frame.setSize(900, 600);
        frame.setLocation(100, 50);
        frame.addKeyListener(listener);
        frame.add(new Contents());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
