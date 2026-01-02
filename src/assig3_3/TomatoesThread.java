package assig3_3;

public class TomatoesThread extends Thread {

	private final SlicerMachine machine;
	
    public TomatoesThread(SlicerMachine machine) {
        this.machine = machine;
    }
	
    @Override
	public void run() {
		while (!isInterrupted()) {
			machine.addOneTomato();
		}
	}
}
