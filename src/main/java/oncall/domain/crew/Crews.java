package oncall.domain.crew;

import java.util.ArrayList;
import java.util.List;

public class Crews {

    private final List<Crew> crewList;

    public Crews() {
        this.crewList = new ArrayList<>();
    }

    public void addCrew(Crew crew) {
        if (crewList.contains(crew)) {
            throw new IllegalArgumentException("[ERROR] 이미 존재하는 근무자입니다.");
        }
        crewList.add(crew);
    }
}
