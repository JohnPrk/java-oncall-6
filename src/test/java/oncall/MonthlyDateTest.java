package oncall;

import oncall.domain.date.MonthlyDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MonthlyDateTest {

    @ParameterizedTest
    @CsvSource({"1,월,31", "2,화,28", "3,수,31", "4,목,30", "5,금,31", "6,토,30", "7,일,31", "8,월,31", "9,화,30", "10,수,31", "11,목,30", "12,금,31"})
    public void 객체를_생성할_때_각각의_달의_마지막_날을_확정한다(int month, String startDayOfWeek, int result) {
        Assertions.assertThat(new MonthlyDate(month, startDayOfWeek).getLastDayOfMonth()).isEqualTo(result);
    }
}
