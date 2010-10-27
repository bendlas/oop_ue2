import java.util.HashMap;
import java.util.Map.Entry;


public class ItemCollection extends HashMap<TradeItem, Integer> {
	private static final long serialVersionUID = 1L;
	
	public void mergeInto(TradeItem item, Integer amount) {
		Integer thisAmount = get(item);
		if (thisAmount == null) { thisAmount = 0; }
		amount += thisAmount;
		put(item, amount);
	}
	
	public void mergeInto(ItemCollection from) {
		for (Entry<TradeItem, Integer> e : from.entrySet()) {
			TradeItem item = e.getKey();
			Integer amount = e.getValue();
			mergeInto(item, amount);
		}
	}
}
