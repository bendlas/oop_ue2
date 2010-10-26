import java.util.Date;
import java.util.Map;

public class Order implements Comparable<Order> {
	public final Date orderDate;
	public final Date targetDate;
	private Map<TradeItem, Integer> order; 
	private Store source;
	private Store destination;
	boolean active = true;
	
	
	public Order(Date target, Map<TradeItem, Integer> order, Store source, Store destination){
		orderDate = new Date();
		targetDate = target;
		this.order = order;
		this.source = source;
		this.destination = destination;
		source.addOutOrder(this);
		destination.addInOrder(this);
	}
	
	public void delete() {
		source.deleteOrder(this);
		destination.deleteOrder(this);
		active = false;
	}
	
	/* 
	 */
	public void executeOrder(){
		if (!active) {
			throw new IllegalStateException("Order "+this+" not active.");
		}
		source.withdrawOrder(order);
		destination.depositOrder(order);
		delete();
	}

	/* Orders are sortable by target date
	 */
	@Override
	public int compareTo(Order other) {
		return targetDate.compareTo(other.targetDate);
	}
}
	

