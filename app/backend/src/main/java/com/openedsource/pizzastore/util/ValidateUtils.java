package com.openedsource.pizzastore.util;

import lombok.NonNull;
import org.springframework.util.ObjectUtils;

public final class ValidateUtils {

    public static boolean isNullOrEmpty(Object obj) {
        return ObjectUtils.isEmpty(obj);
    }

    public static boolean isHalfWidthChar(String str) {
        return isMatch("[ -~｡-ﾟ]+", str);
    }

    public static boolean isFullWidthChar(String str) {
        return isMatch("[^ -~｡-ﾟ]+", str);
    }

    public static boolean isHalfWidthNumeric(String str) {
        return isMatch("-?[0-9]+(\\.[0-9]+)?", str);
    }

    public static boolean isHalfWidthDigit(String str) {
        return isMatch("[0-9]+", str);
    }

    public static boolean isFullWidthDigit(String str) {
        return isMatch("[０-９]+", str);
    }

    public static boolean isHalfWidthUppercaseAlphabet(String str) {
        return isMatch("[A-Z]+", str);
    }

    public static boolean isFullWidthUppercaseAlphabet(String str) {
        return isMatch("[Ａ-Ｚ]+", str);
    }

    public static boolean isHalfWidthLowercaseAlphabet(String str) {
        return isMatch("[a-z]+", str);
    }

    public static boolean isFullWidthLowercaseAlphabet(String str) {
        return isMatch("[ａ-ｚ]+", str);
    }

    public static boolean isHalfWidthAlphabet(String str) {
        return isMatch("[A-Za-z]+", str);
    }

    public static boolean isHalfWidthAlphanumeric(String str) {
        return isMatch("[A-Za-z0-9]+", str);
    }

    public static boolean isFullWidthAlphanumeric(String str) {
        return isMatch("[Ａ-Ｚａ-ｚ０-９]+", str);
    }

    public static boolean isHalfWidthSign(String str) {
        return isMatch("[ -/:-@\\[-`{-~｡｢｣､ﾞﾟ]+", str);
    }

    public static boolean isHalfWidthAlphabetAndSign(String str) {
        return isMatch("[A-Za-z -/:-@\\[-`{-~｡｢｣､ﾞﾟ]+", str);
    }

    public static boolean isHalfWidthAlphanumericAndSign(String str) {
        return isMatch("[A-Za-z0-9 -/:-@\\[-`{-~｡｢｣､ﾞﾟ]+", str);
    }

    public static boolean isFullWidthHiraganaAndKatakana(String str) {
        return isMatch("[ぁ-ゖァ-ヺ　ー・]+", str);
    }

    public static boolean isFullWidthHiraganaAndDigit(String str) {
        return isMatch("[ぁ-ゖ０-９　ー・]+", str);
    }

    public static boolean isFullWidthHiraganaAndUppercaseAlphanumeric(String str) {
        return isMatch("[ぁ-ゖＡ-Ｚ０-９　ー・]+", str);
    }

    public static boolean isTel(String str) {
        return isMatch("[()#*\\-0-9]+", str);
    }

    public static boolean isMailAddress(String str) {
        return isMatch("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", str);
    }

    private static boolean isMatch(@NonNull String regex, String str) {
        if (regex == null) {
            throw new IllegalArgumentException("regex is marked non-null but is null");
        } else {
            return isNullOrEmpty(str) ? false : str.matches(regex);
        }
    }

    public static boolean lessThanOrEqualNumberOfDigits(String numeric, int numberOfIntegerPart, int numberOfDecimalPart) {
        if (!isHalfWidthNumeric(numeric)) {
            return false;
        } else {
            String numericWithNoMinus = numeric.replaceFirst("-", "");
            String[] partOfNumeric = numericWithNoMinus.split("\\.");
            boolean isLessThanInIntegerPart = numberOfIntegerPart < 0 || partOfNumeric[0].length() <= numberOfIntegerPart;
            boolean isLessThanInDecimalPart = numberOfDecimalPart < 0 || partOfNumeric.length == 1 || partOfNumeric[1].length() <= numberOfDecimalPart;
            return isLessThanInIntegerPart && isLessThanInDecimalPart;
        }
    }

    private ValidateUtils() {
    }
}
