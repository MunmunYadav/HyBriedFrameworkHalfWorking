package Com.Demo.QA.Base;
import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	public Base() {
		prop=new Properties();
		File propfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\Com\\TestNG\\QA\\Demo\\Config\\config.propertyFile");
		dataProp=new Properties();
		File dataPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\Com\\TestNG\\Demo\\TestData\\testdata.propertyFile");
		try {
		FileInputStream dataFis=new FileInputStream(dataPropFile);
		dataProp.load(dataFis);}catch (Throwable e) {
			e.printStackTrace();
		}
		try {
		FileInputStream fis=new FileInputStream(propfile);
		prop.load(fis);
		}catch (Throwable e) {
			e.printStackTrace();
			}
		
	}
	public WebDriver initilizeBrowserAndOpenApplicationURL(String browsername) throws InterruptedException {
		
		if(browsername.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browsername.equals("Fire")){
			driver=new FirefoxDriver();
		}else if(browsername.equals("edge")) {
			driver=new EdgeDriver();
		}else if(browsername.equals("safari")) {
			driver=new SafariDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get(prop.getProperty("url"));
		Thread.sleep(3000);
		return driver;
		
	}

}
