package befaster.solutions

import spock.lang.Specification
import befaster.solutions.GroupDiscountSpecialOffer
import befaster.solutions.SpecialOfferResult

class GroupDiscountOfferSpec extends Specification{
	def "has a fixed price for matching items"()
	{
		expect:
			applyingOfferTo([S: 1L, T: 1L, Y: 1L]).specialOfferPrice == 45
	}
	
	def "removes purchased items"()
	{
		expect:
			applyingOfferTo([S: 1L, T: 1L, Y: 1L]).resultingsSkus == [S: 0L, T: 0L, Y: 0L]
	}
	
	def "does not removed unqualified items"()
	{
		expect:
			applyingOfferTo([S: 1L, T: 1L, Y: 1L, U: 1L]).resultingsSkus.U == 1L
	}
	
	def "does not removed qualified but unmatching items"()
	{
		expect:
			applyingOfferTo([S: 2L, T: 1L, Y: 1L, X: 1L]).resultingsSkus.X == 1L
	}
	
	def "reduces qualifying item by 1"()
	{
		expect:
			applyingOfferTo([S: 2L, T: 1L, Y: 1L]).resultingsSkus.Y == 1L
	}
	
	def "only applies if enough qualifying items are included"()
	{
		given:
			def offerResult = applyingOfferTo([S: 1L, T: 1L])
		
		expect:
			offerResult.resultingsSkus == [S: 1L, T: 1L]
			offerResult.specialOfferPrice == 0
	}
	
	def "can be applied multiple times"()
	{
		given:
			def offerResult = applyingOfferTo([S: 2L, T: 1L, Y: 2L, Z: 1L])
		
		expect:
			offerResult.resultingsSkus== [S: 0L, T: 0L, Y: 0L, Z: 0L]
			offerResult.specialOfferPrice == 90
	}
	
	SpecialOfferResult applyingOfferTo(Map<String, Long> map)
	{
		return GroupDiscountSpecialOffer.of(3, 45, "S","T","Y","X","Z").apply(map);
	}
}
