package com.test.setup;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.test.setup.AdminSetup;


public class SuiteRun extends AdminSetup {
	
	@BeforeSuite
	@Parameters({ "BROWSER" })
	public void initiateWebdriver(@Optional("FF") String Browser) throws FileNotFoundException, IOException{
		//PropertyConfigurator.configure("log4j.properties");
		if(Mode.getRunMode().equals("Suite") || Mode.getRunMode().equals("suite")){
			super.setupBrowser(Browser);
			driver.manage().window().maximize();
			super.loadApplication();
		}else{
			return;
		}
	}
	
	@AfterSuite
	public void terminateWebdriver() throws FileNotFoundException, IOException{
		if(Mode.getRunMode().equals("Suite") || Mode.getRunMode().equals("suite")){
			driver.close();driver.quit();
		}else{
			return;
		}
	}
}
