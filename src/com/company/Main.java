package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String lineBreak = "=================================================================";
        Engine engine = new Engine("V8", 8);
        Car car = new Car("Ford", "Focus", engine, 4);
        System.out.println(car);
        System.out.println(lineBreak);
        car.addPassengers(2);     // ADDS TWO PASSENGERS
        System.out.println(car);
        System.out.println(lineBreak);
        car.start();                              // STARTS CAR
        System.out.println(car);
        System.out.println(lineBreak);
        car.drive(50);                     // DRIVES CAR 50 MILES
        System.out.println(car);
        System.out.println(lineBreak);
        car.removePassengers(1);  // REMOVES ONE PASSENGER
        System.out.println(car);
    }
}
