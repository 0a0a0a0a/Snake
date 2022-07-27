package game;

@SuppressWarnings("all")
public class Die extends RuntimeException {
	
	public Die() {
		super("you died!");
	}
}
