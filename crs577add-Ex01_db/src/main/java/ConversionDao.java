import java.math.BigDecimal;

public interface ConversionDao {

	BigDecimal getRate(Currency from, Currency to);

}
