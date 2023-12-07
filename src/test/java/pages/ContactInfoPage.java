package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class ContactInfoPage extends BasePage{

    public ContactInfoPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emailTxt']")
    MobileElement infoEmail;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/phoneTxt']")
    MobileElement infoPhone;
    public MobileElement getElementByPhoneNumber(String phone) {
        return driver.findElement(By.xpath(String.format("//*[@text='%s']", phone)));
    }
    public MobileElement getElementByName(String name, String lastName) {
        String fullName = name + " " + lastName;
        return driver.findElement(By.xpath(String.format("//*[@text='%s']", fullName)));
    }

    public boolean validateCreatedContactUpdated(String phone, String email) {
        clickBase(getElementByPhoneNumber(phone));
        return getTextBase(infoEmail).equals(email);
    }

    public boolean validateCreatedContactUpdatedPhone(String name, String lastName, String randomPhone) {
        clickBase(getElementByName(name, lastName));
        return getTextBase(infoPhone).equals(randomPhone);
    }

    public String getFakerEmail() {
        return fakerEmail;
    }

    public String getRandomPhone() {
        return randomPhone;
    }
}
