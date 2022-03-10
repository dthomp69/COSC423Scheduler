package src;

/*
 * Made by Daniel Thompson for COSC 423
 */
public class Widget {
	private int workedUpon;

	public Widget() {
		this.workedUpon = 0;
	}

	public void workUpon() {
		this.workedUpon++;
	}

	public int getWorkedUpon() {
		return this.workedUpon;
	}
}
