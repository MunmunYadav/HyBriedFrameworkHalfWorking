package Com.TestNG.QA.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	WebElement FirstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement LastNameField;
	
	@FindBy (id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement TelephoneField;
	
	@FindBy (id="input-password")
	private WebElement PasswordField;
	
	@FindBy (id="input-confirm")
	private WebElement PasswordConfirmField;
	

	@FindBy (name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy (xpath="//input[@value='Continue']")
	private WebElement ContinueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement YesNewsletterOption;
	
	@FindBy (xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy (xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy (xpath="//input[contains(@id,'input-firstname')]/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy (xpath="//input[contains(@id,'input-lastname')]/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy (xpath="//input[contains(@id,'input-email')]/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy (xpath="//input[contains(@id,'input-telephone')]/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy (xpath = "//input[contains(@id,'input-password')]/following-sibling::div")
	private WebElement passwordWarning;
	
	
	
	public RegisterPage (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String retrieveFirstNameWarning() {
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	
	}
	
	public String retrieveLastNameWarning() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	
	}
	
	public String retrieveEmailWarning() {
		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	
	}
	
	public String retrievetelephoneWarning() {
		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	
	}
	
	public String retrievePasswordWarning() {
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	
	}
	
	
	public void enterFirstName(String firstNameText) {
		FirstNameField.sendKeys(firstNameText);
	}
	
	
	
	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public void enterLastName(String LastNameText) {
		LastNameField.sendKeys(LastNameText);
	}
	
	public void enterEmaiAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
		
	}
	public void enterTelephoneNumber(String telephoneText) {
		TelephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText) {
		PasswordField.sendKeys(passwordText);
	}
	
	public void enterConformPassword(String ConfirmpasswordText) {
		PasswordConfirmField.sendKeys(ConfirmpasswordText);
	}
	
	public void SelectprivacyPolicy(String privacyPolicyText) {
		privacyPolicyField.sendKeys(privacyPolicyText);
	}
	public void ClickOnContinueButton(String ContinueButtonText) {
		ContinueButton.sendKeys(ContinueButtonText);
	}
	public void SelectYesNewsLetterOption() {
		YesNewsletterOption.click();
		}
	public String retrieveDuplicateEmailAdresswarning() {
		String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}


}
