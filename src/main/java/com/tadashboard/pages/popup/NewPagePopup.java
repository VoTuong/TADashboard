package com.tadashboard.pages.popup;

import com.tadashboard.common.Constants;
import com.tadashboard.control.Element;
import com.tadashboard.enums.enum_ta.GlobalSettingOptions;
import com.tadashboard.objects.Page;
import com.tadashboard.pages.BasePage;

public class NewPagePopup extends BasePage {
    private final Element pboxCreatePage = new Element("//div[@id='div_popup']//div[@class='pbox']");
    private final Element txtPageName = new Element("id=name");
    private final Element ccbParentPage = new Element("id=parent");
    private final Element ccbNumberOfColumns = new Element("id=columnnumber");
    private final Element ccbDisplayAfter = new Element("id=afterpage");
    private final Element chkPublic = new Element("id=ispublic");

    public void enterPageName(String pageName) {
        txtPageName.waitForDisplay();
        txtPageName.inputValue(pageName);
    }
    public void selectParentPage(String parentPage) {
        ccbParentPage.selectOptionByText(parentPage);
    }

    public void selectNumberOfColumns(String number) {
        ccbNumberOfColumns.selectOptionByValue(number);
    }

    public void selectDisplayAfterPage(String pageName) {
        ccbDisplayAfter.selectOptionByText(pageName);
    }

    public void checkBoxPublicSelected(boolean select) {
        chkPublic.selectCheckBox(select);
    }

    public void createPage(Page page) {
        selectOptionOnGlobalSetting(GlobalSettingOptions.ADD_PAGE);

        pboxCreatePage.waitForDisplay();

        enterPageName(page.getPageName());

        if (!page.getParentPageValue().isEmpty()) {
            selectParentPage(page.getParentPageValue());
        }
        if (!page.getNumberOfColumnsValue().isEmpty()) {
            selectNumberOfColumns(page.getNumberOfColumnsValue());
        }
        if (!page.getDisplayAfterValue().isEmpty()) {
            selectDisplayAfterPage(page.getDisplayAfterValue());
        }

        checkBoxPublicSelected(page.isPublic());

        clickOnOKButton();
        pboxCreatePage.waitForDisappear(Constants.WAIT_DEFAULT);
    }
}
