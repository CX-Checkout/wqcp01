package befaster.solutions;

import static java.util.stream.Collectors.counting;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutCalculator {
	private Map<String, SkuPrice> skuValues;
	private List<SpecialOffer> specialOffers;

	public CheckoutCalculator(Map<String, SkuPrice> skuValues, List<SpecialOffer> specialOffers)
	{
		this.skuValues = skuValues;
		this.specialOffers = specialOffers;
	}
	
	public int calculatePriceFor(Basket basket)
	{
		return basket.calculateTotal(skuValues, specialOffers);
	}
}
