package befaster.solutions;

import java.util.HashMap;
import java.util.Map;

public class BOGOFSpecialOffer implements SpecialOffer{
	private final String sku;
	private final int itemsToBuy;
	
	public BOGOFSpecialOffer(String sku, int itemsToBuy) 
	{
		this.sku = sku;
		this.itemsToBuy = itemsToBuy;
	}

	@Override
	public SpecialOfferResult apply(Map<String, Long> individualSkus) {
		long itemsInBasket = individualSkus.getOrDefault(sku, 0L);
		if(itemsInBasket == 0)
		{
			return new SpecialOfferResult(individualSkus, 0);
		}
		
		long eligableItems = itemsInBasket; 
		long timesApplied = 0;
		while(eligableItems >= itemsToBuy && eligableItems - itemsToBuy > 0)
		{
			timesApplied++;
			eligableItems -= itemsToBuy + 1;
		}
		Map<String, Long> resultsSkus = new HashMap<>(individualSkus);
		resultsSkus.put(sku, itemsInBasket - timesApplied);
		return new SpecialOfferResult(resultsSkus, 0);
	}
	
	public static BOGOFSpecialOffer of(String sku, int itemsToBuy)
	{
		return new BOGOFSpecialOffer(sku, itemsToBuy);
	}

}
