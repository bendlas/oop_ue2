import java.math.BigDecimal;
import java.util.*;

public class ProductGroup{
	
	private Set<Product> products = new HashSet<Product>();
	private Set<ProductGroup> subProdGroup = new HashSet<ProductGroup>();
	public final String name;
	
	public ProductGroup(String name){
		this.name = name;
	}
	
	public void setProduct(Product p){
		products.add(p);
	}
	
	public void setProdGroup(ProductGroup pG){
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
}
