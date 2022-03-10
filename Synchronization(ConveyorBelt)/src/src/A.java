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
 * Made by Daniel Thompson for COSC 423, an adaptation of Matthew Evett's Producer class
 */
import java.util.*;

public class A extends Thread {
	public int objectsCreated;
	public boolean thingsToMake;

	public A(BoundedBuffer b) {
		buffer = b;
		this.objectsCreated = 0;
		this.thingsToMake = true;
	}

	public void run() {
		while (this.thingsToMake) {
			BoundedBuffer.napping();

			// produce an item & enter it into the buffer
//			message = new Date();
//			System.out.println("Producer produced " + message);
//
//			buffer.enter(message);
			Widget item = new Widget();
			item.workUpon();
			System.out.println("A made an item.");
			this.buffer.enter(item);
			this.objectsCreated++;
			if (this.objectsCreated == 24) {
				this.thingsToMake = false;
			}
//			try {
//				this.buffer.wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	private BoundedBuffer buffer;
}
