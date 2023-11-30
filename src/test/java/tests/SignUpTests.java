package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.SplashPage;
import utils.RandomUtils;

public class SignUpTests extends AppiumConfig {
    RandomUtils randomUtils = new RandomUtils();
    String email = randomUtils.generateEmail(7);

    @Test
    public void positiveRegTest(){
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().reg(UserDTO.builder()
                        .email(email)
                        .password("Beer12345!")
                        .build())
                        .validateContactListOpened());
    }

    @Test
    public void negativeRegEmptyEmail(){
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("Beer12345")
                .clickLoginButtonNegative().validateErrorTitleAlertCorrect());
    }
}
