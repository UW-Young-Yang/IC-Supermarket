// --== CS400 File Header Information ==--
// Name: Xinze Liu
// Email: xliu855@wisc.edu
// Team: IC
// Role: Test Engineer
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is a test class for HashTableMap which checks the accuracy of the grow(), put(),
 * get(), remove(), clear() methods in HashTableMap.
 * 
 * @author Xinze Liu
 *
 */
public class TestHashTable {
  
  /**
   * This method checks if the HashTableMap double its capacity when the load capacity becomes
   * greater than or equal to 80%. And this method also checks if the size of the map is correct
   * after increasing the capacity.
   * 
   */
  @Test
  public void testGrowth() {
    HashTableMap<Integer, String> map = new HashTableMap<Integer, String>(2);
    map.put(0, "Tommy");
    map.put(1, "Jack");
    map.put(2, "Mary");

    assertEquals(3, map.size());
    
  }

  /**
   * 1.This method checks if the key-value pairs are successfully put into the HashTableMap. 2.This
   * method also considers the collision when adding a pair that has the same hash code as the exist
   * pairs. 3.This method checks if a pair that has the same key as a exist pair is refused to be
   * put in the map.
   * 
   * @return true when the pairs with collision are successfully put into the map and pairs with the
   *         same keys cannot be put into it, and false otherwise
   */
  @Test
  public  void testPut() {
    HashTableMap<Integer, String> map = new HashTableMap<Integer, String>();
    
    assertTrue(map.put(0, "Tommy"));
    assertTrue(map.put(1, "Jack"));
    assertTrue(map.put(2, "Mary"));
    assertTrue(map.put(11, "Eric"));
    assertTrue(!map.put(0, "Danny"));

  }

  /**
   * This method tests the get method, checking if appropriate values are got by calling the get()
   * method. Also this method checks if a NoSuchElementException is thrown when get a pair that is
   * not in the HashTableMap.
   * 
   * @return true when appropriate values are got and NoSuchElementException is thrown when get a
   *         pair that is not in the HashTableMap, and false otherwise
   */
  @Test
  public void testGet() {
    HashTableMap<Integer, String> map = new HashTableMap<Integer, String>(20);
    map.put(0, "Tommy");
    map.put(11, "Mary");
    
    assertEquals("Tommy", map.get(0));

    
    try {
      map.get(2);
      fail("Cannot get a pair that is not in the HashTableMap.");
    } catch (NoSuchElementException e) {
    }
    
  }

  /**
   * This method tests if the pair is successfully removed and the value of it is returned. Also
   * this method checks if the remove() method return null when pass a pair that is not contained in
   * the HashTableMap.
   * 
   * @return true when the pair is removed successfully and return null when remove that pair again,
   *         and false otherwise
   */
  @Test
  public void testRemove() {
    HashTableMap<Integer, String> map = new HashTableMap<Integer, String>(10);
    map.put(1, "Tommy");
    map.put(2, "Mary");

    assertEquals("Tommy", map.remove(1));
    assertEquals(null, map.remove(1));

  }

  /**
   * This method checks if the HashTableMap is successfully cleared by checking the size of the map.
   * 
   * @return true when the map is successfully cleared, and false otherwise
   */
  @Test
  public void testClear() {
    HashTableMap<Integer, String> map = new HashTableMap<Integer, String>(5);
    map.put(4, "Tommy");
    map.put(2, "Mary");

    map.clear();
    assertEquals(0, map.size());
  }


}
