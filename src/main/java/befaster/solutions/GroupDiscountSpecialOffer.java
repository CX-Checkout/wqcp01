package befaster.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupDiscountSpecialOffer implements SpecialOffer{
	private final List<String> applicableSkus;
	private final int noItemsRequired;
	private final int discountPrice;
	
	public GroupDiscountSpecialOffer(List<String> applicableSkus, int noItemsRequired, int discountPrice) 
	{
		this.applicableSkus = applicableSkus;
		this.noItemsRequired = noItemsRequired;
		this.discountPrice = discountPrice;
	}

	@Override
	public SpecialOfferResult apply(Map<String, Long> individualSkus) {
		Map<String, Long> remainingSkus = new HashMap<>(individualSkus);
		Long noQualifyingItems = individualSkus.entrySet().stream().map(entry -> {
			if(applicableSkus.contains(entry.getKey()))
				return entry.getValue();
			return 0L;
		}).collect(Collectors.summingLong(i -> i));
		
		int total = 0;
		while(noQualifyingItems >= noItemsRequired)
		{
			removeOneItemFrom(remainingSkus);
			removeOneItemFrom(remainingSkus);
			removeOneItemFrom(remainingSkus);
			total += discountPrice;
			noQualifyingItems -= noItemsRequired;
		}
		
		return new SpecialOfferResult(remainingSkus, total);
	}

	private void removeOneItemFrom(Map<String, Long> remainingSkus) {
		for(String applicableSku : applicableSkus)
		{
			Long currentVal = remainingSkus.get(applicableSku);
			if(currentVal != null && currentVal > 0)
			{
				remainingSkus.put(applicableSku, currentVal-1);
				return;
			}
		}	
	}

	public static GroupDiscountSpecialOffer of(int itemsToBuy, int discountPrice, String... skus) 
	{
		return new GroupDiscountSpecialOffer(Arrays.asList(skus), itemsToBuy, discountPrice);
	}

}
