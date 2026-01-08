package assig3_4;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Q4 {

    static final Semaphore semA = new Semaphore(1);
    static final Semaphore semB = new Semaphore(0);
    static final Semaphore semC = new Semaphore(0);
    static final Semaphore semD = new Semaphore(0);

    static volatile int aCount = 0;
    static final ThreadLocal<Integer> cCount = ThreadLocal.withInitial(() -> 0);

    static void A() { System.out.print("A"); }
    static void B() { System.out.print("B"); }
    static void C() { System.out.print("C"); }
    static void D() { System.out.print("D"); }

    static boolean keepGoing() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    static class t1 extends Thread {
        public void run() {
            while (true) {
                try {
                    semA.acquire();

                    if (aCount < 3) {
                        A();
                        aCount++;

                        if (aCount < 3) {
                            semA.release();
                        } else {
                            semB.release();
                        }
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    static class t2 extends Thread {
        public void run() {
            while (true) {
                try {
                    semB.acquire();

                    // B
                    B();

                    if (keepGoing()) {
                        semB.release();
                    } else {
                        semC.release();
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    static class t3 extends Thread {
        public void run() {
            while (true) {
                try {
                    semC.acquire();

                    C();

                    int cnt = cCount.get() + 1;
                    cCount.set(cnt);

                    if (cnt < 2) {
                        semC.release();
                    } else {
                        cCount.set(0);
                        semD.release();
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    static class t4 extends Thread {
        public void run() {
            while (true) {
                try {
                    semD.acquire();

                    D();

                    if (keepGoing()) {
                        semD.release();
                    } else {
                        semB.release();
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new t1();
        Thread t2 = new t2();
        Thread t3 = new t3();
        Thread t4 = new t4();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
