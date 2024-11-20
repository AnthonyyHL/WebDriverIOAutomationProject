package com.globant.academy.tests;

import com.globant.academy.popups.SignUpSuccessfullyPopUp;
import com.globant.academy.screens.HomeScreen;
import com.globant.academy.screens.LoginScreen;
import com.globant.academy.screens.SignUpScreen;
import com.globant.academy.utils.baseTest.BaseTest;
import com.globant.academy.utils.data.Data;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignUpTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    LoginScreen loginScreen;

    @BeforeClass(alwaysRun = true)
    public void navigateToLoginScreen() {
        log.info("Sign Up Test Started");
        HomeScreen homeScreen = loadSplashScreen();
        softAssert.assertTrue(homeScreen.isHomePageLabelVisible("Demo app for the appium-boilerplate"), "homescreen");
        log.info("User is at Home Page");

        loginScreen = homeScreen.navigateToLoginScreen();
        softAssert.assertTrue(loginScreen.isLoginScreenVisible("Login"), "login");
        log.info("User is at Login Screen");
    }

    @Test(dataProvider = "credentialManager", dataProviderClass = Data.class, alwaysRun = true)
    public void signUpTest(String email, String password) {
        try {
            SignUpScreen signUpScreen = loginScreen.changeToSignUp();
            softAssert.assertTrue(signUpScreen.isSignUpScreenVisible("Sign up"), "no es visible");
            log.info("User is at SignUp form");

            SignUpSuccessfullyPopUp signUpSuccessfullyPopUp = signUpScreen.signUpUser(email, password);
            softAssert.assertTrue(signUpSuccessfullyPopUp.isSignUpSuccessful("You successfully signed up!"), "success");
            log.info("User successfully signed up");

            signUpScreen = signUpSuccessfullyPopUp.acceptSignUpPopUp();

        } catch(RuntimeException e) {
            softAssert.fail("Unexpected failure: " + e.getMessage());
        }

        softAssert.assertAll();
    }
}