package bg.fmi.mjt.lab.coffee_machine;

import bg.fmi.mjt.lab.coffee_machine.container.Container;
import bg.fmi.mjt.lab.coffee_machine.container.PremiumCoffeeContainer;
import bg.fmi.mjt.lab.coffee_machine.supplies.Beverage;
import bg.fmi.mjt.lab.coffee_machine.utils.LuckGenerator;

public class PremiumCoffeeMachine implements CoffeeMachine {
    private static final double COFFEE_AMOUNT = 1000;
    private static final double WATER_AMOUNT = 1000;
    private static final double MILK_AMOUNT = 1000;
    private static final double CACAO_AMOUNT = 300;

    private PremiumCoffeeContainer container;
    private LuckGenerator luckGenerator;

    private boolean autoRefill;

    public PremiumCoffeeMachine() {
        container = new PremiumCoffeeContainer(COFFEE_AMOUNT, WATER_AMOUNT, MILK_AMOUNT, CACAO_AMOUNT);
        luckGenerator = new LuckGenerator();
        autoRefill = false;
    }

    public PremiumCoffeeMachine(boolean autoRefill) {
        this();
        this.autoRefill = autoRefill;
    }

    private boolean hasEnoughSupplies(Beverage beverage) {
        return container.getCurrentCacao() >= beverage.getCacao() &&
                container.getCurrentCoffee() >= beverage.getCoffee() &&
                container.getCurrentMilk() >= beverage.getMilk() &&
                container.getCurrentWater() >= beverage.getWater();
    }

    @Override
    public Product brew(Beverage beverage) {
        return brew(beverage, 1);
    }

    public Product brew(Beverage beverage, int quantity) {
        if (!(0 <= quantity && quantity <= 3)) {
            return null;
        }

        if (!(hasEnoughSupplies(beverage))) {
            if (!autoRefill) {
                return null;
            }

            refill();
        }

        container.updateSupplies(beverage);

        return new Product(beverage.getName(), quantity, luckGenerator.generateLuck());
    }

    @Override
    public Container getSupplies() {
        return new PremiumCoffeeContainer(container.getCurrentCoffee(), container.getCurrentWater(), container.getCurrentMilk(), container.getCurrentCacao());
    }

    @Override
    public void refill() {
        container.refill();
    }
}
