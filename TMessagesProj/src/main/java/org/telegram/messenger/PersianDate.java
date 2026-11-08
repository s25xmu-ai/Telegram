package org.telegram.messenger;

import java.util.Calendar;

/** Gregorian to Solar Hijri date for common modern dates (1800–2256). */
public final class PersianDate {
    private static final String[] MONTHS = {"فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"};
    private PersianDate() { }

    public static int[] fromGregorian(int year, int month, int day) {
        int[] offsets = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int y = month > 2 ? year + 1 : year;
        int days = 355666 + 365 * year + (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400 + day + offsets[month - 1];
        int jy = -1595 + 33 * (days / 12053);
        days %= 12053;
        jy += 4 * (days / 1461);
        days %= 1461;
        if (days > 365) { jy += (days - 1) / 365; days = (days - 1) % 365; }
        int jm = days < 186 ? 1 + days / 31 : 7 + (days - 186) / 30;
        int jd = 1 + (days < 186 ? days % 31 : (days - 186) % 30);
        return new int[]{jy, jm, jd};
    }

    public static int[] at(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        return fromGregorian(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
    }

    private static String digits(int number) {
        String s = Integer.toString(number);
        StringBuilder result = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            result.append(c >= '0' && c <= '9' ? (char) ('۰' + c - '0') : c);
        }
        return result.toString();
    }

    public static String format(long millis, boolean withYear) {
        int[] d = at(millis);
        return digits(d[2]) + " " + MONTHS[d[1] - 1] + (withYear ? " " + digits(d[0]) : "");
    }
}
