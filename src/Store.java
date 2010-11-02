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
	
	//TODO: koennt ma ned als "bad" punkt in dieser methode die methode getName() aufrufn?
	//precondition: name is set
	//postcondition: name is returned as string
	public String toString(){
		return name;
	}
	
	//precondition: param name is not empty
	//postcondition: field name is set
	public Store(String name){
		this.name = name;
	}
	
	//precondition: name is set
	//postcondition: name is returned as string
	public String getName(){
		return name;
	}
	
	/* Package visible so that Order can call it
	 */
	//precondition: order o is valid
	//postcondition: order o is removed from inOrders and outOrders respectively
	void deleteOrder(Order o) {
		inOrders.remove(o);
		outOrders.remove(o);
	}
	
	//precondition: order o is valid
	//postcondition: order o is added to inOrders
	void addInOrder(Order o) {
		inOrders.add(o);
	}

	//precondition: order o is valid
	//postcondition: order o is added to outOrders
	void addOutOrder(Order o) {
		outOrders.add(o);
	}

	/* Get parts representing a certain amount of a ProductGroup
	 * using cheapest available parts as in Product.compareTo(Product p)
	 */
	//TODO: check postcond
	//precondition: param pg is valid and amount >= 0
	/*postcondition: the desired amount of cheapest products of a productgroup
					 due to availability is returned as an ItemCollection*/
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
	//TODO: check postcond
	//precondition: tradeItem p and date d are valid and date is >= currentDate
	//postcondition: amount of p at a certain date is returned as int
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
	
	//TODO: check postcond
	//postcondition: tradeitem p is valid
	//precondition: amount of p at current date is returned as int
	public int getAmount(TradeItem p) {
		return getAmount(p, new Date());
	}
}
