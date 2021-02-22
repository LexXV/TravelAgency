package edu.khai.voloshyn.travelagency.entity;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {
    private int orderId;
    private User user;
    private Tour tour;
    private int number;
    private double price;
    private OrderStatus orderStatus;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderId() == order.getOrderId() &&
                getNumber() == order.getNumber() &&
                Double.compare(order.getPrice(), getPrice()) == 0 &&
                Objects.equals(getUser(), order.getUser()) &&
                Objects.equals(getTour(), order.getTour()) &&
                getOrderStatus() == order.getOrderStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getUser(), getTour(), getNumber(), getPrice(), getOrderStatus());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", tour=" + tour +
                ", number=" + number +
                ", price=" + price +
                ", orderStatus=" + orderStatus +
                '}';
    }
}