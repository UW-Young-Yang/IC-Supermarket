// --== CS400 File Header Information ==--
// Name: Young Yang
// Email: xyang532@wisc.edu
// Team: IC
// Role: Back End Developer
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

/**
 * This class creates a Commodity object that includes the name, the price, the stock number, and 
 * the sold number of the commodity. Especially whether the commodity needs to be restocked.
 * 
 * @author Young Yang
 */
public class Commodity implements CommodityInterface {
    private String name;
    private double price, stock, sold;
    private boolean needRestock = false;

    /**
     * Create a commodity object with name and price.
     * @param name the name of the commodity
     * @param price the price of the commodity
     */
    public Commodity(String name, double price) {
        this.name = name;
        this.price = price;
        this.stock = 100;
        this.sold = 0;
    }

    /**
     * Create a commodity object.
     * @param name the name of the commodity
     * @param price the price of the commodity
     * @param stock the stock number of the commodity
     * @param sold the sold number of the commodity
     * @param needRestock whether the commodity needs to be restocked
     */
    public Commodity(String name, double price, double stock, double sold, boolean needRestock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.sold = sold;
        this.needRestock = needRestock;
    }

    /**
     * Get the name of the commodity.
     * @return the name of the commodity
     */
    public String getName() {
        return name;
    }

    /**
     * Get the price of the commodity.
     * @return the price of the commodity
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the number of the stock of the commodity.
     * @return the number of stock of the commodity
     */
    public double getStock() {
        return stock;
    }

    /**
     * Get the number of the sold commodity.
     * @return the number of the sold commodity
     */
    public double getSold() {
        return sold;
    }

    /**
     * Decide whether the commodity need to be restocked.
     * @return true if the commodity need to be restocked, 
     * false otherwise.
     */
    public boolean isNeedRestock() {
        return needRestock;
    }

    /**
     * Set the name of the commodity.
     * @param name the name of the commodity
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the price of the commodity.
     * @param price the price of the commodity
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Set the number of the stock of the commodity.
     * @param stock the number of the stock of the commodity
     */
    public void setStock(double stock) {
        this.stock = stock;
    }

    /**
     * Set the number of the sold commodity.
     * @param sold the number of the sold commodity
     */
    public void setSold(double sold) {
        this.sold = sold;
    }

    /**
     * Set whether the commodity need to be restocked.
     * @param needRestock whether the commodity need to be restocked
     */
    public void setNeedRestock(boolean needRestock) {
        this.needRestock = needRestock;
    }

    /**
     * This is a functional interface that contains only one abstract method.
     */
    interface printCommodity {
        /**
         * This is a abstract method needs to be rewritten by lambda expression.
         * @param commodity a commodity object
         */
        public void print(Commodity commodity);
    }

}
