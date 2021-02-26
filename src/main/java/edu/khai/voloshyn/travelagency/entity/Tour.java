package edu.khai.voloshyn.travelagency.entity;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Tour implements Serializable {
    private int tourId;
    private String name;
    private double cost;
    private Date departureDate;
    private int days;
    private int places;
    private TourType tourType;
    private City city;
    private City departureCity;
    private Hotel hotel;
    private Tourist tourist;
    private TourStatus tourStatus;
    private Transport transport;
    private TourDiscount discount;

    public Tour() {
    	discount = new TourDiscount();
    }

    public Tour(String name, double cost, Date departureDate,
                int days, int places, TourType tourType, City city,
                City departureCity, Hotel hotel, Tourist tourist, TourStatus tourStatus, Transport transport) {
        this.name = name;
        this.cost = cost;
        this.departureDate = departureDate;
        this.days = days;
        this.places = places;
        this.tourType = tourType;
        this.city = city;
        this.departureCity = departureCity;
        this.hotel = hotel;
        this.tourist = tourist;
        this.tourStatus = tourStatus;
        this.transport = transport;
        this.discount = new TourDiscount();
    }

    public Tour(int tourId, String name, Date departureDate) {
        this.tourId = tourId;
        this.name = name;
        this.departureDate = departureDate;
        discount = new TourDiscount();
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public Hotel getHotel() {
        return hotel;
    }
    
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
    public Tourist getTourist() {
        return tourist;
    }
    
    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }
    
    public TourStatus getTourStatus() {
        return tourStatus;
    }

    public void setTourStatus(TourStatus tourStatus) {
        this.tourStatus = tourStatus;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
    
    public TourDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(TourDiscount discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour)) return false;
        Tour tour = (Tour) o;
        return getTourId() == tour.getTourId() &&
                Double.compare(tour.getCost(), getCost()) == 0 &&
                getDays() == tour.getDays() &&
                getPlaces() == tour.getPlaces() &&
                Objects.equals(getName(), tour.getName()) &&
                Objects.equals(getDepartureDate(), tour.getDepartureDate()) &&
                Objects.equals(getTourType(), tour.getTourType()) &&
                Objects.equals(getCity(), tour.getCity()) &&
                Objects.equals(getDepartureCity(), tour.getDepartureCity()) &&
                Objects.equals(getHotel(), tour.getHotel()) &&
                Objects.equals(getTourist(), tour.getTourist()) &&
                Objects.equals(getTransport(), tour.getTransport()) &&
                Objects.equals(getDiscount(), tour.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTourId(), getName(), getCost(),
                getDepartureDate(), getDays(), getPlaces(), getTourType(),
                getCity(), getDepartureCity(), getHotel(), getTourist(), getTransport(), getDiscount());
    }

    @Override
    public String toString() {
        return "Tour{" +
                "tourId=" + tourId +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", departureDate=" + departureDate +
                ", days=" + days +
                ", places=" + places +
                ", tourType=" + tourType +
                ", city=" + city +
                ", departureCity=" + departureCity +
                ", hotel=" + hotel +
                ", tourist=" + tourist +
                ", tourStatus=" + tourStatus +
                ", transport=" + transport +
                ", discount=" + discount.getDiscountSize() + '\'' +
                "}\n";
    }
}
