package oncall.view;

import java.util.Arrays;
import java.util.List;

public class InputValidator {

    private static final List<String> DAY_OF_WEEK = Arrays.asList("월", "화", "수", "목", "금", "토", "일");
    private static final String FORMAT_REGEX = "^\\d+,[가-힣]$";
    private static final String WRONG_TYPE_ERROR = "[ERROR] 입력하신 달(월), 요일의 형식이 잘못되었습니다.";
    private static final String LIST_REGEX = "^[가-힣]+(,[가-힣]+)*$";
    private static final String WRONG_TYPE_OF_CREW_LIST = "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요";

    public static void validMonthAndDayOfWeek(String monthAndDayOfWeek) {
        if (!monthAndDayOfWeek.matches(FORMAT_REGEX)) {
            throw new IllegalArgumentException(WRONG_TYPE_ERROR);
        }
        String[] split = monthAndDayOfWeek.split(",");
        int month = Integer.parseInt(split[0]);
        String startDayOfWeek = split[1];
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(WRONG_TYPE_ERROR);
        }
        if (!DAY_OF_WEEK.contains(startDayOfWeek)) {
            throw new IllegalArgumentException(WRONG_TYPE_ERROR);
        }
    }

    public static void validWeekdayCrew(String crews) {
        if (!crews.matches(LIST_REGEX)) {
            throw new IllegalArgumentException(WRONG_TYPE_OF_CREW_LIST);
        }
    }
}
