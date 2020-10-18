package testBasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	private File src ;
	private FileInputStream fis ;
	private XSSFWorkbook wb ;
	private XSSFSheet sh ;
	private Properties prop;
	private ExtentReports reports ;
	private ExtentTest test ;

	public TestBase() {
		
		try {
			src = new File("C:\\Users\\DELL\\eclipse-workspace\\Adidas_Test\\PropertiesFile.properties");

			fis = new FileInputStream(src);

			prop = new Properties();
			prop.load(fis);
			//fis.close();
			//System.out.println("File Loaded");

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getProperties(String propertyValue) {
		String properties =prop.getProperty(propertyValue);
		if(properties!=null)
		return properties;
		else throw new RuntimeException("Properties file not found");
	}
	
	public String readExcelFile(int rowNum , int colNum) {
		String cellData = null ;
		//String[][] arrayExcelData = null;
		try {
			src = new File("C:\\Users\\DELL\\eclipse-workspace\\MavenProject_Selenium_6_july_2020\\TestData.xlsx");

			fis = new FileInputStream(src) ;
			wb = new XSSFWorkbook(fis);
			sh = wb.getSheet("Sheet1");
			int totalRows = sh.getPhysicalNumberOfRows();
			//System.out.println(totalRows);
			//int rows = sh.getLastRowNum() - sh.getFirstRowNum();
			//System.out.println(rows);
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();
			if(rowNum < totalRows && colNum < totalColumns) {
			cellData = sh.getRow(rowNum).getCell(colNum).getStringCellValue() ;
			}else {
				throw new Exception("Either Rows or Columns Exceeded total number of Rows and Columns in test Data sheet");
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cellData;
	}
	
	
	public void log4j() {
		
		Logger logger = Logger.getLogger("TestClass");
		
		PropertyConfigurator.configure(getProperties("Log4JPropertiesFilePath"));
		logger.info("Logs Created");
	}
	
	public void generateExtentReport() {
		
		reports = new ExtentReports("C:\\Users\\DELL\\eclipse-workspace\\Adidas_Test\\ExtentReport.html");
		test = reports.startTest("TestClass");
		
	}
	
	public void logStatusExtentReport(ITestResult result) {
		if(ITestResult.SUCCESS == result.getStatus()) {
			test.log(LogStatus.PASS, result.getName() + " is Passed");
		}else if(ITestResult.FAILURE == result.getStatus()) {
			test.log(LogStatus.FAIL, result.getName() + " is Failed and capturing screenshot");
			FileReaderManager.getInstance().getUtilityReader().captureScreenshotOnFailure(result);
		}else if(ITestResult.SKIP == result.getStatus()) {
			test.log(LogStatus.SKIP, result.getName() + " is Skipped");
		}
	}
	
	public void endExtentReport() {
		reports.endTest(test);
		reports.flush();
	}
	
}
