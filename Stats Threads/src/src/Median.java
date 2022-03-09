package src;

import java.util.Arrays;

public class Median extends Thread {
	private int median;
	private int[] numbers;

	public Median(int[] numbers) {
		this.numbers = numbers;
	}

	@Override
	public void run() {

		try {
			median = 0;
			// Displaying the thread that is running
//			System.out.println("Thread " + Thread.currentThread().getId() + " is running");
			Arrays.sort(this.numbers);

			if (this.numbers.length % 2 == 1) {
				this.median = this.numbers[this.numbers.length / 2];
			}
			if (this.numbers.length % 2 == 0) {
				int first = this.numbers[this.numbers.length / 2];
				int second = this.numbers[this.numbers.length / 2 + 1];
				this.median = (first + second) / 2;

			}

		} catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}
	}

	public int getMedian() {
		return this.median;
	}
}
