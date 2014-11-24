package com.test.setup;

import java.io.FileInputStream;
import java.util.Properties;


public class Mode {
	
	public static String getRunMode(){
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String runMode = prop.getProperty("Run_Mode");
		return runMode;
	}

}
