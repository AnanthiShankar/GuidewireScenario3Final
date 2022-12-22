package utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class ReportSpark {
	
	public static void capturescreenshot(WebDriver driver) {
		// TODO Auto-generated method stub
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(src, new File("./Screenshots/pic"+getCurrentDateTime()+".jpg"));
			System.out.println("Screenshot Captured");
		}catch(IOException e){
			System.out.println("Unable to capture Screenshot "+e.getMessage());
		}
	}
	public static String getCurrentDateTime() {
		DateFormat dateFormat =new SimpleDateFormat("MM_DD_YYYY_HH_mm_ss");
		Date currentDate=new Date();
		return dateFormat.format(currentDate);
		
	}
	public void sparkReportPass(String methodName) {
		ExtentSparkReporter reporter=new ExtentSparkReporter(new File("./Reports/PC"+getCurrentDateTime()+".html"));
		ExtentReports report=new ExtentReports();
		ExtentTest log=report.createTest(methodName);
		//report.attachReporter(null)
		report.attachReporter(reporter);
		log.log(Status.INFO, "Action Performed"+methodName);
		log.log(Status.PASS, "The expected Action "+methodName+"is performed Successfully");
		log.addScreenCaptureFromPath("./Screenshots/");
		report.flush();
		
	}

public void sparkReportFailure(String methodName) {
	ExtentSparkReporter reporter=new ExtentSparkReporter(new File("./Reports/PC"+getCurrentDateTime()+".html"));
	ExtentReports report=new ExtentReports();
	ExtentTest log=report.createTest(methodName);
	report.attachReporter(reporter);
	log.log(Status.INFO, "Action Performed"+methodName);
	log.log(Status.FAIL, "The expected Action "+methodName+"is  not performed");
	log.addScreenCaptureFromPath("./Screenshots/");
	report.flush();
	
}
}