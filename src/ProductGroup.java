import java.util.*;

public class ProductGroup extends TradeItem {
	
	protected Set<Product> products = new HashSet<Product>();
	protected Set<ProductGroup> subProdGroup = new HashSet<ProductGroup>();
	public final String name;
	
	public ProductGroup(String name, int buy, int sell, int store){
		super(0,0,0);
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
	
	public Product getCheapestProd(){
		
		
		return null;
	}

}
