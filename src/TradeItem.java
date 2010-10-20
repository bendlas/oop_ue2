import java.math.BigDecimal;

public abstract class TradeItem {
	
	protected BigDecimal buyPrice, sellPrice, storeCosts;
	
	public TradeItem(int buy, int sell, int store) {
		buyPrice = new BigDecimal(buy);
		sellPrice = new BigDecimal(sell);
		storeCosts = new BigDecimal(store);
	}
	
	public TradeItem(BigDecimal buyPrice, BigDecimal sellPrice, BigDecimal storeCosts){
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.storeCosts = storeCosts;
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
