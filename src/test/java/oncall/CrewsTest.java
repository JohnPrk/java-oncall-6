package oncall;

import oncall.domain.crew.Crews;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CrewsTest {

    @Test
    public void 근무자의_이름으로_크루원_명단_객체를_만들_수_있다() {
        String crewList = "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션";
        String[] nameList = crewList.split(",");
        Crews crews = new Crews(nameList);
        Assertions.assertThat(crews.getCrewList().size()).isEqualTo(nameList.length);
    }

    @Test
    public void 근무자의_이름이_중복될_경우_예외가_발생한다() {
        String crewList = "허브,쥬니,허브,말랑,라온,헤나";
        String[] nameList = crewList.split(",");
        Assertions.assertThatThrownBy(() -> new Crews(nameList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이미 존재하는 근무자입니다.");

    }

    @Test
    public void 근무자의_수가_최소_인원보다_작을_경우_예외가_발생한다() {
        String crewList = "허브,쥬니,말랑,라온";
        String[] nameList = crewList.split(",");
        Assertions.assertThatThrownBy(() -> new Crews(nameList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 최소인원 5명이 필요합니다.");
    }

    @Test
    public void 근무자의_수가_최대_인원보다_많을_경우_예외가_발생한다() {
        String crewList = "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션,민욱";
        String[] nameList = crewList.split(",");
        Assertions.assertThatThrownBy(() -> new Crews(nameList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 최대인원 35명을 초과했습니다.");
    }
}
