import java.math.BigDecimal;

/* Base class for Products and Configurations
 * Abstracting buy, store and sell costs
 * ItemCollection is the Container for it through the program
 * Uses exact numbers for prizes
 */
//GOOD: This class simplyfies the common need to handle Sets of Products, Configurations with associated amount
//      a lot
public abstract class TradeItem {
		
	protected BigDecimal buyPrice, sellPrice, storeCosts;
	
	//GOOD: prices for all TradeItems are calculated & stored in  BigDecimal to guarantee deterministic rounding
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
	
	//post: buyPrice is set
	public void setBuyPrice(BigDecimal buyP){
		buyPrice = buyP;
	}
	
	public BigDecimal getSellPrice(){
		return sellPrice;
	}
	
	//post: sellPrice is set
	public void setSellPrice(BigDecimal sellP){
		sellPrice = sellP;
	}
	
	public BigDecimal getStoreCosts(){
		return storeCosts;
	}
	
	//post: store costs are set
	public void setStoreCosts(BigDecimal storeC){
		storeCosts = storeC;
	}
}
