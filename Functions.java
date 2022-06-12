// --== CS400 File Header Information ==--
// Name: Weiyu Kong
// Email: wkong32@wisc.edu
// Team: IC
// Role: Front End Developer 1
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public interface Functions<keyType, valueType> {
  public valueType search(keyType key) throws NoSuchElementException;

  public void edit(valueType value) throws NoSuchElementException;

  public valueType remove(keyType key) throws NoSuchElementException;

  public void clear();

  public boolean addNewCommodity(valueType value) throws IllegalArgumentException;

  public boolean addNewCommodityList() throws FileNotFoundException;
}

