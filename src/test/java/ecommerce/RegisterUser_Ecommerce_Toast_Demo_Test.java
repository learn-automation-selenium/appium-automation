package ecommerce;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.HybridBase;
import data.TestData;
import pageobject.ecommerce.RegisterUserPage;

public class RegisterUser_Ecommerce_Toast_Demo_Test extends HybridBase {

	@Test(dataProvider = "registerUser", dataProviderClass = TestData.class)
	public void ecommerce_register_user_toast(String country, String userName, String gender) {

		RegisterUserPage registerUserPage = new RegisterUserPage(driver)
				.clickOnCountryDropDown()
				.selectCountry(country)
				.selectGender(gender)
				.clickOnShopButtonToVerifyToastMessage();

		String toastMessage = registerUserPage.getToastMessageText();
		Assert.assertEquals("Please enter your name", toastMessage);

	}
}