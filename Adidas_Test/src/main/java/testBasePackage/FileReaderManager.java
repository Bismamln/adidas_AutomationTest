package testBasePackage;

import pagesPackage.Store_Cart;
import pagesPackage.Store_HomePage;
import pagesPackage.Store_LaptopsPage;

public class FileReaderManager {

	private static FileReaderManager fileReader = new FileReaderManager();
	private static TestBase testBase = new TestBase();
	private static UtilityClass utility = new UtilityClass();
	private static Store_Cart cartReader = new Store_Cart();
	private static Store_HomePage homeReader = new Store_HomePage();
	private static Store_LaptopsPage laptopReader = new Store_LaptopsPage();
	


	private FileReaderManager() {

	}

	public static FileReaderManager getInstance() {

		return fileReader;
	}

	public TestBase getTestBaseReader() {
		return(testBase == null) ? new TestBase() : testBase ;
	}
	
	public UtilityClass getUtilityReader() {
		return(utility == null) ? new UtilityClass() : utility ;
	}
	
	public Store_Cart getCartReader() {
		return(cartReader == null) ? new Store_Cart() : cartReader ;
	}
	
	public Store_HomePage getHomePageReader() {
		return(homeReader == null) ? new Store_HomePage() : homeReader ;
	}
	
	public Store_LaptopsPage getLaptopReader() {
		return(laptopReader == null) ? new Store_LaptopsPage() : laptopReader ;
	}



}