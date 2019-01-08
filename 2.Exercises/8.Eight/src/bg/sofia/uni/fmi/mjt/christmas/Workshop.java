package bg.sofia.uni.fmi.mjt.christmas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Workshop extends Thread {

    private BlockingDeque<Gift> pendingGifts;
    private List<Elf> elves;

    public Workshop() {
        this(20);
    }

    public Workshop(int elvesCount) {
        pendingGifts = new LinkedBlockingDeque<>();
        initElves(elvesCount);
    }

    /**
     * Adds a gift to the elves' backlog.
     **/
    public void postWish(Gift gift) {
        pendingGifts.addLast(gift);
    }

    /**
     * Returns the next gift from the elves' backlog that has to be manufactured.
     **/
    public Gift nextGift() {
        return pendingGifts.pollFirst();
    }

    /**
     * Returns an array of the elves working in Santa's workshop.
     **/
    public Elf[] getElves() {
        Elf[] toReturn = new Elf[elves.size()];
        elves.toArray(toReturn);
        return toReturn;
    }

    /**
     * Returns the total number of wishes sent to Santa's workshop by the kids.
     **/
    public int getWishCount() {
        return pendingGifts.size();
    }

    private void initElves(int elvesCount) {
        elves = new ArrayList<>();
        for (int i = 0; i < elvesCount; i++) {
            elves.add(new Elf(this));
        }
    }

    @Override
    public void run() {
        for (Elf elf : elves) {
            new Thread(elf).start();
        }
    }
}