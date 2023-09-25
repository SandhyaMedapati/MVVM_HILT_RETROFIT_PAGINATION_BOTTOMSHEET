package org.ProjectSTest;

import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class AppTest
        extends Base {

    PageObjects pageObject;

    @Test(priority = 1)
    public void testTabLayout() throws MalformedURLException {
        setUp();
        pageObject = new PageObjects(driver);
        pageObject.clicksStaffTab();
        pageObject.clickCharacterTab();
        pageObject.clickStudentTab();

    }

    @Test(priority = 2)
    public void testBottomSheet() {
        pageObject = new PageObjects(driver);
        pageObject.clickItem();

    }

    @Test(priority = 3)
    public void testBottomSheetClose() {
        pageObject = new PageObjects(driver);
        pageObject.clickOutside();

    }
}
