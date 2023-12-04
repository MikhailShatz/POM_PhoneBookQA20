package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class ContactListPage extends BasePage{
    public ContactListPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@text='Contact list']")
    MobileElement textTitle;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    MobileElement menuMoreOptions;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement btnLogout;

    @FindBy(xpath = "//*[@class='android.widget.ImageButton']")
    MobileElement btnAddNewContact;

    By phoneWrapper = By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowPhone']");

    public boolean validateContactListOpened() {
        return isTextEqual(textTitle, "Contact list");
    }
    public AuthenticationPage logOut(){
        clickBase(menuMoreOptions);
        clickBase(btnLogout);
        return new AuthenticationPage(driver);
    }
    public AddNewContactPage clickBtnAddNewContact(){
        clickBase(btnAddNewContact);
        return new AddNewContactPage(driver);
    }


    public boolean validateCurrentContactCreated(int i) {
        boolean contactFound = false;

        for (MobileElement me : mobEls(phoneWrapper)) {
            if (isTextEqual(me, "1234567" + i)) {
                contactFound = true;
                break;
            }
        }
        return contactFound;
    }

    public List<MobileElement>mobEls(By locator){
        List<MobileElement> phoneNumbers = driver.findElements(locator);
                return phoneNumbers;
    }
}
