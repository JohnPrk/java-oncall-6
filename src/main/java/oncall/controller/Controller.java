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
        Crews weekdayCrews = weekdayCrewsAndWeekendPlusHolidayCrews.getWeekdayCrews();
        Crews weekendPlusHolidayCrews = weekdayCrewsAndWeekendPlusHolidayCrews.getWeekendPlusHolidayCrews();
        Crew priorCrew = null;
        for (int day = monthlyDate.getStartDayOfMonth(); day <= monthlyDate.getLastDayOfMonth(); day++) {
            int dayOfWeekNumber = DayOfWeekDecider.decideFromDayOfWeek(monthlyDate.getStartDayOfWeek());
            int todayDayOfWeekNumber = (day - 1 + dayOfWeekNumber) % 7;
            boolean isWeekend = DayOfWeekDecider.isWeekend(todayDayOfWeekNumber);
            boolean isHoliday = Holiday.isHoliday(monthlyDate.getMonth(), day);
            if (isWeekend || isHoliday) {
                Crew peekedCrew = weekendPlusHolidayCrews.peek();
                Crew crew = null;
                if (peekedCrew.isMe(priorCrew)) {
                    Crew temp = weekendPlusHolidayCrews.remove();
                    crew = weekendPlusHolidayCrews.pop();
                    weekendPlusHolidayCrews.addFirst(temp);
                } else {
                    crew = weekendPlusHolidayCrews.pop();
                }
                if (!isWeekend) {
                    monthlyOnCalls.addMonthlyOnCall(new MonthlyOnCall(monthlyDate.getMonth(), day, DayOfWeekDecider.decideFromNumber(todayDayOfWeekNumber), "공휴일", crew.getName()));
                } else {
                    monthlyOnCalls.addMonthlyOnCall(new MonthlyOnCall(monthlyDate.getMonth(), day, DayOfWeekDecider.decideFromNumber(todayDayOfWeekNumber), null, crew.getName()));
                }
                priorCrew = crew;
            } else {
                Crew peekedCrew = weekdayCrews.peek();
                Crew crew = null;
                if (peekedCrew.isMe(priorCrew)) {
                    Crew temp = weekdayCrews.remove();
                    crew = weekdayCrews.pop();
                    weekdayCrews.addFirst(temp);
                } else {
                    crew = weekdayCrews.pop();
                }
                monthlyOnCalls.addMonthlyOnCall(new MonthlyOnCall(monthlyDate.getMonth(), day, DayOfWeekDecider.decideFromNumber(todayDayOfWeekNumber), null, crew.getName()));
                priorCrew = crew;
            }
        }
        return monthlyOnCalls;
    }
}
