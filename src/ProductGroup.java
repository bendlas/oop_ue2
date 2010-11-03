import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/* Representing a choice of products
 * Stores decide on one choice based on costs
 */
public class ProductGroup{

	private Set<Product> products = new HashSet<Product>();
	private Set<ProductGroup> subProdGroup = new HashSet<ProductGroup>();
	public final String name;
	
	public ProductGroup(String name){
		this.name = name;
	}
	
	//post: Product is added to choices for ProductGroup
	public void addProduct(Product p){
		products.add(p);
	}

	//post: argument ProductGroup is added to choices for this ProductGroup
	public void addSubProductGroup(ProductGroup pG){
		subProdGroup.add(pG);
	}
	
	//pre: Product is a valid choice for ProductGroup
	//post: Product is removed from choices
	public void removeProduct(Product p){
		if(!products.contains(p)){
			throw new IllegalArgumentException("Group doesn't contain required product");
		}
		products.remove(p);
	}

	//pre: argument ProductGroup is empty and a valid choice for this ProductGroup
	//post: argument ProductGroup is removed from choices
	public void removeProduct(ProductGroup pG){
		if(!subProdGroup.isEmpty()){
			throw new IllegalArgumentException("Subgroup contains products - cannot be deleted");
		}
		subProdGroup.remove(pG);
	}

	//GOOD: dynamic binding, weniger Abhängigkeit durch nicht spezifizierung des Sets
	//pre: Set contains only Products from this ProductGroup (or sub)
	//post: the argument Set is returned with Products from this-  and sub- ProductGroups added
	private Set<Product> getProducts(Set<Product> p) {
		p.addAll(products);
		for (ProductGroup sub : subProdGroup) {
			sub.getProducts(p);
		}
		return p;
	}

	//GOOD: dynamic binding, Collections.sort on an ArrayList
	//post: return List contains all Products as in getProducts sorted by price
	public List<Product> getSortByPrice() {
		List<Product> ret = new ArrayList<Product>(products);
		ret.addAll(getProducts(new HashSet<Product>()));
		Collections.sort(ret);
		return ret;
	}

	//BAD: should be named e.g. listItems
	public String toString(boolean printProducts){
		StringBuilder buf = new StringBuilder();
		//GOOD: dynamic binding, we only care about the Set _interface_
		Set<Product> pset = getProducts(new HashSet<Product>());
		for (Product p : pset) {
			buf.append(p.toString());
			buf.append('\n');
		}
		return buf.toString();
	}
	
	public String toString(){
		return name;
	}
}
