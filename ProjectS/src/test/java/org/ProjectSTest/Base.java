package org.ProjectSTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Base {
    public AndroidDriver driver;

//    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 6 Pro API 32 2");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        caps.setCapability(MobileCapabilityType.APP, "C:\\Users\\medapati.sandhya\\AndroidStudioProjects\\ProjectS\\ProjectS\\src\\main\\resources\\app-debug.apk");
        URL url = new URL(" http://127.0.0.1:4723/");
        driver = new AndroidDriver(url, caps);
    }

}

