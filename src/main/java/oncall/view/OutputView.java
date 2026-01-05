package oncall.view;

import oncall.domain.onCall.MonthlyOnCalls;

public class OutputView {

    private static final String ON_CALL_RESULT = "%d월 %d일 %s%s %s%n";

    public static void printMonthlyOnCalls(MonthlyOnCalls monthlyOnCalls) {
        monthlyOnCalls.getMonthlyOnCalls()
                .forEach(m -> System.out.printf(ON_CALL_RESULT, m.getMonth(), m.getDay(), m.getDayOfWeek(), (m.getHolidayStatus() != null ? "(" + m.getHolidayStatus() + ")" : ""), m.getCrewName()));
    }
}
