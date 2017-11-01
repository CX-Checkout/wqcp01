package befaster.solutions;

public class SkuPrice {
	private final int individualPrice; 
	private final int itemForSpecialOffer; 
	private final int specialOfferPrice;
	
	public SkuPrice(int individualPrice, int itemForSpecialOffer, int specialOfferPrice)
	{
		this.individualPrice = individualPrice;
		this.itemForSpecialOffer = itemForSpecialOffer;
		this.specialOfferPrice = specialOfferPrice;
	}
	
	public static SkuPrice of(int individualPrice, int itemForSpecialOffer, int specialOfferPrice)
	{
		return new SkuPrice(individualPrice, itemForSpecialOffer, specialOfferPrice);
	}
	
	public static SkuPrice of(int individualPrice)
	{
		return of(individualPrice, 1, individualPrice);
	}
	
	public int calculatePriceForItemCountOf(Long noItems)
	{
		long specialOffers = noItems / itemForSpecialOffer;
		long remainders = noItems % itemForSpecialOffer;
		long individualValues = remainders * individualPrice;
		long specialOfferValues = specialOffers * specialOfferPrice;
		return (int)(individualValues + specialOfferValues);
	}

}
