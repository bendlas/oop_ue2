
/* comparable by difference of sell, store price
 */

//LOFO
public class Product extends TradeItem implements Comparable<Product> {
	public final String name;
	public final String description;

	//TODO: check "good"
	//Precondition: all Variables are valid
	//invariance: name and description are not empty
	/*postcondition: buyPrice, sellPrice and store costs are set as BigDecimal;
					 name and description are set*/
	//GOOD: variables are set to BIGDecimal via the abstract class>>Dynamic Binding
	public Product(String name, String description, int buy, int sell, int store){
		super(buy, sell, store);
		this.name = name;
		this.description = description;
	}
	
	//precondition: name and description are not empty
	//Postcondition: returns the name and description as a string
	public String toString(){
		return name + ": " + description;
	}

	//Precondition: product "other" exists
	/*Postcondition: the difference between the buyPrices of 2 Products 
	 * 				 with store costs deducted respectively is returned as int*/
	public int compareTo(Product other) {
		return buyPrice.subtract(storeCosts).compareTo(
				other.buyPrice.subtract(other.storeCosts));
	}
}
