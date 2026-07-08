package com.cccy225.carrental.model;

public enum RentalTier {
    ECONOMY(45.0, 4, ComfortLevel.POOR),
    INTERMEDIATE(50.0, 4, ComfortLevel.MEDIUM),
    STANDARD(55.0, 5, ComfortLevel.GOOD),
    VAN(70.0, 7, ComfortLevel.MEDIUM);

    private final double rentalCostPerDay;
    private final int maxPassengers;
    private final ComfortLevel comfortLevel;

    RentalTier(double rentalCostPerDay, int maxPassengers, ComfortLevel comfortLevel) {
        this.rentalCostPerDay = rentalCostPerDay;
        this.maxPassengers = maxPassengers;
        this.comfortLevel = comfortLevel;
    }

    public double getRentalCostPerDay() {
        return rentalCostPerDay;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public ComfortLevel getComfortLevel() {
        return comfortLevel;
    }
}
