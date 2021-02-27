package com.library.step_definitions;

import com.library.pages.LoginPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class LibraryStepDefinitions {
LoginPage loginPage = new LoginPage();

    @Given("User already on login page")
    public void userAlreadyOnLoginPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        BrowserUtils.sleep(3);

    }
    @Given("the user login as a {string} and {string}")
    public void the_user_login_as_a_and(String userName, String password) {
        loginPage.inputEmail.sendKeys(ConfigurationReader.getProperty(userName));
        BrowserUtils.sleep(3);
        loginPage.inputPassword.sendKeys(ConfigurationReader.getProperty(password));
             BrowserUtils.sleep(3);
        loginPage.loginButton.click();
        BrowserUtils.sleep(3);

    }


    @Then("the {string} on  {string}")
    public void the_on(String expectedUser, String expectedPage) {
        String actualUrl= Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedPage));

        String actualUser = loginPage.studentID.getText();
        Assert.assertEquals(expectedUser, actualUser);



    }





    @Then("Student can choose from the list all the category dropdown")
    public void studentCanChooseFromTheListAllTheCategoryDropdown(List<String> allCategories) {

        loginPage.loginToLibraryAsStudent();
        System.out.println("allCategories = " + allCategories);
        Select select = new Select(loginPage.dropDownList);
        List<String> actualCategoryList = new ArrayList<>();

        for(WebElement each : select.getOptions()){
            actualCategoryList.add(each.getText());
        }
        System.out.println("actualCategoryList = " + actualCategoryList);

        Assert.assertEquals(actualCategoryList, allCategories);


    }



}
