package com.cccy225.carrental.model;

import java.text.DecimalFormat;

public abstract class Car {
    public static final double GAS_PRICE_PER_GALLON = 2.25;
    private static final DecimalFormat MONEY = new DecimalFormat("0.00");

    private final String make;
    private final String model;
    private final CarCategory category;
    private final double milesPerGallon;

    protected Car(String make, String model, CarCategory category, double milesPerGallon) {
        this.make = make;
        this.model = model;
        this.category = category;
        this.milesPerGallon = milesPerGallon;
    }

    public abstract RentalTier getRentalTier();

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public CarCategory getCategory() {
        return category;
    }

    public double getMilesPerGallon() {
        return milesPerGallon;
    }

    public int getMaxPassengers() {
        return getRentalTier().getMaxPassengers();
    }

    public ComfortLevel getComfortLevel() {
        return getRentalTier().getComfortLevel();
    }

    public double getRentalCostPerDay() {
        return getRentalTier().getRentalCostPerDay();
    }

    public boolean canFitPassengers(int passengerCount) {
        return passengerCount <= getMaxPassengers();
    }

    public double calculateRentalCost(int rentalDays) {
        return getRentalCostPerDay() * rentalDays;
    }

    public double calculateFuelCost(double tripMileage) {
        return (tripMileage / milesPerGallon) * GAS_PRICE_PER_GALLON;
    }

    public double calculateTotalTripCost(int rentalDays, double tripMileage) {
        return calculateRentalCost(rentalDays) + calculateFuelCost(tripMileage);
    }

    public String getDisplayName() {
        return make + " " + model;
    }

public String toDisplayLine(int rentalDays, double tripMileage) {
    double rentalCost = calculateRentalCost(rentalDays);
    double fuelCost = calculateFuelCost(tripMileage);
    double totalCost = rentalCost + fuelCost;

    return String.format(
            "%s | Category: %s | Tier: %s | Max Passengers: %d | Comfort: %s | " +
            "Rental: $%s | Fuel: $%s | Total: $%s",
            getDisplayName(),
            category,
            getRentalTier(),
            getMaxPassengers(),
            getComfortLevel(),
            MONEY.format(rentalCost),
            MONEY.format(fuelCost),
            MONEY.format(totalCost)
    );

    }
}
