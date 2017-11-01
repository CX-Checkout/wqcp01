 package befaster.solutions

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

class SpecialOfferSpec extends Specification implements CheckoutDsl{	
	@Unroll
	def "will apply special offers"()
	{
		expect:
			computingCheckoutValueOf(skuList) == expectedValue
		
		where:
			skuList | expectedValue
			"A,A,A" | 130
			"B,B" | 45
	}
	
	@Unroll
	def "calculates original value when no special offer apply"()
	{
		expect:
			computingCheckoutValueOf(skuList) == expectedValue
		
		where:
			skuList | expectedValue
			"C,C" | 40
			"C,C,C" | 60
			"C,C,C,C" | 80
	}
	
	@Unroll
	def "picks the best special offer when multiple apply"()
	{
		expect:
			computingCheckoutValueOf(skuList) == expectedValue
		
		where:
			skuList | expectedValue
			"A,A,A,A,A" | 200
			"A,A,A,A,A,A" | 250
			"A,A,A,A,A,A,A,A" | 330
	}
	
	@Unroll
	def "combines special offers and none special offers"()
	{
		expect:
			computingCheckoutValueOf(skuList) == expectedValue
		
		where:
			skuList | expectedValue
			"A,A,A,B" | 160
			"A,A,A,A" | 180
	}
	
	@Unroll
	def "get one B free with 2 E's"()
	{
		expect: 
			computingCheckoutValueOf(skuList) == expectedValue
			
		where:
			skuList | expectedValue
			"E,E,B" | 80
			"E,E,E,B" | 120
			"E,E,E,E,B,B" | 160
	}
	
	@Unroll
	def "get one F free with 2 Others"()
	{
		expect:
			computingCheckoutValueOf(skuList) == expectedValue
			
		where:
			skuList | expectedValue
			"F,F,F" | 20
			"F,F,F,F" | 30
			"F,F,F,F,F" | 40
			"F,F,F,F,F,F" | 40
	}

	def "random test that failed on the actual run"()
	{
		expect:
			computingCheckoutValueOf("A,B,C,D,E,A,B,C,D,E") == 280
	}
}

