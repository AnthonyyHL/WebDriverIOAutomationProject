package com.globant.academy.utils;

import com.globant.academy.screens.LoginScreen;
import com.globant.academy.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MenuBar extends BaseScreen {
     @AndroidFindBy(accessibility = "Home")
     private WebElement homeButton;
     @AndroidFindBy(accessibility = "WebView")
     private WebElement webViewButton;
     @AndroidFindBy(accessibility = "Login")
     private WebElement loginButton;
     @AndroidFindBy(accessibility = "Forms")
     private WebElement formsButton;
     @AndroidFindBy(accessibility = "Swipe")
     private WebElement swipeButton;
     @AndroidFindBy(accessibility = "Drag")
     private WebElement dragButton;

    public MenuBar(AndroidDriver driver) {
        super(driver);
    }

    public LoginScreen goToLoginScreen(AndroidDriver driver) {
        waitToBeClickable(loginButton);
        return new LoginScreen(driver);
    }
}
