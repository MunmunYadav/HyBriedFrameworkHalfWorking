package Com.TestNGTestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Com.Demo.QA.Base.Base;
import Com.TestNG.QA.Pages.AccountSuccessPage;
import Com.TestNG.QA.Pages.HomePage;
import Com.TestNG.QA.Pages.RegisterPage;
import Com.TestNGDemo.Util.Utilites;

public class Register extends Base {
	WebDriver driver;
	@BeforeMethod
	public void Setup() throws InterruptedException {
		driver=initilizeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		homepage.SelectRegisterOption();
	Thread.sleep(3000);
	}
	
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void VerifyRegisteringAnAccountbyProvidingonlytheMandatoryfields() throws InterruptedException {
	RegisterPage registerPage=new RegisterPage(driver);
	registerPage.enterFirstName(dataProp.getProperty("firstName"));
	registerPage.enterLastName(Utilites.GenerateEmailWithtimeStamp());
	registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
	registerPage.enterPassword(prop.getProperty("validPassword"));
	registerPage.enterConformPassword(prop.getProperty("validPassword"));
	//registerPage.SelectprivacyPolicy(prop.getProperty(null));
	driver.findElement(By.name("agree")).click();
	//registerPage.ClickOnContinueButton(null);
	driver.findElement(By.xpath("//input[@value='Continue']")).click();
	AccountSuccessPage accountSuccessPage= new AccountSuccessPage(driver);
	String ActualHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
	SoftAssert Assert=new SoftAssert();
	Assert.assertEquals(ActualHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not display");
	}
	@Test(priority = 2)
	public void VerifyRegisteringAnAccountByProvidingAllTheFields() throws InterruptedException {
		RegisterPage registerPage=new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmaiAddress("validEmail");
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConformPassword(prop.getProperty("validPassword"));
		registerPage.SelectYesNewsLetterOption();
		//registerPage.SelectprivacyPolicy(prop.getProperty(null));
		driver.findElement(By.name("agree")).click();
		//registerPage.ClickOnContinueButton(null);
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		AccountSuccessPage accountSuccessPage= new AccountSuccessPage(driver);
		String ActualHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		SoftAssert Assert=new SoftAssert();
		Assert.assertEquals(ActualHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not display");
		}
	@Test(priority = 3)
	public void VerifyRegisteringAnAccountbyProvidingTheExistingAccountDetails() throws InterruptedException {
    RegisterPage registerPage=new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmaiAddress("validEmail");
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConformPassword(prop.getProperty("validPassword"));
		registerPage.SelectYesNewsLetterOption();
		//registerPage.SelectprivacyPolicy(prop.getProperty(null));
		driver.findElement(By.name("agree")).click();
		//registerPage.ClickOnContinueButton(null);
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String ActualWaring = registerPage.retrieveDuplicateEmailAdresswarning();
		SoftAssert Assert=new SoftAssert();
		Assert.assertTrue(ActualWaring.contains(dataProp.getProperty("duplicateEmailWarning")),"Account must not be created but it is getting created");
	}
	@Test(priority = 4)
	public void VerifyRegisteringAnAccountWithouFillingAnyDetails() {
		RegisterPage registerPage=new RegisterPage(driver);
		//registerPage.ClickOnContinueButton(null);
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String actualPrivacyPolicyWarning=registerPage.retrievePrivacyPolicyWarning();
		SoftAssert Assert=new SoftAssert();
		Assert.assertEquals(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")), "privecy policy worning message is not display");
		String actualFirstNameWarning=registerPage.retrieveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning,dataProp.getProperty("FirstNameWarning"),"First name warning is not display");
		
		String actualLastNameWarning=registerPage.retrieveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning,dataProp.getProperty("LastNameWarning"),"Last name is not display");
		
		String actualEmailWarning=registerPage.retrieveEmailWarning();
		Assert.assertEquals(actualEmailWarning,dataProp.getProperty("EMailWarning"),"Actual warning is not display");
		
		String actualTelePhoneWarning=registerPage.retrievetelephoneWarning();
		Assert.assertEquals(actualTelePhoneWarning,dataProp.getProperty("TelephoneWarning"),"Actual warning is not display");
		
		String actualPasswordWarning=registerPage.retrievePasswordWarning();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("PasswordWarning"),"Actual warning is not display");
		
	}
	}
