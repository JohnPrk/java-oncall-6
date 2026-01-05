package oncall;

import oncall.domain.crew.Crew;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CrewTest {

    @ParameterizedTest
    @ValueSource(strings = {"준팍", "도밥", "고니", "수아", "루루", "글로"})
    public void 다섯_글자_이내로_이름을_넣으면_크루원_객체를_만들_수_있다(String name) {
        Assertions.assertThatCode(() -> new Crew(name))
                .doesNotThrowAnyException();
    }

    @Test
    public void 다섯_글자_초과로_이름을_넣으면_예외가_발생한다() {
        Assertions.assertThatCode(() -> new Crew("아기맹수시현"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 5자 이내여야합니다.");
    }
}
