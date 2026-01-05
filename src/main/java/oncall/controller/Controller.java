package oncall.controller;

import oncall.domain.crew.Crew;
import oncall.domain.crew.Crews;
import oncall.domain.crew.WeekdayCrewsAndWeekendPlusHolidayCrews;
import oncall.domain.date.DayOfWeekDecider;
import oncall.domain.date.MonthlyDate;
import oncall.domain.onCall.Holiday;
import oncall.domain.onCall.MonthlyOnCall;
import oncall.domain.onCall.MonthlyOnCalls;
import oncall.utils.Retry;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Controller {

    public void run() {
        System.out.println("<<비상근무표>>");
        MonthlyDate monthlyDate = inputMonthAndStartDayOfWeek();
        WeekdayCrewsAndWeekendPlusHolidayCrews weekdayCrewsAndWeekendPlusHolidayCrews = inputWeekdayCrewsAndWeekendPlusHolidayCrews();
        MonthlyOnCalls monthlyOnCalls = makeMonthlyOnCalls(monthlyDate, weekdayCrewsAndWeekendPlusHolidayCrews);
        OutputView.printMonthlyOnCalls(monthlyOnCalls);
    }

    private MonthlyDate inputMonthAndStartDayOfWeek() {
        return Retry.repeatUntilSuccess(() -> {
            String monthAndStartDayOfWeek = InputView.inputMonthAndStartDayOfWeek();
            String[] split = monthAndStartDayOfWeek.split(",");
            int month = Integer.parseInt(split[0]);
            String startDayOfWeek = split[1];
            return new MonthlyDate(month, startDayOfWeek);
        });
    }

    private WeekdayCrewsAndWeekendPlusHolidayCrews inputWeekdayCrewsAndWeekendPlusHolidayCrews() {
        return Retry.repeatUntilSuccess(() -> {
            Crews weekdayCrews = InputView.inputWeekdayCrews();
            Crews weekendPlusHolidayCrews = InputView.inputWeekendPlusHolidayCrews();
            return new WeekdayCrewsAndWeekendPlusHolidayCrews(weekdayCrews, weekendPlusHolidayCrews);
        });
    }

    private MonthlyOnCalls makeMonthlyOnCalls(MonthlyDate monthlyDate, WeekdayCrewsAndWeekendPlusHolidayCrews weekdayCrewsAndWeekendPlusHolidayCrews) {
        MonthlyOnCalls monthlyOnCalls = new MonthlyOnCalls();
        Crew priorCrew = null;
        for (int day = monthlyDate.getStartDayOfMonth(); day <= monthlyDate.getLastDayOfMonth(); day++) {
            int month = monthlyDate.getMonth();
            int dayOfWeekNumber = DayOfWeekDecider.decideFromDayOfWeek(monthlyDate.getStartDayOfWeek());
            int todayDayOfWeekNumber = (day - 1 + dayOfWeekNumber) % 7;
            Crews crews = selectCrews(month, day, todayDayOfWeekNumber, weekdayCrewsAndWeekendPlusHolidayCrews);
            priorCrew = addMonthlyOnCallAndReturnCrew(month, crews, priorCrew, monthlyOnCalls, day, todayDayOfWeekNumber);
        }
        return monthlyOnCalls;
    }

    private Crew addMonthlyOnCallAndReturnCrew(int month, Crews crews, Crew priorCrew, MonthlyOnCalls monthlyOnCalls, int day, int todayDayOfWeekNumber) {
        Crew crew = crews.match(priorCrew);
        if (!isWeekend(todayDayOfWeekNumber) && isHoliday(month, day)) {
            addMonthlyOnCall(month, monthlyOnCalls, day, todayDayOfWeekNumber, "공휴일", crew);
        } else {
            addMonthlyOnCall(month, monthlyOnCalls, day, todayDayOfWeekNumber, null, crew);
        }
        return crew;
    }

    private boolean isHoliday(int month, int day) {
        return Holiday.isHoliday(month, day);
    }

    private boolean isWeekend(int todayDayOfWeekNumber) {
        return DayOfWeekDecider.isWeekend(todayDayOfWeekNumber);
    }

    private Crews selectCrews(int month, int day, int todayDayOfWeekNumber, WeekdayCrewsAndWeekendPlusHolidayCrews weekdayCrewsAndWeekendPlusHolidayCrews) {
        if (isWeekend(todayDayOfWeekNumber) || isHoliday(month, day)) {
            return weekdayCrewsAndWeekendPlusHolidayCrews.getWeekendPlusHolidayCrews();
        }
        return weekdayCrewsAndWeekendPlusHolidayCrews.getWeekdayCrews();
    }

    private void addMonthlyOnCall(int month, MonthlyOnCalls monthlyOnCalls, int day, int todayDayOfWeekNumber, String status, Crew crew) {
        monthlyOnCalls.addMonthlyOnCall(new MonthlyOnCall(month, day, DayOfWeekDecider.decideFromNumber(todayDayOfWeekNumber), status, crew.getName()));
    }
}
