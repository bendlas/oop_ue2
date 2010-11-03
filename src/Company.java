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
	
	public Company(String name){
		this.name = name;
	}

	// post: Store is managed by company
	public void addStore(Store s){
		stores.add(s);
	}
	
	// post: ProductGroup is managed by company
	public void addProductGroup(ProductGroup pg){
		productGroups.add(pg);
	}
	
	// post: Configuration is managed by company
	public void addConfiguration(Configuration c){
		items.add(c);
	}
	
	// post: Store is removed from Company
	public void removeStore(Store s){
		stores.remove(s);
	}
	
	// post: PrductGroup is removed from Company
	public void removeProductGroup(ProductGroup pg){
		productGroups.remove(pg);
	}
	
	// post: Configuration is removed from Company
	public void removeConfiguration(Configuration c){
		items.remove(c);
	}
	
	public String itemsToString(){
		Iterator<TradeItem> i= items.iterator();
		StringBuilder out = new StringBuilder();
		while(i.hasNext()){
			out.append(i.next().toString());
			out.append("\n");
		}
		return out.toString();
	}
	
	public String productGroupsToString(){
		Iterator<ProductGroup> i= productGroups.iterator();
		StringBuilder out = new StringBuilder();
		while(i.hasNext()){
			out.append(i.next().toString());
			out.append("\n");
		}
		return out.toString();
	}

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