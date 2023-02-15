import java.math.BigDecimal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DaoTest {

    @Test
    public void testFakeRates() {
        System.out.println("Testing!");
        ConversionDao dao = new MockConversionDao();
        BigDecimal rate = dao.getRate(Currency.USD, Currency.EUR);
        assertEquals(rate.doubleValue(), 1.1, .001);
    }
}