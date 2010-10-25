import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Company{
	private String name; 
	private Set<Store> stores = new HashSet<Store>();
	private Set<ProductGroup> productGroups = new HashSet<ProductGroup>();
	private Set<Configuration> configurations = new HashSet<Configuration>();
	private Set<Order> activeOrders = new HashSet<Order>();

	public Company(String name){
		this.name = name;
	}
	
	public void addStore(Store s){
		stores.add(s);
	}
	public void addProductGroup(ProductGroup pg){
		productGroups.add(pg);
	}
	public void addConfiguration(Configuration c){
		configurations.add(c);
	}
	
	public void removeStore(Store s){
		stores.remove(s);
	}
	public void removeProductGroup(ProductGroup pg){
		productGroups.remove(pg);
	}
	public void removeConfiguration(Configuration c){
		configurations.remove(c);
	}
	
	public void addActiveOrder(Order o){
		activeOrders.add(o);
	}
	public void removeActiveOrder(Order o){
		activeOrders.remove(o);
	}
	
	public String activeOrdersToString(){
		Iterator<Order> i= activeOrders.iterator();
		StringBuilder out = new StringBuilder();
		while(i.hasNext()){
			out.append(i.next().toString());
			out.append("\n");
		}
		return out.toString();
	}
	
	public String configurationsToString(){
		Iterator<Configuration> i= configurations.iterator();
		StringBuilder out = new StringBuilder();
		while(i.hasNext()){
			out.append(i.next().toString());
			out.append("\n");
		}
		return out.toString();
	}
	
	public String productGroupToString(){
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