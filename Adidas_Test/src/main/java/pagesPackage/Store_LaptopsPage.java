package pagesPackage;

import org.apache.commons.math3.ode.FirstOrderFieldDifferentialEquations;

import testBasePackage.FileReaderManager;

public class Store_LaptopsPage {

	public void clickOnSonyLaptop() {
		FileReaderManager.getInstance().getUtilityReader().clickOnElement("SonyVaioI5");
	}
	
	public void clickOnAddToCart() {
		
		FileReaderManager.getInstance().getUtilityReader().explicitWait("AddToCartButton", 10L);
		FileReaderManager.getInstance().getUtilityReader().clickOnElement("AddToCartButton");
		FileReaderManager.getInstance().getUtilityReader().acceptAlert();
		
	}
	
	public void NavigateToHomePage() {
		
		FileReaderManager.getInstance().getUtilityReader().clickOnElement("HomeNavigation");
	}
	
	public void clickOnDellLaptop() {
		
		FileReaderManager.getInstance().getUtilityReader().clickOnElement("DellI78GB");
	}

}
