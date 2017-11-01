package befaster.solutions;

import java.util.Map;

public class SpecialOfferResult {
	private final Map<String, Long> resultingsSkus;
	private final long specialOfferPrice;
	
	public SpecialOfferResult(Map<String, Long> resultingSkus, long specialOfferPrice)
	{
		this.resultingsSkus = resultingSkus;
		this.specialOfferPrice = specialOfferPrice;
	}
	
	public long getSpecialOfferPrice()
	{
		return specialOfferPrice;
	}
	
	public Map<String, Long> getResultingsSkus()
	{
		return resultingsSkus;
	}
}
