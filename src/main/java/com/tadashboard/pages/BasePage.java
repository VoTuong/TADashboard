package com.tadashboard.pages;

import com.tadashboard.helpers.AlertHelper;
import com.tadashboard.common.Constants;
import com.tadashboard.control.Element;
import com.tadashboard.enums.enum_ta.GlobalSettingOptions;
import com.tadashboard.objects.User;

import static com.tadashboard.control.Action.waitForPageLoaded;

public abstract class BasePage {

    /**
     * Element locators of the common web elements' locator
     **/
    private final Element helpOption = new Element("//ul[@class='head-menu']//li[@class= 'hasbg h-help']");
    private final Element labMangerOption = new Element("//ul[@class='head-menu']//li[@class= 'hasbg h-lab']");
    private final Element lblDynamicNewPageBesideOtherPage = new Element("//div[@id='main-menu']//li/a[normalize-space()='%s']/ancestor::li/following::li[normalize-space()='%s']");
    private final Element lblDynamicPageNameOnMainMenu = new Element("//div[@id='main-menu']//li/a[normalize-space()='%s']");
    private final Element optDynamicUserOnMenuBar = new Element("//ul[@class='head-menu']//a[normalize-space()='%s']");
    private final Element optRepositoryOnMenuBar = new Element("//a[contains(@href, '#Repository')]");
    private final Element optDynamicRepositoryOnMenuBar = new Element("//a[contains(@href, '#Repository')]/ancestor::li//a[normalize-space()='%s']");
    private final Element btnLogout = new Element("//ul[@class='head-menu']//a[text()='Logout']");
    private final Element optGlobalMainSetting = new Element("//div[@id='main-menu']//li[@class='mn-setting']");
    private final Element optDynamicGlobalSetting = new Element("//div[@id='main-menu']//li[@class='mn-setting']//li/a[normalize-space()='%s']");
    private final Element popupPage = new Element("//div[@id='div_popup']//div[@class='pbox']");
    private final Element btnOK = new Element("id=OK");
    private final Element lblDynamicTabPage = new Element("//li//a[text()='%s']");
    /**
     * This is place for action method
     */

    public void logout(User user) {
        waitForPageLoaded();
        selectUsernameOnMenuBAr(user);
        clickLogoutButton();
    }

    private void clickLogoutButton() {
        btnLogout.waitForDisplay();
        btnLogout.click();
    }

    private void selectUsernameOnMenuBAr(User user){
        optDynamicUserOnMenuBar.setDynamicValue(user.getUsername().toLowerCase());
        optDynamicUserOnMenuBar.click();
    }

    public void clickOnOKButton() {
        btnOK.waitForDisplay();
        btnOK.click();
    }

    public void selectRepositoryMenuBarOption(String repository) {
        waitForPageLoaded();
        selectRepositoryMenu();
        optDynamicRepositoryOnMenuBar.setDynamicValue(repository);
        optDynamicRepositoryOnMenuBar.waitForDisplay();
        optDynamicRepositoryOnMenuBar.click();
    }

    private void selectRepositoryMenu() {
        optRepositoryOnMenuBar.waitForDisplay();
        optRepositoryOnMenuBar.click();
    }

    public String getCurrentRepositoryOption() {
        return optRepositoryOnMenuBar.getText();
    }

    public void selectOptionOnGlobalSetting(GlobalSettingOptions option) {
        optGlobalMainSetting.waitForDisplay(Constants.WAIT_DEFAULT);
        optGlobalMainSetting.click();
        optDynamicGlobalSetting.setDynamicValue(option.getValue());
        optDynamicGlobalSetting.click();
    }

    /**
     * This is place for wait method
     */
    public void waitForNewPagePopUpDisplay() {
        popupPage.waitForDisplay();
    }

    /**
     * This is place for verification method
     */
    public boolean isGlobalSettingOptionNotVisible(GlobalSettingOptions option) {
        optDynamicGlobalSetting.setDynamicValue(option.getValue());
        return optDynamicGlobalSetting.verifyElementNotClickable(Constants.WAIT_DEFAULT);
    }

    public boolean doesNewPageNextToOverView(String pageName) {
        return doesNewPageNextToPreviousPage(Constants.OVERVIEW_PAGE, pageName);
    }

    public boolean doesNewPageNextToPreviousPage(String newPageName, String previousPageName) {
        lblDynamicNewPageBesideOtherPage.setDynamicValue(newPageName, previousPageName);
        return lblDynamicNewPageBesideOtherPage.verifyElementPresent(Constants.WAIT_DEFAULT);
    }

    public boolean isDashboardDisplay(){
        waitForPageLoaded();
        return helpOption.verifyElementPresent(Constants.WAIT_DEFAULT) && labMangerOption.verifyElementPresent(Constants.WAIT_DEFAULT);
    }

    public void openPageTab(String... pageNames) {
        for (String pageName : pageNames) {
            lblDynamicTabPage.setDynamicValue(pageName);
            lblDynamicTabPage.waitForDisplay();
            lblDynamicTabPage.moveToElement();
        }
        lblDynamicTabPage.waitForDisplay();
        lblDynamicTabPage.click();
    }

    public void deletePageTab(String... pageNames) {
        openPageTab(pageNames);
        selectOptionOnGlobalSetting(GlobalSettingOptions.DELETE);
        AlertHelper.waitForAlertVisible();
        AlertHelper.acceptAlert();
    }

    public void waitForNewlyPageRemoved(String pageName) {
        lblDynamicPageNameOnMainMenu.setDynamicValue(pageName);
        lblDynamicPageNameOnMainMenu.waitForDisappear(Constants.WAIT_DEFAULT);
    }

}
