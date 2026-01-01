package assig3_2;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		GamePlay game = new GamePlay();
		Gamer gamer1 = new Gamer(game);
		Gamer gamer2 = new Gamer(game);
		Judge judge = new Judge(game);

		Thread t1 = new Thread(gamer1);
		Thread t2 = new Thread(gamer2);
		Thread t3 = new Thread(judge, "judge");

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();

		t3.interrupt();
		t3.join();

		if (gamer1.getScore() > gamer2.getScore()) {
			System.out.println(t1.getName() + " wins");
		} else if (gamer2.getScore() > gamer1.getScore()) {
			System.out.println(t2.getName() + " wins");
		} else {
			System.out.println("tie");
		}
	}
}
