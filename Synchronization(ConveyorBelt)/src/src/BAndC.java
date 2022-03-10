package src;

/**
 * Producer.java
 *
 * This is the producer thread for the bounded buffer problem.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */
/*
 * Made by Daniel Thompson for COSC 423, an adaptation of Matthew Evett's Consumer class
 */
import java.util.*;

public class BAndC extends Thread {
	public int thingsWorkedOn;
	public boolean thingsToWorkOn;

	public BAndC(BoundedBuffer b, BoundedBuffer b2) {
		buffer = b;
		buffer2 = b2;
		this.thingsWorkedOn = 0;
		this.thingsToWorkOn = true;
	}

	public void run() {
		while (this.thingsToWorkOn) {
			BoundedBuffer.napping();

			// produce an item & enter it into the buffer
//			message = new Date();
//			System.out.println("Producer produced " + message);
//
//			buffer.enter(message);
			Widget item = (Widget) this.buffer.remove();
			System.out.println("B or C grabbed an item");
			item.workUpon();
			this.thingsWorkedOn++;
			this.buffer2.enter(item);
//			this.buffer.notifyAll();
//			try {
//				this.buffer2.wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			if (this.thingsWorkedOn == 24) {
				this.thingsToWorkOn = false;
			}
		}
	}

	private BoundedBuffer buffer;
	private BoundedBuffer buffer2;
}
