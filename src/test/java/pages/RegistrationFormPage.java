package pages;


import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationFormPage {

    CalendarComponent calendar = new CalendarComponent();


    //locators
    public SelenideElement tableTitle() {
        return $("#example-modal-sizes-title-lg");
    }

    public SelenideElement tableRow(String label) {
        return $x("//td[preceding-sibling::td='" + label + "']");
    }

    public SelenideElement modal() {
        return $("#modal-dialog modal-lg");
    }


    //actions
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    public RegistrationFormPage setFirstName(String fistName) {
        $("#firstName").setValue(fistName);
        return this;
    }

    public RegistrationFormPage setLastName(String lastName) {
        $("#lastName").setValue(lastName);
        return this;
    }

    public RegistrationFormPage setEmail(String email) {
        $("#userEmail").setValue(email);
        return this;
    }

    public RegistrationFormPage setGender(String gender) {
        $(byText(gender)).click();
        return this;
    }

    public RegistrationFormPage setPhoneNumber(String mobile) {
        $("#userNumber").setValue(mobile);
        return this;
    }

    public RegistrationFormPage setDateOfBirth(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDateOfBirth(day, month, year);
        return this;
    }

    public RegistrationFormPage setSubjectsWithAutocomplete(List<String> listParts, List<String> listFull) {
        for (int i = 0; i < listParts.size(); i++) {
            $("#subjectsInput").setValue(listParts.get(i));
            $(byText(listFull.get(i))).click();
        }
        return this;
    }

    public RegistrationFormPage setHobbies(List<String> list) {
        for (String s : list) {
            $(byText(s)).click();
        }
        return this;
    }

    public RegistrationFormPage setPicture(String path) {
        $("#uploadPicture").uploadFromClasspath(path);
        return this;
    }

    public RegistrationFormPage setAddress(String address) {
        $("#currentAddress").setValue(address);
        return this;
    }

    public RegistrationFormPage setState(String state) {
        $("#state").scrollIntoView(true).click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationFormPage setCity(String city) {
        $("#city").click();
        $(byText(city)).click();
        return this;
    }

    public void submit() {
        $("#submit").click();
    }

    public RegistrationFormPage checkTableHeaderHasText(String text) {
        tableTitle().shouldHave(text(text));
        return this;
    }

    public RegistrationFormPage checkTableRowHasText(String label, String text) {
        tableRow(label).shouldHave(text(text));
        return this;
    }

    public void closeModal() {
        $("#closeLargeModal").click();
    }


}
