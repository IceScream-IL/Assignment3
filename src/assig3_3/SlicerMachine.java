package assig3_3;

public class SlicerMachine {

    private int numOfCucumbers = 0;
    private int numOfTomatoes = 0;
    private int numOfOnions = 0;
    private int numOfPreparedSalads = 0;

    private final int cucumbersNeededForOneSalad = 5;
    private final int tomatoesNeededForOneSalad = 3;
    private final int onionsNeededForOneSalad = 3;

    public synchronized void addOneCucumber() {
        try {
            while (numOfCucumbers >= cucumbersNeededForOneSalad) {
                wait();
            }
            System.out.println("adding one cucumber to the machine");
            numOfCucumbers++;
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void addOneTomato() {
        try {
            while (numOfTomatoes >= tomatoesNeededForOneSalad) {
                wait();
            }
            System.out.println("adding one tomato to the machine");
            numOfTomatoes++;
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void addOneOnion() {
        try {
            while (numOfOnions >= onionsNeededForOneSalad) {
                wait();
            }
            System.out.println("adding one onion to the machine");
            numOfOnions++;
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void sliceVegetables() {
        try {
            while (!canMakeSalad()) {
                wait();
            }
            makeNewSalad();
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean canMakeSalad() {
        return numOfOnions >= onionsNeededForOneSalad
            && numOfCucumbers >= cucumbersNeededForOneSalad
            && numOfTomatoes >= tomatoesNeededForOneSalad;
    }

    private void makeNewSalad() {
        System.out.println("== preparing one more salad ==");
        numOfPreparedSalads++;

        numOfTomatoes -= tomatoesNeededForOneSalad;
        numOfCucumbers -= cucumbersNeededForOneSalad;
        numOfOnions -= onionsNeededForOneSalad;
    }

    public synchronized int getNumOfPreparedSalads() {
        return numOfPreparedSalads;
    }
}
