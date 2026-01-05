package oncall.domain.date;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class MonthlyDate {

    private final int month;
    private final int startDayOfMonth;
    private final int lastDayOfMonth;
    private final String startDayOfWeek;

    public MonthlyDate(int month, String startDayOfWeek) {
        this.month = month;
        this.startDayOfMonth = 1;
        if (month == 2) {
            this.lastDayOfMonth = 28;
        } else {
            this.lastDayOfMonth = LocalDate.of(LocalDate.now().getYear(), month, 1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        }
        this.startDayOfWeek = startDayOfWeek;
    }

    public int getMonth() {
        return month;
    }

    public int getStartDayOfMonth() {
        return startDayOfMonth;
    }

    public String getStartDayOfWeek() {
        return startDayOfWeek;
    }

    public int getLastDayOfMonth() {
        return lastDayOfMonth;
    }
}
