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
		
		// register user page
		ProductsPage productPage = new RegisterUserPage(driver)
		.clickOnCountryDropDown()
		.selectCountry(country)
		.enterName(userName)
		.selectGender(gender)
		.clickOnShopButton();
		
		// product page
		CartPage cartPage = productPage
		.scrollToItemAndAddToCart(item)
		.clickOnCartButton();
		
		// cart page
		Assert.assertEquals(item, cartPage.getItemText());
		
		
		
		//String scrollItem = "new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))\")";
		/*
		 * String scrollItem =
		 * "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
		 * + productNameText +"\").instance(0))";
		 * driver.findElement(MobileBy.AndroidUIAutomator(scrollItem));
		 */
		
		/*int visibleProductCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		
		for(int i=0; i<visibleProductCount; i++) {
			String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(productName.equalsIgnoreCase(item)) {
				System.out.println("index number is : " + i);
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();*/
		
		
		
		
		
		
		
		//String itemName = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']")).getText();
		
		//System.out.println(itemName.equals(productNameText));
		//validate the items added to cart
	}
}