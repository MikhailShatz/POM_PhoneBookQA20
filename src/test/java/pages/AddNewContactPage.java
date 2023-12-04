package pages;

import dto.ContactDTO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class AddNewContactPage extends BasePage{
    public AddNewContactPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputName']")
    MobileElement inputName;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputLastName']")
    MobileElement inputLastName;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement inputEmail ;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPhone']")
    MobileElement inputPhone;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputAddress']")
    MobileElement inputAddress ;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputDesc']")
    MobileElement inputDesc;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/createBtn']")
    MobileElement btnCreateNewContact;
    @FindBy(xpath = "//*[@resource-id='android:id/alertTitle']")
    MobileElement textAlertHError;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement btnOkAlertError;

    public AddNewContactPage sendTextInputName(String name){
        typeTextBase(inputName, name);
        return this;
    }

    public AddNewContactPage sendTextInputLastname(String lastName){
        typeTextBase(inputLastName, lastName);
        return this;
    }
    public AddNewContactPage sendTextInputEmail(String email){
        typeTextBase(inputEmail, email);
        return this;
    }
    public AddNewContactPage sendTextInputPhone(String phone){
        typeTextBase(inputPhone, phone);
        return this;
    }

    public  AddNewContactPage sendTextInputAddress(String address){
        typeTextBase(inputAddress, address);
        return this;
    }

    public AddNewContactPage sendTextInputDesc(String desc){
        typeTextBase(inputDesc, desc);
        return this;
    }

    public ContactListPage clickBtnAddNewContact(){
        clickBase(btnCreateNewContact);
        return new ContactListPage(driver);
    }
    public AddNewContactPage clickBtnAddNewContactNegative(){
        clickBase(btnCreateNewContact);
        return this;
    }

    public ContactListPage addNewContact(ContactDTO contact){
        return sendTextInputName(contact.getName()).sendTextInputLastname(contact.getLastName())
                .sendTextInputEmail(contact.getEmail()).sendTextInputPhone(contact.getPhone())
                .sendTextInputAddress(contact.getAddress()).sendTextInputDesc(contact.getDescription())
                .clickBtnAddNewContact();
    }

    public AddNewContactPage addNewContactNegative(ContactDTO contact) {
       return  sendTextInputName(contact.getName()).sendTextInputLastname(contact.getLastName())
                .sendTextInputEmail(contact.getEmail()).sendTextInputPhone(contact.getPhone())
                .sendTextInputAddress(contact.getAddress()).sendTextInputDesc(contact.getDescription())
                .clickBtnAddNewContactNegative();
    }

    public boolean validateErrorMessage() {
        return  isTextEqual(textAlertHError, "Error");
    }

    public AddNewContactPage clickOkCloseAlert() {
        clickBase(btnOkAlertError);
        return this;
    }

    public ContactListPage backToContactList(){
        backBtnOnEmulator();
        return new ContactListPage(driver);
    }
}
