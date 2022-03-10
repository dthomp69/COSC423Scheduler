package src;

/*
 * Made by Daniel Thompson for COSC 423
 */
public class Factory {

	public static void main(String[] args) {
		BoundedBuffer AB = new BoundedBuffer();
		A A = new A(AB);

		BoundedBuffer BC = new BoundedBuffer();

		BAndC B = new BAndC(AB, BC);

		BoundedBuffer CD = new BoundedBuffer();

		BAndC C = new BAndC(BC, CD);
		D D = new D(CD);

		A.start();
		B.start();
		C.start();
		D.start();

	}

}
