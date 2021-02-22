package edu.khai.voloshyn.travelagency.dao.constants;

public enum SqlColumn {
    USER_ID,
    USER_LOGIN,
    USER_PASSWORD,
    USER_NAME,
    USER_SURNAME,
    USER_DISCOUNT_ID,
    USER_DISCOUNT_SIZE,
    USER_CASH,
    USER_PHONE,
    USER_ROLE_ID,
    USER_ROLE,
    USER_SUM,
    TOUR_ID,
    TOUR_NAME,
    TOUR_COST,
    TOUR_DEPARTURE_DATE,
    TOUR_DAYS,
    TOUR_PLACES,
    TOUR_TYPE_ID,
    TOUR_CITY_ID,
    TOUR_DEPARTURE_CITY_ID,
    TOUR_HOTEL_ID,
    TOUR_DISCOUNT_ID,
    TOUR_TRANSPORT_ID,
    CITY,
    CITY_ID,
    HOTEL,
    HOTEL_ID,
    TOUR_TYPE,
    TRANSPORT,
    TRANSPORT_ID,
    TOUR_STATUS,
    TOUR_STATUS_ID,
    ORDER_ID,
    ORDER_STATUS,
    ORDER_STATUS_ID,
    TOUR_NUMBER,
    PRICE;

    @Override
    public String toString() {
        return super.toString();
    }
}
