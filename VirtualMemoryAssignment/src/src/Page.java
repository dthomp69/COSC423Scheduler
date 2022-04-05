package src;

public class Page {

	private int pageNumber;

	// For FIFO
	private int timePutIn;

	// For LRU
	private int lastTimeUsed;

	// For LFU
	private int timesUsed;

	public Page(int nextInt) {
		this.setPageNumber(nextInt);
		this.setTimePutIn(-1);
		this.setLastTimeUsed(-1);
		this.setTimesUsed(1);
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTimePutIn() {
		return timePutIn;
	}

	public void setTimePutIn(int timePutIn) {
		this.timePutIn = timePutIn;
	}

	// Had to override this to get my tests working
	@Override
	public boolean equals(Object page) {
		if (((Page) page).getPageNumber() == this.pageNumber) {
			return true;
		} else {
			return false;
		}
	}

	public int getLastTimeUsed() {
		return lastTimeUsed;
	}

	public void setLastTimeUsed(int lastTimeUsed) {
		this.lastTimeUsed = lastTimeUsed;
	}

	public int getTimesUsed() {
		return timesUsed;
	}

	public void setTimesUsed(int timesUsed) {
		this.timesUsed = timesUsed;
	}

	public void incrementTimesUsed() {
		this.timesUsed++;
	}

}
