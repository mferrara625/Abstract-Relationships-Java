package com.company;

public class Car extends Vehicle {

    private int numberOfDoors;

    public Car(String make, String model, Engine engine, int numberOfDoors) {
        super(make, model, engine);
        this.numberOfDoors = numberOfDoors;
        this.numberOfPassengers = 0;
    }

    public void start(){
        isOn = true;
    }

    public void addPassengers(int numberOfPassengers){
        this.numberOfPassengers += numberOfPassengers;
    }

    public void removePassengers(int numberOfPassengers){
        this.numberOfPassengers -= numberOfPassengers;
    }

    public void drive(int distance){
        distanceTraveled += distance;
    }
}
