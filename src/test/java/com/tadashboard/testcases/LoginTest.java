package com.tadashboard.testcases;

import com.tadashboard.base.BaseTest;
import com.tadashboard.helpers.AlertHelper;
import com.tadashboard.common.Constants;
import com.tadashboard.objects.User;
import com.tadashboard.pages.HomePage;
import extentreports.ExtentLogger;
import com.tadashboard.pages.LoginPage;
import com.tadashboard.utils.UserUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    User validAccount = UserUtils.getValidAccount();
    User invalidUsername = UserUtils.getInvalidUser();
    User invalidPassword = UserUtils.getInvalidPassword();
    User differentRepo = UserUtils.getOtherRepo();
    User emptyUser = UserUtils.getEmptyAccount();

    @Test(description = "DA_LOGIN_TC001: Verify that user can login specific repository successfully via Dashboard login page with correct credentials")
    public void loginWithValidAccount(){

        ExtentLogger.info("Login with valid account: " + validAccount.getUsername() + " on repository " + validAccount.getRepository());
        loginPage.login(validAccount);

        ExtentLogger.info("Verify that Dashboard Main page appears");
        softAssert.assertTrue(homePage.isDashboardDisplay(), "Login failed");

        ExtentLogger.info("Post-Condition: Logout from TA DashBoard");
        loginPage.logout(validAccount);

        softAssert.assertAll();
    }

    @Test(description = "DA_LOGIN_TC002: Verify that user fails to login specific repository successfully via Dashboard login page with incorrect credentials")
    public void loginWithIncorrectUsername() {

        ExtentLogger.info("Login with invalid account: " + invalidUsername.getUsername());
        loginPage.login(invalidUsername);

        AlertHelper.waitForAlertVisible();

        ExtentLogger.info("Verify that Dashboard Error message 'Username or password is invalid' appears");
        softAssert.assertEquals(AlertHelper.getTextAlert(), Constants.INVALID_ACCOUNT_ERROR_MESSAGE, "Login successfully");

        ExtentLogger.info("Post-Condition: Close message alert");
        AlertHelper.dismissAlert();

        softAssert.assertAll();
    }

    @Test(description = "DA_LOGIN_TC003: Verify that user fails to log in specific repository successfully via Dashboard login page with correct username and incorrect password")
    public void loginWithIncorrectPassword() {

        ExtentLogger.info("Login with valid account " + invalidPassword.getUsername());
        loginPage.login(invalidPassword);

        AlertHelper.waitForAlertVisible();

        ExtentLogger.info("Verify that Dashboard Error message 'Username or password is invalid' appears");
        softAssert.assertEquals(AlertHelper.getTextAlert(), Constants.INVALID_ACCOUNT_ERROR_MESSAGE, "Login successfully");

        ExtentLogger.info("Post-Condition: Close message alert");
        AlertHelper.dismissAlert();

        softAssert.assertAll();
    }

    @Test(description = "DA_LOGIN_TC004: Verify that user is able to log in different repositories successfully after logging out current repository")
    public void loginWithDifferentRepository() {

        ExtentLogger.info("Login with valid account: " + validAccount.getUsername() + " on repository " + validAccount.getRepository());
        loginPage.login(validAccount);

        ExtentLogger.info("Verify that Dashboard Main page appears");
        softAssert.assertTrue(homePage.isDashboardDisplay(), "Login failed");

        ExtentLogger.info("Logout from TA DashBoard");
        loginPage.logout(validAccount);

        ExtentLogger.info("Login with valid account with different repository: " + differentRepo.getUsername() + " on repository " + differentRepo.getRepository());
        loginPage.login(differentRepo);

        ExtentLogger.info("Verify that Dashboard Main page appears");
        softAssert.assertTrue(homePage.isDashboardDisplay(), "Login failed");

        ExtentLogger.info("Post-Condition: Logout from TA DashBoard");
        loginPage.logout(differentRepo);

        softAssert.assertAll();
    }

    @Test(description = "DA_LOGIN_TC005: Verify that there is no Login dialog when switching between 2 repositories with the same account")
    public void switchingTwoRepository() {

        ExtentLogger.info("Login with valid account: " + validAccount.getUsername() + " on repository " + validAccount.getRepository());
        loginPage.login(validAccount);

        ExtentLogger.info("Choose another repository in Repository list");
        loginPage.selectRepositoryMenuBarOption(Constants.TEST_REPOSITORY);

        ExtentLogger.info("Verify that there is no Login Repository dialog");
        softAssert.assertTrue(loginPage.verifyLoginDialogNotVisible(), "Login Repository dialog show up");

        ExtentLogger.info("Verify that The Repository menu displays name of switched repository");
        String currentRepository = "Repository: " + Constants.TEST_REPOSITORY;
        softAssert.assertEquals(loginPage.getCurrentRepositoryOption(), currentRepository,
                "The Repository menu don't display name of switched repository");

        ExtentLogger.info("Post-Condition: Logout from TA DashBoard");
        loginPage.logout(validAccount);

        softAssert.assertAll();
    }

    @Test(description = "DA_LOGIN_TC010: Verify that the page works correctly for the case when no input entered to Password and Username field")
    public void emptyDataLogin() {

        ExtentLogger.info("Login with empty account: " + emptyUser.getUsername() + " on repository " + emptyUser.getRepository());
        loginPage.login(emptyUser);

        ExtentLogger.info("Verify that Dashboard Error message 'Username or password is invalid' appears");
        softAssert.assertEquals(AlertHelper.getTextAlert(), Constants.EMPTY_USER_ERROR_MESSAGE, "Login successfully");

        ExtentLogger.info("Post-Condition: Close message alert");
        AlertHelper.dismissAlert();

        softAssert.assertAll();
    }

}
