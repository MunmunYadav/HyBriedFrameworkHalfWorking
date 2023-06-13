package Com.TestNGTestcases;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Com.Demo.QA.Base.Base;
import Com.TestNG.QA.Pages.HomePage;
import Com.TestNG.QA.Pages.SearchPage;
public class Search extends Base{
	WebDriver driver;
	@BeforeMethod
	public void Setup() throws InterruptedException {
		driver=initilizeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		Thread.sleep(3000);
	}
		@AfterMethod
	public void TearDown() {
		driver.quit();
	}
@Test(priority = 1)
public void VerifySearchingWithProduct() {
	HomePage homePage=new HomePage(driver);
	homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
	homePage.clickOnSearchButton();
	
	SearchPage searchPage=new SearchPage(driver);

	SoftAssert Assert=new SoftAssert();
Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product HP is not display");
}
@Test(priority = 2)
public void VerifySearchingWithANonExistingProductName() {
	HomePage homePage=new HomePage(driver);
	homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
	
	homePage.clickOnSearchButton();
	
	SearchPage searchPage=new SearchPage(driver);
    String ActualSearchMessage = searchPage.retriveNoProductMessageText();
	SoftAssert Assert=new SoftAssert();
Assert.assertEquals(ActualSearchMessage,dataProp.getProperty("NoProductTextInSearchResult"),"No product in a search result");
}

@Test
private void VerifySearchingWithoutProvidingAnyProductName() {
	HomePage homePage=new HomePage(driver);
	
	homePage.clickOnSearchButton();
	SearchPage searchPage=new SearchPage(driver);
    
	String ActualSearchMessage = searchPage.retriveNoProductMessageText();
	SoftAssert Assert=new SoftAssert();
 Assert.assertEquals(ActualSearchMessage,dataProp.getProperty("NoProductTextInSearchResult"),"No product in a search result");
}
}

