package com.cccy225.carrental.app;
//
import com.cccy225.carrental.auth.User;
import com.cccy225.carrental.model.RecommendationResult;
import com.cccy225.carrental.model.TripRequest;
import com.cccy225.carrental.persistence.UserFileRepository;
import com.cccy225.carrental.repository.CarRepository;
import com.cccy225.carrental.security.InputValidator;
import com.cccy225.carrental.security.SecureErrorHandler;
import com.cccy225.carrental.service.AuthService;
import com.cccy225.carrental.service.CarRecommendationService;
import com.cccy225.carrental.util.ConsolePrinter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsolePrinter.printBanner();
        Scanner scanner = new Scanner(System.in);
        CarRecommendationService recommendationService = new CarRecommendationService(CarRepository.getAvailableCars());
        AuthService authService = new AuthService(new UserFileRepository());

        try {
            User authenticatedUser = authenticateUser(scanner, authService);
            System.out.println();
            System.out.println("Welcome, " + authenticatedUser.getUsername() + "!");
            System.out.println();

            TripRequest request = readTripRequest(scanner);
         List<RecommendationResult> recommendations = recommendationService.recommendCars(request);
System.out.println();

if (recommendations.isEmpty()) {
    System.out.println(" No cars available for your request.");
} else {
    ConsolePrinter.printResults(recommendations);
}
        } catch (IllegalArgumentException ex) {
            if ("Program closed by user.".equals(ex.getMessage())) {
                System.out.println();
                System.out.println("Program closed safely.");
            } else {
                System.out.println();
                System.out.println(SecureErrorHandler.toSafeMessage(ex));
            }
        } catch (Exception ex) {
            System.out.println();
            System.out.println(SecureErrorHandler.toSafeMessage(ex));
        } finally {
            scanner.close();
        }
    }

    private static User authenticateUser(Scanner scanner, AuthService authService) {
        while (true) {
            ConsolePrinter.printAuthMenu();
            String choice = scanner.nextLine().trim();

            try {
                if ("1".equals(choice)) {
                    System.out.print("Choose a username: ");
                    String username = scanner.nextLine().trim();
                    System.out.print("Choose a password: ");
                    String password = scanner.nextLine();
                    authService.signUp(username, password);
                    System.out.println("Account created successfully. Please sign in.");
                    System.out.println();
                } else if ("2".equals(choice)) {
                    System.out.print("Username: ");
                    String username = scanner.nextLine().trim();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    return authService.signIn(username, password);
                } else if ("3".equals(choice)) {
                    throw new IllegalArgumentException("Program closed by user.");
                } else {
                    System.out.println("Invalid menu selection. Please choose 1, 2, or 3.");
                    System.out.println();
                }
            } catch (IllegalArgumentException ex) {
                if ("Program closed by user.".equals(ex.getMessage())) {
                    throw ex;
                }
                System.out.println("Authentication error: " + ex.getMessage());
                System.out.println();
            }
        }
    }

    private static TripRequest readTripRequest(Scanner scanner) {
        while (true) {
            try {
                int passengers = readInteger(scanner, "Enter number of passengers: ");
                int rentalDays = readInteger(scanner, "Enter number of rental days: ");
                double tripMileage = readDouble(scanner, "Enter approximate trip mileage: ");

                TripRequest request = new TripRequest(passengers, rentalDays, tripMileage);
                InputValidator.validate(request);
                return request;
            } catch (IllegalArgumentException ex) {
                System.out.println("Input error: " + ex.getMessage());
                System.out.println("Please re-enter the trip information.\n");
            }
        }
    }

    private static int readInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                System.out.println("Input error: Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException ex) {
                System.out.println("Input error: Please enter a valid number.");
            }
        }
    }
}
