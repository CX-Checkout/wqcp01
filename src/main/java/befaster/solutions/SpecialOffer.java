package befaster.solutions;

import java.util.Map;

public interface SpecialOffer {
	SpecialOfferResult apply(Map<String, Long> individualSkus);
}