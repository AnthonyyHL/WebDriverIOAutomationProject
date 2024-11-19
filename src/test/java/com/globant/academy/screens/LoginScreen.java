package com.globant.academy.screens;

import com.globant.academy.utils.MenuBar;
import com.globant.academy.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginScreen extends BaseScreen {
    private MenuBar menuBar;
    @AndroidFindBy(accessibility = "button-sign-up-container")
    private WebElement signUpChangerButton;

    @AndroidFindBy(accessibility = "button-LOGIN")
    private WebElement loginLabel;



    public LoginScreen(AndroidDriver driver) {
        super(driver);
        menuBar = new MenuBar(driver);
    }

    public SignUpScreen changeToSignUp() {
        waitToBeClickable(signUpChangerButton);
        signUpChangerButton.click();
        return new SignUpScreen(getDriver());
    }

    public boolean isLoginScreenVisible(String expectedText) {
        waitToBeVisible(loginLabel);
        WebElement loginButtonLabel = loginLabel.findElement(By.className("android.widget.TextView"));
        return loginButtonLabel.isDisplayed() &&
                loginButtonLabel.getText().toLowerCase().equals(expectedText.toLowerCase());
    }
}