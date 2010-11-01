
/* comparable by difference of sell, store price
 */
public class Product extends TradeItem implements Comparable<Product> {
	public final String name;
	public final String description;


	//Postcondition: sets up the variables, name, description, converts buy, sell, store 
	//into BigDecimal, via TradeItem
	//GOOD: variables are set to BIGDecimal via the abstract class>>Dynamic Binding
	public Product(String name, String description, int buy, int sell, int store){
		super(buy, sell, store);
		this.name = name;
		this.description = description;
	}
	
	//Postcondition: returns the name and description as a string
	public String toString(){
		return name + ": " + description;
	}

	@Override
	//Postcondition: returns the overall price difference between 2 products, as Integer
	public int compareTo(Product other) {
		return buyPrice.subtract(storeCosts).compareTo(
				other.buyPrice.subtract(other.storeCosts));
	}
}
