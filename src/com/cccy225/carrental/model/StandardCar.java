package com.cccy225.carrental.model;

public class StandardCar extends Car {
    public StandardCar(String make, String model, CarCategory category, double milesPerGallon) {
        super(make, model, category, milesPerGallon);
        if (category != CarCategory.TRUCK && category != CarCategory.CROSSOVER && category != CarCategory.SUV) {
            throw new IllegalArgumentException("Standard cars must be Truck, Crossover, or SUV.");
        }
    }

    @Override
    public RentalTier getRentalTier() {
        return RentalTier.STANDARD;
    }
}
