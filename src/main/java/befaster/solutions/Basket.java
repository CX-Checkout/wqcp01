package befaster.solutions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.counting;

public class Basket {
	private String[] individualSkus;
	
	public Basket(String[] individualSkus)
	{
		this.individualSkus = individualSkus;
	}
	
	public static Basket of(String[] individualSkus)
	{
		return new Basket(individualSkus);
	}
	
	public int calculateTotal(Map<String, SkuPrice> skuValues, List<SpecialOffer> specialOffers)
	{
		Map<String, Long> groupedSkus = Arrays.stream(individualSkus).collect(Collectors.groupingBy(s -> s, counting()));
		int total = 0;
		for(SpecialOffer specialOffer : specialOffers)
		{
			SpecialOfferResult specialOfferResult = specialOffer.apply(groupedSkus);
			total += specialOfferResult.getSpecialOfferPrice();	
			groupedSkus = specialOfferResult.getResultingsSkus();
		}
		
		total += groupedSkus.entrySet().stream().mapToInt(entry -> skuValues.get(entry.getKey()).calculatePriceForItemCountOf(entry.getValue())).mapToLong(value -> value).sum();                                                                                                             
		return total;
	}
}
