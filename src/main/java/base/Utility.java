package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class Utility extends HybridBase{

	public static void addItemToCart(AndroidDriver<AndroidElement> driver, String itemNameText) {
		String scrollItem = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+ itemNameText +"\").instance(0))";
		driver.findElement(MobileBy.AndroidUIAutomator(scrollItem));
		
		int visibleProductCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		
		for(int i=0; i<visibleProductCount; i++) {
			String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(productName.equalsIgnoreCase(itemNameText)) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}
	}
	
	public static void longPressElement(AndroidDriver<AndroidElement> driver, AndroidElement element) {
		TouchAction action = new TouchAction(driver);
		
		action.longPress(LongPressOptions.longPressOptions()
				.withElement(ElementOption.element(element))
				.withDuration(Duration.ofSeconds(5)))
		.release()
		.perform();
		
		//action.
	}
	
	public static void selectCheckBox(AndroidDriver<AndroidElement> driver, AndroidElement element) {
		TouchAction action = new TouchAction(driver);
		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
	}
	
	/*public String getScreenShot(String testcaseName) {
		long number = getRandomNumber();
		String screenShotPath = "";
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			screenShotPath = System.getProperty("user.dir") + "\\screenshots\\" + testcaseName + number + ".png";
			FileUtils.copyFile(srcFile, new File(screenShotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenShotPath;
	}*/
	
	public static long getRandomNumber() {
		return (long) Math.floor(Math.random() * 900000000L) + 10000000L;
	}
	
	public String getScrollToText(String dropdownValue) {
		return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+dropdownValue+"\").instance(0))";
	}
}
