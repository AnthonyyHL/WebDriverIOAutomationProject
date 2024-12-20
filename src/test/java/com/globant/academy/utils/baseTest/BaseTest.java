package com.globant.academy.utils.baseTest;

import com.globant.academy.screens.HomeScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import java.util.logging.Logger;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected static final Logger log = Logger.getLogger(BaseTest.class.getName());
    private static String PROPERTIES_FILE = "src/test/resources/config.properties";
    private static Properties properties = new Properties();
    public static AndroidDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setupEnvironment() {
        UiAutomator2Options capabilities = new UiAutomator2Options();
        loadProperties();
        setUpCapabilities(capabilities);

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void loadProperties() {
        try {
            FileInputStream fis = new FileInputStream(PROPERTIES_FILE);
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties files " + PROPERTIES_FILE);
        }
    }

    public void setUpCapabilities(UiAutomator2Options capabilities) {
        capabilities.setPlatformName(getCapabilities("platformName"));
        capabilities.setPlatformVersion(getCapabilities("platformVersion"));
        capabilities.setDeviceName(getCapabilities("deviceName"));
        capabilities.setAppPackage(getCapabilities("appPackage"));
        capabilities.setAppActivity(getCapabilities("appActivity"));
    }

    public String getCapabilities(String variable) {
        return properties.getProperty(variable);
    }

    public HomeScreen loadSplashScreen() {
        return new HomeScreen(driver);
    }

    @AfterTest
    public void quitServer() {
        driver.quit();
    }
}