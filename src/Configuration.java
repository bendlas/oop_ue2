import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Configuration extends TradeItem {
	public final String name;

	private ItemCollection items;
	//GOOD: dynamic binding, we don't care about implementation types here
	private Map<ProductGroup, Integer> productGroups;
	
	public Configuration(String name) {
		super(0,0,0);
		this.name = name;
		items = new ItemCollection();
		productGroups = new HashMap<ProductGroup, Integer>();
	}
	
	//pre: count > 0
	//post: count amount TradeItem is added to items comprising `this`
	public void addTradeItem (TradeItem item, int count){
		items.deposit(item, count);
	}
	
	//pre: count > 0
	//post: count amount of ProductGroup is added to items comprising `this`
	public void addProductGroup (ProductGroup pg, int count){
		if (productGroups.containsKey(pg)) {
			productGroups.put(pg, productGroups.get(pg) + count);
		} else {
			productGroups.put(pg, count);
		}
	}
	
	//inv:  store is not modified
	//post: returns an ItemCollection to build amount of `this`
	//      chooses Items as are available in Store
	//      returns null if Items are not available in store
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
	
	//post: builds an instance of `this` with parts from Store and places this new instance in Store
	//inv: Store is only modified if build is successful
	public void buildConfiguration(Store s) {
		ItemCollection parts = checkItems(s, 1);
		if (parts == null) {
			throw new IllegalArgumentException("Store has not enough parts");
		}
		s.withdraw(parts);
		s.deposit(this, 1);
	}

	//BAD: toString should return a short representation
	//     listing of parts should be a separate method
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
		  

		  ret.append(productsInGroup.toString()); 
		  ret.append("\n");
		}

		return ret.toString();
	}
}
