package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestBoxTests {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1018";
    }

    @Test
    void fillFormTest () {

        String name = "Alex Egor";

        open("https://demoqa.com/text-box");

        $("[id=userName]").setValue(name);
        $("[id=userEmail]").setValue("exmaple@test");
        $("[id=currentAddress]").setValue("Address");
        $("[id=permanentAddress]").setValue("Address 2");
        $("[id=submit]").click();

        $("[id=output]").shouldHave(text(name), text("exmaple@test"),
                text("Address"), text("Address 2"));

        $("[id=output] [id=name]").shouldHave(text(name));
        $("[id=output]").$("[id=name]").shouldHave(text(name));
    }
}


