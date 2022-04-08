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
import static java.lang.String.format;


public class PracticeFormTests {

    //Path
    String studentRegistrationForm = "/automation-practice-form";
    //Test data
    String fistName = "Nikolay",
            lastName = "Serpukhov",
            fullName = format("%s %s", fistName, lastName),
            email = "nserpukhov@ya.ru",
            gender = "Other",
            mobile = "1234567890",
            birthDay = "28",
            birthMonth = "February",
            birthYear = "1992",
            dateOfBirthFormatted = birthMonth + " " + birthDay + "th, " + birthYear;
    List<String> subjectsParts = List.of("Che", "Ma", "Ph");
    List<String> subjectsFull = List.of("Chemistry", "Maths", "Physics");
    List<String> hobbies = List.of("Reading", "Music");
    String address = "Street, House Number",
            state = "NCR",
            city = "Delhi";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillSubmitCloseRegistrationForm() {

        open(studentRegistrationForm);
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        //Filling and submitting the form
        $("#firstName").setValue(fistName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#gender-radio-3").parent().click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(birthMonth);
        $(".react-datepicker__year-select").selectOption(birthYear);
        $("[aria-label$='" + dateOfBirthFormatted + "']").click();
        //$(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
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
        $x(getValueCell("Student Name")).shouldHave(text(fullName));
        $x(getValueCell("Student Email")).shouldHave(text(email));
        $x(getValueCell("Gender")).shouldHave(text(gender));
        $x(getValueCell("Mobile")).shouldHave(text(mobile));
        $x(getValueCell("Date of Birth")).shouldHave(text(birthDay + " " + birthMonth + "," + birthYear));
        $x(getValueCell("Subjects")).shouldHave(text(String.join(", ",subjectsFull)));
        $x(getValueCell("Hobbies")).shouldHave(text(String.join(", ",hobbies)));
        $x(getValueCell("Picture")).shouldHave(text("cat.png"));
        $x(getValueCell("Address")).shouldHave(text(address));
        $x(getValueCell("Mobile")).shouldHave(text(mobile));
        $x(getValueCell("State and City")).shouldHave(text(state + " " + city));

        //Closing the modal
        $("#closeLargeModal").click();
        //Asserting the modal is closed
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        }
    }