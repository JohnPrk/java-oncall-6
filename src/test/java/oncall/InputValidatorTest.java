package oncall;

import oncall.view.InputValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"1,월", "2,화", "3,수", "4,목", "5,금", "6,토", "7,일", "8,월", "9,화", "10,수", "11,목", "12,금"})
    public void 달과_요일의_형식을_맞게_넣을_경우에는_예외가_발생되지_않는다(String monthAndStartDayOfWeek) {
        Assertions.assertThatCode(() -> {
            InputValidator.validMonthAndDayOfWeek(monthAndStartDayOfWeek);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"13,월", "0,화", "3,하", "4,티"})
    public void 달과_요일의_형식이_맞지_않을_경우에는_예외가_발생된다(String monthAndStartDayOfWeek) {
        Assertions.assertThatThrownBy(() -> {
                    InputValidator.validMonthAndDayOfWeek(monthAndStartDayOfWeek);
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력하신 달(월), 요일의 형식이 잘못되었습니다.");
    }
}
