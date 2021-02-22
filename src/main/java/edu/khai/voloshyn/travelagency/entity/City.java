package edu.khai.voloshyn.travelagency.entity;

import java.io.Serializable;
import java.util.Objects;

public class City implements Serializable {
    private int cityId;
    private String city;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city1 = (City) o;
        return getCityId() == city1.getCityId() &&
                Objects.equals(getCity(), city1.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCityId(), getCity());
    }

    @Override
    public String toString() {
        return "{" +
                "cityId=" + cityId +
                ", city='" + city + '\'' +
                '}';
    }
}
