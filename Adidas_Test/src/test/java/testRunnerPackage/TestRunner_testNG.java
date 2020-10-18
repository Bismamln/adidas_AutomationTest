package testRunnerPackage;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import retryPackage.RetryAnalyzer;
import testBasePackage.FileReaderManager;

public class TestRunner_testNG {

	@BeforeClass
	public void initiateReports() {
		FileReaderManager.getInstance().getTestBaseReader().generateExtentReport();
	}

	@BeforeTest
	public void openBrowser() {

		FileReaderManager.getInstance().getUtilityReader().ChromeBrowserInstantiation();
		FileReaderManager.getInstance().getUtilityReader().maximizeWindow();
		FileReaderManager.getInstance().getUtilityReader().openURL();
		FileReaderManager.getInstance().getUtilityReader().implicitWait();
	}

	@AfterTest
	public void closeConnection() {
		//closeBrowser();	//Un-comment when Utility class to be extended here
		FileReaderManager.getInstance().getTestBaseReader().log4j();
		FileReaderManager.getInstance().getUtilityReader().closeBrowser();
	}

	@AfterMethod
	public void extentReport(ITestResult result) {

		FileReaderManager.getInstance().getTestBaseReader().logStatusExtentReport(result);
	}

	@AfterClass
	public void closeConnections() {

		FileReaderManager.getInstance().getTestBaseReader().endExtentReport();
	}

	@Test(priority = 1 , retryAnalyzer = RetryAnalyzer.class)
	public void selectSonyLaptopForPurchase() {
		try {
			FileReaderManager.getInstance().getHomePageReader().clickOnLaptopCategory();
			FileReaderManager.getInstance().getLaptopReader().clickOnSonyLaptop();
			FileReaderManager.getInstance().getLaptopReader().clickOnAddToCart();
			FileReaderManager.getInstance().getLaptopReader().NavigateToHomePage();
			FileReaderManager.getInstance().getHomePageReader().clickOnLaptopCategory();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2 , retryAnalyzer = RetryAnalyzer.class)
	public void selectDellLaptopForPurchase() {
		try {
			FileReaderManager.getInstance().getLaptopReader().clickOnDellLaptop();
			FileReaderManager.getInstance().getLaptopReader().clickOnAddToCart();
			FileReaderManager.getInstance().getCartReader().navigateToCart();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3 , retryAnalyzer = RetryAnalyzer.class)
	public void deleteDellLaptop() {
		try {
			FileReaderManager.getInstance().getCartReader().deleteItemFromCart();
			FileReaderManager.getInstance().getCartReader().clickOnPlaceOrder();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4 , retryAnalyzer = RetryAnalyzer.class)
	public void fillTheFormToPurchase() {
		try {
			FileReaderManager.getInstance().getCartReader().fillUpForm();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 5 , retryAnalyzer = RetryAnalyzer.class)
	public void purchaseOrder() {
		try {
			FileReaderManager.getInstance().getCartReader().clickOnPurchaseButton();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 6 , retryAnalyzer = RetryAnalyzer.class)
	public void captureIdAndVerifyAmount() {
		try {
			FileReaderManager.getInstance().getCartReader().getIdAndVerifyAmount();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 7 , retryAnalyzer = RetryAnalyzer.class)
	public void clickOKToCloseDialog() {
		try {
			FileReaderManager.getInstance().getCartReader().clickOnOK();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
