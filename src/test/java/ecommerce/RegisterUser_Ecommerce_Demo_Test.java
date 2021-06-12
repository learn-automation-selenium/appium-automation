package ecommerce;

import org.testng.annotations.Test;

import base.HybridBase;
import data.TestData;
import pageobject.ecommerce.RegisterUserPage;

public class RegisterUser_Ecommerce_Demo_Test extends HybridBase {

	
	
	@Test(dataProvider = "userDetails", dataProviderClass = TestData.class)
	public void ecommerce_register_user(String country, String userName, String gender) {
		
		//appName = "E-COMMERCE_APP_NAME";
		//AndroidDriver<AndroidElement> driver = capabilities(PropertyReader.getPropertyValue("E-COMMERCE_APP_NAME"));

		new RegisterUserPage(driver)
		.clickOnCountryDropDown()
		.selectCountry(country)
		.enterName(userName)
		.selectGender(gender)
		.clickOnShopButton();

	}
	
}