package com.globant.academy.screens;

import com.globant.academy.popups.LoginSuccessfullyPopUp;
import com.globant.academy.utils.MenuBar;
import com.globant.academy.utils.baseScreen.BaseScreen;
import com.globant.academy.utils.exceptions.SignUpScreenException;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginScreen extends BaseScreen {
    private MenuBar menuBar;
    @AndroidFindBy(accessibility = "button-sign-up-container")
    private WebElement signUpChangerButton;

    @AndroidFindBy(accessibility = "button-LOGIN")
    private WebElement loginButton;

    @AndroidFindBy(accessibility = "input-email")
    private WebElement loginEmailInput;
    @AndroidFindBy(accessibility = "input-password")
    private WebElement loginPasswordInput;

    public LoginScreen(AndroidDriver driver) {
        super(driver);
        menuBar = new MenuBar(driver);
    }

    public SignUpScreen changeToSignUp() {
        waitToBeClickable(signUpChangerButton);
        signUpChangerButton.click();
        return new SignUpScreen(getDriver());
    }

    public void setUserCredentials(String email, String password) {
        waitToBeVisible(loginEmailInput);
        loginEmailInput.sendKeys(email);
        if (loginEmailInput.getText().isEmpty()) throw new SignUpScreenException("User couldn't enter email");

        waitToBeVisible(loginPasswordInput);
        loginPasswordInput.sendKeys(password);
        if (loginPasswordInput.getText().isEmpty()) throw new SignUpScreenException("User couldn't enter password");
    }

    public LoginSuccessfullyPopUp loginUser(String email, String password) {
        setUserCredentials(email, password);
        waitToBeClickable(loginButton);
        loginButton.click();
        return new LoginSuccessfullyPopUp(getDriver());
    }

    public boolean isLoginScreenVisible(String expectedText) {
        waitToBeVisible(loginButton);
        WebElement loginButtonLabel = loginButton.findElement(By.className("android.widget.TextView"));
        return loginButtonLabel.isDisplayed() &&
                loginButtonLabel.getText().toLowerCase().equals(expectedText.toLowerCase());
    }
}