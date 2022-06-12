// --== CS400 File Header Information ==--
// Name: Young Yang
// Email: xyang532@wisc.edu
// Team: IC
// Role: Back End Developer
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * This class implements the function of Hash Table.
 * 
 * @author Young Yang
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private LinkedList<KeyType>[] keyListTable;
  private LinkedList<ValueType>[] valueListTable;
  private int size = 0;
  private int on = 1;
  private int capacity;

  /**
   * This constructor generates an array with specific capacity.
   * @param capacity the capacity of array of Hash Table.
   * @throws IllegalArgumentException when the capacity is less than 1.
   */
  @SuppressWarnings("unchecked")
	public HashTableMap(int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("The capacity of HashTableMap must bigger than ZERO!");
    }
    this.capacity = capacity;
    keyListTable = new LinkedList[capacity];
    valueListTable = new LinkedList[capacity];
  }

  /**
   * This constructor generates an array with default capacity of 10.
   */
  @SuppressWarnings("unchecked")
  public HashTableMap() { // with default capacity = 10
    this.capacity = 10;
    keyListTable = new LinkedList[this.capacity];
    valueListTable = new LinkedList[this.capacity];
  }

  /**
   * Grow the capacity of array of Hash Table by doubling and rehashing, 
   * whenever its load capacity becomes greater than or equal to 80%.
   */
  @SuppressWarnings("unchecked")
  private void helper() {
    LinkedList<KeyType>[] copyOfKeyTable = (LinkedList<KeyType>[]) keyListTable.clone();
    LinkedList<ValueType>[] copyOfValueTable = (LinkedList<ValueType>[]) valueListTable.clone();

    keyListTable = new LinkedList[this.capacity * 2]; // null
    valueListTable = new LinkedList[this.capacity * 2]; // null
    this.capacity *= 2;   // this.capacity -> double size
    this.size = 0;  // initial size

    for (int i = 0; i < this.capacity/2; i++) {   // this.capacity -> original size
      if (copyOfValueTable[i] != null) {
        for (int j = 0; j < copyOfKeyTable[i].size(); j++) {
          put(copyOfKeyTable[i].get(j), copyOfValueTable[i].get(j));
        }
      }
    }
    
    this.on = 1;
  }

  /**
   * Store new values in Hash Table at the index corresponding to the 
   * ( absolute value of key's hashCode() ) modulus the HashTableMap's current capacity.
   * @param key the key in key-value pairs
   * @param value the value in key-value pairs
   * @return false without making any changes to the Hash Table when
   * the key is already in Hash Table. true otherwise.
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    if (containsKey(key)) return false;

    int index = Math.abs(key.hashCode()) % this.capacity;

    // record list table size
    if (keyListTable[index] == null) {
      keyListTable[index] = new LinkedList<KeyType>();
      valueListTable[index] = new LinkedList<ValueType>();
    }

    // put operation
    keyListTable[index].add(key);
    valueListTable[index].add(value);
    this.size++;

    // load operation
    if (this.size >= this.capacity * 0.8 && this.on == 1) {
      this.on = 0;
      helper();
    }

    return true;
  }

  /**
   * Get the specific value stored in the array of Hash Table
   * @param key the key in key-value pairs
   * @return the value for the specific key
   * @throws NoSuchElementException when the specific key doesn't exist
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    int index = Math.abs(key.hashCode()) % this.capacity;

    if (!containsKey(key)) throw new NoSuchElementException();

    return valueListTable[index].get(keyListTable[index].indexOf(key));
  }

  /**
   * Get the number of key-value pairs stored in Hash Table
   * @return the number of key-value pairs stored in Hash Table
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Decide wether there is a key in the Hash Table
   * @param key the key in key-value pairs
   * @return true if the key is in the Hash Table, false otherwise.
   */
  @Override
  public boolean containsKey(KeyType key) {
    if(this.size == 0) return false;

    for (LinkedList<KeyType> keyList : keyListTable) {
      if (keyList != null) {
        if (keyList.indexOf(key) != -1) return true;
      }
    }
    return false;
  }

  /**
   * Remove the specific key and value from Hash Table.
   * @param key the key that would be remove
   * @return a reference to the value associated with the key that is being removed;
   * null when the key being removed does not exist
   */
  @Override
  public ValueType remove(KeyType key) {
    if (!containsKey(key)) return null;

    int index = Math.abs(key.hashCode()) % this.capacity;    // the index of linked list in the array
    int keyIndex = keyListTable[index].indexOf(key);  // the index of key in the linked list
    ValueType removedValue = get(key);

    keyListTable[index].remove(keyIndex);
    valueListTable[index].remove(keyIndex);
    this.size--;

    return removedValue;
  }

  /**
   * Remove all key-value pairs from Hash Table.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void clear() {
    keyListTable = new LinkedList[this.capacity];
    valueListTable = new LinkedList[this.capacity];
    this.size = 0;
  }

}
