package oncall.domain.onCall;

import java.util.ArrayList;
import java.util.List;

public class MonthlyOnCalls {

    private final List<MonthlyOnCall> monthlyOnCalls;

    public MonthlyOnCalls() {
        this.monthlyOnCalls = new ArrayList<>();
    }

    public void addMonthlyOnCall(MonthlyOnCall monthlyOnCall) {
        monthlyOnCalls.add(monthlyOnCall);
    }

    public List<MonthlyOnCall> getMonthlyOnCalls() {
        return monthlyOnCalls;
    }
}
