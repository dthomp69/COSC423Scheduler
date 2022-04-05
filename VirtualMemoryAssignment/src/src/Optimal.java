package src;

import java.util.ArrayList;

public class Optimal {
	Page[] frames;
	ArrayList<Page> inputs;

	// Need to store info for printing:
	ArrayList<Page[]> storedFrames;

	public Optimal(int frameNumber, ArrayList<Page> run) {
		this.frames = new Page[frameNumber];
		this.fillFrames(frameNumber);
		this.inputs = run;

		// Initialize the printing fields:
		this.storedFrames = new ArrayList<Page[]>();
	}

	private void fillFrames(int frameNumber) {
		for (int i = 0; i < this.frames.length; i++) {
			this.frames[i] = new Page(-1);
		}
	}

	public void run() {
		System.out.println("Optimal:");
		System.out.println();

		for (int i = 0; i < this.inputs.size(); i++) {
			// Check and see if the page is just already in memory:
			boolean pageAlreadyInMemory = false;
			int indexOfPageJustUsed = -1;
			for (int k = 0; k < this.frames.length; k++) {
				if (this.frames[k].getPageNumber() == this.inputs.get(i).getPageNumber()) {
					pageAlreadyInMemory = true;
					indexOfPageJustUsed = k;
				}
			}
			if (pageAlreadyInMemory) {
				this.frames[indexOfPageJustUsed].setLastTimeUsed(i);
			}

			if (!pageAlreadyInMemory) {
				int freeFrameIndex = this.areFreeFrames();
				if (freeFrameIndex != -1) {
					this.frames[freeFrameIndex] = this.inputs.get(i);
					this.frames[freeFrameIndex].setTimePutIn(i);
					this.frames[freeFrameIndex].setLastTimeUsed(i);

					// Then, add the current frames to the storedFrames for printing
					Page[] toAddToStored = this.frames.clone();
					this.storedFrames.add(toAddToStored);
				} else {
					// This should only run if there are no empty slots. IE, no pages with timeIndex
					// initialized to -1.
					// If it runs when there are free frames, it will break, since it will treat the
					// empty page as the lowest time index. Proper behavior though?

					// Note:This code does not produce the same output as on the webpage, since it
					// replaces the last frame found that isn't used again, instead of the first
					// frame
					// found that isn't used again.
					// The number of page faults should be the exact same as the optimal algo
					// though.
					int longestUseTime = 0;
					int indexToReplace = 0;
					for (int k = 0; k < this.frames.length; k++) {
						int currentPageNumber = this.frames[k].getPageNumber();
						for (int j = i; j < this.inputs.size(); j++) {
							if (this.inputs.get(j).getPageNumber() == currentPageNumber) {
								if (j > longestUseTime) {
									longestUseTime = j;
									indexToReplace = k;
								} else {
									break;
								}
							}
							if (j + 1 == this.inputs.size()) {
								longestUseTime = this.inputs.size();
								indexToReplace = k;
								break;
							}
						}

					}

					this.frames[indexToReplace] = this.inputs.get(i);

					Page[] toAddToStored = this.frames.clone();
					this.storedFrames.add(toAddToStored);
				}
			} else {
				Page[] toAddToStored = this.frames.clone();
				this.storedFrames.add(toAddToStored);
			}
		}
	}

	// Return -1 if there are no free frames, otherwise, return the index of the
	// free frame
	public int areFreeFrames() {
		for (int i = 0; i < this.frames.length; i++) {
			if (this.frames[i].getPageNumber() == -1) {
				return i;
			}
		}
		return -1;
	}

	public void printStoredFrames() {
		int realLengthOfInput = this.inputs.size();
		while (realLengthOfInput > 40) {
			int lengthOfInput = 40;

			// Print out the input string
			for (int i = 0; i < lengthOfInput; i++) {
				System.out.print(this.inputs.get(i).getPageNumber() + " ");
			}
			System.out.println();

			// Print out a filler line
			for (int i = 0; i < lengthOfInput; i++) {
				System.out.print("__");
			}
			System.out.println();

			// Print out the stored frames
			for (int j = 0; j < this.frames.length; j++) {
				int lastPrinted0 = -1;
				int lastPrinted1 = -1;
				int lastPrinted2 = -1;
				for (int i = 0; i < lengthOfInput; i++) {
					if (this.storedFrames.get(i)[j].getPageNumber() == -1) {
						System.out.print(0 + " ");
					} else {
						boolean shouldPrint = true;
						for (int k = 0; k < this.storedFrames.get(i).length; k++) {
							if (this.storedFrames.get(i)[0].getPageNumber() == lastPrinted0
									&& this.storedFrames.get(i)[1].getPageNumber() == lastPrinted1
									&& this.storedFrames.get(i)[2].getPageNumber() == lastPrinted2) {
								shouldPrint = false;
							}
						}
						if (shouldPrint) {

							System.out.print(this.storedFrames.get(i)[j].getPageNumber() + " ");
							if (j == 0) {
								lastPrinted0 = this.storedFrames.get(i)[j].getPageNumber();
							}
							if (j == 1) {
								lastPrinted1 = this.storedFrames.get(i)[j].getPageNumber();
							}
							if (j == 2) {
								lastPrinted2 = this.storedFrames.get(i)[j].getPageNumber();
							}
						} else {
							System.out.print("  ");
						}
					}
				}
				System.out.println();
			}
			System.out.println();

			realLengthOfInput -= 40;
		}
		int lengthOfInput = realLengthOfInput;

		// Print out the input string
		for (int i = 0; i < lengthOfInput; i++) {
			System.out.print(this.inputs.get(i).getPageNumber() + " ");
		}
		System.out.println();

		// Print out a filler line
		for (int i = 0; i < lengthOfInput; i++) {
			System.out.print("__");
		}
		System.out.println();

		// Print out the stored frames
		for (int j = 0; j < this.frames.length; j++) {
			int lastPrinted0 = -1;
			int lastPrinted1 = -1;
			int lastPrinted2 = -1;
			for (int i = 0; i < lengthOfInput; i++) {
				if (this.storedFrames.get(i)[j].getPageNumber() == -1) {
					System.out.print(0 + " ");
				} else {
					boolean shouldPrint = true;
					for (int k = 0; k < this.storedFrames.get(i).length; k++) {
						if (this.storedFrames.get(i)[0].getPageNumber() == lastPrinted0
								&& this.storedFrames.get(i)[1].getPageNumber() == lastPrinted1
								&& this.storedFrames.get(i)[2].getPageNumber() == lastPrinted2) {
							shouldPrint = false;
						}
					}
					if (shouldPrint) {

						System.out.print(this.storedFrames.get(i)[j].getPageNumber() + " ");
						if (j == 0) {
							lastPrinted0 = this.storedFrames.get(i)[j].getPageNumber();
						}
						if (j == 1) {
							lastPrinted1 = this.storedFrames.get(i)[j].getPageNumber();
						}
						if (j == 2) {
							lastPrinted2 = this.storedFrames.get(i)[j].getPageNumber();
						}
					} else {
						System.out.print("  ");
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}

}
