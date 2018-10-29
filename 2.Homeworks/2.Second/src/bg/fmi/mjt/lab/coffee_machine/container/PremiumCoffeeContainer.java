package bg.fmi.mjt.lab.coffee_machine.container;

import bg.fmi.mjt.lab.coffee_machine.supplies.Beverage;

public class PremiumCoffeeContainer extends BasicCoffeeContainer {
    private final double milk;
    private final double cacao;

    private double currentMilkLeft;
    private double currentCacaoLeft;

    public PremiumCoffeeContainer(double initialCoffee, double initialWater, double initialMilk, double initialCacao) {
        super(initialCoffee, initialWater);

        currentMilkLeft = initialMilk;
        milk = initialMilk;

        currentCacaoLeft = initialCacao;
        cacao = initialCacao;
    }

    @Override
    public void refill() {
        super.refill();

        currentMilkLeft = milk;
        currentCacaoLeft = cacao;
    }

    @Override
    public double getCurrentMilk() {
        return currentMilkLeft;
    }

    @Override
    public double getCurrentCacao() {
        return currentCacaoLeft;
    }

    @Override
    public void updateSupplies(Beverage beverage) {
        super.updateSupplies(beverage);

        currentMilkLeft -= beverage.getMilk();
        currentCacaoLeft -= beverage.getCacao();
    }
}
