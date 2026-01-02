package assig3_3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Please Type How Many Salads To Prepare:");
        Scanner scan = new Scanner(System.in);
        final int numOfSaladsToPrepare = scan.nextInt();
        System.out.println("Preparing " + numOfSaladsToPrepare + " Salads...");

        SlicerMachine machine = new SlicerMachine();

        CucumbersThread c = new CucumbersThread(machine);
        TomatoesThread t = new TomatoesThread(machine);
        OnionThread o = new OnionThread(machine);
        SlicerThread s = new SlicerThread(machine);

        c.start();
        t.start();
        o.start();
        s.start();

        while (machine.getNumOfPreparedSalads() < numOfSaladsToPrepare) {
            Thread.yield();
        }

        c.interrupt();
        t.interrupt();
        o.interrupt();
        s.interrupt();

        try {
            c.join();
            t.join();
            o.join();
            s.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Done");
        scan.close();
    }
}
