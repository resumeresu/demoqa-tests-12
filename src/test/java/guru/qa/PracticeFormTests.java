package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    String studentRegistrationForm = "/automation-practice-form";

    Map<String, String> formData = Map.of(
            "fistName", "Nikolay",
            "lastName", "Serpukhov",
            "email", "nserpukhov@ya.ru",
            "gender", "Other",
            "Mobile", "+79998887766",
            "birthDay", "27",
            "birthMonth", "February",
            "birthYear", "1991",
            "subjects", "Gender studies",
            "hobbies", "Reading"
    );

    @Test
    void fillAndSubmitStudentRegistrationForm() {
        open(studentRegistrationForm);
        $("#firstName").setValue(formData.get("fistName"));
        $("#lastName").setValue(formData.get("lastName"));
        $("#userEmail").setValue(formData.get("email"));
    }

}
