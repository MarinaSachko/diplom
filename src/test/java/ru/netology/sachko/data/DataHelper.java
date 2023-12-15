package ru.netology.sachko.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
public class DataHelper {

    private DataHelper() {
    }


    @Data
    @AllArgsConstructor
    public static class CardNumber {
        String cardNumber;
        String status;
    }

    public static CardNumber approvedCardInfo() {
        CardNumber cardNumber = new CardNumber("4444 4444 4444 4441", "APPROVED");
        return cardNumber;
    }

    public static CardNumber declinedCardInfo() {
        CardNumber cardNumber = new CardNumber("4444 4444 4444 4442", "DECLINED");
        return cardNumber;
    }


    public static String getApprovedCardInfo() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardInfo() {
        return "4444 4444 4444 4442";
    }

    public static String getInvalidCardNumber() {
        Faker faker = new Faker();
        return Long.toString(faker.number().randomNumber(16, true));
    }
    public static String getInvalidCardNumberLetters() {
          return "fgdg fgdg jjkj iopm";
    }


    public static String getEmptyString() {
        return "";
    }

    public static String getValidMonth() {
        String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        int rnd = new Random().nextInt(month.length);
        return month[rnd];
    }

    public static String getInvalidMonthOneNumber() {
        String[] month = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int rnd = new Random().nextInt(month.length);
        return month[rnd];
    }

    public static String getInvalidMonthTwoNumbers() {
        String[] month = {"21", "22", "23", "24", "25", "26", "27", "28", "29"};
        int rnd = new Random().nextInt(month.length);
        return month[rnd];
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getValidYear() {
        return LocalDate.now().plusYears(3).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getInvalidYear() {
        String[] year = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int rnd = new Random().nextInt(year.length);
        return year[rnd];
    }

    public static String getInvalidLastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getZeroYear() {
        return "00";
    }

    public static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String getInvalidOwnerCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String getInvalidOwnerMaths() {
        String[] owner = {"212*62", "32/12", "36%", "4+", "-5", "=6", "^7", "(8)"};
        int rnd = new Random().nextInt(owner.length);
        return owner[rnd];
    }

    public static String getValidCvc() {
        Faker faker = new Faker();
        return faker.numerify("###");
    }

    public static String getInvalidCvc() {
        Faker faker = new Faker();
        return faker.numerify("##");
    }

    public static String getZeroCvc() {
        return "000";
    }

}
