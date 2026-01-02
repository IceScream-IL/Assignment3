package assig3_3;

public class SlicerThread extends Thread {
    private final SlicerMachine machine;

    public SlicerThread(SlicerMachine machine) {
        this.machine = machine;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            machine.sliceVegetables();
        }
    }
}
