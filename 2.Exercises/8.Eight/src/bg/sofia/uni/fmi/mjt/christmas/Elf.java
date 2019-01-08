package bg.sofia.uni.fmi.mjt.christmas;

public class Elf implements Runnable{
    private static int ID;
    private int id;
    private Workshop workshop;
    private int totalGiftsCrafted = 0;

    public Elf(int id, Workshop workshop) {
        this.id = id;
        this.workshop = workshop;
    }

    public Elf(Workshop workshop) {
        this(ID, workshop);
        ID++;
    }

    /**
     * Gets a wish from the backlog and creates the wanted gift.
     **/
    public void craftGift() throws InterruptedException {
        Gift gift = workshop.nextGift();
        if (gift == null) {
            System.out.println(id + "finished working");
            return;
        }
        Thread.sleep(gift.getCraftTime() * 100);
        System.out.println("Gift " + gift + " was done by: " + id);
    }

    /**
     * Returns the total number of gifts that the given elf has crafted.
     **/
    public int getTotalGiftsCrafted() {
        return totalGiftsCrafted;
    }

    @Override
    public void run() {
        try {
            craftGift();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}