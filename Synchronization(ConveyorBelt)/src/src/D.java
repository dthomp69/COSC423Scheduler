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

public class D extends Thread {
	public ArrayList<Widget> widgets;
	private boolean thingsToWorkOn;

	public D(BoundedBuffer b) {
		buffer = b;
		this.widgets = new ArrayList<Widget>();
		this.thingsToWorkOn = true;
	}

	public void run() {
		while (this.thingsToWorkOn) {
			BoundedBuffer.napping();

			// produce an item & enter it into the buffer
			Widget item = (Widget) this.buffer.remove();
			item.workUpon();
			this.widgets.add(item);
			System.out.println("D grabbed an item." + "Widgets size: " + this.widgets.size());
//			this.buffer.notifyAll();
			if (this.widgets.size() == 24) {
				this.thingsToWorkOn = false;
				this.printWidgets();
			}
		}
	}

	private void printWidgets() {
		int i = 0;
		for (Widget item : this.widgets) {
			System.out.println("Widget " + i + " workedUpon value: " + item.getWorkedUpon());
			i++;
		}
	}

	private BoundedBuffer buffer;
}
