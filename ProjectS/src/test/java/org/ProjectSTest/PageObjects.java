package org.ProjectSTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjects extends Base{


    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Staff\"]")
    public WebElement staffTab;
    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Student\"]")
    public WebElement studentTab;

    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Characters\"]/android.widget.TextView")
    public WebElement characterTab;
    @FindBy(id = "com.sandhya.projects:id/imageView")
    public WebElement particularCharacter;
    @FindBy(id = "com.sandhya.projects:id/touch_outside")
    public WebElement touchOutside;


    private  WebDriverWait wait;

    public PageObjects(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver) ,this);
    }

    public void clicksStaffTab() {
        staffTab.click();

    }
    public void clickStudentTab() {
        studentTab.click();

    }
    public void clickCharacterTab() {
        characterTab.click();

    }
    public void clickItem() {
        particularCharacter.click();

    }
    public void clickOutside() {
        touchOutside.click();

    }

}

