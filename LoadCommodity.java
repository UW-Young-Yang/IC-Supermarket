// --== CS400 File Header Information ==--
// Name: Tong Jiao
// Email: tjiao4@wisc.edu
// Team: IC
// Role: Data Wrangler
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class involves a method to retrieve data from a correctly formed csv file and many methods
 * to help test engineers to test more easily.
 *
 */
public class LoadCommodity {
  /**
   * Retrieve commodity data from a csv file into a hashtable.
   * 
   * @param fileAddress The address of a csv file containing commodity information.
   * @return A hashtable containing all commodity information. Key is commodity name and value is a
   *         Commodity object containing other information(unit price, % in stock, % sold and if
   *         this commodity need to be restocked)
   * @throws FileNotFoundException
   */
  public static HashTableMap<String, Commodity> loadCommodity(String fileAddress)
      throws FileNotFoundException {
    HashTableMap<String, Commodity> result = new HashTableMap<>();
    File file = new File(fileAddress);
    Scanner scnr = null;
    scnr = new Scanner(file);
    scnr.nextLine();
    while (scnr.hasNext()) {
      String[] nextLine = scnr.nextLine().split(",");
      String commodityName = nextLine[0].trim();
      double price = Double.parseDouble(nextLine[1].trim());
      double inStock = Double.parseDouble(nextLine[2].replace('%', ' ').trim());
      double sold = Double.parseDouble(nextLine[3].replace('%', ' ').trim());
      boolean needRestock = (inStock - 20) < 0.001;
      Commodity toAdd = new Commodity(commodityName, price, inStock, sold, needRestock);
      result.put(commodityName, toAdd);
    }
    scnr.close();
    return result;
  }

  /**
   * Get actual number of commodities in the csv file(number of lines with the correct form)
   * 
   * @param fileAddress The address of a csv file containing commodity information.
   * @return Number of lines with the correct commodity form
   */
  public static int ActualNumOfCommodity(String fileAddress) {
    File file = new File(fileAddress);
    int ret = 0;
    Scanner scnr = null;
    try {
      scnr = new Scanner(file);
      while (scnr.hasNext()) {
        String[] nextLine = scnr.nextLine().split(",");
        if (nextLine.length == 4 && Character.isDigit(nextLine[1].charAt(0))
            && Character.isDigit(nextLine[2].charAt(0))
            && Character.isDigit(nextLine[3].charAt(0))) { // is a commodity
          ret++;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    scnr.close();
    return ret;
  }

  public static void main(String[] args) throws FileNotFoundException {
    HashTableMap<String, Commodity> test = loadCommodity("CommodityList.csv");
    System.out.println(ActualNumOfCommodity("CommodityList.csv"));
    System.out.println(test.size());
    System.out.println(test.get("Coconut").getPrice());
    System.out.println(test.get("Coconut").getSold());
    System.out.println(test.get("Coconut").getStock());
    System.out.println(test.get("Coconut").isNeedRestock());
  }

}
