package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.FileReader;
import src.Page;

class TestFileReader {

	private FileReader fileReader;

	@BeforeEach
	public void before() {
		FileReader testThis = new FileReader();
		this.fileReader = testThis;
	}

	@Test
	void testFindFile() {
		boolean output = fileReader.findFile("input0.dat");
		assertEquals(true, output);
	}

	@Test
	void testFindFileFailure() {
		boolean output = fileReader.findFile("fakefile");
		assertEquals(false, output);
	}

	@Test
	void testProcessInputsFromFile() {
		boolean output = fileReader.processInputsFromFile("input0.dat");
		assertEquals(true, output);
	}

	@Test
	void testProcessInputsFromFileFailure() {
		boolean output = fileReader.processInputsFromFile("fakefile");
		assertEquals(false, output);
	}

	// The below two tests are not done correctly. Basically, since it's a file IO,
	// the validation process for making sure the inputs are read correctly is hard
	// to do with TDD, and this is the best I came up with

	// Makes sure that the frame numbers are set correctly from input0.dat
	@Test
	void testGetFrameNumbers() {
		boolean output = fileReader.processInputsFromFile("input0.dat");
		assertEquals(true, output);

		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(3);
		expected.add(4);

		ArrayList<Integer> actual = fileReader.getFrameNumbers();

		assertEquals(expected, actual);
	}

	@Test
	void testGetPageNumbers() {
		boolean output = fileReader.processInputsFromFile("input0.dat");
		assertEquals(true, output);

		ArrayList<ArrayList<Page>> expected = new ArrayList<ArrayList<Page>>();
		ArrayList<Page> first = new ArrayList<Page>();
		first.add(new Page(1));
		first.add(new Page(2));
		first.add(new Page(3));
		first.add(new Page(4));
		first.add(new Page(3));
		first.add(new Page(4));
		first.add(new Page(2));
		first.add(new Page(3));
		first.add(new Page(5));
		first.add(new Page(6));
		first.add(new Page(4));
		first.add(new Page(2));
		first.add(new Page(1));
		first.add(new Page(2));

		expected.add(first);

		ArrayList<Page> second = new ArrayList<Page>();
		second.add(new Page(1));
		second.add(new Page(2));
		second.add(new Page(3));
		second.add(new Page(4));
		second.add(new Page(2));
		second.add(new Page(7));
		second.add(new Page(5));
		second.add(new Page(1));
		second.add(new Page(1));
		second.add(new Page(6));
		second.add(new Page(4));
		second.add(new Page(7));
		second.add(new Page(2));
		second.add(new Page(1));
		second.add(new Page(2));
		second.add(new Page(5));

		expected.add(second);

		ArrayList<ArrayList<Page>> actual = fileReader.getPages();

		assertEquals(expected, actual);
	}

}
