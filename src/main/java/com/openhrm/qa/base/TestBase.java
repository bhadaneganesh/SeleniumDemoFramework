package com.openhrm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.openhrm.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
public	static WebDriver driver;
public	static Properties prop;

	public TestBase() {
		//	System.getProperty("user.dir");
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\ganesh bhadane\\eclipse-workspace\\SeleniumDemoFramework\\src\\main\\java\\com\\openhrm\\qa\\config\\config.properties");
		  	prop.load(fis);
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void initialization() {
		String browsername=prop.getProperty("browser");
		
		if(browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//	System.setProperty("webdriver.chrome.driver", "D:\\projectjars\\browserdriver\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver", "E:\\browserdriver\\geckodriver19\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();

			//System.setProperty("webdriver.ie.driver", "E:\\browserdriver\\IEDriver311\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}else {
			System.out.println("Invalid Browser choice");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	


}
