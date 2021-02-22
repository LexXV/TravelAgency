package edu.khai.voloshyn.travelagency.entity;

import java.io.Serializable;
import java.util.Objects;

public class Transport implements Serializable {
    private int transportId;
    private String type;

    public Transport() {
    }

    public Transport(int transportId, String type) {
        this.transportId = transportId;
        this.type = type;
    }

    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transport)) return false;
        Transport transport = (Transport) o;
        return getTransportId() == transport.getTransportId() &&
                Objects.equals(getType(), transport.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransportId(), getType());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "transportId=" + transportId +
                ", type='" + type + '\'' +
                '}';
    }
}
