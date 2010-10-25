import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Order implements Comparable<Order>{
	public final Date orderDate;
	public final Date targetDate;
	private Map<TradeItem, Integer> order; 
	private Store source;
	private Store destination;
	
	public Order(Date target, Map<TradeItem, Integer> order, Store source, Store destination){
		orderDate = new Date();
		targetDate = target;
		this.order = order;
		this.source = source;
		this.destination = destination;
	}

	public void executeOrder(){
		// imagine some fancy transaction logic here
	}

	@Override
	public int compareTo(Order other) {
		return targetDate.compareTo(other.targetDate);
	}
}
	

