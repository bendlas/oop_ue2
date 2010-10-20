import java.util.Date;
import java.util.HashMap;


public class Order {
	protected Date inTime;
	protected Date outTime;
	protected HashMap<TradeItem, Integer> order; 
	protected Store source;
	protected Store destination;
	
	public Order(Date in, Date out, HashMap<TradeItem, Integer> order, Store source, Store destination){
		this.inTime = in;
		this.outTime = out;
		this.order = order;
		this.source = source;
		this.destination = destination;
	}
	
	public void executeOrder(){
		
	}
	
	public void deleteOrder(){
		
	}
	

}
	

