package oncall.domain.crew;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Crews {

    private static final String ALREADY_CREW_EXISTS = "[ERROR] 이미 존재하는 근무자입니다.";
    private static final String MINIMUM_FIVE = "[ERROR] 최소인원 5명이 필요합니다.";
    private static final String MAXIMUM_THIRTY_FIVE = "[ERROR] 최대인원 35명을 초과했습니다.";

    private final ArrayDeque<Crew> crewList;

    public Crews(String[] nameList) {
        this.crewList = new ArrayDeque<>();
        Arrays.stream(nameList)
                .forEach(name -> addCrew(new Crew(name)));
        validNotUnderFive();
        validNotUpperThirtyFive();
    }

    public Crew match(Crew priorCrew) {
        Crew crew = null;
        if (peek().equals(priorCrew)) {
            Crew temp = remove();
            crew = pop();
            crewList.addFirst(temp);
        } else {
            crew = pop();
        }
        return crew;
    }

    public Crew peek() {
        return crewList.peek();
    }

    public Crew pop() {
        Crew crew = crewList.pop();
        crewList.addLast(crew);
        return crew;
    }

    private void addCrew(Crew crew) {
        if (crewList.contains(crew)) {
            throw new IllegalArgumentException(ALREADY_CREW_EXISTS);
        }
        crewList.add(crew);
    }

    private void validNotUnderFive() {
        if (crewList.size() < 5) {
            throw new IllegalArgumentException(MINIMUM_FIVE);
        }
    }

    private void validNotUpperThirtyFive() {
        if (crewList.size() > 35) {
            throw new IllegalArgumentException(MAXIMUM_THIRTY_FIVE);
        }
    }

    public ArrayDeque<Crew> getCrewList() {
        return crewList;
    }

    public void addFirst(Crew crew) {
        crewList.addFirst(crew);
    }

    public Crew remove() {
        return crewList.remove();
    }
}
