import java.math.BigDecimal;

/* Base class for Products and Configurations
 * Abstracting buy, store and sell costs
 * ItemCollection is the Container for it through the program
 * Uses exact numbers for prizes
 */
public abstract class TradeItem {
		
	protected BigDecimal buyPrice, sellPrice, storeCosts;
	
	public TradeItem(int buy, int sell, int store) {
		buyPrice = p(buy);
		sellPrice = p(sell);
		storeCosts = p(store);
	}
	
	public TradeItem(BigDecimal buyPrice, BigDecimal sellPrice, BigDecimal storeCosts){
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.storeCosts = storeCosts;
	}
	
	static public BigDecimal p(int i) {
		return new BigDecimal(i);
	}
	
	static public BigDecimal p(float i) {
		return new BigDecimal(i);
	}
	
	public BigDecimal getBuyPrice(){
		return buyPrice;
	}
	public void setBuyPrice(BigDecimal buyP){
		buyPrice = buyP;
	}
	
	public BigDecimal getSellPrice(){
		return sellPrice;
	}
	public void setSellPrice(BigDecimal sellP){
		sellPrice = sellP;
	}
	
	public BigDecimal getStoreCosts(){
		return storeCosts;
	}
	public void setStoreCosts(BigDecimal storeC){
		storeCosts = storeC;
	}
}
