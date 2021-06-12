package data;

import org.testng.annotations.DataProvider;

public class TestData {

	@DataProvider(name = "registerUser")
	public Object[][] getRegistrationData() {
		Object[][] data = { { "Argentina", "Afsana", "Female" }, { "India", "Iqbal", "Male" } };
		return data;
	}
	
	@DataProvider(name = "addToCart")
	public Object[][] getAddToCartData() {
		Object[][] data = { { "Argentina", "Afsana", "Female", "Air Jordan 4 Retro" } };
		return data;
	}
}
