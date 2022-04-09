package utils;

import com.github.javafaker.Faker;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class RandomUtils {


    public static String getRandomStringOfDigits(int length) {
        String SALTCHARS = "1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            result.append(SALTCHARS.charAt(index));
        }
        return result.toString();
    }

    public static String getRandomYear() {
        Faker faker = new Faker();
        return faker.random().nextInt(1900, Calendar.getInstance().get(Calendar.YEAR)).toString();
    }

    public static String getRandomMonth() {
        List<String> months = List.of("January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December");
        Random rnd = new Random();
        return months.get(rnd.nextInt(months.size()));
    }

    public static  String getRandomDay() {
        Faker faker = new Faker();
        return faker.random().nextInt(1, 27).toString();
    }

    public static String getRandomGender() {
        List<String> genders = List.of("Male", "Female", "Other");
        Random rnd = new Random();
        return genders.get(rnd.nextInt(genders.size()));
    }

}
