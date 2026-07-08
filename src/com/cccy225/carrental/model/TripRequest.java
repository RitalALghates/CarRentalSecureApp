package com.cccy225.carrental.model;

public class TripRequest {
    private final int passengerCount;
    private final int rentalDays;
    private final double tripMileage;

    public TripRequest(int passengerCount, int rentalDays, double tripMileage) {
        this.passengerCount = passengerCount;
        this.rentalDays = rentalDays;
        this.tripMileage = tripMileage;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTripMileage() {
        return tripMileage;
    }
}
