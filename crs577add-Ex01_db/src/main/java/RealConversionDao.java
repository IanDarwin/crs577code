import java.math.BigDecimal;

class RealConversionDao implements ConversionDao {

	public BigDecimal getRate(Currency From, Currency to) {
		throw new IllegalStateException("Real db-based converter not written yet");
	}
}
