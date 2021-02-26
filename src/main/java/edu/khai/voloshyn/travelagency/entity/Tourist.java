package edu.khai.voloshyn.travelagency.entity;

import java.io.Serializable;
import java.util.Objects;

public class Tourist implements Serializable {
    private int touristId;
    private String tourist;

    public int getTouristId() {
        return touristId;
    }

    public void setTouristId(int touristId) {
        this.touristId = touristId;
    }

    public String getTourist() {
        return tourist;
    }

    public void setTourist(String tourist) {
        this.tourist = tourist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tourist)) return false;
        Tourist tourist1 = (Tourist) o;
        return getTouristId() == tourist1.getTouristId() &&
                Objects.equals(getTourist(), tourist1.getTourist());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTouristId(), getTourist());
    }

    @Override
    public String toString() {
        return "{" +
                "touristId=" + touristId +
                ", tourist='" + tourist + '\'' +
                '}';
    }
}
