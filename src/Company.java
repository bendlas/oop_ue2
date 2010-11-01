import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* Super object
 * serves as a catalog for ProductGroups, Stores, Products, Configurations
 */
public class Company{
	public final String name; 
	private Set<Store> stores = new HashSet<Store>();
	private Set<ProductGroup> productGroups = new HashSet<ProductGroup>();
	private Set<TradeItem> items = new HashSet<TradeItem>();
	
	//invariance: param string is not empty
	//postcondition: company's name is set
	public Company(String name){
		this.name = name;
	}
	
	//precondition: store s exists
	//postcondition: store s is added
	public void addStore(Store s){
		stores.add(s);
	}
	
	//precondition: productgroup pg exists
	//postcondition: productgroup pg is added
	public void addProductGroup(ProductGroup pg){
		productGroups.add(pg);
	}
	
	//precondition: config c exists
	//postcondition: config c is added
	public void addConfiguration(Configuration c){
		items.add(c);
	}
	
	//precondition: store s exists
	//postcondition: store s is removed
	public void removeStore(Store s){
		stores.remove(s);
	}
	
	//precondition: productgroup pg exists
	//postcondition: productgroup pg is removed
	public void removeProductGroup(ProductGroup pg){
		productGroups.remove(pg);
	}
	
	//precondition: config c exists
	//postcondition: config c is removed
	public void removeConfiguration(Configuration c){
		items.remove(c);
	}
	
	//precondition: each tradeItem has a name
	//postcondition: a string of all TradeItems is returned
	public String itemsToString(){
		Iterator<TradeItem> i= items.iterator();
		StringBuilder out = new StringBuilder();
		while(i.hasNext()){
			out.append(i.next().toString());
			out.append("\n");
		}
		return out.toString();
	}
	
	//precondition: each productgroup already has a name
	//postcondition: a string of all productGroups is returned
	public String productGroupsToString(){
		Iterator<ProductGroup> i= productGroups.iterator();
		StringBuilder out = new StringBuilder();
		while(i.hasNext()){
			out.append(i.next().toString());
			out.append("\n");
		}
		return out.toString();
	}
	
	//precondition: each store already has a name
	//postcondition: a string of all stores is returned
	public String storesToString(){
		Iterator<Store> i= stores.iterator();
		StringBuilder out = new StringBuilder();
		while(i.hasNext()){
			out.append(i.next().toString());
			out.append("\n");
		}
		return out.toString();
	}
	
}