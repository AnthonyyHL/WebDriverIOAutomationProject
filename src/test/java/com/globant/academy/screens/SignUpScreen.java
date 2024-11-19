package com.globant.academy.screens;

import com.globant.academy.popups.SignUpSuccessfullyPopUp;
import com.globant.academy.utils.MenuBar;
import com.globant.academy.utils.baseScreen.BaseScreen;
import com.globant.academy.utils.exceptions.SignUpScreenException;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SignUpScreen extends BaseScreen {
    private MenuBar menuBar;
    @AndroidFindBy(accessibility = "button-login-container")
    private WebElement loginChangerButton;

    @AndroidFindBy(accessibility = "button-SIGN UP")
    private WebElement signUpButton;

    @AndroidFindBy(accessibility = "input-email")
    private WebElement signUpEmailInput;
    @AndroidFindBy(accessibility = "input-password")
    private WebElement signUpPasswordInput;
    @AndroidFindBy(accessibility = "input-repeat-password")
    private WebElement signUpConfirmedPasswordInput;

    public SignUpScreen(AndroidDriver driver) {
        super(driver);
        menuBar = new MenuBar(driver);
    }

    public LoginScreen changeToLogin() {
        waitToBeClickable(loginChangerButton);
        loginChangerButton.click();
        return new LoginScreen(getDriver());
    }

    public void setNewUserCredentials(String email, String password) {
        waitToBeVisible(signUpEmailInput);
        signUpEmailInput.sendKeys(email);
        if (signUpEmailInput.getText().isEmpty()) throw new SignUpScreenException("User couldn't enter email");

        waitToBeVisible(signUpPasswordInput);
        signUpPasswordInput.sendKeys(password);
        if (signUpPasswordInput.getText().isEmpty()) throw new SignUpScreenException("User couldn't enter password");

        waitToBeVisible(signUpConfirmedPasswordInput);
        signUpConfirmedPasswordInput.sendKeys(password);
        if (signUpConfirmedPasswordInput.getText().isEmpty()) throw new SignUpScreenException("User couldn't confirm password");
    }

    public SignUpSuccessfullyPopUp signUpUser(String email, String password) {
        setNewUserCredentials(email, password);
        waitToBeClickable(signUpButton);
        signUpButton.click();
        return new SignUpSuccessfullyPopUp(getDriver());
    }

    public boolean isSignUpScreenVisible(String expectedText) {
        waitToBeVisible(signUpButton);
        WebElement signUpButtonLabel = signUpButton.findElement(By.className("android.widget.TextView"));
        return signUpButtonLabel.isDisplayed() &&
                signUpButtonLabel.getText().toLowerCase().equals(expectedText.toLowerCase());
    }
}
