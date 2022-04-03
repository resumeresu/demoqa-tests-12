package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static guru.qa.PracticeFormUtils.*;


public class PracticeFormTests {

    //Path
    String studentRegistrationForm = "/automation-practice-form";
    //Test data
    String fistName = "Nikolay";
    String lastName = "Serpukhov";
    String email = "nserpukhov@ya.ru";
    String gender = "Other";
    String mobile = "1234567890";
    String birthDay = "28";
    String birthMonth = "February";
    String birthYear = "1992";
    String dateOfBirthFormatted = birthMonth + " " + birthDay + "th, " + birthYear;
    List<String> subjectsParts = List.of("Che", "Ma", "Ph");
    List<String> subjectsFull = List.of("Chemistry", "Maths", "Physics");
    List<String> hobbies = List.of("Reading", "Music");
    String address = "Street, House Number";
    String state = "NCR";
    String city = "Delhi";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillSubmitCloseRegistrationForm() {
        open(studentRegistrationForm);

        //Filling and submitting the form
        $("#firstName").setValue(fistName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(birthMonth);
        $(".react-datepicker__year-select").selectOption(birthYear);
        $("[aria-label$='" + dateOfBirthFormatted + "']").click();
        setSubjectsWithAutocomplete(subjectsParts, subjectsFull);
        setHobbies(hobbies);
        $("#uploadPicture").uploadFromClasspath("img/cat.png");
        $("#currentAddress").setValue(address);
        $("#state").scrollIntoView(true).click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        $("#submit").click();

        //Asserting the data is present
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//td[preceding-sibling::td='Student Name']").shouldHave(text(fistName + " " + lastName));
        $x("//td[preceding-sibling::td='Student Email']").shouldHave(text(email));
        $x("//td[preceding-sibling::td='Gender']").shouldHave(text(gender));
        $x("//td[preceding-sibling::td='Mobile']").shouldHave(text(mobile));
        $x("//td[preceding-sibling::td='Date of Birth']").shouldHave(text(birthDay + " " + birthMonth + "," + birthYear));
        $x("//td[preceding-sibling::td='Subjects']").shouldHave(text(String.join(", ",subjectsFull)));
        $x("//td[preceding-sibling::td='Hobbies']").shouldHave(text(String.join(", ",hobbies)));
        $x("//td[preceding-sibling::td='Picture']").shouldHave(text("cat.png"));
        $x("//td[preceding-sibling::td='Address']").shouldHave(text(address));
        $x("//td[preceding-sibling::td='Mobile']").shouldHave(text(mobile));
        $x("//td[preceding-sibling::td='State and City']").shouldHave(text(state + " " + city));

        //Closing the modal
        $("#closeLargeModal").click();
        //Asserting the modal is closed
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        }
    }