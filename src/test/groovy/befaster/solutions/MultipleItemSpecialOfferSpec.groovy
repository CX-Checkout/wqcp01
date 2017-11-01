package befaster.solutions

import spock.lang.Specification

class MultipleItemSpecialOfferSpec extends Specification{
	def "applies a discount for multiple items"()
	{
		given:
			MultipleItemSpecialOffer offer = MultipleItemSpecialOffer.of("A", 3, 100)
			
		when:
			def result = offer.apply(A: 3l)
			
		then:
			result.specialOfferPrice == 100
			result.resultingsSkus == [A: 0l]
	}
}
