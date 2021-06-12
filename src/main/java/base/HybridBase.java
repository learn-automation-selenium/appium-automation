package base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;

public class HybridBase {

	private AppiumDriverLocalService service;
	protected static AndroidDriver<AndroidElement> driver;
	// public String appName =
	// PropertyReader.getPropertyValue("E-COMMERCE_APP_NAME");
	public String appName;
	PropertyReader property;
	
	@BeforeClass
	public void startUpDetails() {
		//appName = "E-COMMERCE_APP_NAME";
	}
	
	public HybridBase() {
		property = new PropertyReader();
	}
	/**
	 * initialization
	 * 
	 * @param appName
	 * @return
	 */
	@BeforeMethod
	public AndroidDriver<AndroidElement> capabilities() {
		appName = "E-COMMERCE_APP_NAME";
		try {
			killAllProcessNodes();
			boolean isServerRunning = checkIfServerIsRunning(4723);
			if (!isServerRunning) {
				service = AppiumDriverLocalService.buildDefaultService();
				service.start();

				if (service == null || !service.isRunning()) {
					throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
				}
			}

			File appDir = new File("src\\main\\resources\\apk");
			File app = new File(appDir, property.getPropertyValue(appName));

			String device = property.getPropertyValue("DEVICE_NAME");

			// check if device is emulator, the only start emulator
			/*
			 * if(device.contains("emulator")) { 
			 * 		startEmulator(); 
			 * }
			 */

			// set the desired capabilities
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// based on the condition script will execute on real device / emulator
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);

			// capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android
			// Device");
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, property.getPropertyValue("COMMAND_TIME_OUT"));
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath().toString());

			Thread.sleep(20 * 1000);
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (MalformedURLException | InterruptedException e) {
			e.printStackTrace();
		}
		return driver;
	}

	/**
	 * check if server is up and running and returns boolean value based on the
	 * result
	 * 
	 * @param port
	 * @return
	 */
	public boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (IOException e) {
			// if control comes here, then it means that port is in use
			isServerRunning = true;
		} finally {
			socket = null;
		}
		return isServerRunning;
	}

	/**
	 * start emulator
	 */
	public void startEmulator() {
		try {
			File appDir = new File("src\\main\\resources");
			File app = new File(appDir, "start-emulator.bat");
			Runtime.getRuntime().exec(app.getAbsolutePath());
			Thread.sleep(30 * 1000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void killAllProcessNodes() {
		try {
			File appDir = new File("src\\main\\resources");
			File app = new File(appDir, "kill-all-process-nodes.bat");
			Runtime.getRuntime().exec(app.getAbsolutePath());
			Thread.sleep(3 * 1000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void stopServer() {
		if (driver != null) {
			driver.quit();
		}

		if (service != null) {
			service.stop();
		}
	}
}
