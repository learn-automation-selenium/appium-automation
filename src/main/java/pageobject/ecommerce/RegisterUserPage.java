package pageobject.ecommerce;

import org.openqa.selenium.support.PageFactory;

import base.Utility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RegisterUserPage {

	AndroidDriver<AndroidElement> driver;
	public String dropDownPath = "";
	Utility utility;
	/******************************** Web elements ************************************/
	
	@AndroidFindBy(id = "android:id/text1")
	private AndroidElement countrySelection;
	
	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Argentina\").instance(0))")
	private AndroidElement countryName;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private AndroidElement userName;
	
	@AndroidFindBy(className = "UIAKeyboard")
    private AndroidElement keyboard;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private AndroidElement femaleRadioButton;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private AndroidElement maleRadioButton;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private AndroidElement shopButton;
	
	@AndroidFindBy(xpath = "//android.widget.Toast[1]")
	private AndroidElement toastMessage;
	
	
	/******************************** Getters ************************************/
	
	/**
	 * returns country drop down web element
	 * @return
	 */
	public AndroidElement getCountrySelection() {
		return countrySelection;
	}

	/**
	 * returns country name web element
	 * @return
	 */
	public AndroidElement getCountryName() {
		return countryName;
	}

	/**
	 * returns user name web element
	 * @return
	 */
	public AndroidElement getUserName() {
		return userName;
	}

	/**
	 * returns android keyboard object
	 * @return
	 */
	public AndroidElement getKeyboard() {
		return keyboard;
	}

	/**
	 * returns female radio button web element
	 * @return
	 */
	public AndroidElement getFemaleRadioButton() {
		return femaleRadioButton;
	}

	/**
	 * returns female radio button web element
	 * @return
	 */
	public AndroidElement getMaleRadioButton() {
		return maleRadioButton;
	}
	
	/**
	 * returns shop button web element
	 * @return
	 */
	public AndroidElement getShopButton() {
		System.out.println("Clicking on Let's Shop Button");
		return shopButton;
	}

	/**
	 * returns toast message
	 * @return
	 */
	public AndroidElement getToastMessage() {
		return toastMessage;
	}

	
	/******************************** Constructor ************************************/
	
	/**
	 * initialize all the web elements on this page
	 * @param driver
	 */
	public RegisterUserPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		utility = new Utility();
	}
	
	
	/******************************** Functions ************************************/
	
	/**
	 * clicks on country drop down selection
	 * @return
	 */
	public RegisterUserPage clickOnCountryDropDown() {
		getCountrySelection().click();
		return this;
	}
	
	/**
	 * select a country from drop down and click on that country name
	 * @return
	 */
	public RegisterUserPage selectCountry(String countryName) {
		this.driver.findElementByAndroidUIAutomator(utility.getScrollToText(countryName)).click();
		//getCountryName().click();
		return this;
	}
	
	/**
	 * enter user name
	 * @param name
	 * @return
	 */
	public RegisterUserPage enterName(String name) {
		getUserName().sendKeys(name);
		hideKeyboardIfVisible();
		return this;
	}
	
	/**
	 * click on shop button
	 * @return
	 */
	public ProductsPage clickOnShopButtonAndNavigateToProductPage() {
		getShopButton().click();
		return new ProductsPage(this.driver);
	}
	
	public RegisterUserPage clickOnShopButtonToVerifyToastMessage() {
		getShopButton().click();
		return this;
	}
	
	/**
	 * select female radio button
	 * @return
	 */
	public RegisterUserPage selectGender(String gender) {
		
		if(gender.equalsIgnoreCase("male")) {
			boolean maleRadioSelected = getMaleRadioButton().isSelected();
			if (!maleRadioSelected) {
				getMaleRadioButton().click();
			}
		} else {
			boolean femaleRadioSelected = getFemaleRadioButton().isSelected();
			if (!femaleRadioSelected) {
				getFemaleRadioButton().click();
			}
		}
		return this;
	}
	
	/**
	 * get toast message text and returns it in text message 
	 * @return
	 */
	public String getToastMessageText() {
		String toastMessage = "";
		try {
			toastMessage = getToastMessage().getAttribute("name");
			return toastMessage;
		} catch (Exception ex) {
			return toastMessage;
		}
	}
	
	/**
	 * close keyboard if open
	 */
	public void hideKeyboardIfVisible() {
        if (keyboard != null) {
        	this.driver.pressKey(new KeyEvent(AndroidKey.ESCAPE));
        }
    }
	
}
