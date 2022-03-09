package src;

public class Average implements Runnable {

	private int average;
	private int[] numbers;

	public Average(int[] numbers) {
		this.numbers = numbers;
	}

	@Override
	public void run() {

		try {
			average = 0;
			// Displaying the thread that is running
//			System.out.println("Thread " + Thread.currentThread().getId() + " is running");

			int sum = 0;
			for (int i = 0; i < numbers.length; i++) {
				sum += numbers[i];
			}

			int average = (sum / this.numbers.length);
//			System.out.println("Average: " + average);
			this.average = average;

		} catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}
	}

	public int getAverage() {
		return this.average;
	}
}
