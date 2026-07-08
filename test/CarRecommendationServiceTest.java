import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.cccy225.carrental.model.RecommendationResult;
import com.cccy225.carrental.model.TripRequest;
import com.cccy225.carrental.repository.CarRepository;
import com.cccy225.carrental.service.CarRecommendationService;

class CarRecommendationServiceTest {

    @Test
    void testNormalCarRecommendation() {
        CarRecommendationService service =
                new CarRecommendationService(CarRepository.getAvailableCars());

        TripRequest request = new TripRequest(3, 5, 200);
        List<RecommendationResult> results = service.recommendCars(request);

        assertFalse(results.isEmpty());
        assertTrue(results.get(0).getCar().getMaxPassengers() >= 3);
    }

    @Test
    void testMinimumValidTripRecommendation() {
        CarRecommendationService service =
                new CarRecommendationService(CarRepository.getAvailableCars());

        TripRequest request = new TripRequest(1, 1, 250);
        List<RecommendationResult> results = service.recommendCars(request);

        assertFalse(results.isEmpty());
        assertTrue(results.get(0).getCar().getMaxPassengers() >= 1);
    }

    @Test
    void testMaximumPassengerRecommendation() {
        CarRecommendationService service =
                new CarRecommendationService(CarRepository.getAvailableCars());

        TripRequest request = new TripRequest(7, 3, 120);
        List<RecommendationResult> results = service.recommendCars(request);

        assertFalse(results.isEmpty());
        assertTrue(results.get(0).getCar().getMaxPassengers() >= 7);
    }

    @Test
    void testRecommendationWithExceededPassengerLimit() {
        CarRecommendationService service =
                new CarRecommendationService(CarRepository.getAvailableCars());

        TripRequest request = new TripRequest(10, 3, 150);

        assertThrows(IllegalArgumentException.class, () -> {
            service.recommendCars(request);
        });
    }
}