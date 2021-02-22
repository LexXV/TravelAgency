package edu.khai.voloshyn.travelagency.entity;

import java.io.Serializable;
import java.util.Objects;

public class Hotel implements Serializable {
    private int hotelId;
    private String hotel;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        Hotel hotel1 = (Hotel) o;
        return getHotelId() == hotel1.getHotelId() &&
                Objects.equals(getHotel(), hotel1.getHotel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHotelId(), getHotel());
    }

    @Override
    public String toString() {
        return "{" +
                "hotelId=" + hotelId +
                ", hotel='" + hotel + '\'' +
                '}';
    }
}
