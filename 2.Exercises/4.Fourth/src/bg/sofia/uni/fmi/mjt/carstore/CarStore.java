package bg.sofia.uni.fmi.mjt.carstore;

import bg.sofia.uni.fmi.mjt.carstore.car.Car;
import bg.sofia.uni.fmi.mjt.carstore.enums.Model;
import bg.sofia.uni.fmi.mjt.carstore.exception.CarNotFoundException;

import java.util.*;

public class CarStore {

    //car collections for different operations
    private Set<Car> cars;
    private Map<Model, Collection<Car>> modelToCars;
    private Map<String, Car> regNumToCar;

    private int totalPriceOfCars;

    public CarStore() {
        setUp();
    }

    private void setUp() {
        cars = new HashSet<>();
        modelToCars = new HashMap<>();
        regNumToCar = new HashMap<>();
        for (Model model : Model.values()) {
            modelToCars.put(model, new HashSet<>());

        }
    }

    public boolean add(Car car) {
        if (cars.contains(car)) {
            return false;
        }

        cars.add(car);
        modelToCars.get(car.getModel()).add(car);
        regNumToCar.put(car.getRegistrationNumber(), car);
        totalPriceOfCars += car.getPrice();
        return true;
    }

    public boolean addAll(Collection<Car> cars) {
        int addedCars = 0;

        for (Car car : cars) {
            if (this.cars.contains(car)) {
                continue;
            }

            addedCars ++;
            this.cars.add(car);
            modelToCars.get(car.getModel()).add(car);
            regNumToCar.put(car.getRegistrationNumber(), car);
            totalPriceOfCars += car.getPrice();
        }

        return addedCars > 0;
    }

    public boolean remove(Car car) {
        if (!cars.contains(car)) {
            return false;
        }

        cars.remove(car);
        regNumToCar.remove(car.getRegistrationNumber());
        modelToCars.get(car.getModel()).remove(car);
        totalPriceOfCars -= car.getPrice();
        return true;
    }

    public Collection<Car> getCarsByModel(Model model) {
        return new ArrayList<>(modelToCars.get(model));
    }


    public Car getCarByRegistrationNumber(String registrationNumber) {
        Car car = regNumToCar.get(registrationNumber);

        if (car == null) {
            throw new CarNotFoundException();
        }

        return car;
    }

    public Collection<Car> getCars() {
        List<Car> toReturn = new ArrayList<>(cars);

        Collections.sort(toReturn);

        return toReturn;
    }

    public Collection<Car> getCars(Comparator<Car> comparator) {
        List<Car> toReturn = new ArrayList<>(cars);
        toReturn.sort(comparator);
        return toReturn;
    }

    public Collection<Car> getCars(Comparator<Car> comparator, boolean isReversed) {
        List<Car> toReturn = new ArrayList<>(cars);

        if (isReversed) {
            toReturn.sort(comparator.reversed());
        } else {
            toReturn.sort(comparator);
        }

        return toReturn;
    }

    public int getNumberOfCars() {
        return cars.size();
    }

    public int getTotalPriceForCars() {
        return totalPriceOfCars;
    }
}