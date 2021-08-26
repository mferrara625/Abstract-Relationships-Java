package com.company;

public abstract class Vehicle {
    private String make;
    private String model;
    private Engine engine;
    protected int numberOfPassengers;
    protected int distanceTraveled;
    protected boolean isOn;

    public Vehicle(String make, String model, Engine engine){
        this.make = make;
        this.model = model;
        this.engine = engine;
        numberOfPassengers = 0;
        distanceTraveled = 0;
        isOn = false;

    }

    public abstract void start();

    public abstract void stop();

    public abstract void addPassengers(int numberOfPassengers);

    public abstract void removePassengers(int numberOfPassengers);

    public abstract void drive(int distance);



    public String toString(){
        return "* Make: " + make + " * Model: " + model + " * Number of Passengers: " + numberOfPassengers + " * Engine On? " + isOn + " * Odometer: " + distanceTraveled;
    }
}
