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
		
		
		Map<String, SkuPrice> skuValues = allProducts();

		
		String[] individualSkus = skuList.split("");
		if(Arrays.stream(individualSkus).anyMatch(sku -> !skuValues.containsKey(sku)))
		{
			return -1;
		}

		List<SpecialOffer> specialOffers = allSpecialOffers();
		
		CheckoutCalculator calculator = new CheckoutCalculator(skuValues, specialOffers);
		
		return calculator.calculatePriceFor(Basket.of(individualSkus));		
	}

	private static List<SpecialOffer> allSpecialOffers() {
		List<SpecialOffer> specialOffers = Arrays.asList(
				FreeWithOtherPurchaseSpecialOffer.of("E", 2, "B"),
				FreeWithOtherPurchaseSpecialOffer.of("N", 3, "M"),
				FreeWithOtherPurchaseSpecialOffer.of("R", 3, "Q"),
				BOGOFSpecialOffer.of("F", 2),
				BOGOFSpecialOffer.of("U", 3),
				MultipleItemSpecialOffer.of("A", 5, 200),
				MultipleItemSpecialOffer.of("A", 3, 130), 
				MultipleItemSpecialOffer.of("B", 2, 45),
				MultipleItemSpecialOffer.of("H", 10, 80),
				MultipleItemSpecialOffer.of("H", 5, 45),
				MultipleItemSpecialOffer.of("K", 2, 150),
				MultipleItemSpecialOffer.of("P", 5, 200),
				MultipleItemSpecialOffer.of("Q", 3, 80),
				MultipleItemSpecialOffer.of("V", 3, 130),
				MultipleItemSpecialOffer.of("V", 2, 90)
		);
		return specialOffers;
	}

	private static Map<String, SkuPrice> allProducts() {
		Map<String, SkuPrice> skuValues = new HashMap<>();
		skuValues.put("A", SkuPrice.of(50));
		skuValues.put("B", SkuPrice.of(30));
		skuValues.put("C", SkuPrice.of(20));
		skuValues.put("D", SkuPrice.of(15));
		skuValues.put("E", SkuPrice.of(40));
		skuValues.put("F", SkuPrice.of(10));
		skuValues.put("G", SkuPrice.of(20));
		skuValues.put("H", SkuPrice.of(10));
		skuValues.put("I", SkuPrice.of(35));
		skuValues.put("J", SkuPrice.of(60));
		skuValues.put("K", SkuPrice.of(80));
		skuValues.put("L", SkuPrice.of(90));
		skuValues.put("M", SkuPrice.of(15));
		skuValues.put("N", SkuPrice.of(40));
		skuValues.put("O", SkuPrice.of(10));
		skuValues.put("P", SkuPrice.of(50));
		skuValues.put("Q", SkuPrice.of(30));
		skuValues.put("R", SkuPrice.of(50));
		skuValues.put("S", SkuPrice.of(30));
		skuValues.put("T", SkuPrice.of(20));
		skuValues.put("U", SkuPrice.of(40));
		skuValues.put("V", SkuPrice.of(50));
		skuValues.put("W", SkuPrice.of(20));
		skuValues.put("X", SkuPrice.of(90));
		skuValues.put("Y", SkuPrice.of(10));
		skuValues.put("Z", SkuPrice.of(50));
		return skuValues;
	}
	

}
