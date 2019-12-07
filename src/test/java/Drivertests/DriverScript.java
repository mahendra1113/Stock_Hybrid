package Drivertests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Utilities.Excelutilis;
import commonfunlibrary.Functionlibrary;

public class DriverScript {
	static WebDriver driver;

	@Test
	public static void startTest() throws Exception {
		Excelutilis xl = new Excelutilis();
		int mrc = xl.rowcount("MasterTestCases");

		for (int i = 1; i <= mrc; i++) {
			String Execution_Mode = xl.getcelldata("MasterTestCases", i, 2);
			String ApplicationLogin = xl.getcelldata("MasterTestCases", i, 1);

			if (Execution_Mode.equalsIgnoreCase("y")) {
				int arc = xl.rowcount(ApplicationLogin);
				String modulestatus = "";
				for (int j = 1; j <= arc; j++) {
					String Description = xl.getcelldata(ApplicationLogin, j, 0);
					String Object_Type = xl.getcelldata(ApplicationLogin, j, 1);
					String atrtype = xl.getcelldata(ApplicationLogin, j, 2);
					String atrvalue = xl.getcelldata(ApplicationLogin, j, 3);
					String timetowait = xl.getcelldata(ApplicationLogin, j, 4);
					String testdata = xl.getcelldata(ApplicationLogin, j, 4);
					try {

						if (Object_Type.equalsIgnoreCase("startBrowser")) {
							System.out.println("i came here");
							driver = Functionlibrary.startBrowser();
							modulestatus = "true";
						} else if (Object_Type.equalsIgnoreCase("openApplication")) {
							Functionlibrary.openApplication();
							modulestatus = "true";
						} else if (Object_Type.equalsIgnoreCase("waitForElement")) {
							Functionlibrary.waitForElement(atrtype, atrvalue, timetowait);
							modulestatus = "true";
						} else if (Object_Type.equalsIgnoreCase("typeAction")) {
							Functionlibrary.typeAction(atrtype, atrvalue, testdata);
							modulestatus = "true";
						} else if (Object_Type.equalsIgnoreCase("clickAction")) {
							Functionlibrary.clickAction(atrtype, atrvalue);
							modulestatus = "true";
						} else if (Object_Type.equalsIgnoreCase("closeBrowser")) {
							Functionlibrary.closeBrowser();
							modulestatus = "true";
						}
						modulestatus = "true";
						xl.setcelldata(ApplicationLogin, j, 5, "pass");

					} catch (Exception e) {
						modulestatus = "false";
						xl.setcelldata(ApplicationLogin, j, 5, "fail");
						File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(srcFile, new File("D:\\mahiselenium\\Stockaccounting_BDD\\Screens\\"
								+ Description + Functionlibrary.generatedate() + ".png"));
						Functionlibrary.closeBrowser();
						break;
					}
				}
				if (modulestatus.equalsIgnoreCase("true")) {
					xl.setcelldata("MasterTestCases", i, 3, "pass");
				} else if (modulestatus.equalsIgnoreCase("false")) {
					xl.setcelldata("MasterTestCases", i, 3, "fail");
				}
			}

		}

	}
}
