package edu.khai.voloshyn.travelagency.entity;

import java.io.Serializable;
import java.util.Objects;

public class TourDiscount implements Serializable {
    private int id;
    private double discountSize;

    public TourDiscount() {
    }

    public TourDiscount(int id, double discountSize) {
        this.id = id;
        this.discountSize = discountSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscountSize() {
        return discountSize;
    }

    public void setDiscountSize(double discountSize) {
        this.discountSize = discountSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TourDiscount)) return false;
        TourDiscount that = (TourDiscount) o;
        return getId() == that.getId() &&
                Double.compare(that.getDiscountSize(), getDiscountSize()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDiscountSize());
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountID=" + id +
                ", discountSize=" + discountSize +
                '}';
    }
}
