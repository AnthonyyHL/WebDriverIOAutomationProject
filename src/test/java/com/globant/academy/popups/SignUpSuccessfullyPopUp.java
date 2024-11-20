package com.globant.academy.popups;

import com.globant.academy.screens.SignUpScreen;
import com.globant.academy.utils.basePopUp.BasePopUp;
import io.appium.java_client.android.AndroidDriver;

public class SignUpSuccessfullyPopUp extends BasePopUp {
    public SignUpSuccessfullyPopUp(AndroidDriver driver) {
        super(driver);
    }

    public SignUpScreen acceptSignUpPopUp() {
        clickAccept();
        return new SignUpScreen(getDriver());
    }

    public boolean isSignUpSuccessful(String expectedText) {
        return getMessage().toLowerCase().equals(expectedText.toLowerCase());
    }
}
