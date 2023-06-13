package Com.TestNGTestcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Com.Demo.QA.Base.Base;
import Com.TestNG.QA.Pages.AccountPage;
import Com.TestNG.QA.Pages.HomePage;
import Com.TestNG.QA.Pages.LoginPage;
import Com.TestNGDemo.Util.Utilites;

public class Login extends Base{
	public Login() {
		super();
	}
	
	WebDriver driver;
	@BeforeMethod
	public void Setup() throws InterruptedException {
		
		driver=initilizeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
		Thread.sleep(3000);
		
	}
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
	@Test(priority = 1,dataProvider = "validCredentialSupplier")
	public void LoginWithValidCridential(String email, String Password) {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(Password);
		loginPage.clickOnLoginButton();
		SoftAssert Asser=new SoftAssert();
		AccountPage accountPage = new AccountPage(driver);
		Asser.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information");
		}
	@DataProvider(name="validCredentialSupplier")
	public Object[][] supplyTestData() {
		Object[][] data= Utilites.getTestDataFromExcel("Login");
				return data;
	}
	@Test(priority = 2)
	public void LogInWithInvalidCredential() {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Utilites.GenerateEmailWithtimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		String ActualWerningMessage = loginPage.retrieveEmailPasswordNotMachingWarningMessageText();
		SoftAssert Assert=new SoftAssert();
		String ExpectedWarningMessage=dataProp.getProperty("EmailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWerningMessage.contains(ExpectedWarningMessage),"Expected werning message is not display");
			}
	@Test(priority = 3)
	public void LoginWithInValidEmailAndInvalidPassword(){
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Utilites.GenerateEmailWithtimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		String ActualWerningMessage = loginPage.retrieveEmailPasswordNotMachingWarningMessageText();
		SoftAssert Assert=new SoftAssert();
		String ExpectedWarningMessage=dataProp.getProperty("EmailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWerningMessage.contains(ExpectedWarningMessage),"Expected werning message is not display");
		}
	@Test(priority = 4)
	public void LoginWithValidEmailAndInvalidPassword() {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		String ActualWerningMessage = loginPage.retrieveEmailPasswordNotMachingWarningMessageText();
		SoftAssert Assert=new SoftAssert();
		String ExpectedWarningMessage=dataProp.getProperty("EmailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWerningMessage.contains(ExpectedWarningMessage),"Expected werning message is not display");
		
	}
	@Test(priority = 5)
	public void LoginWithoutEmailAndPassword() throws InterruptedException {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.clickOnLoginButton();
		String ActualWerningMessage = loginPage.retrieveEmailPasswordNotMachingWarningMessageText();
		SoftAssert Assert=new SoftAssert();
		String ExpectedWarningMessage=dataProp.getProperty("EmailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWerningMessage.contains(ExpectedWarningMessage),"Expected werning message is not display");
		
	}
	
}