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

	
	/* so wies jetzt is muss man selber schauen dass die order dem Datum nach ausgeführt werden
	 * wenn wir wirklich zwischen tatsächlichen & vorhandenen unterscheiden wollen wirds iwie kompliziert ?
	 * bestellen kann man nur Konfigurationen & einzelne Produkte
	 */
	public void executeOrder(){

		for( Map.Entry<TradeItem, Integer> entry : order.entrySet()){
			while(entry.getKey().getProducts().iterator().hasNext()){
				source.withdraw(entry.getKey().getProducts().iterator().next(), entry.getValue());
				destination.deposit(entry.getKey().getProducts().iterator().next(), entry.getValue());
			}
		}
	}

	@Override
	public int compareTo(Order other) {
		return targetDate.compareTo(other.targetDate);
	}
}
	

