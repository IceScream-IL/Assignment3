package assig3_2;

import java.util.concurrent.ThreadLocalRandom;

public class GamePlay {

    private boolean coin_available_;
    private int rounds_counter_;

    public synchronized void makeCoinAvail(boolean val) {
        boolean wasAvailable = coin_available_;
        coin_available_ = val;

        if (val && !wasAvailable) {
            notifyAll();
        }
    }

    public synchronized int flipCoin() {
        while (!coin_available_) {
            System.out.println(Thread.currentThread().getName() + " is waiting for coin");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("flipCoin was interrupted", e);
            }
        }

        makeCoinAvail(false);
        System.out.println(Thread.currentThread().getName() + " is flipping coin");

        rounds_counter_++;
        int result = ThreadLocalRandom.current().nextInt(2);

        makeCoinAvail(true);
        return result;
    }

    public synchronized int getNumOfRounds() {
        return rounds_counter_;
    }
}
