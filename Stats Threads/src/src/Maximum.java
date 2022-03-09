package src;

public class Maximum extends Thread {

	private int[] numbers;
	private int highest;

	public Maximum(int[] numbers) {
		this.numbers = numbers;
	}

	@Override
	public void run() {
		try {
			// Displaying the thread that is running
//			System.out.println("Thread " + Thread.currentThread().getId() + " is running");
			int currentHighest = -1;
			for (int i = 0; i < numbers.length; i++) {
				if (currentHighest == -1) {
					currentHighest = numbers[i];
				}
				if (currentHighest < numbers[i]) {
					currentHighest = numbers[i];
				}
			}
			this.highest = currentHighest;

//			System.out.println("Maximum: " + this.highest);

		} catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}
	}

	public int getMaximum() {
		return this.highest;
	}
}
