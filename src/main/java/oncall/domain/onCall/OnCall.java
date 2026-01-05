package oncall.domain.onCall;

public class OnCall {

    private final int month;
    private final int day;
    private final String dayOfWeek;
    private final String crew;

    public OnCall(int month, int day, String dayOfWeek, String crew) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.crew = crew;
    }
}
