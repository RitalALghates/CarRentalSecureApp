package com.cccy225.carrental.model;

public class VanCar extends Car {
    public VanCar(String make, String model, double milesPerGallon) {
        super(make, model, CarCategory.VAN_MINIVAN, milesPerGallon);
    }

    @Override
    public RentalTier getRentalTier() {
        return RentalTier.VAN;
    }
}
