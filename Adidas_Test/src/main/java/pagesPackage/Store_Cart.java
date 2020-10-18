package pagesPackage;

import org.testng.Assert;

import testBasePackage.FileReaderManager;

public class Store_Cart {
	
	String actualAmount;
	String expectedAmount;
	
	public void navigateToCart() {
		
		FileReaderManager.getInstance().getUtilityReader().clickOnElement("CartNavigation");
	}
	
	public void deleteItemFromCart() {
		
		String laptopName = FileReaderManager.getInstance().getUtilityReader().getTextFunction("CartTableRows");
		
		if(laptopName.equalsIgnoreCase("Dell i7 8gb")) {
			FileReaderManager.getInstance().getUtilityReader().clickOnElement("DeleteSecondItemFromCart");
		}else {
			FileReaderManager.getInstance().getUtilityReader().clickOnElement("DeleteFirstItemFromCart");
		}
	}
	
	public void clickOnPlaceOrder() {
		
		FileReaderManager.getInstance().getUtilityReader().clickOnElement("PlaceOrder");
	}
	
	public void fillUpForm() {
		
		FileReaderManager.getInstance().getUtilityReader().explicitWait("TotalAmountOnForm", 10L);
		actualAmount = FileReaderManager.getInstance().getUtilityReader().getTextFunction("TotalAmountOnForm");
		FileReaderManager.getInstance().getUtilityReader().sendKeysMethod("NameInForm", "ASD");
		FileReaderManager.getInstance().getUtilityReader().sendKeysMethod("CountryInForm", "India");
		FileReaderManager.getInstance().getUtilityReader().sendKeysMethod("CityInForm", "gur");
		FileReaderManager.getInstance().getUtilityReader().sendKeysMethod("CardInForm", "123");
		FileReaderManager.getInstance().getUtilityReader().sendKeysMethod("MonthInForm", "11");
		FileReaderManager.getInstance().getUtilityReader().sendKeysMethod("YearInForm", "11");
	}
	
	public void clickOnPurchaseButton() {
		
		FileReaderManager.getInstance().getUtilityReader().clickOnElement("PurchaseOrder");
	}
	
	public void getIdAndVerifyAmount() {
		String id = FileReaderManager.getInstance().getUtilityReader().getTextFunction("PurchaseId");
		expectedAmount = FileReaderManager.getInstance().getUtilityReader().getTextFunction("PurchaseAmount");
		
		String[] amount = expectedAmount.split(":");
		String amount1 = amount[1];
		
		Assert.assertEquals(actualAmount, amount1 , "The Amount is not matching");
		
	}
	
	public void clickOnOK() {
		
		FileReaderManager.getInstance().getUtilityReader().clickOnElement("OkButton");
	}

}
