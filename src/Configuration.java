import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Configuration extends TradeItem {
	public final String name;

	private Map<TradeItem, Integer> items;
	private Map<ProductGroup, Integer> productGroups;

	public Configuration(String name) {
		super(0,0,0);
		this.name = name;
		items = new HashMap<TradeItem, Integer>();
		productGroups = new HashMap<ProductGroup, Integer>();
	}
	
	//if map already contains trade items the amount of the items is being added
	public void addTradeItem (TradeItem item, int count){
		if (items.containsKey(item)) {
			items.put(item, items.get(item) + count);
		} else {
			items.put(item, count);
		}
	}
	
	public void addProductGroup (ProductGroup pg, int count){
		if (productGroups.containsKey(pg)) {
			productGroups.put(pg, items.get(pg) + count);
		} else {
			productGroups.put(pg, count);
		}
	}
	
	public void addConfiguration (Configuration c, int count){
		if (items.containsKey(c)) {
			items.put(c, items.get(c) + count);
		} else {
			items.put(c, count);
		}
	}
	
	/*get the Products from this Configuration (the cheapest, if in Productgroup)
	 */
	public List<TradeItem> getTradeItems(){
		List<TradeItem> ret = new ArrayList<TradeItem>();
		for (Map.Entry<TradeItem, Integer> entry : items.entrySet()) {
			for(int i=0; i<entry.getValue(); i++){
				ret.add(entry.getKey());
			}
		}
		for (Map.Entry<ProductGroup, Integer> entry : productGroups.entrySet()) {
			for(int i=0; i<entry.getValue(); i++){
				ret.add(entry.getKey().getSortByPrice().get(0));
			}
		}
		return ret;
		
	}

	public String toString(){
		StringBuilder productsInGroup = new StringBuilder();
		StringBuilder ret = new StringBuilder("Konfiguration " + name + " beinhaltet:" + "\n"+ "\n");
		
		ret.append("-ProductGroups: " + "\n");
		for( Map.Entry<ProductGroup, Integer> entry : productGroups.entrySet() )
		{
		  ret.append(entry.getValue()+ "x");
		  ret.append(entry.getKey().toString());
		  ret.append("\n");
		  
		  productsInGroup.append(entry.getKey().toString(true) + "\n");
		}
		
		ret.append("- Produkte:" + "\n");
		for( Map.Entry<TradeItem, Integer> entry : items.entrySet() )
		{
		  ret.append(entry.getValue()+ "x");
		  ret.append(entry.getKey().toString());
		  ret.append("\n");
		  
		  //hab jetzt auch die Produkte in den Gruppen als Produkte hinzugefuegt
		  ret.append(productsInGroup.toString()); 
		  ret.append("\n");
		}

		return ret.toString();
	}
}
