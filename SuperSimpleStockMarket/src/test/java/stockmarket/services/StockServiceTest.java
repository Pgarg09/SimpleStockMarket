package stockmarket.services;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import stockmarket.exceptions.BusinessException;
import stockmarket.exceptions.InvalidValueException;
import stockmarket.model.GBCEDataModelSingleton;
import stockmarket.model.StockDataModel;
import stockmarket.util.StockMarketConstant;

public class StockServiceTest {

	private static Collection<StockDataModel> stockDataModels= null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stockDataModels = GBCEDataModelSingleton.getStockDataModel().values();
		List<StockDataModel> dataModels = stockDataModels.stream().collect(Collectors.toList());
		Iterator<StockDataModel> iterator = dataModels.iterator();
		while(iterator.hasNext()) {
			iterator.next().setPrice(10.00);
		}
	}
	
	@Test
	public void testGetInstance(){
		int hashCodeValue1 = StockService.getInstance().hashCode();
		int hashCodeValue2 = StockService.getInstance().hashCode();
		assertEquals(hashCodeValue1, hashCodeValue2);
	}

	@Test(expected = BusinessException.class)
	public void testCalculateDividendYield_InvalidStock() {
		StockService service = StockService.getInstance();
		service.calculateDividendYield("ABC", 12.12);
	}
	
	@Test(expected = InvalidValueException.class)
	public void testCalculateDividendYield_InvalidPrice() {
		StockService service = StockService.getInstance();
		service.calculateDividendYield("Tea", -10.0);
	}
	
	@Test
	public void testCalculateDividendYield_Common() {
		StockService service = StockService.getInstance();
		assertEquals("0.8", service.calculateDividendYield("Pop", 10.0));
	}
	
	@Test
	public void testCalculateDividendYield_Preferred() {
		StockService service = StockService.getInstance();
		assertEquals("20.0", service.calculateDividendYield("Gin", 10.0));
	}

	@Test(expected = BusinessException.class)
	public void testCalculatePERatio_InvalidStock() {
		StockService service = StockService.getInstance();		
		service.calculatePERatio("ABC", 12.12);
	}
	
	@Test(expected = InvalidValueException.class)
	public void testCalculatePERatio_InvalidPrice() {
		StockService service = StockService.getInstance();		
		service.calculatePERatio("Pop", -12.12);
	}
	
	@Test
	public void testCalculatePERatio() {
		StockService service = StockService.getInstance();		
		assertEquals("1.51", service.calculatePERatio("Pop", 12.12));
	}
	

	@Test(expected = BusinessException.class)
	public void testRecordTrade_InvalidStock() {
		StockService service = StockService.getInstance();
		service.recordTrade("ABCD", 12, StockMarketConstant.BUY_INDICATOR, new Date(), 12.12);
	}
	
	@Test(expected = InvalidValueException.class)
	public void testRecordTrade_InvalidQuantity() {
		StockService service = StockService.getInstance();
		service.recordTrade("Pop", -12, StockMarketConstant.BUY_INDICATOR, new Date(), 12.12);
	}
	
	@Test(expected = InvalidValueException.class)
	public void testRecordTrade_InvalidBuySell() {
		StockService service = StockService.getInstance();
		service.recordTrade("Pop", 12, "Purchase",new Date(), 12.12);
	}
	
	@Test(expected = InvalidValueException.class)
	public void testRecordTrade_InvalidPrice() {
		StockService service = StockService.getInstance();
		service.recordTrade("Pop", 12, StockMarketConstant.BUY_INDICATOR, new Date(), -12.12);
	}
	
	
	@Test
	public void testRecordTrade() {
		StockService service = StockService.getInstance();
		assertTrue(service.recordTrade("Pop", 12, StockMarketConstant.BUY_INDICATOR, new Date(), 12));
	}
	

	@Test
	public void testCalVolWeightedStockPrice_NoTrade() {
		long time = new Date().getTime();
		StockService service = StockService.getInstance();
		service.getTradingModelList().clear();
		service.recordTrade("Pop", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-20*60*1000), 12);
		service.recordTrade("Tea", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-16*60*1000), 12);
		service.recordTrade("Gin", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-17*60*1000), 12);
		service.recordTrade("Joe", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-18*60*1000), 12);
		assertEquals("0.00", service.calVolWeightedStockPrice());
		
		
	}
	
	@Test
	public void testCalVolWeightedStockPrice() {
		long time = new Date().getTime();
		StockService service = StockService.getInstance();
		service.getTradingModelList().clear();
		service.recordTrade("Pop", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-10*60*1000), 12);
		service.recordTrade("Tea", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-11*60*1000), 12);
		service.recordTrade("Gin", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-5*60*1000), 12);
		service.recordTrade("Joe", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-2*60*1000), 12);
		assertEquals("12", service.calVolWeightedStockPrice());
		
		
	}

	@Test
	public void testGetMeanOfPrices() {
		StockService service = StockService.getInstance();
		assertEquals("316.23", service.getMeanOfPrices());
	}

}
