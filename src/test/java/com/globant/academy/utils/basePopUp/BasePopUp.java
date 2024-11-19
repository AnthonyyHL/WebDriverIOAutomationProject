package com.globant.academy.utils.basePopUp;

import com.globant.academy.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class BasePopUp extends BaseScreen {
    @AndroidFindBy(id = "android:id/android:id/alertTitle")
    private WebElement alertTile;
    @AndroidFindBy(id = "android:id/message")
    private WebElement message;
    @AndroidFindBy(id = "android:id/button1")
    private WebElement button1;
    @AndroidFindBy(id = "android:id/button2")
    private WebElement button2;

    public BasePopUp(AndroidDriver driver) {
        super(driver);
    }

    public void clickAccept() {
        waitToBeClickable(button1);
        button1.click();
    }

    public void clickCancel() {
        waitToBeClickable(button2);
        button2.click();
    }

    public String getMessage() {
        waitToBeVisible(message);
        return message.getText();
    }
}
