package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.Utility;

public class CustomListener implements ITestListener {

	Utility utility = new Utility();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testCaseName = result.getName();
		//utility.getScreenShot(testCaseName);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testCaseName = result.getName();
		//utility.getScreenShot(testCaseName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testCaseName = result.getName();
		//utility.getScreenShot(testCaseName);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	
}
