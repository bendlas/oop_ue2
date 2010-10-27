import java.util.Date;
import java.util.Map.Entry;

/* Represents a transaction of TradeItems at a specified point in time
 */
public class Order implements Comparable<Order> {
	// set to now by constructor
	public final Date orderDate;
	public final Date targetDate;
	
	// package visible because store needs it for projecting
	ItemCollection items; 
	
	private Store source;
	private Store destination;
	private boolean active = true;
	
	public Order(Date target, ItemCollection order, Store source, Store destination){
		orderDate = new Date();
		targetDate = target;
		items = order;
		this.source = source;
		check();
		this.destination = destination;
		source.addOutOrder(this);
		destination.addInOrder(this);
	}

	public boolean getActive() {return active;}
	
	/* set order to inactive
	 */
	public void delete() {
		source.deleteOrder(this);
		destination.deleteOrder(this);
		active = false;
	}
	
	/* make the transaction from one source to target
	 */
	public void executeOrder(){
		if (!active) {
			throw new IllegalStateException("Order "+this+" not active.");
		}
		source.withdraw(items);
		destination.deposit(items);
		delete();
	}

	/* Orders are sortable by target date
	 */
	@Override
	public int compareTo(Order other) {
		return targetDate.compareTo(other.targetDate);
	}
	
	private void check() {
		for (Entry<TradeItem, Integer> e : items.entrySet()) {
			if(e.getValue() > source.getAmount(e.getKey(), targetDate)){
				throw new IllegalArgumentException("TradeItem "+e.getKey()+" is less than "+e.getValue()+" by "+targetDate);
			}
		}
	}
}
	

