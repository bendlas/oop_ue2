import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Configuration extends TradeItem {
	public final String name;

	protected Map<Product, Integer> products;
	protected Map<ProductGroup, Integer> productGroups;
	protected Map<Configuration, Integer> configurations;
	protected boolean customPrice = false;

	public Configuration(String name) {
		super(0,0,0);
		this.name = name;
		products = new HashMap<Product, Integer>();
		productGroups = new HashMap<ProductGroup, Integer>();
		configurations = new HashMap<Configuration, Integer>();
	}
	
	@Override
	public void setBuyPrice(BigDecimal buyP) {
		super.setBuyPrice(buyP);
		customPrice = true;
	}
	@Override
	public void setSellPrice(BigDecimal sellP) {
		super.setSellPrice(sellP);
		customPrice = true;
	}
	@Override
	public void setStoreCosts(BigDecimal storeC) {
		super.setStoreCosts(storeC);
		customPrice = true;
	}
	
	protected void addPrice(TradeItem t, int count) {
		if (!customPrice) {
			buyPrice = buyPrice.add(t.buyPrice.multiply(p(count)));
			sellPrice = sellPrice.add(t.sellPrice.multiply(p(count)));
			storeCosts = storeCosts.add(t.storeCosts.multiply(p(count)));
		}
	}

	public void addProduct (Product p, int count){
		addPrice(p, count);
		if (products.containsKey(p)) {
			products.put(p, configurations.get(p) + count);
		} else {
			products.put(p, count);
		}
	}
	
	public void addProductGroup (ProductGroup pg, int count){
		addPrice(pg, count);
		if (productGroups.containsKey(pg)) {
			productGroups.put(pg, configurations.get(pg) + count);
		} else {
			productGroups.put(pg, count);
		}
	}
	
	public void addConfiguration (Configuration c, int count){
		addPrice(c, count);
		if (configurations.containsKey(c)) {
			configurations.put(c, configurations.get(c) + count);
		} else {
			configurations.put(c, count);
		}
	}

	public String toString(){
		StringBuilder ret = new StringBuilder("Konfiguration " + name + ": ");
		// TODO recurse through products and confs
		return ret.toString();
	}
}
