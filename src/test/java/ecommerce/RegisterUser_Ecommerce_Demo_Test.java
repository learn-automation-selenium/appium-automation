package ecommerce;

import org.testng.annotations.Test;

import base.HybridBase;
import data.TestData;
import pageobject.ecommerce.RegisterUserPage;

public class RegisterUser_Ecommerce_Demo_Test extends HybridBase {

	@Test(dataProvider = "registerUser", dataProviderClass = TestData.class)
	public void ecommerce_register_user(String country, String userName, String gender) {

		new RegisterUserPage(driver)
		.clickOnCountryDropDown()
		.selectCountry(country)
		.enterName(userName)
		.selectGender(gender)
		.clickOnShopButtonAndNavigateToProductPage();

	}

}