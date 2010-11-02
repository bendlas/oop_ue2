import java.util.Date;
import java.util.Map.Entry;

/* Represents a transaction of TradeItems at a specified point in time
 */

//LOFO
public class Order implements Comparable<Order> {
	public final Date orderDate;
	public final Date targetDate;
	
	// package visible because store needs it for projecting
	ItemCollection items; 
	
	private Store source;
	private Store destination;
	private boolean active = true;
	
	//Precondition: all parameters are valid
	//Invariance: orderDate and targetDate are valid and >= currentDate
	//Postcondition: orderDate, targetDate, items, sourceStore, destinationStore are set
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

	//precondition: activeStatus is set true by default;
	//postcondition: returns true if order is active
	public boolean getActive() {return active;}
	
	/* set order to inactive
	 */
	
	//TODO: check bad
	//Precondition: requires items of this order to be in source and target store
	//Postcondition: deletes this order in the source and target store & sets it inactive
	/*BAD: no test if order is already inactive, or exception handling
		   in case its double deleted, or a store is deleted*/
	public void delete() {
		source.deleteOrder(this);
		destination.deleteOrder(this);
		active = false;
	}
	
	/* make the transaction from one source to target
	 */
	
	/*Precondition: requires the order status to be set as active;
					requires items of order to be in the source store*/
	/*Postcondition: items are withdrawed from source store and deposited
					 in destination store and order is deleted*/
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
	//Precondition: Order "other" and this.date have a valid date
	/*Postcondition: returns 0 if the 2 dates compared are equal;
					 returns <0 if date of order "other" is further in the future than ;
					 returns >0 if date of order "other" is further in the past;*/
					 
	public int compareTo(Order other) {
		return targetDate.compareTo(other.targetDate);
	}
	
	/*Postcondition: returns IAE if the source store has not enough TradeItems 
					 for the order at the targeted date*/
	private void check() {
		for (Entry<TradeItem, Integer> e : items.entrySet()) {
			if(e.getValue() > source.getAmount(e.getKey(), targetDate)){
				throw new IllegalArgumentException("TradeItem "+e.getKey()+" is less than "+e.getValue()+" by "+targetDate);
			}
		}
	}
}
	

