package com.globant.academy.screens;

import com.globant.academy.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginScreen extends BaseScreen {
    @AndroidFindBy(uiAutomator = "text(\"Login\").instance(0)")
    private WebElement loginLabel;

    public LoginScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isLoginScreenVisible(String expectedText) {
        waitToBeVisible(loginLabel);
        return loginLabel.isDisplayed() &&
                loginLabel.getText().toLowerCase().equals(expectedText.toLowerCase());
    }
}
