import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/* A Collection of TradeItems with Orders
 * Orders are used to project amounts
 */

//TODO: fill in pre, post and in
public class Store extends ItemCollection {
	private static final long serialVersionUID = 1L;

	private String name;
	
	// pending incoming and outgoing orders sorted by date
	
	private SortedSet<Order> inOrders = new TreeSet<Order>(),
	                        outOrders = new TreeSet<Order>();
	
	public String toString(){
		return name;
	}
	
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

	/* Get parts representing a certain amount of a ProductGroup
	 * using cheapest available parts as in Product.compareTo(Product p)
	 */
	public ItemCollection getProductGroup(ProductGroup pg, int amount) {
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
	
	/* calculate projected amount at a date
	 * consider pending incoming and outgoing orders
	 */
	public int getAmount(TradeItem p, Date d) {
		ItemCollection future = new ItemCollection(this);
		Iterator<Order> in = inOrders.iterator();
		while (in.hasNext()) {
			Order o = in.next();
			if ( o.targetDate.compareTo(d) > 0 ) {
				break;
			}
			if (o.getActive()) {
				future.deposit(o.items);
			}
		}
		Iterator<Order> out = outOrders.iterator();
		while (out.hasNext()) {
			Order o = out.next();
			if ( o.targetDate.compareTo(d) > 0 ) {
				break;
			}
			if (o.getActive()) {
				future.withdraw(o.items);
			}
		}
		Integer amt = future.get(p);
		if (amt == null) {return 0;}
		else {return amt;}
	}
	
	public int getAmount(TradeItem p) {
		return getAmount(p, new Date());
	}
}
