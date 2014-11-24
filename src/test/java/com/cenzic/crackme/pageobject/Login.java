package com.cenzic.crackme.pageobject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Login {
	
	@FindBy(id="LoginName")
	private static WebElement userID;
	@FindBy(how = How.ID, using = "Password")
	private static WebElement passwordField;
	@FindBy(name="sendbutton1")
	private static WebElement signInButton;
	
  public static void userLogin(String username, String password) {
	  userID.clear();
	  userID.sendKeys(username);
	  passwordField.clear();
	  passwordField.sendKeys(password);
	  passwordField.sendKeys(Keys.TAB);
	  try {
		Thread.sleep(1500);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	  signInButton.click();
  }
}
