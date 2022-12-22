package pages;

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
import com.aventstack.extentreports.MediaEntityBuilder;

//import io.cucumber.plugin.event.Status;
import utility.BrowserUtil;
import utility.ConfigDataProvider;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class FirstandFinal {
	public static WebDriver driver;
	ConfigDataProvider CDP=new ConfigDataProvider();
	public ExtentTest log;
	public ExtentReports report;
	public ExtentReporter reporter;
	public ExtentHtmlReporter htmlreport;

	/*
	 * public void beforeSuite() { reporter=new ExtentSparkReporter(new
	 * File("./Reports/PC.html")); report=new ExtentReports();
	 * report.attachReporter(reporter); }
	 */
	public void setUp() {

			htmlreport=new ExtentHtmlReporter(new File("./Reports/PChtml"+getCurrentDateTime()+".html"));
			report=new ExtentReports();
			report.attachReporter(htmlreport);
		
		}
		
	
	public void launchBrowser() {
		try {
			driver=BrowserUtil.initApplication(driver,CDP.getBrowser(), CDP.getUrl());
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	public void launchBrowser(String browser) {
		try {
			driver=BrowserUtil.initApplication(driver,browser, CDP.getUrl());
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	public void exitBrowser() {
		BrowserUtil.exitBrowser(driver);
		//report.flush();
	}
	public static String capturescreenshot(WebDriver driver) {
		// TODO Auto-generated method stub
		String screenshotName = null;
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			screenshotName="pic"+getCurrentDateTime()+".jpg";
			FileHandler.copy(src, new File("./Screenshots/"+screenshotName));
			System.out.println("Screenshot Captured");
		}catch(IOException e){
			System.out.println("Unable to capture Screenshot "+e.getMessage());
		}
		return screenshotName;
	}
	public static String getCurrentDateTime() {
		DateFormat dateFormat =new SimpleDateFormat("MM_DD_YYYY_HH_mm_ss");
		Date currentDate=new Date();
		return dateFormat.format(currentDate);
		
	}
	
	public void sparkReportPass(String methodName) {
		//ExtentSparkReporter reporter=new ExtentSparkReporter(new File("./Reports/PC"+getCurrentDateTime()+".html"));
		
		log=report.createTest(methodName);
		
		String filename=capturescreenshot(driver);
		System.out.println("captured Screenshot name"+filename);
		log.log(Status.INFO,"Action Performed"+methodName);
		log.log(Status.PASS, "The expected Action "+methodName+"is performed Successfully");
		//log.info(MediaEntityBuilder.createScreenCaptureFromPath(filename).build());
		log.addScreenCaptureFromPath("./Screenshots/");
		report.flush();
		
	}

public void sparkReportFailure(String methodName) {
	//ExtentSparkReporter reporter=new ExtentSparkReporter(new File("./Reports/PC"+getCurrentDateTime()+".html"));
	//ExtentReports report=new ExtentReports();
	 log=report.createTest(methodName);
	//report.attachReporter(htmlreport);
	String filename=capturescreenshot(driver);
	log.log(Status.INFO, "Action Performed"+methodName);
	log.log(Status.FAIL, "The expected Action "+methodName+"is  not performed");
	log.addScreenCaptureFromPath("./Screenshots/"+filename);
	report.flush();
	
}
	
	 

}
