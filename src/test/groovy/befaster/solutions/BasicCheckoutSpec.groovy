package befaster.solutions

import spock.lang.Specification
import spock.lang.Unroll

class BasicCheckoutSpec extends Specification{
	@Unroll
	def "can purchase a single item"()
	{
		expect:
			computingCheckoutValueOf(sku) == expectedValue
			
		where:
			sku | expectedValue
			"A" | 50
			"B" | 30
			"C" | 20
			"D" | 15
			"E" | 40
			"F" | 10
			"G" | 20
			"H" | 10
			"I" | 35
			"J" | 60
			"K" | 70
			"L" | 90
			"M" | 15
			"N" | 40
			"O" | 10
			"P" | 50
			"Q" | 30
			"R" | 50
			"S" | 20
			"T" | 20
			"U" | 40
			"V" | 50
			"W" | 20
			"X" | 17
			"Y" | 20
			"Z" | 21
	}
	
	@Unroll
	def "can purchase multiple items"()
	{
		expect:
			computingCheckoutValueOf(skuList) == expectedValue
			
		where:
			skuList | expectedValue
			"A,B" | 80
			"B,C" | 50
			"A,B,C,D" | 115
	}
	
	@Unroll
	def "does not compute and invalid string"()
	{
		expect:
			computingCheckoutValueOf(illegalInput) == -1
			
		where:
			illegalInput | _
			"A!BB" 		 | _
			"GARBA.GE"    | _
	}
	
	def "returns 0 when there are no more items"()
	{
		expect:
			computingCheckoutValueOf("") == 0
	}
	
	/*@Unroll
	def "will apply special offers"()
	{
		expect:
			computingCheckoutValueOf(skuList) == expectedValue
		
		where:
			skuList | expectedValue
			"A,A,A" | 130
			"A,A,A,A,A,A" | 260
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
	def "combine special offers and none special offers"()
	{
		expect:
			computingCheckoutValueOf(skuList) == expectedValue
		
		where:
			skuList | expectedValue
			"A,A,A,B" | 160
			"A,A,A,A" | 180
	}*/

	private static int computingCheckoutValueOf(String skuList)
	{
		Checkout.checkout(skuList.split(",").join(""))
	}
}
