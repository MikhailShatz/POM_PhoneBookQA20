package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.ContactListPage;
import pages.SplashPage;

public class LoginTests extends AppiumConfig {
    boolean isFlagUserLogin = false;
    boolean flagIsPopUpErrorDisplays = false;
    @AfterMethod
    public void afterMethod() {
        if (isFlagUserLogin) {
            isFlagUserLogin = false;
            new ContactListPage(driver).logOut();
        } else if (flagIsPopUpErrorDisplays) {
            flagIsPopUpErrorDisplays = false;
            new AuthenticationPage(driver).clickOkBtnAlert();
        }
    }
    @Test
    public void positiveLogin(){
        isFlagUserLogin = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().login(UserDTO.builder()
                        .email("awqfwf@gmail.com")
                        .password("Beer12345!")
                        .build())
                .validateContactListOpened());
    }
    @Test
    public void negativeLoginEmptyEmail(){
        flagIsPopUpErrorDisplays = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("Beer12345!")
                .clickLoginButtonNegative().validateErrorTitleAlertCorrect());
    }

}
