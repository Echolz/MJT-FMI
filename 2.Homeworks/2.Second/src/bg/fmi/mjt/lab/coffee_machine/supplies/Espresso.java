package bg.fmi.mjt.lab.coffee_machine.supplies;

public class Espresso implements Beverage {
    private static final double COFFEE = 10;
    private static final double WATER = 30;
    private static final double MILK = 0;
    private static final double CACAO = 0;
    private static final String NAME = "Espresso";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getMilk() {
        return MILK;
    }

    @Override
    public double getCoffee() {
        return COFFEE;
    }

    @Override
    public double getWater() {
        return WATER;
    }

    @Override
    public double getCacao() {
        return CACAO;
    }
}
