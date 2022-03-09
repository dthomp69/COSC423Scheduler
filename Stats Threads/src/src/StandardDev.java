package src;

public class StandardDev extends Thread {
	private double std;
	private int[] numbers;
	private int average;

	public StandardDev(int[] numbers, int average) {
		this.numbers = numbers;
		this.average = average;
	}

	@Override
	public void run() {
		try {
			int sum = 0;
			for (int i = 0; i < this.numbers.length; i++) {
				sum += Math.pow(Math.abs(this.numbers[i] - this.average), 2);
			}
//			System.out.println("Sum: " + sum);
			double sum2 = sum / this.numbers.length;
//			System.out.println("Sum2: " + sum2);
			double sum3 = Math.sqrt(sum2);
//			System.out.println("Sum3: " + sum3);
			this.std = sum3;

		} catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}
	}

	public double getStandardDeviation() {
		return this.std;
	}
}
