package com.cccy225.carrental.service;

import com.cccy225.carrental.model.Car;
import com.cccy225.carrental.model.RecommendationResult;
import com.cccy225.carrental.model.TripRequest;
import com.cccy225.carrental.security.InputValidator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarRecommendationService {
    private final List<Car> availableCars;

    public CarRecommendationService(List<Car> availableCars) {
        this.availableCars = new ArrayList<Car>(availableCars);
    }

    public List<RecommendationResult> recommendCars(TripRequest request) {
        InputValidator.validate(request);

        List<RecommendationResult> candidates = new ArrayList<RecommendationResult>();
        for (Car car : availableCars) {
            if (car.canFitPassengers(request.getPassengerCount())) {
                double totalCost = car.calculateTotalTripCost(request.getRentalDays(), request.getTripMileage());
                candidates.add(new RecommendationResult(car, totalCost));
            }
        }

        candidates.sort(Comparator
                .comparingDouble(RecommendationResult::getTotalCost)
                .thenComparing((RecommendationResult r) -> r.getCar().getComfortLevel().getRank(), Comparator.reverseOrder())
                .thenComparing(r -> r.getCar().getDisplayName()));

        if (candidates.isEmpty()) {
            return candidates;
        }

        double minimumCost = candidates.get(0).getTotalCost();
        List<RecommendationResult> finalRecommendations = new ArrayList<RecommendationResult>();

        for (RecommendationResult candidate : candidates) {
            if (Double.compare(candidate.getTotalCost(), minimumCost) == 0) {
                finalRecommendations.add(candidate);
            }
        }

        finalRecommendations.sort(Comparator
                .comparing((RecommendationResult r) -> r.getCar().getComfortLevel().getRank(), Comparator.reverseOrder())
                .thenComparing(r -> r.getCar().getDisplayName()));

        return finalRecommendations;
    }
}
