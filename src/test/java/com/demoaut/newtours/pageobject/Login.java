package com.demoaut.newtours.pageobject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Login {
	
	@FindBy(name="userName")
	private static WebElement userName;
	@FindBy(how = How.NAME, using = "password")
	private static WebElement passwordField;
	@FindBy(name="login")
	private static WebElement signInButton;
	
  public static void userLogin(String username, String password) {
	  userName.clear();
	  userName.sendKeys(username);
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
