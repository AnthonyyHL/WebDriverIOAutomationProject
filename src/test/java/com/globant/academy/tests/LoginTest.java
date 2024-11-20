package com.globant.academy.tests;

import com.globant.academy.popups.LoginSuccessfullyPopUp;
import com.globant.academy.popups.SignUpSuccessfullyPopUp;
import com.globant.academy.screens.HomeScreen;
import com.globant.academy.screens.LoginScreen;
import com.globant.academy.screens.SignUpScreen;
import com.globant.academy.utils.baseTest.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {
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

        SignUpScreen signUpScreen = loginScreen.changeToSignUp();
        softAssert.assertTrue(signUpScreen.isSignUpScreenVisible("Sign up"), "no es visible");
        log.info("User is at SignUp form");

        SignUpSuccessfullyPopUp signUpSuccessfullyPopUp = signUpScreen.signUpUser("logintest@gmail.com", "password");
        softAssert.assertTrue(signUpSuccessfullyPopUp.isSignUpSuccessful("You successfully signed up!"), "success");
        log.info("User successfully signed up");

        signUpScreen = signUpSuccessfullyPopUp.acceptSignUpPopUp();
        loginScreen = signUpScreen.changeToLogin();
        softAssert.assertTrue(loginScreen.isLoginScreenVisible("Login"), "login");
        log.info("User is at Login Screen");
    }

    @Test
    public void loginTest() {
        try {
            LoginSuccessfullyPopUp loginSuccessfullyPopUp = loginScreen.loginUser("logintest@gmail.com", "password");
            softAssert.assertTrue(loginSuccessfullyPopUp.isLoginSuccessful("You are logged in!"), "success");
            log.info("User successfully logged in");

            loginScreen = loginSuccessfullyPopUp.acceptLoginPopUp();
        } catch(RuntimeException e) {
            softAssert.fail("Unexpected failure: " + e.getMessage());
        }

        softAssert.assertAll();
    }
}
