package com.test.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class AdminSetup {
	
	public static WebDriver driver;
	static Properties prop = new Properties();
	static InputStream propFile = null;
	
	public static void setupBrowser(String Browser){
		if(Browser.equals("FF")){
			driver = new FirefoxDriver();
		}
		else if(Browser.equals("CH")){
			String path ="binary/chromedriver_32bit.exe";
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
		}
		else if (Browser.equals("IE")){
			String path = "binary/IEDriverServer_64bit.exe";
			System.setProperty("webdriver.ie.driver", path);
			driver = new InternetExplorerDriver();
		}
		else if (Browser.equals("SF")){
			File safariExt = new File("binary/SafariDriver2.32.0.safariextz");
			SafariOptions opt = new SafariOptions();
			opt.addExtensions(safariExt);
			driver = new SafariDriver();
		}
	}
	
	public static String getProjectUrl() throws FileNotFoundException, IOException{
		prop.load(new FileInputStream("config.properties"));
		String projectUrl = prop.getProperty("Project_URL");
		return projectUrl;
	}
	
	public static void loadApplication(){
		try{
			driver.get(AdminSetup.getProjectUrl());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
