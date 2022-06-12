// --== CS400 File Header Information ==--
// Name: Xinze Liu
// Email: xliu855@wisc.edu
// Team: IC
// Role: Test Engineer
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * This class test the Commodity class to make sure all the functions works.
 * 
 * @author Xinze Liu
 *
 */
public class TestCommodity {

  Commodity empty = new Commodity("Empty", 0);
  Commodity tidybear = new Commodity("TidyBear", 1.08, 87, 13, false);
  Commodity kitty = new Commodity("Kitty", 3.24, 19, 81, true);

  /**
   * This method tests some basic data of the commodity which are: name, price, stock, sold, and if
   * it need to be restocked.
   */
  @Test
  public void testGetData() {
    // Test getName() method
    assertEquals("Empty", empty.getName());
    assertEquals("TidyBear", tidybear.getName());
    assertEquals("Kitty", kitty.getName());

    // Test getPrice() method
    assertEquals(0.0, empty.getPrice());
    assertEquals(1.08, tidybear.getPrice());
    assertEquals(3.24, kitty.getPrice());

    // Test getStock() method
    assertEquals(100.0, empty.getStock());
    assertEquals(87, tidybear.getStock());
    assertEquals(19, kitty.getStock());

    // Test getSold() method
    assertEquals(0.0, empty.getSold());
    assertEquals(13, tidybear.getSold());
    assertEquals(81, kitty.getSold());

    // Test isNeedRestock() method
    assertTrue(!(empty.isNeedRestock()));
    assertTrue(!(tidybear.isNeedRestock()));
    assertTrue(kitty.isNeedRestock());
  }

  /**
   * This method checks the set methods of commodity
   */
  @Test
  public void testSetting() {
    // Test setName() method
    empty.setName("Nothing");
    tidybear.setName("BearKnight");
    kitty.setName("Princess");

    assertEquals("Nothing", empty.getName());
    assertEquals("BearKnight", tidybear.getName());
    assertEquals("Princess", kitty.getName());

    // Test setPrice() method
    empty.setPrice(0.01);
    tidybear.setPrice(200);
    kitty.setPrice(199);

    assertEquals(0.01, empty.getPrice());
    assertEquals(200, tidybear.getPrice());
    assertEquals(199, kitty.getPrice());

    // Test setStock() method
    empty.setStock(1);
    tidybear.setStock(20);
    kitty.setStock(50);

    assertEquals(1, empty.getStock());
    assertEquals(20, tidybear.getStock());
    assertEquals(50, kitty.getStock());

    // Test setSold() method
    empty.setSold(1);
    tidybear.setSold(67);
    kitty.setSold(18);

    assertEquals(1, empty.getSold());
    assertEquals(67, tidybear.getSold());
    assertEquals(18, kitty.getSold());

    // Test setNeedRestock() method
    empty.setNeedRestock(true);
    tidybear.setNeedRestock(true);
    kitty.setNeedRestock(true);

    assertTrue(empty.isNeedRestock());
    assertTrue(tidybear.isNeedRestock());
    assertTrue(kitty.isNeedRestock());
  }

}
