package ecommerce;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.HybridBase;
import data.TestData;
import pageobject.ecommerce.CartPage;
import pageobject.ecommerce.ProductsPage;
import pageobject.ecommerce.RegisterUserPage;

public class Ecommerce_Add_To_Cart_Demo_Test extends HybridBase {

	@Test(dataProvider = "addToCart", dataProviderClass = TestData.class)
	public void add_to_cart(String country, String userName, String gender, String item) {
		
		//Register user page
		RegisterUserPage registerUserPage = new RegisterUserPage(driver)
		.clickOnCountryDropDown()
		.selectCountry(country)
		.enterName(userName)
		.selectGender(gender);
		
		// Product page
		ProductsPage productsPage = registerUserPage
				.clickOnShopButtonAndNavigateToProductPage()
				.scrollToItemAndAddToCart(item);
		
		//Cart page
		CartPage cartPage = productsPage
				.clickOnCartButton();
		
		Assert.assertEquals(item, cartPage.getItemText());
		
	}
}