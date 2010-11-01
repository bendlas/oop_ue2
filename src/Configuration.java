import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//LOFO
public class Configuration extends TradeItem {
	public final String name;

	private ItemCollection items;
	private Map<ProductGroup, Integer> productGroups;

	public Configuration(String name) {
		super(0,0,0);
		this.name = name;
		items = new ItemCollection();
		productGroups = new HashMap<ProductGroup, Integer>();
	}
	
	//if map already contains trade items the amount of the items is being added
	public void addTradeItem (TradeItem item, int count){
		items.deposit(item, count);
	}
	
	public void addProductGroup (ProductGroup pg, int count){
		if (productGroups.containsKey(pg)) {
			productGroups.put(pg, items.get(pg) + count);
		} else {
			productGroups.put(pg, count);
		}
	}

	public ItemCollection checkItems(Store s, Integer amount) {
		ItemCollection ret = new ItemCollection();
		for (Entry<TradeItem, Integer> e : items.entrySet()) {
			TradeItem item = e.getKey();
			Integer i = e.getValue();
			int storedAmount = s.getAmount(item);
			ret.deposit(item, storedAmount);
			if (storedAmount < i) {
				if (item instanceof Configuration) {
					ItemCollection subItems = ((Configuration) item).checkItems(s, i - storedAmount);
					if (subItems == null) { return null; }
					ret.deposit(subItems);
				} else {
					return null;
				}
			}
		}
		for (Entry<ProductGroup, Integer> e : productGroups.entrySet()) {
			ItemCollection groupParts = s.getProductGroup(e.getKey(), e.getValue());
			if (groupParts == null) { return null; }
			ret.deposit(groupParts);
		}
		return ret;
	}
	
	/* Build configuration with Items from Store
	 * and store it back if some subconfiguration isn't stored,
	 * it will be built too.
	 */
	public void buildConfiguration(Store s) {
		ItemCollection parts = checkItems(s, 1);
		if (parts == null) {
			throw new IllegalArgumentException("Store has not enough parts");
		}
		s.withdraw(parts);
		s.deposit(this, 1);
	}

	public String toString(){
		StringBuilder productsInGroup = new StringBuilder();
		StringBuilder ret = new StringBuilder("Konfiguration " + name + " beinhaltet:" + "\n"+ "\n");
		
		ret.append("-ProductGroups: " + "\n");
		for( Entry<ProductGroup, Integer> entry : productGroups.entrySet() )
		{
		  ret.append(entry.getValue()+ "x");
		  ret.append(entry.getKey().toString());
		  ret.append("\n");
		  
		  productsInGroup.append(entry.getKey().toString(true) + "\n");
		}
		
		ret.append("- Produkte:" + "\n");
		for( Entry<TradeItem, Integer> entry : items.entrySet() )
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
