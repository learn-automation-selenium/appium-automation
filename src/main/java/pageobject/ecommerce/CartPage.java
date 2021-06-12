package pageobject.ecommerce;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage {

	AndroidDriver<AndroidElement> driver;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private AndroidElement itemInCart;
	
	/******************************** Getters ************************************/
	
	public AndroidElement getItemInCart() {
		return itemInCart;
	}

	/******************************** Constructor ************************************/

	/**
	 * initialize all the web elements on this page
	 * @param driver
	 */
	public CartPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/******************************** Functions ************************************/
	
	public String getItemText() {
		return getItemInCart().getText();
	}
}
