import java.util.HashSet;
import java.util.Set;


public class Product extends TradeItem implements Comparable<Product> {
	public final String name;
	public final String description;

	public Product(String name, String description, int buy, int sell, int store){
		super(buy, sell, store);
		this.name = name;
		this.description = description;
	}
	
	public String toString(){
		return name + ": " + description;
	}

	@Override
	public int compareTo(Product other) {
		return buyPrice.subtract(storeCosts).compareTo(
				other.buyPrice.subtract(other.storeCosts));
	}
}
