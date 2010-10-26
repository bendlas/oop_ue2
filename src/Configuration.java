import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


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
	/*
	public void buildConfig(){
		if (!checkConfig()) {
			System.out.println("Die Konfiguration " + name + " " +
			"konnte nicht zusammengestellt werden - " +
			"fehlende Komponenten" + "\n");
			return;
		}
		for(Entry<Product, Integer> entry : configurations.entrySet()){
			Product p = entry.getKey();
			int amount = entry.getValue();
			p.decreaseStock(amount);
		}
	}

		private boolean checkConfig() {
			return countConfig() > 0;
		} 
	
	private int countConfig(){
		int count = Integer.MAX_VALUE;
		for(Entry<Product, Integer> entry : configurations.entrySet()){
			Product p = entry.getKey();
			int amount = entry.getValue();
			count = Math.min(count, p.getStock() / amount);
		}
		return count;
	}
	
*/
	public String toString(){
		StringBuilder ret = new StringBuilder("Konfiguration " + name + " beinhaltet:" + "\n"+ "\n");
		//  recurse through products and confs
		ret.append("- Produkte:" + "\n");
		for( Map.Entry<Product, Integer> entry : products.entrySet() )
		{
		  ret.append(entry.getValue()+ "x");
		  ret.append(entry.toString());
		  ret.append("\n");
		}
		
		ret.append("-Konfigurationen: " + "\n");
		for( Map.Entry<Configuration, Integer> entry : configurations.entrySet() )
		{
		  ret.append(entry.getValue()+ "x");
		  ret.append(entry.toString());
		  ret.append("\n");
		}
		
		ret.append("-ProductGroups: " + "\n");
		for( Map.Entry<ProductGroup, Integer> entry : productGroups.entrySet() )
		{
		  ret.append(entry.getValue()+ "x");
		  ret.append(entry.toString());
		  ret.append("\n");
		}

		return ret.toString();
	}
}
