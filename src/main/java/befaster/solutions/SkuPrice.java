package befaster.solutions;

public class SkuPrice {
	private final int individualPrice; 
	
	public SkuPrice(int individualPrice, SpecialOffer[] specialOffers)
	{
		this.individualPrice = individualPrice;
	}
		
	public int calculatePriceForItemCountOf(Long noItems)
	{
		return (int)(individualPrice * noItems); 
	}
	
	public static SkuPrice of(int basePrice, SpecialOffer... specialOffer)
	{
		return new SkuPrice(basePrice, specialOffer);
	}

}
