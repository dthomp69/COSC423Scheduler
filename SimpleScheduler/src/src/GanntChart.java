package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>
 * Title: GanntChart
 * </p>
 * <p>
 * Description: Maintain data necessary to render a Gannt chart.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015, 2004 by Matt Evett
 * </p>
 * 
 * @author Matt Evett
 * @version 2.0 simulates the scheduler
 */

public class GanntChart {
	private long systemStartTime; // wall time when the Gannt chart starts. Is used
									// to display all timings as relative to this time
	private ArrayList<GanntRecord> events = new ArrayList<GanntRecord>();

	public GanntChart() {
		// TODO: Write code to actually output the gannt chart
	}

	public void start() {
		systemStartTime = System.currentTimeMillis(); // set os start time
	}

	public void recordEvent(long startTime, long endTime, String eventDescriptor) {
		events.add(new GanntRecord(startTime, endTime, eventDescriptor));
	}

	public void end() {
		long endTime = System.currentTimeMillis();
		events.add(new GanntRecord(endTime, endTime, "FINISHED"));
	}

	public void print() {
		// System.out.println("TO_DO GanntChart.print not yet implemented");
		// System.out.println("TO_DO GanntChart.print work in progress");

		try {

			File file = new File(System.getProperty("user.dir") + "\\src\\src\\" + "GanntChartOut.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write("Gannt Chart:");
			bufferedWriter.newLine();

			bufferedWriter.write("BurstStart" + " " + "BurstEnd" + " " + "Job:");
			bufferedWriter.newLine();

			// Need to go through and add gap events to fill out the Gannt Chart.
			for (int i = 0; i < this.events.size(); i++) {
				if ((i + 1) < this.events.size()) {
					if (this.events.get(i).endTime < this.events.get(i + 1).startTime) {
						GanntRecord gapRecord = new GanntRecord(this.events.get(i).endTime,
								this.events.get(i + 1).startTime, "IDLE");
						this.events.add(i + 1, gapRecord);
					}
				}
			}

			for (int i = 0; i < this.events.size(); i++) {
				System.out.println(this.events.get(i).startTime + " " + this.events.get(i).endTime + " "
						+ this.events.get(i).eventDescriptor);
				bufferedWriter.write(this.events.get(i).startTime + " " + this.events.get(i).endTime + " "
						+ this.events.get(i).eventDescriptor);
				bufferedWriter.newLine();
			}
			// Add a line so that subsequent runs get added with a gap
			bufferedWriter.newLine();

			bufferedWriter.close();

		} catch (IOException e) {
			System.out.println("Problem writing to GanntOut");
		}
	}

	/**
	 * Inner class to record the data of one Gannt chart event
	 * 
	 * @author matt
	 *
	 */
	private class GanntRecord {
		long startTime;
		long endTime;
		String eventDescriptor;

		GanntRecord(long start, long end, String descrip) {
			startTime = start;
			endTime = end;
			eventDescriptor = descrip;
		}
	}

}
