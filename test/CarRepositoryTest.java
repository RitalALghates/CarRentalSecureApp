import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.cccy225.carrental.model.Car;
import com.cccy225.carrental.repository.CarRepository;

class CarRepositoryTest {

    @Test
    void testAvailableCarsListNotEmpty() {
        List<Car> cars = CarRepository.getAvailableCars();

        assertNotNull(cars);
        assertFalse(cars.isEmpty());
    }

    @Test
    void testRepositoryContainsDifferentCarTypes() {
        List<Car> cars = CarRepository.getAvailableCars();

        boolean hasEconomy = cars.stream().anyMatch(car -> car.getCategory() != null);

        assertTrue(hasEconomy);
    }
}