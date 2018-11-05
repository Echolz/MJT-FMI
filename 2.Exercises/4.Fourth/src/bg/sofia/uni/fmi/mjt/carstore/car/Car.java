package bg.sofia.uni.fmi.mjt.carstore.car;

import bg.sofia.uni.fmi.mjt.carstore.enums.EngineType;
import bg.sofia.uni.fmi.mjt.carstore.enums.Model;
import bg.sofia.uni.fmi.mjt.carstore.enums.Region;
import bg.sofia.uni.fmi.mjt.carstore.util.RandomLetterGenerator;

import java.util.Objects;

public abstract class Car implements Comparable<Car> {
    private Model model;
    private int year;
    private int price;
    private EngineType engineType;
    private Region region;
    private String registrationNumber;

    protected Car(Model model, int year, int price, EngineType engineType, Region region) {
        this.model = model;
        this.year = year;
        this.price = price;
        this.engineType = engineType;
        this.region = region;
        setRegistrationNumber();
    }

    public Model getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    private void setRegistrationNumber() {
        this.registrationNumber = String.format("%s%d%c%c", region.getPrefix(), region.getCarsInRegion(), RandomLetterGenerator.getRandomLetter(), RandomLetterGenerator.getRandomLetter());
    }

    @Override
    public boolean equals(Object o) {
        try {
            Car car = (Car) o;
            return this.registrationNumber.equals(car.registrationNumber);
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }

    @Override
    public String toString() {
        return this.registrationNumber;
    }

    @Override
    public int compareTo(Car car) {
        if (model.compareTo(car.model) == 0) {
            if (car.year - year == 0) {

                if (car.hashCode() == hashCode()) {
                    return 0;
                }

                return 1;
            }

            return Integer.compare(year, car.year);
        }

        return model.compareTo(car.model);
    }
}
