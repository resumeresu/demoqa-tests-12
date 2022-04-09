package guru.qa;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static java.lang.String.format;
import static utils.RandomUtils.*;


public class PracticeFormTests {

    Faker faker = new Faker();
    RegistrationFormPage registrationForm = new RegistrationFormPage();

    //Path
    String path = "/automation-practice-form";

    //Test data
    List<String> subjectsParts = List.of("Che", "Ma", "Ph");
    List<String> subjectsFull = List.of("Chemistry", "Maths", "Physics");
    List<String> hobbies = List.of("Reading", "Music");
    String fistName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            fullName = format("%s %s", fistName, lastName),
            gender = getRandomGender(),
            email = faker.internet().emailAddress(),
            mobile = getRandomStringOfDigits(10),
            birthDay = getRandomDay(),
            birthYear = getRandomYear(),
            birthMonth = getRandomMonth(),
            address = faker.address().fullAddress(),
            state = "NCR",
            city = "Delhi";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillSubmitCloseRegistrationForm() {

        //Preparing
        registrationForm.openPage(path);

        //Filling and submitting the form
        registrationForm.setFirstName(fistName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(mobile)
                .setDateOfBirth(birthDay, birthMonth, birthYear)
                .setSubjectsWithAutocomplete(subjectsParts, subjectsFull)
                .setHobbies(hobbies)
                .setPicture("img/cat.png")
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit();

        //Asserting data in the modal
        registrationForm.tableTitle().shouldHave(text("Thanks for submitting the form"));
        registrationForm.tableRow("Student Name").shouldHave(text(fullName));
        registrationForm.tableRow("Student Email").shouldHave(text(email));
        registrationForm.tableRow("Gender").shouldHave(text(gender));
        registrationForm.tableRow("Mobile").shouldHave(text(mobile));
        registrationForm.tableRow("Date of Birth").shouldHave(text(birthDay + " " + birthMonth + "," + birthYear));
        registrationForm.tableRow("Subjects").shouldHave(text(String.join(", ", subjectsFull)));
        registrationForm.tableRow("Hobbies").shouldHave(text(String.join(", ", hobbies)));
        registrationForm.tableRow("Picture").shouldHave(text("cat.png"));
        registrationForm.tableRow("Address").shouldHave(text(address));
        registrationForm.tableRow("Mobile").shouldHave(text(mobile));
        registrationForm.tableRow("State and City").shouldHave(text(state + " " + city));


        //Closing the modal
        registrationForm.closeModal();

        //Asserting the modal is closed
        registrationForm.modal().shouldNotBe(visible);
    }


}