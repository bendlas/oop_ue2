import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	/*get the Products from this Configuration (the cheapest, if in Productgroup)
	 */
	public List<Product> getProducts(){
		List<Product> ret = new ArrayList<Product>();
		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			for(int i=0; i<entry.getValue(); i++){
				ret.add(entry.getKey());
			}
		}
		for (Map.Entry<ProductGroup, Integer> entry : productGroups.entrySet()) {
			for(int i=0; i<entry.getValue(); i++){
				ret.add(entry.getKey().getSortByPrice().get(0));
			}
		}
		for (Map.Entry<Configuration, Integer> entry : configurations.entrySet()) {
			for(int i=0; i<entry.getValue(); i++){
				while(entry.getKey().getProducts().iterator().hasNext()){
					ret.add(entry.getKey().getProducts().iterator().next());
				}
			}
		}
		return ret;
		
	}

	public String toString(){
		StringBuilder productsInGroup = new StringBuilder();
		StringBuilder ret = new StringBuilder("Konfiguration " + name + " beinhaltet:" + "\n"+ "\n");
		
		ret.append("-Konfigurationen: " + "\n");
		for( Map.Entry<Configuration, Integer> entry : configurations.entrySet() )
		{
		  ret.append(entry.getValue()+ "x");
		  ret.append(entry.getKey().toString());
		  ret.append("\n");
		}
		
		ret.append("-ProductGroups: " + "\n");
		for( Map.Entry<ProductGroup, Integer> entry : productGroups.entrySet() )
		{
		  ret.append(entry.getValue()+ "x");
		  ret.append(entry.getKey().toString());
		  ret.append("\n");
		  
		  productsInGroup.append(entry.getKey().toString(true) + "\n");
		}
		
		ret.append("- Produkte:" + "\n");
		for( Map.Entry<Product, Integer> entry : products.entrySet() )
		{
		  ret.append(entry.getValue()+ "x");
		  ret.append(entry.getKey().toString());
		  ret.append("\n");
		  
		  //hab jetzt auch die Produkte in den Gruppen als Produkte hinzugefügt
		  ret.append(productsInGroup.toString()); 
		  ret.append("\n");
		}

		return ret.toString();
	}
}
