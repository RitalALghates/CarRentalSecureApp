import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cccy225.carrental.model.TripRequest;
import com.cccy225.carrental.security.InputValidator;

class InputValidatorTest {

    @Test
    void testNegativePassengerInput() {
        TripRequest request = new TripRequest(-1, 3, 234);

        assertThrows(IllegalArgumentException.class, () -> {
        	InputValidator.validate(request);
        });
    }

    @Test
    void testRentalDaysEqualZero() {
        TripRequest request = new TripRequest(3, 0, 3);

        assertThrows(IllegalArgumentException.class, () -> {
        	InputValidator.validate(request);
        });
    }

    @Test
    void testNegativeTripMileage() {
        TripRequest request = new TripRequest(2, 23, -2);

        assertThrows(IllegalArgumentException.class, () -> {
        	InputValidator.validate(request);
        });
    }

    @Test
    void testValidBoundaryTripInput() {
        TripRequest request = new TripRequest(1, 1, 1);

        assertDoesNotThrow(() -> {
        	InputValidator.validate(request);
        });
    }
}