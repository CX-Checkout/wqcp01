package befaster.solutions;

import java.util.HashMap;
import java.util.Map;

public class FreeWithOtherPurchaseSpecialOffer implements SpecialOffer{
	
	private final String originalSku;
	private final int unitsToBuy;
	private final String skuOfFreeItem;
	
	public FreeWithOtherPurchaseSpecialOffer(String originalSku, int unitsToBuy, String skuOfFreeItem)
	{
		this.originalSku = originalSku;
		this.unitsToBuy = unitsToBuy;
		this.skuOfFreeItem = skuOfFreeItem;
	}
	
	
	public static SpecialOffer of(String originalSku, int unitsToBuy, String skuOfFreeItem)
	{
		return new FreeWithOtherPurchaseSpecialOffer(originalSku, unitsToBuy, skuOfFreeItem);
	}

	@Override
	public SpecialOfferResult apply(Map<String, Long> individualSkus) {
		Map<String, Long> resultsSkus = new HashMap<>(individualSkus);
		long sourceItemCount = individualSkus.getOrDefault(originalSku, 0L);
		while(sourceItemCount >= unitsToBuy && individualSkus.containsKey(skuOfFreeItem))
		{
			resultsSkus.put(skuOfFreeItem, resultsSkus.get(skuOfFreeItem)-1);
			sourceItemCount -= unitsToBuy;
		}
		return new SpecialOfferResult(resultsSkus, 0);
	}

}
