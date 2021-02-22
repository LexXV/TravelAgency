<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <html lang="en">
    <head>
        <title><fmt:message key="search.title"/></title>
        <meta charset="utf-8">
        <%@ taglib prefix="ctg" uri="customTags" %>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Travello template project">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/bootstrap4/bootstrap.min.css">
        <link href="${root}/resources/plugins/font-awesome-4.7.0/css/font-awesome.min.css"
              rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/news.css">
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/news_responsive.css">
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/mainPage.css">
        <link rel="stylesheet"
              href="${root}/resources/js/chosen_v1.8.7/docsupport/style.css">
        <link rel="stylesheet"
              href="${root}/resources/js/chosen_v1.8.7/docsupport/prism.css">
        <link rel="stylesheet"
              href="${root}/resources/js/chosen_v1.8.7/chosen.css">
    </head>
    <body>
    <div class="super_container">
        <c:import url="/WEB-INF/view/header.jsp"/>
        <div class="home">
            <div class="background_image"
                 style="background-image:url(${root}/resources/img/news.jpg)"></div>
        </div>
        <div class="home_search">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="home_search_container">
                            <div class="home_search_title"><fmt:message
                                    key="search.text"/>
                            </div>
                            <div class="home_search_content">
                                <form method="get">
                                    <div class="d-flex flex-lg-row flex-column align-items-start justify-content-lg-between justify-content-start">
                                        <div class="input-group">
                                            <div class="rs-select2 js-select-simple select--no-search">
                                                <select name="to_city"
                                                        style="height: 52px">
                                                    <option disabled="disabled">
                                                        <fmt:message
                                                                key="tour.toCity"/>
                                                    </option>
                                                    <c:forEach
                                                            items="${sessionScope.cities}"
                                                            var="city">
                                                        <option value="${city.cityId}">${city.city}</option>
                                                    </c:forEach>
                                                </select>
                                                <div class="select-dropdown"></div>
                                            </div>
                                            <div class="rs-select2 js-select-simple select--no-search">
                                                <select name="to_hotel"
                                                        style="height: 52px">
                                                    <option disabled="disabled">
                                                        <fmt:message
                                                                key="tour.toHotel"/>
                                                    </option>
                                                    <c:forEach
                                                            items="${sessionScope.hotels}"
                                                            var="hotel">
                                                        <option value="${hotel.hotelId}">${hotel.hotel}</option>
                                                    </c:forEach>
                                                </select>
                                                <div class="select-dropdown"></div>
                                            </div>
                                            <input type="date"
                                                   class="search_input search_input_2"
                                                   required
                                                   placeholder="Departure"
                                                   name="departure_date">
                                            <input type="text"
                                                   class="search_input search_input_3"
                                                   name="days"
                                                   required
                                                   placeholder=<fmt:message
                                                    key="search.numberOfDays"/>>
                                            <input type="text"
                                                   class="search_input search_input_4"
                                                   name="cost"
                                                   placeholder=<fmt:message
                                                    key="search.budget"/>>
                                            <button class="home_search_button"
                                                    type="submit" name="command"
                                                    value="SEARCH_TOURS">
                                                <fmt:message
                                                        key="button.search"/>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="${root}/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${root}/resources/css/bootstrap4/popper.js"></script>
    <script src="${root}/resources/css/bootstrap4/bootstrap.min.js"></script>
    <script src="${root}/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
    <script src="${root}/resources/plugins/easing/easing.js"></script>
    <script src="${root}/resources/plugins/parallax-js-master/parallax.min.js"></script>
    <script src="${root}/resources/js/news.js"></script>
    <script src="${root}/resources/js/chosen_v1.8.7/docsupport/jquery-3.2.1.min.js"
            type="text/javascript"></script>
    <script src="${root}/resources/js/chosen_v1.8.7/chosen.jquery.js"
            type="text/javascript"></script>
    <script src="${root}/resources/js/chosen_v1.8.7/docsupport/prism.js"
            type="text/javascript" charset="utf-8"></script>
    <script src="${root}/resources/js/chosen_v1.8.7/docsupport/init.js"
            type="text/javascript" charset="utf-8"></script>
    <script src="${root}/resources/js/global.js"></script>
    </body>
    <footer>
        <div class="copyrights wrapper">
            <ctg:copyrightTag/>
        </div>
    </footer>
    </html>
</fmt:bundle>