import java.util.Date;
import java.util.Map.Entry;

/* Represents a transaction of TradeItems at a specified point in time
 */
public class Order implements Comparable<Order> {
	public final Date orderDate;
	public final Date targetDate;
	
	// package visible because store needs it for projecting
	ItemCollection items; 
	
	private Store source;
	private Store destination;
	private boolean active = true;
	
	//post: Order is valid or IAE is thrown
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

	//pre: Order is registered in source and destination, and is active
	//post: Order is set inactive and removed from source and destination store
	//BAD: Should throw if attempting to double delete
	public void delete() {
		source.deleteOrder(this);
		destination.deleteOrder(this);
		active = false;
	}

	//pre: Order is active
	//post: Order Items are transfered from source to destination store and order is deleted
	//GOOD: is simply made by using the customized Item collection, otherwise it would have been
	//more complex to get out the products from the configurations, etc.
	public void executeOrder(){
		if (!active) {
			throw new IllegalStateException("Order "+this+" not active.");
		}
		source.withdraw(items);
		destination.deposit(items);
		delete();
	}

	public int compareTo(Order other) {
		return targetDate.compareTo(other.targetDate);
	}
	
	//post: throws IAE if Order can not be withdrawn from source
	private void check() {
		for (Entry<TradeItem, Integer> e : items.entrySet()) {
			if(e.getValue() > source.getAmount(e.getKey(), targetDate)){
				throw new IllegalArgumentException("TradeItem "+e.getKey()+" is less than "+e.getValue()+" by "+targetDate);
			}
		}
	}
}
	

