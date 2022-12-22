package parallel;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
		features="Features",
		glue={"StepDefinitions"})
		//plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
		
public class ParallelRun extends AbstractTestNGCucumberTests {
		 
	    @Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
	 
	}

