package bg.sofia.uni.fmi.mjt.christmas;

public class Kid implements Runnable{

    private Workshop workshop;

    public Kid(Workshop workshop) {
        this.workshop = workshop;
    }

    /**
     * Sends a wish for the given gift to Santa's workshop.
     **/
    public void makeWish(Gift gift) {
        int randomTime = RandomGenerator.generateRandom(10000, 15000);
        System.out.println(randomTime);
        try {
            Thread.sleep(randomTime);
        } catch (InterruptedException ignored) {
        }

        workshop.postWish(gift);
    }

    @Override
    public void run() {
        makeWish(Gift.getGift());
    }
}