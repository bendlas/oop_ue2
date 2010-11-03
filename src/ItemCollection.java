import java.util.HashMap;
import java.util.Map.Entry;

/* A Map of TradeItems with their amounts
 * can merge/remove other ItemCollections into/from itself
 * 
 * Used for representing Stores and Transactions
 */
public class ItemCollection extends HashMap<TradeItem, Integer> {
	private static final long serialVersionUID = 1L;
	
	public ItemCollection() {
		super();
	}
	
	ItemCollection(ItemCollection o) {
		super(o);
	}
	
	//post: Like HashMap.put, but adds values if key is already in map
	public void deposit(TradeItem item, Integer amount) {
		Integer thisAmount = get(item);
		if (thisAmount == null) { thisAmount = 0; }
		amount += thisAmount;
		put(item, amount);
	}
	
	//post: each Item from ItemCollection is deposited with the given amount
	public void deposit(ItemCollection from) {
		for (Entry<TradeItem, Integer> e : from.entrySet()) {
			TradeItem item = e.getKey();
			Integer amount = e.getValue();
			deposit(item, amount);
		}
	}
	
	//post: amount of TradeItem is subtracted from stored amount
	//      IAE is thrown if too few (or none) are in stock
	public void withdraw(TradeItem p, int amount) {
		Integer storedAmount = get(p);
		if (storedAmount == null || storedAmount < amount) {
			throw new IllegalArgumentException("Store doesn't contain enough " + p);			
		} else if (storedAmount > amount) {
			put(p, storedAmount - amount);
		} else {
			remove(p);
		}
	}
	
	//post: each Item from ItemCollection is withdrawn by given amount
	//inv: all items are withdrawn or ItemCollection remains unchanged
	public void withdraw(ItemCollection from) {
		for (Entry<TradeItem, Integer> it : from.entrySet()) {
			Integer storedAmount = get(it.getKey());
			if (storedAmount == null || storedAmount < it.getValue()) {
				throw new IllegalArgumentException("Store doesn't contain enough " + it.getKey());				
			}
		}
		for (Entry<TradeItem, Integer> it : from.entrySet()) {
			withdraw(it.getKey(), it.getValue());
		}
	}
}
