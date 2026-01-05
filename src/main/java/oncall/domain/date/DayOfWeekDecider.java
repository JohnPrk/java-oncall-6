package oncall.domain.date;

import java.util.Arrays;

public enum DayOfWeekDecider {
    MONDAY(1, "월"),
    TUESDAY(2, "화"),
    WEDNESDAY(3, "수"),
    THURSDAY(4, "목"),
    FRIDAY(5, "금"),
    SATURDAY(6, "토"),
    SUNDAY(0, "일");

    private static final String WRONG_TYPE_DAY_OF_WEEK = "[ERROR] 잘못된 형태의 요일 형태입니다";

    private final int number;
    private final String dayOfWeek;

    DayOfWeekDecider(int number, String dayOfWeek) {
        this.number = number;
        this.dayOfWeek = dayOfWeek;
    }

    public static boolean isWeekend(int number) {
        return of(number).equals(SATURDAY) || of(number).equals(SUNDAY);
    }

    public static int decideFromDayOfWeek(String dayOfWeek) {
        return of(dayOfWeek).number;

    }

    public static String decideFromNumber(int number) {
        return of(number).dayOfWeek;
    }

    public static DayOfWeekDecider of(String dayOfWeek) {
        return Arrays.stream(values())
                .filter(dayOfWeekDecider -> dayOfWeekDecider.dayOfWeek.equals(dayOfWeek))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_TYPE_DAY_OF_WEEK));
    }

    public static DayOfWeekDecider of(int number) {
        return Arrays.stream(values())
                .filter(dayOfWeekDecider -> dayOfWeekDecider.number == (number))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_TYPE_DAY_OF_WEEK));
    }
}
