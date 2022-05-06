package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

import java.util.List;

import static java.lang.String.format;
import static utils.RandomUtils.*;


public class PracticeFormTest {

    Faker faker = new Faker();
    RegistrationFormPage registrationForm = new RegistrationFormPage();

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

    @Test
    void fillSubmitCloseRegistrationForm() {

        //Preparing
        registrationForm.openPage();

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
        registrationForm.checkTableHeaderHasText("Thanks for submitting the form")
                .checkTableRowHasText("Student Name", fullName)
                .checkTableRowHasText("Student Email", email)
                .checkTableRowHasText("Gender", gender)
                .checkTableRowHasText("Mobile", mobile)
                .checkTableRowHasText("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
                .checkTableRowHasText("Subjects", String.join(", ", subjectsFull))
                .checkTableRowHasText("Hobbies", String.join(", ", hobbies))
                .checkTableRowHasText("Picture", "cat.png")
                .checkTableRowHasText("Address", address)
                .checkTableRowHasText("Mobile", mobile)
                .checkTableRowHasText("State and City", state + " " + city);

        //Closing the modal
        registrationForm.closeModal()
                .checkModalClosed();
    }


}