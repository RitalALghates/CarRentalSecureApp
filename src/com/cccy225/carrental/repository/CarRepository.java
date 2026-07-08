package com.cccy225.carrental.repository;

import com.cccy225.carrental.model.Car;
import com.cccy225.carrental.model.CarCategory;
import com.cccy225.carrental.model.EconomyCar;
import com.cccy225.carrental.model.IntermediateCar;
import com.cccy225.carrental.model.StandardCar;
import com.cccy225.carrental.model.VanCar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CarRepository {
    private CarRepository() {
    }

    public static List<Car> getAvailableCars() {
        List<Car> cars = new ArrayList<Car>();

        // Covers all categories requested in the project specification.
        cars.add(new EconomyCar("Ford", "Mustang", 26.0));
        cars.add(new IntermediateCar("Honda", "Accord", CarCategory.SEDAN, 32.0));
        cars.add(new IntermediateCar("Toyota", "Prius", CarCategory.HYBRID, 52.0));
        cars.add(new StandardCar("Ford", "F-150", CarCategory.TRUCK, 22.0));
        cars.add(new StandardCar("Subaru", "Outback", CarCategory.CROSSOVER, 29.0));
        cars.add(new StandardCar("Toyota", "RAV4", CarCategory.SUV, 30.0));
        cars.add(new VanCar("Honda", "Odyssey", 22.0));

        return Collections.unmodifiableList(cars);
    }
}
