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
	
	//precondition: name is not empty
	//postcondition: a name for the productgroup is set
	public ProductGroup(String name){
		this.name = name;
	}
	
	//precondition: product p exists
	//postcondition: product p is added
	public void addProduct(Product p){
		products.add(p);
	}

	//precondition: productgroup pG exists
	//postcondition: productgroup pG is added as a subproductgroup
	public void addSubProductGroup(ProductGroup pG){
		subProdGroup.add(pG);
	}
	
	//Precondition: product p exists
	//Postcondition: product p is removed
	public void removeProduct(Product p){
		if(!products.contains(p)){
			throw new IllegalArgumentException("Group doesn't contain required product");
		}
		products.remove(p);
	}

	//Precondition: pG doesn't contain products
	//Postcondition: productgroup pG is removed
	public void removeProduct(ProductGroup pG){
		if(!subProdGroup.isEmpty()){
			throw new IllegalArgumentException("Subgroup contains products - cannot be deleted");
		}
		subProdGroup.remove(pG);
	}

	//TODO check precond and "good"
	//Gut: weniger Abhängigkeit durch nicht spezifizierung des Sets
	//precondition: set p is empty
	//postcondition: all products of the group and those of the subgroup are returned as a set
	private Set<Product> getProducts(Set<Product> p) {
		p.addAll(products);
		for (ProductGroup sub : subProdGroup) {
			sub.getProducts(p);
		}
		return p;
	}

	/* Get products sorted by price
	 */
	//precondition: every product has a price of the type bigdecimal
	//postcondition: all products sorted by the price are returned as a list
	public List<Product> getSortByPrice() {
		List<Product> ret = new ArrayList<Product>(products);
		ret.addAll(getProducts(new HashSet<Product>()));
		Collections.sort(ret);
		return ret;
	}

	//TODO: where the hell wird printProducts definiert?
	//TODO: check postcond
	//precondition: 
	//postcondition: all products of one productgroup are returned as a string
	public String toString(boolean printProducts){
		StringBuilder buf = new StringBuilder();
		Set<Product> pset = getProducts(new HashSet<Product>());
		for (Product p : pset) {
			buf.append(p.toString());
			buf.append('\n');
		}
		return buf.toString();
	}
	
	//precondition: the name of the productgroup is not empty
	//postcondition: the name of the productgroup is returned
	public String toString(){
		return name;
	}

}
