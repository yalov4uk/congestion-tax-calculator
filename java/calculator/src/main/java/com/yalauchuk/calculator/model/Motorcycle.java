package com.yalauchuk.calculator.model;

public class Motorcycle implements Vehicle {

    public static final String NAME = "Motorcycle";

    @Override
    public String getVehicleType() {
        return NAME;
    }
}
