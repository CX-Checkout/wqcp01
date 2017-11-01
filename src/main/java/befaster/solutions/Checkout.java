package befaster.solutions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.counting;

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

		
		String[] individualSkus = skuList.split("");
		if(Arrays.stream(individualSkus).anyMatch(sku -> !skuValues.containsKey(sku)))
		{
			return -1;
		}

		List<SpecialOffer> specialOffers = Arrays.asList(
				FreeWithOtherPurchaseSpecialOffer.of("E", 2, "B"),
				MultipleItemSpecialOffer.of("A", 5, 200),
				MultipleItemSpecialOffer.of("A", 3, 130), 
				MultipleItemSpecialOffer.of("B", 2, 45)
		);
		
		CheckoutCalculator calculator = new CheckoutCalculator(skuValues, specialOffers);
		
		return calculator.calculatePriceFor(Basket.of(individualSkus));		
	}
	

}
