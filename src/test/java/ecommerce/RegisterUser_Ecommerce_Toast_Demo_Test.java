package ecommerce;

import org.testng.annotations.Test;

import base.HybridBase;
import pageobject.ecommerce.RegisterUserPage;

public class RegisterUser_Ecommerce_Toast_Demo_Test extends HybridBase {

	/*@BeforeTest
	public void startUpDetails() {
		appName = "E-COMMERCE_APP_NAME";
	}*/
	
	@Test
	public void ecommerce_register_user_toast() {
		
		new RegisterUserPage(driver)
		.clickOnCountryDropDown()
		.selectCountry("Argentina")
		.selectGender("Female")
		.clickOnShopButton();
		
		/*String toastMessage = register.getToastMessageText();
		Assert.assertEquals("Please enter your name", toastMessage);*/

	}
}