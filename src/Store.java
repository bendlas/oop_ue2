import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;



public class Store {
	private String name;
	private Map<Product, Integer> store = new HashMap<Product, Integer>();
	private List<Order> inOrders = new ArrayList<Order>(),
	                    outOrders = new ArrayList<Order>();
	
	public Store(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	//fill the store
	public void deposit(Product p, int amount) {
		if(store.containsKey(p)){
			amount += store.get(p);
		}
		store.put(p, amount);
	}
	
	public void depositOrder(final Map<Product, Integer> added) {
		for (Entry<Product, Integer> addItem : added.entrySet()) {
			deposit(addItem.getKey(), addItem.getValue());
		}
	}
	
	public void withdraw(Product p, int amount) {
		int storedAmount = store.get(p);
		if (storedAmount > amount) {
			store.put(p, storedAmount - amount);
		} else if (storedAmount == amount) {
			store.remove(p);
		} else {
			throw new IllegalArgumentException("Store doesn't contain enough " + p);
		}
	}
	
	public void withdrawOrder(final Map<Product, Integer> wd) {
		for (Entry<Product, Integer> it : wd.entrySet()) {
			int storedAmount = store.get(it.getKey());
			if (storedAmount < it.getValue()) {
				throw new IllegalArgumentException("Store doesn't contain enough " + it.getKey());				
			}
		}
		for (Entry<Product, Integer> it : wd.entrySet()) {
			withdraw(it.getKey(), it.getValue());
		}

	}

	public Map<Product, Integer> getProductGroup(ProductGroup pg, int amount) {
		// TODO use projected amounts
		Map<Product, Integer> ret = new HashMap<Product, Integer>();
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
	
	public int getAmount(Product p) {
		//TODO get projected amount
		Integer s = store.get(p);
		if (s == null) {
			return 0;
		} else {
			return s;
		}
	}
}
