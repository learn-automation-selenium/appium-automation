package pageobject.ecommerce;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import base.Utility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage {

	AndroidDriver<AndroidElement> driver;
	Utility utility;
	
	/******************************** Web Element ************************************/
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private List<AndroidElement> itemsList;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	private List<AndroidElement> addItemsToCart;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private AndroidElement cartButton;
	
	
	/******************************** Getters ************************************/
	
	public List<AndroidElement> getItemsList() {
		return itemsList;
	}

	public List<AndroidElement> getAddItemsToCart() {
		return addItemsToCart;
	}
	
	
	public AndroidElement getCartButton() {
		return cartButton;
	}

	
	/******************************** Constructor ************************************/

	/**
	 * initialize all the web elements on this page
	 * @param driver
	 */
	public ProductsPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		utility = new Utility();
	}
	
	
	/******************************** Functions ************************************/
	
	public ProductsPage selectItem(String item) {
		this.driver.findElementByAndroidUIAutomator(utility.getScrollToText(item)).click();
		return this;
	}
	
	public ProductsPage scrollToItemAndAddToCart(String item) {
		this.driver.findElementByAndroidUIAutomator(utility.getScrollToText(item));
		int visibleProductCount = getItemsList().size();
		for(int i=0; i<visibleProductCount; i++) {
			String productName = getItemsList().get(i).getText();
			if(productName.equalsIgnoreCase(item)) {
				System.out.println("index number is : " + i);
				getAddItemsToCart().get(i).click();
				break;
			}
		}
		return this;
	}
	
	public CartPage clickOnCartButton() {
		getCartButton().click();
		return new CartPage(this.driver);
	}
}
