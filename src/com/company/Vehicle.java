package com.company;

public abstract class Vehicle {
    private String make;
    private String model;
    private Engine engine;
    protected int numberOfPassengers;
    protected boolean isOn;

    public Vehicle(String make, String model, Engine engine){
        this.make = make;
        this.model = model;
        this.engine = engine;
        numberOfPassengers = 0;
        isOn = false;
    }

    public abstract void start();

    public abstract void addPassengers(int numberOfPassengers);

    public abstract void removePassengers(int numberOfPassengers);



    public String toString(){
        return "* Make: " + make + " * Model: " + model + " * Number of Passengers: " + numberOfPassengers + " * Engine On? " + isOn;
    }
}
