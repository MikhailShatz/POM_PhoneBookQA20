package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SplashPage;

public class LoginTests extends AppiumConfig {
    @Test
    public void positiveLogin(){
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().login(UserDTO.builder()
                        .email("awqfwf@gmail.com")
                        .password("Beer12345!")
                        .build())
                .validateContactListOpened());
    }
    @Test
    public void negativeLoginEmptyEmail(){
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("Beer12345!")
                .clickLoginButtonNegative().validateErrorTitleAlertCorrect());
    }

}
