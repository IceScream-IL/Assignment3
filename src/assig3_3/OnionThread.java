package assig3_3;

public class OnionThread extends Thread {

	private final SlicerMachine machine;

    public OnionThread(SlicerMachine machine) {
        this.machine = machine;
    }
	
    @Override
	public void run() {
		while (!isInterrupted()) {
			machine.addOneOnion();
		}
	}
}
