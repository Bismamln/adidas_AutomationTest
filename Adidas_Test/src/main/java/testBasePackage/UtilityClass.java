package testBasePackage;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

public class UtilityClass{
	static WebDriver driver ;
	static WebElement ele ;
	

	public void ChromeBrowserInstantiation() {
		System.setProperty(FileReaderManager.getInstance().getTestBaseReader().getProperties("ChromeProperties"),
				FileReaderManager.getInstance().getTestBaseReader().getProperties("ChromeDriverPath"));
		driver = new ChromeDriver();
	}

	public void openURL() {

		driver.get(FileReaderManager.getInstance().getTestBaseReader().getProperties("BrowserURL"));
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void closeBrowser() {
		driver.close();
	}

	public void explicitWait(String locatorPath , Long waitTimeInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver , waitTimeInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorPath)));
	}

	public void implicitWait() {
		Long implicitWait = Long.parseLong(FileReaderManager.getInstance().getTestBaseReader().getProperties("implicitlyWait"));
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
	}

	public  WebElement clickOnElement(String elementLocator) {
		
		try {
			ele = driver.findElement(By.xpath(FileReaderManager.getInstance().getTestBaseReader().getProperties(elementLocator)));

			if(ele.isDisplayed()) {
				ele.click();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return ele ;
	}

	public WebElement sendKeysMethod(String elementLocator, String value) {
		
		try {
			ele = driver.findElement(By.xpath(FileReaderManager.getInstance().getTestBaseReader().getProperties(elementLocator)));

			if(ele.isDisplayed()) {
				ele.sendKeys(value);
			}
		}catch(Exception e ) {

		}
		return ele;
	}

	public WebElement selectValueFromDropdown(String elementLocator , String value) {
		
		try {
			ele = driver.findElement(By.xpath(FileReaderManager.getInstance().getTestBaseReader().getProperties(elementLocator)));
			if(ele.isEnabled()) {
				Select select = new Select(ele);
				select.selectByValue(value);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

		return ele;
	}

	public void captureScreenshotOnFailure(ITestResult result) {

		try {
			//if(ITestResult.FAILURE == result.getStatus()) {

				TakesScreenshot screenshot = (TakesScreenshot)driver ;

				File source = screenshot.getScreenshotAs(OutputType.FILE);
				File destination = new File(FileReaderManager.getInstance().getTestBaseReader().getProperties("ScreenshotPath")
						+ result.getName() + ".png");

				FileHandler.copy(source, destination);
				//System.out.println(result.getName() + " Screenshot Captured");

			//}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getTextFunction(String elementLocator) {
		String text = null;
		try {
		ele = driver.findElement(By.xpath(FileReaderManager.getInstance().getTestBaseReader().getProperties(elementLocator)));
		text = ele.getText();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return text;
	}

	
	public void acceptAlert() {
		
		driver.switchTo().alert().accept();
	}


}
