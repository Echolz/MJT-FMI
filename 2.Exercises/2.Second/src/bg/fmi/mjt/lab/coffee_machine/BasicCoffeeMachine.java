package bg.fmi.mjt.lab.coffee_machine;

import bg.fmi.mjt.lab.coffee_machine.container.BasicCoffeeContainer;
import bg.fmi.mjt.lab.coffee_machine.container.Container;
import bg.fmi.mjt.lab.coffee_machine.supplies.Beverage;

public class BasicCoffeeMachine implements CoffeeMachine {
    private static final double COFFEE_AMOUNT = 600;
    private static final double WATER_AMOUNT = 600;
    private static final double MILK_AMOUNT = 0;
    private static final double CACAO_AMOUNT = 0;

    private BasicCoffeeContainer container;

    public BasicCoffeeMachine() {
        this.container = new BasicCoffeeContainer(COFFEE_AMOUNT, WATER_AMOUNT);
    }

    private boolean hasEnoughSupplies(Beverage beverage) {
        return container.getCurrentCacao() >= beverage.getCacao() &&
        container.getCurrentCoffee() >= beverage.getCoffee() &&
        container.getCurrentMilk() >= beverage.getMilk() &&
        container.getCurrentWater() >= beverage.getWater();
    }

    @Override
    public Product brew(Beverage beverage) {
        if (!(hasEnoughSupplies(beverage))) {
            return null;
        }

        this.container.updateSupplies(beverage);

        return new Product(beverage.getName(), 1, null);
    }

    @Override
    public Container getSupplies() {
        return new BasicCoffeeContainer(container.getCurrentCoffee(), container.getCurrentWater());
    }

    @Override
    public void refill() {
        container.refill();
    }
}
