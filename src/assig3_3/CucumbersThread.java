package assig3_3;

public class CucumbersThread extends Thread {

	private final SlicerMachine machine;
	
    public CucumbersThread(SlicerMachine machine) {
        this.machine = machine;
    }
	
    @Override
	public void run() {
		while (!isInterrupted()) {
			machine.addOneCucumber();
		}
	}
}
