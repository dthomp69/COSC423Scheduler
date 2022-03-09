package src;

public class Minimum extends Thread {

	private int[] numbers;
	private int lowest;

	public Minimum(int[] numbers) {
		this.numbers = numbers;
	}

	@Override
	public void run() {
		try {
			// Displaying the thread that is running
//			System.out.println("Thread " + Thread.currentThread().getId() + " is running");
			int currentLowest = -1;
			for (int i = 0; i < numbers.length; i++) {
				if (currentLowest == -1) {
					currentLowest = numbers[i];
				}
				if (currentLowest > numbers[i]) {
					currentLowest = numbers[i];
				}
			}
			this.lowest = currentLowest;

//			System.out.println("Minimum: " + this.lowest);

		} catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}
	}

	public int getMinimum() {
		return this.lowest;
	}

}
