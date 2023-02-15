import java.math.BigDecimal;

/**
 * Cheap, cheery and cheesy: any conversion rate will be 1.1.
 * This makes it easy to write unit tests :-)
 */
public class MockConversionDao implements ConversionDao {

    private final BigDecimal alwaysRate = new BigDecimal("1.1");

    public BigDecimal getRate(Currency from, Currency to) {
        return alwaysRate;
    }
}