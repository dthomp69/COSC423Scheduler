package src;

import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

	public Runner() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		FileReader fileReader = new FileReader();

		Scanner scanner = new Scanner(System.in);

		System.out.println("What is the name of the file you would like to find?");
		String fileNameInput = scanner.nextLine();
		System.out.println("Looking for file: " + fileNameInput);
		System.out.println();

		boolean foundFile = fileReader.findFile(fileNameInput);
		if (foundFile) {
			System.out.println("Found the file.");
			System.out.println();

			boolean processedFile = fileReader.processInputsFromFile(fileNameInput);
			if (processedFile) {

				ArrayList<Integer> numberOfFrames = fileReader.getFrameNumbers();
				ArrayList<ArrayList<Page>> pages = fileReader.getPages();

				// Now hand this information off to the algorithms:
				// Do a separate run of all for, for each of the sample input strings.
				// We can see how many input strings there are by checking the size of the
				// numberOfFrames ArrayList.
				for (int i = 0; i < numberOfFrames.size(); i++) {
					int frameNumber = numberOfFrames.get(i);
					ArrayList<Page> run = pages.get(i);

//					System.out.println("Running input string: ");
//					
//					for (int j = 0; j < run.size(); j++) {
//						System.out.print(run.get(j).getPageNumber() + " ");
//					}
//					System.out.println();
//					System.out.println();
					System.out.println("Running input string: ");

					int relativeSize = run.size();

					while (relativeSize > 40) {

						for (int j = 0; j < 40; j++) {
							System.out.print(run.get(j).getPageNumber() + " ");
						}
						System.out.println();
						relativeSize -= 40;
					}
					for (int j = 0; j < relativeSize; j++) {
						System.out.print(run.get(j).getPageNumber() + " ");
					}
					System.out.println();
					System.out.println();

					// First, FIFO
					FirstInFirstOut FIFO = new FirstInFirstOut(frameNumber, run);
					FIFO.run();
					FIFO.printStoredFrames();
					int FIFOfaults = FIFO.calculatePageFaults();

					// Second, LRU
					LeastRecentlyUsed LRU = new LeastRecentlyUsed(frameNumber, run);
					LRU.run();
					LRU.printStoredFrames();
					int LRUfaults = LRU.calculatePageFaults();

					// Thirdly, LFU
					LeastFrequentlyUsed LFU = new LeastFrequentlyUsed(frameNumber, run);
					LFU.run();
					LFU.printStoredFrames();
					int LFUfaults = LFU.calculatePageFaults();

					// Lastly, Optimal
					Optimal optimal = new Optimal(frameNumber, run);
					optimal.run();
					optimal.printStoredFrames();
					int optimalFaults = optimal.calculatePageFaults();

					double fifoPercent = (((double) FIFOfaults / (double) optimalFaults) * 100);
					double lrupercent = (((double) LRUfaults / (double) optimalFaults) * 100);
					double lfupercent = (((double) LFUfaults / (double) optimalFaults) * 100);

					System.out.println("|Algo|" + "|Number of faults|" + "|%Optimal|");
					System.out.println("FIFO Faults: " + FIFOfaults + " " + fifoPercent + "%");
					System.out.println("LRU Faults: " + LRUfaults + " " + lrupercent + "%");
					System.out.println("LFU Faults: " + LFUfaults + " " + lfupercent + "%");
					System.out.println("Optimal Faults: " + optimalFaults + " " + "100%");
					System.out.println();
					System.out.println();

				}
			} else {
				System.out.println("FileReader couldn't process the file.");
			}

		} else {
			System.out.println("Couldn't find the file.");
		}

		// Close the scanner!
		scanner.close();
	}

}
