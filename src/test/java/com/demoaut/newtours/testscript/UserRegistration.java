package com.demoaut.newtours.testscript;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.excelsheet.ReadExcelSheet;
import com.test.setup.AdminSetup;
import com.test.setup.SingleRun;

public class UserRegistration extends AdminSetup {

	@Parameters("Browser")
	@BeforeClass(alwaysRun = true)
	private void startDriver(String Browser) throws FileNotFoundException,
			IOException {
		SingleRun run = SingleRun.getInstance();
		run.initiateWebdriver(Browser);
	}

	@ReadExcelSheet(fileName = "registration.xls", sheetName = "Registration")
	@Test(priority = 1, dataProviderClass = com.test.excelsheet.ReturnExcelSheetData.class, dataProvider = "extractData")
	public void validRegistration(String fName, String lName, String phone,
			String email, String add1, String add2, String city, String state,
			String pCode, String country,String username, String pwd, String cPwd) throws InterruptedException,
			FileNotFoundException, IOException {
		driver.manage().deleteAllCookies();
		driver.navigate().to(super.getProjectUrl());
		driver.findElement(By.linkText("REGISTER")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("firstName")).clear();
		driver.findElement(By.name("firstName")).sendKeys(fName);
		driver.findElement(By.name("lastName")).clear();
		driver.findElement(By.name("lastName")).sendKeys(lName);
		driver.findElement(By.name("phone")).clear();
		driver.findElement(By.name("phone")).sendKeys(phone);
		driver.findElement(By.name("userName")).clear();
		driver.findElement(By.name("userName")).sendKeys(email);
		driver.findElement(By.name("address1")).clear();
		driver.findElement(By.name("address1")).sendKeys(add1);
		driver.findElement(By.name("address2")).clear();
		driver.findElement(By.name("address2")).sendKeys(add2);
		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).clear();
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("postalCode")).clear();
		driver.findElement(By.name("postalCode")).sendKeys(pCode);
		Select countryList = new Select(driver.findElement(By.name("country")));
		countryList.selectByVisibleText(country);
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(pwd);
		driver.findElement(By.name("confirmPassword")).clear();
		driver.findElement(By.name("confirmPassword")).sendKeys(cPwd);
	}

	@AfterClass(alwaysRun = true)
	private void endDriver() throws FileNotFoundException, IOException {
		SingleRun run = SingleRun.getInstance();
		run.terminateWebdriver();
	}
}
