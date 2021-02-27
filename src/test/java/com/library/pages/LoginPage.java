package com.library.pages;

import com.library.utilities.ConfigurationReader;
import com.library.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "inputEmail")
    public WebElement inputEmail;

    @FindBy(id = "inputPassword")
    public WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

  @FindBy(xpath = "//select[@id='book_categories']")
  public WebElement dropDownList;

@FindBy(xpath = "//ul[@class='navbar-nav  navbar-right']")
public WebElement studentID;



    public void loginToLibraryAsStudent() {
        inputEmail.sendKeys(ConfigurationReader.getProperty("email"));
        inputPassword.sendKeys(ConfigurationReader.getProperty("password"));
        loginButton.click();
    }
}

