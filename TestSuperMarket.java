// --== CS400 File Header Information ==--
// Name: Aaron Chen
// Email: kchen339@wisc.edu
// Team: IC
// Role: Test Engineer 1
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This is a test class for the Front End Developer's Main class. This test file
 * contains different method to test if the Main class's methods is functioning
 * and correct.
 * 
 * @author Aaron Chen
 * 
 */

class TestSuperMarket {

	Main storeMain = new Main();

	/**
	 * This method is to test if the Main class can add new commodity into the
	 * hashtable and search for it.
	 * 
	 */

	@Test
	public void testAddNewCommodityAndSearch() {
		storeMain.clear();
		Commodity commodity1 = new Commodity("apple", 0.30);
		Commodity commodity2 = new Commodity("grape", 0.50, 60, 70, false);

		storeMain.addNewCommodity(commodity1);
		storeMain.addNewCommodity(commodity2);

		assertEquals("apple", storeMain.search("apple").getName());
	}

	/**
	 * This method is to test if the Main class can remove a commodity from the
	 * hashtable.
	 */

	@Test
	public void testRemove() {
		storeMain.clear();
		Commodity commodity1 = new Commodity("apple", 0.30);
		Commodity commodity2 = new Commodity("grape", 0.50, 60, 70, false);

		storeMain.addNewCommodity(commodity1);
		storeMain.addNewCommodity(commodity2);

		assertEquals(2, storeMain.store.size());
		storeMain.remove("apple");
		assertEquals(1, storeMain.store.size());
	}

	/**
	 * This method is to test if the Main class can clear all the commodity in the
	 * hashtable.
	 * 
	 */

	@Test
	public void testClear() {
		storeMain.clear();

		Commodity commodity1 = new Commodity("apple", 0.30);
		Commodity commodity2 = new Commodity("grape", 0.50, 60, 70, false);

		storeMain.addNewCommodity(commodity1);
		storeMain.addNewCommodity(commodity2);

		assertEquals(2, storeMain.store.size());
		storeMain.clear();
		assertEquals(0, storeMain.store.size());
	}

	/**
	 * This method is to test if the Main class can edit a commodity in the
	 * hashtable.
	 * 
	 */

	@Test
	public void testEdit() {
		storeMain.clear();

		Commodity commodity1 = new Commodity("apple", 0.30);
		Commodity commodity2 = new Commodity("grape", 0.50, 60, 70, false);

		storeMain.addNewCommodity(commodity1);
		storeMain.addNewCommodity(commodity2);

		Commodity commodity3 = new Commodity("apple", 0.25);

		assertEquals(0.30, storeMain.search("apple").getPrice());

		storeMain.edit(commodity3);

		assertEquals(0.25, storeMain.search("apple").getPrice());
	}

	/**
	 * This method is to test if the Main class can add a csv file into the
	 * hashtable.
	 * 
	 */

	@Test
	public void testAddNewCommodityList() {
		storeMain.clear();
		assertTrue(storeMain.addNewCommodityList());

	}
}
