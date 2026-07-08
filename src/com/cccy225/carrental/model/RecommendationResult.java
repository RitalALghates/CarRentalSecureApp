package com.cccy225.carrental.model;

public class RecommendationResult {
    private final Car car;
    private final double totalCost;

    public RecommendationResult(Car car, double totalCost) {
        this.car = car;
        this.totalCost = totalCost;
    }

    public Car getCar() {
        return car;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
