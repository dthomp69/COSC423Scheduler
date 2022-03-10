package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.GanntChart;

class TestGanntChart {

	@Test
	void test() {
		GanntChart ganntChart = new GanntChart();
		ganntChart.start();

		ganntChart.recordEvent(0, 200, "Job One");
		ganntChart.recordEvent(500, 800, "Job Two");
		ganntChart.recordEvent(1100, 1400, "Job Three");
		ganntChart.recordEvent(1500, 1800, "Job Four");

		ganntChart.end();

		ganntChart.print();

	}

}
