package bg.sofia.uni.fmi.mjt.christmas;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Workshop workshop = new Workshop();



        for (int i = 0; i < 20; i++) {
            Kid c = new Kid(workshop);
            Thread t = new Thread(c);
            t.start();
        }

        System.out.println("wow");

    }
}
