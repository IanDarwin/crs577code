/**
 * Chapter 3 lab exercise, final Integration Test
 */
package com.gdr.brokerage;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.ltree.converter.generated.Amount;
import com.ltree.converter.generated.Currency;
import com.ltree.converter.generated.CurrencyConverter;
import com.ltree.converter.generated.CurrencyConverterImplService;
import com.ltree.converter.generated.CurrencyLookupException;
import com.ltree.converter.generated.CurrencyLookupFaultDetail;

/**
 * Integration test client for forex service
 */
public class ForexClientIT {
	
	CurrencyConverterImplService currencyConverterService;
	CurrencyConverter converterProxy;
	Amount from;

	@Before
    	public void main() throws Exception {
        
        	// Use the generated code to create a CurrencyConverter
        	currencyConverterService = new CurrencyConverterImplService();
		converterProxy = currencyConverterService.getCurrencyConverterImplPort();
        
        // To redirect requests to soapUI, uncomment the following lines of code 
//        ((javax.xml.ws.BindingProvider) converterProxy).getRequestContext().put(
//            javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY, 
//            "http://localhost:7777");
        
        	from = new Amount();
    }
	
    @Test
    /** Test one valid conversion.
     * The design is limited: the conversion rakes are baked into
     * the service, making them rather hard to test; for now
     * we just use the baked-in conversion rate.
     * In future maybe the team will provide a mockable interface.
     */
    public void testOne() throws Exception {

	// Use the generated code to create an amount of 100 US Dollars
        from.setValue(new BigDecimal(100));
        from.setCurrency(Currency.USD);
        
        System.out.println("Attempting to convert USD to EUR:");
        // Convert the amount into Euros and print out the result
        Amount result = converterProxy.getConvertedAmount(from, Currency.EUR);
        System.out.println("\t" + formatFrom(from) + " equals " + formatFrom(result));
	assertEquals(71.90, result.getAmount().getValue());
    }
	
    @Test
    // Not using "expected" with @Test here so
    // we can explore the faultinfo stuff.
    public void testErrorHandling() {
        from.setValue(new BigDecimal(100));
        from.setCurrency(Currency.JPY); // unsupported currency
        
        try {
            System.out.println("Attempting to convert JPY to EUR:");
            converterProxy.getConvertedAmount(from, Currency.EUR);
            fail("Did not throw expected exception");
        } 
        catch (CurrencyLookupException e) {
            CurrencyLookupFaultDetail faultInfo = e.getFaultInfo();
            System.err.println(e.getMessage() + ": " + faultInfo.getCurrency());
	    assertEquals(Currency.JPY, faultInfo.getCurrency());
        } 
    }

    private static String formatFrom(Amount from) {
        return from.getValue().setScale(2) + " " + from.getCurrency();
    }

}
