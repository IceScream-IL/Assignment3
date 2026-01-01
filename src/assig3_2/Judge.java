package assig3_2;

public class Judge implements Runnable {

    private final GamePlay game;

    public Judge(GamePlay game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            game.makeCoinAvail(false);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            game.makeCoinAvail(true);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
