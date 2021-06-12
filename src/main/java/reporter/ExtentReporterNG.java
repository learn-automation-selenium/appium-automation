package reporter;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG implements IReporter {

	ExtentReports extent;
	ExtentSparkReporter spark;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("target/index.html");
		extent.attachReporter(spark);
		
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> results = suite.getResults();

			for (ISuiteResult result : results.values()) {
				ITestContext context = result.getTestContext();
				buildTestNodes(context.getPassedTests(), Status.PASS);
				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
			}
		}
		extent.flush();

	}

	private void buildTestNodes(IResultMap tests, Status status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());
				for (String group : result.getMethod().getGroups()) {
					test.assignCategory(group);
				}

				String message = "Test " + status.toString().toLowerCase() + "ed";

				if (result.getThrowable() != null) {
					message = result.getThrowable().getMessage();
				}

				test.log(status, message);

				// extent.endTest(test);
			}
		}
	}

	/*private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}*/

}
