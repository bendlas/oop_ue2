import java.math.BigDecimal;

/* Base class for Products and Configurations
 * Abstracting buy, store and sell costs
 * ItemCollection is the Container for it through the program
 * Uses exact numbers for prizes
 */
public abstract class TradeItem {
		
	protected BigDecimal buyPrice, sellPrice, storeCosts;
	
	//invariance: buy, sell and store are >= 0
	/*postcondition: Type of buy, sell and store is BigDecimal;
					 all prices are set
					 */
	//GOOD: prices for all TradeItems are calculated & stored in  BigDecimal to guarantee a correct amount
	public TradeItem(int buy, int sell, int store) {
		buyPrice = p(buy);
		sellPrice = p(sell);
		storeCosts = p(store);
	}
	
	/*Ueberschriebener Konstruktor
	 */
	//preconditon: all params are >= 0
	//postcondition: all prices are set
	public TradeItem(BigDecimal buyPrice, BigDecimal sellPrice, BigDecimal storeCosts){
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.storeCosts = storeCosts;
	}
	
	//invariance: i is >= 0
	//postcondition: i is of type BigDecimal 
	static public BigDecimal p(int i) {
		return new BigDecimal(i);
	}
	
	//invariance: i is >= 0
	//postcondition: i is of type BigDecimal 
	static public BigDecimal p(float i) {
		return new BigDecimal(i);
	}
	
	//postcondition: buyprice is returned as BigDecimal
	public BigDecimal getBuyPrice(){
		return buyPrice;
	}
	
	//invariance: buyPrice is >= 0
	//postcondition: buyPrice is set
	public void setBuyPrice(BigDecimal buyP){
		buyPrice = buyP;
	}
	
	//postcondition: sellprice is returned as BigDecimal
	public BigDecimal getSellPrice(){
		return sellPrice;
	}
	
	//invariance: sellPrice is >= 0
	//postcondition: sellPrice is set
	public void setSellPrice(BigDecimal sellP){
		sellPrice = sellP;
	}
	
	//postcondition: store costs are returned as BigDecimal
	public BigDecimal getStoreCosts(){
		return storeCosts;
	}
	
	//invariance: store costs are >= 0
	//postcondition: store costs are set
	public void setStoreCosts(BigDecimal storeC){
		storeCosts = storeC;
	}
}
