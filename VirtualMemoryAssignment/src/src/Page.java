package src;

public class Page {

	private int pageNumber;

	private int timePutIn;

	public Page(int nextInt) {
		this.setPageNumber(nextInt);
		this.setTimePutIn(-1);
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

}
