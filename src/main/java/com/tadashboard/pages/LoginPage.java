package com.tadashboard.pages;

import com.tadashboard.common.Constants;
import com.tadashboard.control.Element;
import com.tadashboard.objects.User;
import extentreports.ExtentLogger;

public class LoginPage extends BasePage{

    public LoginPage() {

    }

    private final Element cbbRepository = new Element("id=repository");
    private final Element txtUsername = new Element("id=username");
    private final Element txtPassword = new Element("id=password");
    private final Element btnLogin = new Element("//div[@class='btn-login']");


    public void fillLoginForm(String repository, String username, String password){
        setRepository(repository);
        setUsername(username);
        setPassword(password);
    }

    public void login(User user){
        fillLoginForm(user.getRepository(), user.getUsername(), user.getPassword());
        clickBtnLogIn();
    }

    public void setRepository(String repository){
        ExtentLogger.info("Selecting Repository");
        cbbRepository.waitForDisplay(Constants.WAIT_EXPLICIT);
        cbbRepository.selectOptionByText(repository);
    }

    public void setUsername(String username){
        ExtentLogger.info("Filling Username");
        txtUsername.clearText();
        txtUsername.inputValue(username);
    }

    public void setPassword(String password){
        ExtentLogger.info("Filling Password");
        txtPassword.clearText();
        txtPassword.inputValue(password);
    }

    public void clickBtnLogIn(){
        btnLogin.waitForDisplay(Constants.WAIT_EXPLICIT);
        btnLogin.click();
    }

    public boolean verifyLoginDialogNotVisible() {
        return txtUsername.verifyElementNotPresent(Constants.WAIT_DEFAULT) && txtPassword.verifyElementNotPresent(Constants.WAIT_DEFAULT)
                && btnLogin.verifyElementNotPresent(Constants.WAIT_DEFAULT);
    }


}
