<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <%@ taglib prefix="ctg" uri="customTags" %>
    <html lang="en">
    <head>
        <title><fmt:message key="updateTour.title"/></title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <meta name="viewport"
              content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet"
              href="${root}/resources/js/chosen_v1.8.7/docsupport/style.css">
        <link rel="stylesheet"
              href="${root}/resources/js/chosen_v1.8.7/docsupport/prism.css">
        <link rel="stylesheet"
              href="${root}/resources/js/chosen_v1.8.7/chosen.css">
        <meta http-equiv="Content-Security-Policy"
              content="default-src &apos;self&apos;; script-src &apos;self&apos; https://ajax.googleapis.com; style-src &apos;self&apos;; img-src &apos;self&apos; data:">
        <link href="${root}/resources/vendor/mdi-font/css/material-design-iconic-font.min.css"
              rel="stylesheet" media="all">
        <link href="${root}/resources/vendor/font-awesome-4.7/css/font-awesome.min.css"
              rel="stylesheet" media="all">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i"
              rel="stylesheet">
        <link href="${root}/resources/vendor/select2/select2.min.css"
              rel="stylesheet" media="all">
        <link href="${root}/resources/vendor/datepicker/daterangepicker.css"
              rel="stylesheet" media="all">
        <link href="${root}/resources/css/addTour.css" rel="stylesheet"
              media="all">
    </head>

    <header>
        <c:import url="/WEB-INF/view/header.jsp"/>
    </header>

    <body>
    <div class="page-wrapper bg-blue p-t-100 p-b-100 font-robo">
        <div class="wrapper wrapper--w680">
            <div class="card card-1">
                <div class="card-heading"></div>
                <div class="card-body">
                    <h2 class="title"><fmt:message key="updateTour.title"/></h2>
                    <form method="post"
                          action="${pageContext.request.contextPath}/"
                          enctype="multipart/form-data">
                        <div class="input-group">
                            <h4><fmt:message
                                    key="updateTour.previousCost"/> ${sessionScope.tour.cost}</h4>
                            <input class="input--style-1" type="text"
                                   name="cost"
                                   pattern="^(\d*\.)?\d+$" required
                                   placeholder=<fmt:message
                                    key="updateTour.newCost"/>>
                        </div>
                        <div class="input-group">
                            <h4><fmt:message
                                    key="updateTour.previousDays"/> ${sessionScope.tour.days}</h4>
                            <input class="input--style-1" type="text"
                                   name="days"
                                   pattern="^[0-9]+$" required
                                   placeholder=<fmt:message
                                    key="updateTour.newDays"/>>
                        </div>
                        <div class="input-group">
                            <h4><fmt:message
                                    key="updateTour.previousPlaces"/> ${sessionScope.tour.places}</h4>
                            <input class="input--style-1" type="text"
                                   name="places"
                                   pattern="^[0-9]+$"
                                   required
                                   placeholder=<fmt:message
                                    key="updateTour.newPlaces"/>>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <h4><fmt:message
                                            key="updateTour.previousTourType"/> ${sessionScope.tour.tourType.type}</h4>
                                    <div class="rs-select2 js-select-simple select--no-search">
                                        <select name="tour_type">
                                            <option disabled="disabled">
                                                <fmt:message
                                                        key="updateTour.newTourType"/>
                                            </option>
                                            <c:forEach
                                                    items="${sessionScope.tour_types}"
                                                    var="tourType">
                                                <option value="${tourType.tourTypeId}">${tourType.type}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="select-dropdown"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <h4><fmt:message
                                            key="updateTour.previousTransport"/> ${sessionScope.tour.transport.type}</h4>
                                    <div class="rs-select2 js-select-simple select--no-search">
                                        <select name="tour_transport">
                                            <option disabled="disabled">
                                                <fmt:message
                                                        key="updateTour.newTransport"/>
                                            </option>
                                            <c:forEach
                                                    items="${sessionScope.transports}"
                                                    var="transport">
                                                <option value="${transport.transportId}">${transport.type}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="select-dropdown"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <h4><fmt:message
                                            key="updateTour.previousTourDiscount"/> ${sessionScope.tour.discount.discountSize}</h4>
                                    <div class="rs-select2 js-select-simple select--no-search">
                                        <select name="tour_discount_size">
                                            <option disabled="disabled">
                                                <fmt:message
                                                        key="updateTour.newTourDiscount"/>
                                            </option>
                                            <c:forEach
                                                    items="${sessionScope.tour_discounts}"
                                                    var="discount">
                                                <option value="${discount.id}">${discount.discountSize}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="select-dropdown"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <h4><fmt:message
                                            key="updateTour.previousToCity"/> ${sessionScope.tour.city.city}</h4>
                                    <div class="rs-select2 js-select-simple select--no-search">
                                        <select name="to_city">
                                            <option disabled="disabled">
                                                <fmt:message
                                                        key="updateTour.newToCity"/>
                                            </option>
                                            <c:forEach
                                                    items="${sessionScope.cities}"
                                                    var="city">
                                                <option value="${city.cityId}">${city.city}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="select-dropdown"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <h4><fmt:message
                                            key="updateTour.previousDepartureCity"/> ${sessionScope.tour.departureCity.city}</h4>
                                    <div class="rs-select2 js-select-simple select--no-search">
                                        <select name="departure_city">
                                            <option disabled="disabled">
                                                <fmt:message
                                                        key="updateTour.newDepartureCity"/>
                                            </option>
                                            <c:forEach
                                                    items="${sessionScope.cities}"
                                                    var="city">
                                                <option value="${city.cityId}">${city.city}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="select-dropdown"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <h4><fmt:message
                                            key="updateTour.previousToHotel"/> ${sessionScope.tour.hotel.hotel}</h4>
                                    <div class="rs-select2 js-select-simple select--no-search">
                                        <select name="to_hotel">
                                            <option disabled="disabled">
                                                <fmt:message
                                                        key="updateTour.newToHotel"/>
                                            </option>
                                            <c:forEach
                                                    items="${sessionScope.hotels}"
                                                    var="hotel">
                                                <option value="${hotel.hotelId}">${hotel.hotel}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="select-dropdown"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <h4><fmt:message
                                            key="updateTour.previousToTourist"/> ${sessionScope.tour.tourist.tourist}</h4>
                                    <div class="rs-select2 js-select-simple select--no-search">
                                        <select name="to_tourist">
                                            <option disabled="disabled">
                                                <fmt:message
                                                        key="updateTour.newToTourist"/>
                                            </option>
                                            <c:forEach
                                                    items="${sessionScope.tourists}"
                                                    var="tourist">
                                                <option value="${tourist.touristId}">${tourist.tourist}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="select-dropdown"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <h4><fmt:message
                                    key="updateTour.previousDate"/> ${sessionScope.tour.departureDate}</h4>
                            <input class="input--style-1"
                                   type="date"
                                   name="departure_date" required
                                   placeholder=<fmt:message
                                    key="updateTour.newDate"/>>
                        </div>
                        <button type="submit" class="btn btn-info"
                                aria-label="Update"
                                name="command" value="update_tour"><fmt:message
                                key="button.submit"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="${root}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${root}/resources/vendor/select2/select2.min.js"></script>
    <script src="${root}/resources/vendor/datepicker/moment.min.js"></script>
    <script src="${root}/resources/vendor/datepicker/daterangepicker.js"></script>
    <script src="${root}/resources/js/chosen_v1.8.7/docsupport/jquery-3.2.1.min.js"
            type="text/javascript"></script>
    <script src="${root}/resources/js/chosen_v1.8.7/chosen.jquery.js"
            type="text/javascript"></script>
    <script src="${root}/resources/js/chosen_v1.8.7/docsupport/prism.js"
            type="text/javascript" charset="utf-8"></script>
    <script src="${root}/resources/js/chosen_v1.8.7/docsupport/init.js"
            type="text/javascript" charset="utf-8"></script>
    <script src="${root}/resources/js/global.js"></script>

    <div id="dropDownSelect1"></div>
    </body>
    <footer>
        <div class="copyrights wrapper">
            <ctg:copyrightTag/>
        </div>
    </footer>
    </html>
</fmt:bundle>

