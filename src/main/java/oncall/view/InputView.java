package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String INPUT_MONTH_AND_START_DAY_OF_WEEK_MESSAGE = "비상 근무를 배정할 월과 시작 요일을 입력하세요";

    public static String inputMonthAndStartDayOfWeek() {
        System.out.println(INPUT_MONTH_AND_START_DAY_OF_WEEK_MESSAGE);
        String monthAndStartDayOfWeek = Console.readLine();
        InputValidator.validMonthAndDayOfWeek(monthAndStartDayOfWeek);
        return monthAndStartDayOfWeek;
    }
}
