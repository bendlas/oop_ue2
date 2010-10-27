import java.util.HashMap;
import java.util.Map.Entry;


public class ItemCollection extends HashMap<TradeItem, Integer> {
	private static final long serialVersionUID = 1L;
	
	public ItemCollection() {
		super();
	}
	
	ItemCollection(ItemCollection o) {
		super(o);
	}
	
	public void deposit(TradeItem item, Integer amount) {
		Integer thisAmount = get(item);
		if (thisAmount == null) { thisAmount = 0; }
		amount += thisAmount;
		put(item, amount);
	}
	
	public void deposit(ItemCollection from) {
		for (Entry<TradeItem, Integer> e : from.entrySet()) {
			TradeItem item = e.getKey();
			Integer amount = e.getValue();
			deposit(item, amount);
		}
	}
	
	public void withdraw(TradeItem p, int amount) {
		int storedAmount = get(p);
		if (storedAmount > amount) {
			put(p, storedAmount - amount);
		} else if (storedAmount == amount) {
			remove(p);
		} else {
			throw new IllegalArgumentException("Store doesn't contain enough " + p);
		}
	}
	
	public void withdraw(ItemCollection wd) {
		for (Entry<TradeItem, Integer> it : wd.entrySet()) {
			int storedAmount = get(it.getKey());
			if (storedAmount < it.getValue()) {
				throw new IllegalArgumentException("Store doesn't contain enough " + it.getKey());				
			}
		}
		for (Entry<TradeItem, Integer> it : wd.entrySet()) {
			withdraw(it.getKey(), it.getValue());
		}
	}
}
