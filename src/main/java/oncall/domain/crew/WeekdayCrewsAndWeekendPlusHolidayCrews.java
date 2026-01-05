package oncall.domain.crew;

public class WeekdayCrewsAndWeekendPlusHolidayCrews {

    private final Crews weekdayCrews;
    private final Crews weekendPlusHolidayCrews;

    public WeekdayCrewsAndWeekendPlusHolidayCrews(Crews weekdayCrews, Crews weekendPlusHolidayCrews) {
        this.weekdayCrews = weekdayCrews;
        this.weekendPlusHolidayCrews = weekendPlusHolidayCrews;
    }

    public Crews getWeekdayCrews() {
        return weekdayCrews;
    }

    public Crews getWeekendPlusHolidayCrews() {
        return weekendPlusHolidayCrews;
    }
}
