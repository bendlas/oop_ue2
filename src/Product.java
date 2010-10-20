
public class Product extends TradeItem {
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
}
