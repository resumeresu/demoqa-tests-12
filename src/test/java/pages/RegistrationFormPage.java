package pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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
    @Step
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    @Step
    public RegistrationFormPage setFirstName(String fistName) {
        $("#firstName").setValue(fistName);
        return this;
    }

    @Step
    public RegistrationFormPage setLastName(String lastName) {
        $("#lastName").setValue(lastName);
        return this;
    }

    @Step
    public RegistrationFormPage setEmail(String email) {
        $("#userEmail").setValue(email);
        return this;
    }

    @Step
    public RegistrationFormPage setGender(String gender) {
        $(byText(gender)).click();
        return this;
    }

    @Step
    public RegistrationFormPage setPhoneNumber(String mobile) {
        $("#userNumber").setValue(mobile);
        return this;
    }

    @Step
    public RegistrationFormPage setDateOfBirth(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDateOfBirth(day, month, year);
        return this;
    }

    @Step
    public RegistrationFormPage setSubjectsWithAutocomplete(List<String> listParts, List<String> listFull) {
        for (int i = 0; i < listParts.size(); i++) {
            $("#subjectsInput").setValue(listParts.get(i));
            $(byText(listFull.get(i))).click();
        }
        return this;
    }

    @Step
    public RegistrationFormPage setHobbies(List<String> list) {
        for (String s : list) {
            $(byText(s)).click();
        }
        return this;
    }

    @Step
    public RegistrationFormPage setPicture(String path) {
        $("#uploadPicture").uploadFromClasspath(path);
        return this;
    }

    @Step
    public RegistrationFormPage setAddress(String address) {
        $("#currentAddress").setValue(address);
        return this;
    }

    @Step
    public RegistrationFormPage setState(String state) {
        $("#state").scrollIntoView(true).click();
        $(byText(state)).click();
        return this;
    }

    @Step
    public RegistrationFormPage setCity(String city) {
        $("#city").click();
        $(byText(city)).click();
        return this;
    }

    @Step
    public RegistrationFormPage submit() {
        $("#submit").click();
        return this;
    }

    @Step
    public RegistrationFormPage checkTableHeaderHasText(String text) {
        tableTitle().shouldHave(text(text));
        return this;
    }

    @Step
    public RegistrationFormPage checkTableRowHasText(String label, String text) {
        tableRow(label).shouldHave(text(text));
        return this;
    }

    @Step
    public RegistrationFormPage closeModal() {
        $("#closeLargeModal").click();
        return this;
    }

    @Step
    public void checkModalClosed() {
        modal().shouldNotBe(visible);
    }
}
