package befaster.solutions

import befaster.solutions.FreeWithOtherPurchaseSpecialOffer
import befaster.solutions.MultipleItemSpecialOffer
import spock.lang.Specification

class BuyXGetAnotherFreeSpec extends Specification{

	def "does not apply cost right now"()
	{		
		expect:
			applyingOfferTo([A: 3l, B: 2l]).specialOfferPrice == 0
	}
	
	def "reduces target item by 1"()
	{
		expect:
			applyingOfferTo([A: 3l, B: 2l]).resultingsSkus.B == 1l
	}
	
	def "does not apply discount when condition isn't met"()
	{
		expect:
			applyingOfferTo(A: 1l, B: 3l).resultingsSkus.B == 3l
			applyingOfferTo(B: 3l).resultingsSkus.B == 3l
	}
	
	def "does not apply discount if  not free items are available"()
	{
		expect:
			def skus = applyingOfferTo([A: 3l]).resultingsSkus
			!skus.containsKey("B")
	}
	
	def "can be applied multiple times"()
	{
		expect: 
			applyingOfferTo(A: 6L, B: 3L).resultingsSkus.B == 1L
	}
	
	def "can be applied to the same item"()
	{
		expect:
			applyingOfferTo(A: 6L, B: 3L).resultingsSkus.B == 1L
	}
	
	SpecialOfferResult applyingOfferTo(def map)
	{
		return FreeWithOtherPurchaseSpecialOffer.of("A", 3, "B").apply(map)
	}
}
