import java.math.BigDecimal;

/**
 * Meant for use only internally inside the FX Service.
 * Simulates what a real FX service would have to do.
 */
public interface ConversionDao {

	BigDecimal getRate(Currency from, Currency to);

}
