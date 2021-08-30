package com.company;

public abstract class Vehicle {
    private String make;
    private String model;
    protected Engine engine;
    protected int numberOfPassengers;
    protected int distanceTraveled;
    protected int maxSpeed;
    protected int maxPassengers;

    public Vehicle(String make, String model, Engine engine, int maxSpeed, int maxPassengers){
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.maxSpeed = maxSpeed;
        this.maxPassengers = maxPassengers;
        numberOfPassengers = 0;
        distanceTraveled = 0;
        engine.isOn = false;

    }

    public abstract void start();

    public abstract void stop();

    public abstract void addPassengers(int numberOfPassengers);

    public abstract void removePassengers(int numberOfPassengers);

    public abstract void drive(int distance);



    public String toString(){
        return "* Make: " + make + " * Model: " + model + " * Number of Passengers: " + numberOfPassengers + " * Engine On? " + engine.isOn + " * Speed: " + Math.abs(Contents.xV) + " * Odometer: " + distanceTraveled;
    }
}
