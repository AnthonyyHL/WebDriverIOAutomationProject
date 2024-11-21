package com.globant.academy.tests;

import com.globant.academy.screens.HomeScreen;
import com.globant.academy.screens.SwipeCardsScreen;
import com.globant.academy.utils.baseTest.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SwipeCardsTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    SwipeCardsScreen swipeCardsScreen;

    @BeforeClass(alwaysRun = true)
    public void navigateToSwipeScreen() {
        log.info("Sign Up Test Started");
        HomeScreen homeScreen = loadSplashScreen();
        softAssert.assertTrue(homeScreen.isHomePageLabelVisible("Demo app for the appium-boilerplate"), "homescreen");
        log.info("User is at Home Page");

        swipeCardsScreen = homeScreen.navigateToSwipeScreen();
        softAssert.assertTrue(swipeCardsScreen.isSwipeCardsScreenVisible("Swipe Horizontal"), "Swipe screen has loaded");
        log.info("User is at Swipe Screen");
    }

    @Test
    public void isFirstCardVisibleTest() {
        try {
            softAssert.assertTrue(swipeCardsScreen.isFirstCardVisible(), "First Card is visible");
            log.info("First Card is visible");

            swipeCardsScreen.swipeCardsNTimes(1);

            softAssert.assertTrue(!swipeCardsScreen.isFirstCardVisible(), "First Card is not visible");
            log.info("First Card is not visible");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        softAssert.assertAll();
    }

    @Test
    public void isLastCardTheOnlyOneVisibleTest() {
        try {
            swipeCardsScreen.swipeCardsNTimes(7);

            softAssert.assertTrue(!swipeCardsScreen.isLastCardTheOnlyOneVisible(), "Last Card is the only one visible");
            log.info("Last Card is the only one visible");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        softAssert.assertAll();
    }

     @Test
     public void swipeVerticallyTest() {
        try {
            swipeCardsScreen.scrollVerticallyNTimes(15);

            softAssert.assertTrue(swipeCardsScreen.isYouFoundMeLabelVisible("You found me!!!"), "You found me label is visible");
            log.info("You found me label is visible");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        softAssert.assertAll();
     }
}
