package bg.fmi.mjt.lab.coffee_machine.supplies;

public class Mochaccino implements Beverage {
    private static final double COFFEE = 18;
    private static final double WATER = 0;
    private static final double MILK = 150;
    private static final double CACAO = 10;
    private static final String NAME = "Mochaccino";

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
