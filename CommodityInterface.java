// --== CS400 File Header Information ==--
// Name: Weiyu Kong
// Email: wkong32@wisc.edu
// Team: IC
// Role: Front End Developer 1
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

public interface CommodityInterface {
  public String getName();

  public double getPrice();

  public double getStock();

  public double getSold();

  public boolean isNeedRestock();

  public void setName(String name);

  public void setPrice(double price);

  public void setStock(double stock);

  public void setSold(double sold);

  public void setNeedRestock(boolean needRestock);

}
