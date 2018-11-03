package bg.fmi.mjt.lab.coffee_machine;

import bg.fmi.mjt.lab.coffee_machine.container.Container;
import bg.fmi.mjt.lab.coffee_machine.supplies.Beverage;

public interface CoffeeMachine {
    Product brew (Beverage beverage);

    Container getSupplies();

    void refill();
}
