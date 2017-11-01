package befaster.solutions

import befaster.solutions.FreeWithOtherPurchaseSpecialOffer
import befaster.solutions.MultipleItemSpecialOffer
import spock.lang.Specification

class BuyTheSameItemGetAnotherFreeSpec extends Specification{

	def "remove one of the original item"()
	{		
		expect:
			applyingOfferTo([F: 3L]).resultingsSkus.F == 2L
	}
	
	def "can remove multiple items when applicable"()
	{
		expect:
			applyingOfferTo([F: 6L]).resultingsSkus.F == 4L
	}
	
	def "does not apply more than once if no target available"()
	{
		expect:
			applyingOfferTo([F: 5L]).resultingsSkus.F == 4L
	}
	
	
	SpecialOfferResult applyingOfferTo(def map)
	{
		return BOGOFSpecialOffer.of("F", 2).apply(map)
	}
}
