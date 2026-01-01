package assig3_2;

public class Gamer implements Runnable {
    private int goodFlipsCounter;
    private final GamePlay game;

    public Gamer(GamePlay game) {
        this.game = game;
        this.goodFlipsCounter = 0;
    }

    public void play() {
        int res = game.flipCoin();
        if (res == 1) {
            goodFlipsCounter++;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted() && game.getNumOfRounds() < 10) {
                play();
            }
        } catch (RuntimeException e) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            throw e;
        }
    }


    public int getScore() {
        return goodFlipsCounter;
    }
}
