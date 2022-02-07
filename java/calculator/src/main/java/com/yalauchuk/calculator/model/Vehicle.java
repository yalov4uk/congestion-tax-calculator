package com.yalauchuk.calculator.model;

public interface Vehicle {

    /**
     * A simple factory method to create instance from a string.
     */
    static Vehicle of(String type) {
        return switch (type) {
            case Motorcycle.NAME -> new Motorcycle();
            case Car.NAME -> new Car();
            default -> new Other(type);
        };
    }

    String getVehicleType();
}
