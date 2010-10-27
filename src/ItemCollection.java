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
	
	/* Like HashMap.put, but adds amounts if already in map
	 */
	public void deposit(TradeItem item, Integer amount) {
		Integer thisAmount = get(item);
		if (thisAmount == null) { thisAmount = 0; }
		amount += thisAmount;
		put(item, amount);
	}
	
	/* deposit for every item in from
	 */
	public void deposit(ItemCollection from) {
		for (Entry<TradeItem, Integer> e : from.entrySet()) {
			TradeItem item = e.getKey();
			Integer amount = e.getValue();
			deposit(item, amount);
		}
	}
	
	/* Like HashMap.put, but adds subtracts if already in map
	 * deletes if amount goes to 0
	 */
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
	
	/* withdraw for every item in from
	 * also checks amounts beforehand, so that no half-done withdrawals will occur
	 */
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
