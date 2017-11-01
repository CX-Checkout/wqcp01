package befaster.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checkout {
	
	
	public static int checkout(String skuList)
	{
		if(skuList.trim().equals(""))
			return 0;
		
		
		Map<String, SkuPrice> skuValues = new HashMap<>();
		skuValues.put("A", SkuPrice.of(50));
		skuValues.put("B", SkuPrice.of(30));
		skuValues.put("C", SkuPrice.of(20));
		skuValues.put("D", SkuPrice.of(15));
		skuValues.put("E", SkuPrice.of(40));
		skuValues.put("F", SkuPrice.of(10));

		
		String[] individualSkus = skuList.split("");
		if(Arrays.stream(individualSkus).anyMatch(sku -> !skuValues.containsKey(sku)))
		{
			return -1;
		}

		List<SpecialOffer> specialOffers = Arrays.asList(
				FreeWithOtherPurchaseSpecialOffer.of("E", 2, "B"),
				BOGOFSpecialOffer.of("F", 2),
				MultipleItemSpecialOffer.of("A", 5, 200),
				MultipleItemSpecialOffer.of("A", 3, 130), 
				MultipleItemSpecialOffer.of("B", 2, 45)
		);
		
		CheckoutCalculator calculator = new CheckoutCalculator(skuValues, specialOffers);
		
		return calculator.calculatePriceFor(Basket.of(individualSkus));		
	}
	

}
