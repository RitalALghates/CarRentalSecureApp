package com.cccy225.carrental.model;

public class IntermediateCar extends Car {
    public IntermediateCar(String make, String model, CarCategory category, double milesPerGallon) {
        super(make, model, category, milesPerGallon);
        if (category != CarCategory.SEDAN && category != CarCategory.HYBRID) {
            throw new IllegalArgumentException("Intermediate cars must be Sedan or Hybrid.");
        }
    }

    @Override
    public RentalTier getRentalTier() {
        return RentalTier.INTERMEDIATE;
    }
}
