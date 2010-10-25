import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Configuration extends TradeItem {
	public final String name;

	private Map<Product, Integer> products;
	private Map<ProductGroup, Integer> productGroups;
	private Map<Configuration, Integer> configurations;

	public Configuration(String name) {
		super(0,0,0);
		this.name = name;
		products = new HashMap<Product, Integer>();
		productGroups = new HashMap<ProductGroup, Integer>();
		configurations = new HashMap<Configuration, Integer>();
	}
	
	public void addProduct (Product p, int count){
		if (products.containsKey(p)) {
			products.put(p, configurations.get(p) + count);
		} else {
			products.put(p, count);
		}
	}
	
	public void addProductGroup (ProductGroup pg, int count){
		if (productGroups.containsKey(pg)) {
			productGroups.put(pg, configurations.get(pg) + count);
		} else {
			productGroups.put(pg, count);
		}
	}
	
	public void addConfiguration (Configuration c, int count){
		if (configurations.containsKey(c)) {
			configurations.put(c, configurations.get(c) + count);
		} else {
			configurations.put(c, count);
		}
	}

	public String toString(){
		StringBuilder ret = new StringBuilder("Konfiguration " + name + ": ");
		// TODO recurse through products and confs
		return ret.toString();
	}
}
