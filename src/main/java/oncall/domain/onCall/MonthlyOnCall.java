package oncall.domain.onCall;

public class MonthlyOnCall {

    private final int month;
    private final int day;
    private final String dayOfWeek;
    private final String holidayStatus;
    private final String crewName;

    public MonthlyOnCall(int month, int day, String dayOfWeek, String holidayStatus, String crewName) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.holidayStatus = holidayStatus;
        this.crewName = crewName;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getHolidayStatus() {
        return holidayStatus;
    }

    public String getCrewName() {
        return crewName;
    }
}
