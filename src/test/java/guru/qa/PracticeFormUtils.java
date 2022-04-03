package guru.qa;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormUtils {

    public static void setSubjectsWithAutocomplete(List<String> listParts, List<String> listFull) {
        for (int i = 0; i < listParts.size(); i++) {
            $("#subjectsInput").setValue(listParts.get(i));
            $(byText(listFull.get(i))).click();
        }
    }

    public  static  void setHobbies(List<String> list) {
        for (String s : list) {
            $(byText(s)).click();
        }
    }

    public static String getValueCell(String label) {
        return "//td[preceding-sibling::td='" + label + "']";
    }
}
