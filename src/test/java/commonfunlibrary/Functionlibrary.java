package commonfunlibrary;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileutil;

public class Functionlibrary {
	static WebDriver driver;

	public static WebDriver startBrowser() throws Exception {

		String browser = PropertyFileutil.getvaluekey("Browser");
		System.out.println(browser);
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("the driver is "+driver);
			
			driver.manage().window().maximize();
		}else{
			throw new Exception();
		}
		return driver;
	}

	public static void openApplication() throws Exception {
		driver.get(PropertyFileutil.getvaluekey("url"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public static void waitForElement(String atrtype, String atrvalue, String timetowait) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(timetowait));
		if (atrtype.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(atrvalue)));
		} else if (atrtype.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(atrvalue)));

		}else{
			throw new Exception();
		}
	}

	public static void typeAction(String atrtype, String atrvalue, String testdata) throws Exception {
		if (atrtype.equalsIgnoreCase("id")) {
			driver.findElement(By.id(atrvalue)).clear();
			driver.findElement(By.id(atrvalue)).sendKeys(testdata);
		} else if (atrtype.equalsIgnoreCase("name")) {
			driver.findElement(By.name(atrvalue)).clear();
			driver.findElement(By.name(atrvalue)).sendKeys(testdata);
		}  else if (atrtype.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(atrvalue)).clear();
			driver.findElement(By.xpath(atrvalue)).sendKeys(testdata);
		}
		else {
			System.out.println("element not found");
			throw new Exception();
		}
	}

	public static void clickAction(String atrtype, String atrvalue) throws Exception {
		if (atrtype.equalsIgnoreCase("id")) {
			driver.findElement(By.id(atrvalue)).click();
		} else if (atrtype.equalsIgnoreCase("name")) {
			driver.findElement(By.name(atrvalue)).click();
		} else if (atrtype.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(atrvalue)).click();
		}else{
			throw new Exception();
		}
	}
	public static String generatedate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_DD_SS");
		return sdf.format(date);
	}

	public static void closeBrowser() {
		driver.close();
	}

}
