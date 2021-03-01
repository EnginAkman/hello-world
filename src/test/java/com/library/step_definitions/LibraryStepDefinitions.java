package com.library.step_definitions;

import com.github.javafaker.Faker;
import com.library.pages.AddUserPage;
import com.library.pages.BookManagementPage;
import com.library.pages.LibraryDashboardPage;
import com.library.pages.LoginPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class LibraryStepDefinitions {
    LoginPage loginPage = new LoginPage();
    BookManagementPage bookManagementPage = new BookManagementPage();
    LibraryDashboardPage libraryDashboardPage = new LibraryDashboardPage();
    AddUserPage addUserPage = new AddUserPage();

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
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedPage));

        String actualUser = loginPage.studentID.getText();
        Assert.assertEquals(expectedUser, actualUser);


        Driver.closeDriver();


    }


    @Then("Student can choose from the list all the category dropdown")
    public void studentCanChooseFromTheListAllTheCategoryDropdown(List<String> allCategories) {

        loginPage.loginToLibraryAsStudent();
        System.out.println("allCategories = " + allCategories);
        Select select = new Select(loginPage.dropDownList);
        List<String> actualCategoryList = new ArrayList<>();

        for (WebElement each : select.getOptions()) {
            actualCategoryList.add(each.getText());
        }
        System.out.println("actualCategoryList = " + actualCategoryList);

        Assert.assertEquals(actualCategoryList, allCategories);

        Driver.closeDriver();
    }


    @Given("Student is on books page")
    public void studentIsOnBooksPage() {
        loginPage.loginToLibraryAsStudent();
    }

    @Then("Student can click logout and logout")
    public void studentCanClickLogoutAndLogout() {
        bookManagementPage.studentID.click();
        bookManagementPage.logOutDropdown.click();

        String expectedInUrl = "login";

        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expectedInUrl));
        Driver.closeDriver();

    }

    @Given("the student on the home page")
    public void theStudentOnTheHomePage() {
        loginPage.loginToLibraryAsStudent();

    }

    @Then("the user should see following modules")
    public void theUserShouldSeeFollowingModules(List<String> expectedModules) {
        List<String> actualModules = BrowserUtils.getElementsText(bookManagementPage.studentModules);

        System.out.println("expectedModules = " + expectedModules);
        System.out.println("actualModules = " + actualModules);

        Assert.assertEquals(actualModules, expectedModules);
        Driver.closeDriver();

    }

    @Given("the librarian on the homepage")
    public void theLibrarianOnTheHomepage() {
        loginPage.loginAsLibrarian();
        BrowserUtils.sleep(3);

    }

    @Then("the librarian should see following modules")
    public void theLibrarianShouldSeeFollowingModules(List<String> expectedLibrarianModules) {
        List<String> actualLibrarianModules = BrowserUtils.getElementsText(bookManagementPage.librarianModules);

        System.out.println("actualLibrarianModules = " + actualLibrarianModules);
        System.out.println("expectedLibrarianModules = " + expectedLibrarianModules);

        Assert.assertEquals(actualLibrarianModules, expectedLibrarianModules);
          /*
    Report bug/ or check with the Dev
    actualLibrarianModules = [Dashboard, Users, Books]
     expectedLibrarianModules = [Dashboard, Books, Borrowing Books]
     */
        Driver.closeDriver();

    }

    @Then("add users with all valid info.")
    public void addUsersWithAllValidInfo() {
        libraryDashboardPage.usersButton.click();
        BrowserUtils.sleep(2);
        libraryDashboardPage.addUserButton.click();
        BrowserUtils.sleep(2);

        Faker faker = new Faker();

        addUserPage.fullNameInput.sendKeys("abdi gul");
        addUserPage.passwordInput.sendKeys("Wrongpassword:)");
        addUserPage.emailInput.sendKeys("a@b.com");
        Select select1 = new Select(addUserPage.userGroup);
        select1.selectByIndex(1);
        Select select2 = new Select(addUserPage.statusDropdown);
        select2.selectByIndex(0);
        addUserPage.startDateInput.sendKeys("2021-02-27");
        addUserPage.endDateInput.sendKeys("2021-05-27");
        addUserPage.addressInput.sendKeys("154 A Court 14562 PA");
        BrowserUtils.sleep(3);
        addUserPage.saveChanges.click();
        BrowserUtils.sleep(3);

    }


    @And("Librarians able to close the add user window with close button")
    public void librariansAbleToCloseTheAddUserWindowWithCloseButton() {
        addUserPage.closeButton.click();
    }

    @Then("Librarians able to edit user info.")
    public void librariansAbleToEditUserInfo() {
        addUserPage.editUserButton.click();

    }

    @Then("verify that the default record is {int}")
    public void verifyThatTheDefaultRecordIs(int arg0) {
        loginPage.loginAsLibrarian();
        libraryDashboardPage.booksButton.click();
        Select select = new Select(libraryDashboardPage.showBookRecord);
        int defaultTen = Integer.parseInt(select.getFirstSelectedOption().getText());
        int expectedTen = 10;
        Assert.assertEquals(defaultTen, expectedTen);
    }


    @Then("Show records for <count> options")
    public void showRecordsForCountOptions(List<String> expectedRecordList) {
        System.out.println("expectedRecordList = " + expectedRecordList);
        Select select = new Select(libraryDashboardPage.showBookRecord);
        List<WebElement> opt = select.getOptions();
        List<String> actualOpts = BrowserUtils.getElementsText(opt);
        Assert.assertEquals(expectedRecordList, actualOpts);
        System.out.println("actualOpts = " + actualOpts);
        System.out.println("expectedRecordList = " + expectedRecordList);

        /*
        report bug
        Expected :[5, 10, 15, 50, 100]
         Actual   :[5, 10, 15, 50, 100, 200, 500]
         */

    Driver.closeDriver();
    }

    @Given("the user on the homepage")
    public void theUserOnTheHomepage() {
        loginPage.loginToLibraryAsStudent();
    }

    @Then("the user shoulde see the following column names")
    public void theUserShouldeSeeTheFollowingColumnNames(List<String> expectedColumnNames) {
        List<String> actualCN = BrowserUtils.getElementsText(bookManagementPage.studentBookColumn);
        System.out.println("expectedColumnNames = " + expectedColumnNames);
        System.out.println("actualCN = " + actualCN);
        Assert.assertEquals(expectedColumnNames, actualCN);
        Driver.closeDriver();
    }

    @When("the user click Borrowing Books module")
    public void theUserClickBorrowingBooksModule() {
        loginPage.inputEmail.clear();
        loginPage.inputPassword.clear();
        loginPage.loginToLibraryAsStudent();
        BrowserUtils.sleep(5);
        bookManagementPage.borrowingBooksLink.click();
    }


    @Then("the student shoulde see the following column names:")
    public void theStudentShouldeSeeTheFollowingColumnNames(List<String> columnNamesBorrowing) {
        System.out.println("columnNamesBorrowing = " + columnNamesBorrowing);
    }

    @When("the user click users moudle")
    public void theUserClickUsersMoudle() {
        libraryDashboardPage.usersButton.click();
    }

    @And("the user click User Group dopdown")
    public void theUserClickUserGroupDopdown() {
    }

    @Then("the user should see the following options:")
    public void theUserShouldSeeTheFollowingOptions(List<String> expectedDropdownOptions) {
        Select select = new Select(libraryDashboardPage.userGroupOptions);

        List<String> actualDropdownOptions = BrowserUtils.getElementsText(select.getOptions());

        System.out.println("expectedDropdownOptions = " + expectedDropdownOptions);
        System.out.println("actualDropdownOptions = " + actualDropdownOptions);

        Assert.assertEquals(expectedDropdownOptions, actualDropdownOptions);

        /*
        Report bug
        Expected :[All, Librarian, Students]
         Actual   :[ALL, Librarian, Students]
         */
        Driver.closeDriver();

    }


    @And("the user click Status dopdown")
    public void theUserClickStatusDopdown() {

        libraryDashboardPage.statusDropdown.click();


    }

    @Then("the user should see the following status options")
    public void theUserShouldSeeTheFollowingStatusOptions(List<String> expectedStatusOptions) {
        Select select=new Select(libraryDashboardPage.statusDropdown);
        List<String> actualStatusOptions = BrowserUtils.getElementsText(select.getOptions());

        System.out.println("expectedStatusOptions = " + expectedStatusOptions);
        System.out.println("actualStatusOptions = " + actualStatusOptions);


    }


    @Then("the librarian shoulde see the following column names:")
    public void theLibrarianShouldeSeeTheFollowingColumnNames(List<String> expectedColumnNames)  {
        List<String> actualColumnNames = BrowserUtils.getElementsText(libraryDashboardPage.userColumns);
        System.out.println("expectedColumnNames = " + expectedColumnNames);
        System.out.println("actualColumnNames = " + actualColumnNames);

        Assert.assertEquals(actualColumnNames, expectedColumnNames);

    }
}
