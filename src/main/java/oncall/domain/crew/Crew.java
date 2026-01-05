package oncall.domain.crew;

import java.util.Objects;

public class Crew {

    private static final String WITHIN_FIVE_CHARACTER = "[ERROR] 이름은 5자 이내여야합니다.";
    private final String name;

    public Crew(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException(WITHIN_FIVE_CHARACTER);
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Crew crew = (Crew) o;
        return Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
