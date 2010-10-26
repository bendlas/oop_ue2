import java.util.*;


public class Product extends TradeItem implements Comparable<Product> {
	public final String name;
	public final String description;

	public Product(String name, String description, int buy, int sell, int store){
		super(buy, sell, store);
		this.name = name;
		this.description = description;
	}
	
	public List<Product> getProducts(){
		List<Product> ret = new ArrayList<Product>();
		ret.add(this);
		return ret;
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
