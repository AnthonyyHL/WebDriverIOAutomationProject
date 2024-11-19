package com.globant.academy.screens;

import com.globant.academy.utils.MenuBar;
import com.globant.academy.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomeScreen extends BaseScreen {
    MenuBar navBar;

    @AndroidFindBy(uiAutomator = "text(\"Demo app for the appium-boilerplate\")")
    private WebElement homePageLabel;


    public HomeScreen(AndroidDriver driver) {
        super(driver);
        navBar = new MenuBar(driver);
    }

    public boolean isHomePageLabelVisible(String textExpected) {
        waitToBeVisible(homePageLabel);
        return homePageLabel.isDisplayed() &&
                homePageLabel.getText().toLowerCase().equals(textExpected.toLowerCase());
    }

    public LoginScreen navigateToLoginScreen() {
        return navBar.goToLoginScreen(getDriver());
    }
}