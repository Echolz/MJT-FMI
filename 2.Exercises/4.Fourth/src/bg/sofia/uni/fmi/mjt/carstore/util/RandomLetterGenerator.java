package bg.sofia.uni.fmi.mjt.carstore.util;

public class RandomLetterGenerator {

    private final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static Character getRandomLetter() {
        int random = (int) (Math.random() * alphabet.length() - 1 + 0);

        return alphabet.charAt(random);
    }
}
