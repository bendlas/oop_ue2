import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductGroup{
	
	private Set<Product> products = new HashSet<Product>();
	private Set<ProductGroup> subProdGroup = new HashSet<ProductGroup>();
	public final String name;
	
	public ProductGroup(String name){
		this.name = name;
	}
	
	//TODO @param: instead of Product TradeItem to add Configs?
	public void setProduct(Product p){
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
	
	private void getProducts(List<Product> p) {
		p.addAll(products);
		for (ProductGroup sub : subProdGroup) {
			sub.getProducts(p);
		}
	}
	
	public List<Product> getSortByPrice() {
		List<Product> ret = new ArrayList<Product>(products);
		getProducts(ret);
		Collections.sort(ret);
		return ret;
	}
	

	protected Set<Product> getProductsFromTree(Set<Product> prodSet) {
		prodSet.addAll(products);

		for (ProductGroup pg : subProdGroup) {
		pg.getProductsFromTree(prodSet);
		}
		return prodSet;
		}

	public String toString(boolean printProducts){
		StringBuilder buf = new StringBuilder();
		Set<Product> pset = getProductsFromTree(new HashSet<Product>());
		
/*		buf.append("Die Produktgruppe ");
		buf.append(name);
		buf.append(" beinhaltet:\n");
*/		
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
