import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

public class Store extends ItemCollection {
	private String name;
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

	public ItemCollection getProductGroup(ProductGroup pg, int amount) throws Error {
		// TODO use projected amounts
		ItemCollection ret = new ItemCollection();
		Iterator<Product> pGroupSorted = pg.getSortByPrice().iterator();
		while (pGroupSorted.hasNext() && amount > 0) {
			Product p = pGroupSorted.next();
			int hasAmount = getAmount(p);
			int useAmount = Math.min(hasAmount, amount);
			ret.put(p, useAmount);
			amount -= useAmount;
		}
		if (amount > 0) {
			return null;
		}
		return ret;
	}
	
	public int getAmount(TradeItem p, Date d) {
		ItemCollection future = new ItemCollection(this);
		Iterator<Order> in = inOrders.iterator();
		for(Order o=in.next(); o.targetDate.compareTo(d) <= 0; o=in.next()) {

		}
		return 0;
	}
	
	public int getAmount(TradeItem p) {
		//TODO get projected amount
		Integer s = get(p);
		if (s == null) {
			return 0;
		} else {
			return s;
		}
	}
}
