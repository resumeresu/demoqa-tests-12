package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public void setDateOfBirth(String day, String month, String year){
        String formattedDay = dayToFormat(day);
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--" + formattedDay +
                ":not(.react-datepicker__day--outside-month)").click();
    }

    public String dayToFormat(String day) {
        String s;
        if (day.length() == 1) {
            s = "00" + day;
        } else {
            s = "0" + day;
        }
        return s;
    }

}
