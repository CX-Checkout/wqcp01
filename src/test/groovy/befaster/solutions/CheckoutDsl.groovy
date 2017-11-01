package befaster.solutions

import spock.lang.Specification
import spock.lang.Unroll

trait CheckoutDsl {
	static int computingCheckoutValueOf(String skuList)
	{
		Checkout.checkout(skuList.split(",").join(""))
	}
}
