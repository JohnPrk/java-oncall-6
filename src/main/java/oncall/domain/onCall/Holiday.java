package oncall.domain.onCall;

public enum Holiday {

    NEW_YEAR_S_DAY(1, 1),
    INDEPENDENT_MOVEMENT_DAY(3, 1),
    CHILDREN_S_DAY(5, 5),
    MEMORIAL_DAY(6, 6),
    NATIONAL_LIBERATION_DAY(8, 15),
    NATIONAL_FOUNDATION_DAY(10, 3),
    KOREAN_ALPHABET_DAY(10, 9),
    CHRISTMAS(12, 25);

    private final int month;
    private final int day;

    Holiday(int month, int day) {
        this.month = month;
        this.day = day;
    }
}
