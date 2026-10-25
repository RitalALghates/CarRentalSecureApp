package com.cccy225.carrental.util;

import com.cccy225.carrental.model.RecommendationResult;
import java.text.DecimalFormat;
import java.util.List;

public final class ConsolePrinter {
    private static final DecimalFormat MONEY = new DecimalFormat("0.00");

    private ConsolePrinter() {
    }

    public static void printBanner() {
        System.out.println("============================================");
        System.out.println("   Secure Car Rental Recommendation System");
        System.out.println("============================================");
        System.out.println("Gas price used for calculation: $2.25/gallon");
        System.out.println();
    }

    public static void printAuthMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Sign Up");
        System.out.println("2. Sign In");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

public static void printResults(List<RecommendationResult> results) {
    if (results.isEmpty()) {
        System.out.println(" No suitable cars were found for your request.");
        return;
    }

    System.out.println("\n===== Recommended Cars =====\n");

    for (RecommendationResult result : results) {
        System.out.println("Car: " + result.getCar().getDisplayName());
        System.out.println("   Category: " + result.getCar().getCategory());
        System.out.println("   Tier: " + result.getCar().getRentalTier() +
                " (" + result.getCar().getComfortLevel() + ")");
        System.out.println("   Passengers: " + result.getCar().getMaxPassengers());
        System.out.println("   Total Cost: $" + MONEY.format(result.getTotalCost()));
        System.out.println("----------------------------------\n");
    }
}
}
