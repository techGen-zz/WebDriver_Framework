package com.test.setup;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Optional;

import com.test.setup.AdminSetup;

public class SingleRun extends AdminSetup {
	
	private SingleRun(){}
	private static SingleRun singleRun;
	
	public static SingleRun getInstance(){
		singleRun = new SingleRun();
		return singleRun;
	}

	public void initiateWebdriver(@Optional("FF") String Browser) throws FileNotFoundException, IOException{
		if(Mode.getRunMode().equals("Single") || Mode.getRunMode().equals("single")){
			super.setupBrowser(Browser);
			driver.manage().window().maximize();
			super.loadApplication();
		}else{
			return;
		}
	}
	
	public void terminateWebdriver() throws FileNotFoundException, IOException{
		if(Mode.getRunMode().equals("Single") || Mode.getRunMode().equals("single")){
			driver.close();driver.quit();
		}else{
			return;
		}
	}
}
