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
	def "3N get one M free"()
	{
		expect:
			computingCheckoutValueOf("N,N,N,M") == 120
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
	
	@Unroll
	def "H is discounted"()
	{
		expect:
			computingCheckoutValueOf(skuList) == expectedValue
			
		where:
			skuList | expectedValue
			"H,H,H,H,H" | 45
			"H,H,H,H,H,H,H,H,H,H" | 80
	}
	
	@Unroll
	def "K is discounted"()
	{
		expect:
			computingCheckoutValueOf(skuList) == expectedValue
			
		where:
			skuList | expectedValue
			"K,K" | 150
			"K,K,K,K" | 300
	}
	
	def "5P for 200"()
	{
		expect: 
			computingCheckoutValueOf("P,P,P,P,P") == 200
	}
	
	def "3Q for 80"()
	{
		expect:
			computingCheckoutValueOf("Q,Q,Q") == 80 
	}
	
	def "3R get one Q free"()
	{
		expect:
			computingCheckoutValueOf("R,R,R,Q") == 150
	}
	
	def "3U get one U free"()
	{
		expect:
			computingCheckoutValueOf("U,U,U,U") == 120
	}

	@Unroll
	def "2V for 90, 3V for 130"()
	{
		expect:
			computingCheckoutValueOf(skuList) == expectedValue
			
		where:
			skuList | expectedValue
			"V,V" | 90
			"V,V,V" | 130
	}
	
	def "random test that failed on the actual run"()
	{
		expect:
			computingCheckoutValueOf("A,B,C,D,E,A,B,C,D,E") == 280
	}
}

