package bg.fmi.mjt.lab.coffee_machine.container;

import bg.fmi.mjt.lab.coffee_machine.supplies.Beverage;

public class BasicCoffeeContainer extends Container {
    private final double coffee;
    private final double water;

    private double currentCoffeeLeft;
    private double currentWaterLeft;

    public BasicCoffeeContainer(double initialCoffee, double initialWater) {
        this.currentCoffeeLeft = initialCoffee;
        this.coffee = initialCoffee;

        this.currentWaterLeft = initialWater;
        this.water = initialWater;
    }

    public void refill() {
        currentCoffeeLeft = coffee;
        currentWaterLeft = water;
    }

    public void updateSupplies(Beverage beverage) {
        this.currentCoffeeLeft -= beverage.getCoffee();
        this.currentWaterLeft -= beverage.getWater();
    }

    @Override

    public double getCurrentWater() {
        return currentWaterLeft;
    }

    @Override
    public double getCurrentMilk() {
        return 0;
    }

    @Override
    public double getCurrentCoffee() {
        return currentCoffeeLeft;
    }

    @Override
    public double getCurrentCacao() {
        return 0;
    }
}
