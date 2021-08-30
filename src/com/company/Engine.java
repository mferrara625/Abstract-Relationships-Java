package com.company;

public abstract class Engine {
    protected String type;
    private int numberOfCylinders;
    protected boolean isOn;


    public Engine(String type, int numberOfCylinders){
        this.type = type;
        this.numberOfCylinders = numberOfCylinders;
        isOn = false;
    }
}
