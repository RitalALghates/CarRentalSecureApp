package com.cccy225.carrental.model;

public class EconomyCar extends Car {
    public EconomyCar(String make, String model, double milesPerGallon) {
        super(make, model, CarCategory.COUPE, milesPerGallon);
    }

    @Override
    public RentalTier getRentalTier() {
        return RentalTier.ECONOMY;
    }
}
