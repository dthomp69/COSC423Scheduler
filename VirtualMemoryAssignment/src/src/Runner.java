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

		boolean foundFile = fileReader.findFile(fileNameInput);
		if (foundFile) {
			System.out.println("Found the file.");

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

					// First, FIFO
					FirstInFirstOut FIFO = new FirstInFirstOut(frameNumber, run);
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
