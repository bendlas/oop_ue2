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

	public void addProduct(Product p){
		products.add(p);
	}

	public void addSubProductGroup(ProductGroup pG){
		subProdGroup.add(pG);
	}

	public void removeProduct(Product p){
		if(!products.contains(p)){
			throw new IllegalArgumentException("Group doesn't contain required product");
		}
		products.remove(p);
	}

	public void removeProduct(ProductGroup pG){
		if(!subProdGroup.isEmpty()){
			throw new IllegalArgumentException("Subgroup contains products - cannot be deleted");
		}
		subProdGroup.remove(pG);
	}

	private Set<Product> getProducts(Set<Product> p) {
		p.addAll(products);
		for (ProductGroup sub : subProdGroup) {
			sub.getProducts(p);
		}
		return p;
	}

	/* Get products sorted by price
	 */
	public List<Product> getSortByPrice() {
		List<Product> ret = new ArrayList<Product>(products);
		ret.addAll(getProducts(new HashSet<Product>()));
		Collections.sort(ret);
		return ret;
	}

	public String toString(boolean printProducts){
		StringBuilder buf = new StringBuilder();
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
