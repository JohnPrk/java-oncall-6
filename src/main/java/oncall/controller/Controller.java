package oncall.controller;

import oncall.domain.date.MonthlyDate;
import oncall.utils.Retry;
import oncall.view.InputView;

public class Controller {

    public void run() {
        System.out.println("비상근무표");
        inputMonthAndStartDayOfWeek();
    }

    private static MonthlyDate inputMonthAndStartDayOfWeek() {
        return Retry.repeatUntilSuccess(() -> {
            String monthAndStartDayOfWeek = InputView.inputMonthAndStartDayOfWeek();
            String[] split = monthAndStartDayOfWeek.split(",");
            int month = Integer.parseInt(split[0]);
            String startDayOfWeek = split[1];
            return new MonthlyDate(month, startDayOfWeek);
        });
    }
}
