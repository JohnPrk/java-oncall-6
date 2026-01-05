package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.domain.crew.Crews;

public class InputView {

    private static final String INPUT_MONTH_AND_START_DAY_OF_WEEK_MESSAGE = "비상 근무를 배정할 월과 시작 요일을 입력하세요";
    private static final String INPUT_WEEKDAY_CREWS_MESSAGE = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요";
    private static final String INPUT_WEEKEND_PLUS_HOLIDAY_CREWS_MESSAGE = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요";

    public static String inputMonthAndStartDayOfWeek() {
        System.out.println(INPUT_MONTH_AND_START_DAY_OF_WEEK_MESSAGE);
        String monthAndStartDayOfWeek = Console.readLine();
        InputValidator.validMonthAndDayOfWeek(monthAndStartDayOfWeek);
        return monthAndStartDayOfWeek;
    }

    public static Crews inputWeekdayCrews() {
        return getCrews(INPUT_WEEKDAY_CREWS_MESSAGE);
    }

    public static Crews inputWeekendPlusHolidayCrews() {
        return getCrews(INPUT_WEEKEND_PLUS_HOLIDAY_CREWS_MESSAGE);
    }

    private static Crews getCrews(String inputWeekdayCrewsMessage) {
        System.out.println(inputWeekdayCrewsMessage);
        String crews = Console.readLine();
        InputValidator.validWeekdayCrew(crews);
        return new Crews(crews.split(","));
    }
}
