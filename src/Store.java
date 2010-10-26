import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

public class Store {
	private String name;
	private Map<TradeItem, Integer> store = new HashMap<TradeItem, Integer>();
	private SortedSet<Order> inOrders = new TreeSet<Order>(),
	                        outOrders = new TreeSet<Order>();
	
	public Store(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	/* Package visible so that Order can call it
	 */
	void deleteOrder(Order o) {
		inOrders.remove(o);
		outOrders.remove(o);
	}

	void addInOrder(Order o) {
		inOrders.add(o);
	}
	
	void addOutOrder(Order o) {
		outOrders.add(o);
	}
	
	//fill the store
	public void deposit(TradeItem p, int amount) {
		if(store.containsKey(p)){
			amount += store.get(p);
		}
		store.put(p, amount);
	}
	
	public void depositOrder(final Map<TradeItem, Integer> added) {
		for (Entry<TradeItem, Integer> addItem : added.entrySet()) {
			deposit(addItem.getKey(), addItem.getValue());
		}
	}
	
	public void withdraw(TradeItem p, int amount) {
		int storedAmount = store.get(p);
		if (storedAmount > amount) {
			store.put(p, storedAmount - amount);
		} else if (storedAmount == amount) {
			store.remove(p);
		} else {
			throw new IllegalArgumentException("Store doesn't contain enough " + p);
		}
	}
	
	public void withdrawOrder(final Map<TradeItem, Integer> wd) {
		for (Entry<TradeItem, Integer> it : wd.entrySet()) {
			int storedAmount = store.get(it.getKey());
			if (storedAmount < it.getValue()) {
				throw new IllegalArgumentException("Store doesn't contain enough " + it.getKey());				
			}
		}
		for (Entry<TradeItem, Integer> it : wd.entrySet()) {
			withdraw(it.getKey(), it.getValue());
		}

	}

	public Map<TradeItem, Integer> getProductGroup(ProductGroup pg, int amount) {
		// TODO use projected amounts
		Map<TradeItem, Integer> ret = new HashMap<TradeItem, Integer>();
		Iterator<Product> pGroupSorted = pg.getSortByPrice().iterator();
		while (pGroupSorted.hasNext() && amount > 0) {
			Product p = pGroupSorted.next();
			int hasAmount = getAmount(p);
			int useAmount = Math.min(hasAmount, amount);
			ret.put(p, useAmount);
			amount -= useAmount;
		}
		if (amount > 0) {
			
		}
		return ret;
	}
	
	public int getAmount(TradeItem p) {
		//TODO get projected amount
		Integer s = store.get(p);
		if (s == null) {
			return 0;
		} else {
			return s;
		}
	}
}
