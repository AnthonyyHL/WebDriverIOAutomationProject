package com.globant.academy.popups;

import com.globant.academy.screens.LoginScreen;
import com.globant.academy.utils.basePopUp.BasePopUp;
import io.appium.java_client.android.AndroidDriver;

public class LoginSuccessfullyPopUp extends BasePopUp {
    public LoginSuccessfullyPopUp(AndroidDriver driver) {
        super(driver);
    }

    public LoginScreen acceptLoginPopUp() {
        clickAccept();
        return new LoginScreen(getDriver());
    }

    public boolean isLoginSuccessful(String expectedText) {
        return getMessage().toLowerCase().equals(expectedText.toLowerCase());
    }
}
