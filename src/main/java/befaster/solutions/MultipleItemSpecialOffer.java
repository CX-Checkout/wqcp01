package befaster.solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MultipleItemSpecialOffer implements SpecialOffer {
	private int quantity;
	private int price;
	private String sku;
	
	public MultipleItemSpecialOffer(String sku, int quantity, int price)
	{
		this.sku = sku;
		this.quantity = quantity;
		this.price = price;
	}	



	public static MultipleItemSpecialOffer of(String sku, int quantity, int price)
	{
		return new MultipleItemSpecialOffer(sku, quantity, price);
	}

	@Override
	public SpecialOfferResult apply(Map<String, Long> individualSkus) {
		return Optional.ofNullable(individualSkus.get(sku)).map(noItems -> {
			long specialOfferItems = noItems / quantity;
			long specialOfferValues = specialOfferItems * price;
			
			Map<String, Long> resultingSkus = new HashMap<>(individualSkus);
			resultingSkus.put(sku, resultingSkus.get(sku) - specialOfferItems * quantity);
			return new SpecialOfferResult(resultingSkus, specialOfferValues);
		}).orElseGet(() -> new SpecialOfferResult(individualSkus, 0));
		
	}
}
