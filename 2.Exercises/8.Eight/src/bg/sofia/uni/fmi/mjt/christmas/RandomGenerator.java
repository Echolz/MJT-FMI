package bg.sofia.uni.fmi.mjt.christmas;

import java.util.Random;

public class RandomGenerator {
    private static Random random = new Random();

    public static int generateRandom(int lowerBound, int upperBound) {
        return random.nextInt(upperBound) + lowerBound;
    }
}
