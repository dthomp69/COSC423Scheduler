package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Reads the information from the input.dat file
 * 
 * Daniel Thompson
 */
public class FileReader {

	private ArrayList<Integer> frameNumbers;
	private ArrayList<ArrayList<Page>> pages;

	public FileReader() {
		this.setFrameNumbers(new ArrayList<Integer>());
		this.setPages(new ArrayList<ArrayList<Page>>());
	}

	public boolean findFile(String fileNameInput) {
		try {
			Scanner tryFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\src\\" + fileNameInput));
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	public boolean processInputsFromFile(String fileNameInput) {
		try {
			Scanner tryFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\src\\" + fileNameInput));

			while (tryFile.hasNext()) {
				// Grab the first standalone int, which should be the page number.
				this.frameNumbers.add(tryFile.nextInt());

				ArrayList<Page> pages = new ArrayList<Page>();
				int nextInt = tryFile.nextInt();
				while (nextInt != -1) {
					Page page = new Page(nextInt);
					pages.add(page);
					nextInt = tryFile.nextInt();
				}
				this.pages.add(pages);
			}

			// Return false if it looks like the variables were not set correctly
			if (this.frameNumbers.size() == 0) {
				return false;
			}
			if (this.pages.size() == 0) {
				return false;
			}

			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	public ArrayList<Integer> getFrameNumbers() {
		return frameNumbers;
	}

	public void setFrameNumbers(ArrayList<Integer> frameNumbers) {
		this.frameNumbers = frameNumbers;
	}

	public ArrayList<ArrayList<Page>> getPages() {
		return pages;
	}

	public void setPages(ArrayList<ArrayList<Page>> pages) {
		this.pages = pages;
	}

}
