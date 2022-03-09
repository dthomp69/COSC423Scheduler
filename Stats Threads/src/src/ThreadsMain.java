package src;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * https://stackoverflow.com/questions/14635136/read-integers-separated-with-whitespace-into-int-array
 */

public class ThreadsMain {

	public static void main(String[] args) {
		int[] numbers = { 90, 81, 78, 95, 79, 72, 85 };
//		for (int i = 0; i < numbers.length; i++) {
//			System.out.println(numbers[0]);
//		}

		Average av = new Average(numbers);
		Minimum min = new Minimum(numbers);
		Maximum max = new Maximum(numbers);
		av.run();
		min.run();
		max.run();

		Median med = new Median(numbers);
		med.run();

		int average = av.getAverage();
		int minimum = min.getMinimum();
		int maximum = max.getMaximum();

		System.out.println("Average: " + average);
		System.out.println("Minimum: " + minimum);
		System.out.println("Maximum: " + maximum);

		int median = med.getMedian();
		System.out.println("Median: " + median);

		StandardDev std = new StandardDev(numbers, average);
		std.run();
		double stddev = std.getStandardDeviation();
		System.out.println("Standard Deviation: " + stddev);

	}
}
