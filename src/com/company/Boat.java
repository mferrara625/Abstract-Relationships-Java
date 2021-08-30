package com.company;

public class Boat extends Vehicle{
    public Boat(String make, String model, Engine engine, int maxSpeed, int maxPassengers) {
        super(make, model, engine, maxSpeed, maxPassengers);
    }

    @Override
    public void start(){
        engine.isOn = true;
    }


    @Override
    public void stop(){
        engine.isOn = false;
    }


    @Override
    public void addPassengers(int numberOfPassengers){
        this.numberOfPassengers += numberOfPassengers;
    }


    @Override
    public void removePassengers(int numberOfPassengers){
        this.numberOfPassengers -= numberOfPassengers;
    }


    @Override
    public void drive(int distance){
        distanceTraveled += distance;
    }

}
