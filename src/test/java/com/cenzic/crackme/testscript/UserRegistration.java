package com.cenzic.crackme.testscript;

import java.io.FileNotFoundException;
import java.io.IOException;

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
	public void validRegistration(String fName, String lName, String uid,
			String pwd, String dob, String address, String city, String state,
			String teleNo, String email) throws InterruptedException,
			FileNotFoundException, IOException {
		driver.manage().deleteAllCookies();
		driver.navigate().to(super.getProjectUrl());
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("txtFirstName")).clear();
		driver.findElement(By.id("txtFirstName")).sendKeys(fName);
		driver.findElement(By.id("txtLastName")).clear();
		driver.findElement(By.id("txtLastName")).sendKeys(lName);
		driver.findElement(By.id("txtUserId")).clear();
		driver.findElement(By.id("txtUserId")).sendKeys(uid);
		driver.findElement(By.id("txtpass")).clear();
		driver.findElement(By.id("txtpass")).sendKeys(pwd);
		driver.findElement(By.id("txtDOB")).clear();
		driver.findElement(By.id("txtDOB")).sendKeys(dob);
		driver.findElement(By.id("txtAddress")).clear();
		driver.findElement(By.id("txtAddress")).sendKeys(address);
		driver.findElement(By.id("txtCity")).clear();
		driver.findElement(By.id("txtCity")).sendKeys(city);
		Select State = new Select(driver.findElement(By.name("drpState")));
		State.selectByVisibleText(state);
		driver.findElement(By.id("txtTelephoneNo")).clear();
		driver.findElement(By.id("txtTelephoneNo")).sendKeys(teleNo);
		driver.findElement(By.id("txtEmail")).clear();
		driver.findElement(By.id("txtEmail")).sendKeys(email);

	}

	@AfterClass(alwaysRun = true)
	private void endDriver() throws FileNotFoundException, IOException {
		SingleRun run = SingleRun.getInstance();
		run.terminateWebdriver();
	}
}
