package game;

public interface Effect extends Runnable {
	
	static void kill() {
		PowerUp.trigger.interrupt();
	}
}
