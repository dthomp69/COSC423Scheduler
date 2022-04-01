package src;

public class Page {

	private int pageNumber;

	public Page(int nextInt) {
		this.setPageNumber(nextInt);
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public boolean equals(Object page) {
		if (((Page) page).getPageNumber() == this.pageNumber) {
			return true;
		} else {
			return false;
		}
	}

}
