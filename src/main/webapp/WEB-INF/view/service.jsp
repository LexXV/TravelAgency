<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <html lang="en">
    <head>
        <title><fmt:message key="service.title"/></title>
        <%@ taglib prefix="ctg" uri="customTags" %>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/mainPage.css">
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/resources/fonts/iconic/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/resources/css/util.css">
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/resources/css/main.css">
    </head>
    <header>
        <c:import url="/WEB-INF/view/header.jsp"/>
    </header>
    <body>
    <div class="limiter">
        <div class="container-login100"
             style="background-image: url('${pageContext.request.contextPath}/resources/img/bg-01.jpg');">
            <form method="get">
                <div class="container-login100-form-btn">
                    <button class="login100-form-btn"
                            name="command" value="redirect">
                        <fmt:message key="service.addNewTour"/>
                        <input type="hidden" name="address"
                               value="ADD_TOUR_PAGE">
                    </button>
                </div>
            </form>
            <div class="container-login100"
                 style="background-image: url('${pageContext.request.contextPath}/resources/img/bg-01.jpg');">
                <div class="wrap-login100">
                    <form method="post"
                          action="${pageContext.request.contextPath}/">
                        <span class="login100-form-title p-b-34 p-t-27"><fmt:message
                                key="service.newCity"/></span>
                        <div class="wrap-input100"
                             data-validate="Enter city">
                            <input class="input100" type="text" name="city"
                                   required
                                   placeholder="<fmt:message key="tour.city"/>">
                            <span class="focus-input100"></span>
                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit"
                                    name="command" value="add_city">
                                OK
                            </button>
                        </div>
                    </form>
                </div>
                <div class="wrap-login100">
                    <form method="post"
                          action="${pageContext.request.contextPath}/">
                        <span class="login100-form-title p-b-34 p-t-27"><fmt:message
                                key="service.newHotel"/></span>
                        <div class="wrap-input100"
                             data-validate="Enter hotel info">
                            <input class="input100" type="text" name="hotel"
                                   required
                                   placeholder="<fmt:message key="tour.hotel"/>">
                            <span class="focus-input100"></span>
                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit"
                                    name="command" value="add_hotel">
                                OK
                            </button>
                        </div>
                    </form>
                </div>
                <div class="wrap-login100">
                    <form method="post"
                          action="${pageContext.request.contextPath}/">
                        <span class="login100-form-title p-b-34 p-t-27"><fmt:message
                                key="service.newTourist"/></span>
                        <div class="wrap-input100"
                             data-validate="Enter tourists number info">
                            <input class="input100" type="text" name="tourist"
                                   required
                                   placeholder="<fmt:message key="tour.tourist"/>">
                            <span class="focus-input100"></span>
                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit"
                                    name="command" value="add_tourist">
                                OK
                            </button>
                        </div>
                    </form>
                </div>
                <div class="wrap-login100">
                    <form method="post"
                          action="${pageContext.request.contextPath}/">
                        <span class="login100-form-title p-b-34 p-t-27"><fmt:message
                                key="service.newTourType"/></span>
                        <div class="wrap-input100"
                             data-validate="Enter type">
                            <input class="input100" type="text" name="tour_type"
                                   required
                                   placeholder="<fmt:message key="tour.tourType"/>">
                            <span class="focus-input100"></span>
                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit"
                                    name="command" value="add_tour_type">
                                OK
                            </button>
                        </div>
                    </form>
                </div>
                <div class="wrap-login100">
                    <form method="post"
                          action="${pageContext.request.contextPath}/">
                        <span class="login100-form-title p-b-34 p-t-27"><fmt:message
                                key="service.deleteCity"/></span>
                        <div class="wrap-input100">
                            <select name="city">
                                <option disabled="disabled"><fmt:message
                                        key="tour.city"/></option>
                                <c:forEach items="${sessionScope.cities}"
                                           var="city">
                                    <option value="${city.cityId}">${city.city}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit"
                                    name="command" value="delete_city">
                                OK
                            </button>
                        </div>
                    </form>
                </div>
                <div class="wrap-login100">
                    <form method="post"
                          action="${pageContext.request.contextPath}/">
                        <span class="login100-form-title p-b-34 p-t-27"><fmt:message
                                key="service.deleteHotel"/></span>
                        <div class="wrap-input100">
                            <select name="hotel">
                                <option disabled="disabled"><fmt:message
                                        key="tour.hotel"/></option>
                                <c:forEach items="${sessionScope.hotels}"
                                           var="hotel">
                                    <option value="${hotel.hotelId}">${hotel.hotel}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit"
                                    name="command" value="delete_hotel">
                                OK
                            </button>
                        </div>
                    </form>
                </div>
                <div class="wrap-login100">
                    <form method="post"
                          action="${pageContext.request.contextPath}/">
                        <span class="login100-form-title p-b-34 p-t-27"><fmt:message
                                key="service.deleteTourist"/></span>
                        <div class="wrap-input100">
                            <select name="tourist">
                                <option disabled="disabled"><fmt:message
                                        key="tour.tourist"/></option>
                                <c:forEach items="${sessionScope.tourists}"
                                           var="tourist">
                                    <option value="${tourist.touristId}">${tourist.tourist}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit"
                                    name="command" value="delete_tourist">
                                OK
                            </button>
                        </div>
                    </form>
                </div>
                <div class="wrap-login100">
                    <form method="post"
                          action="${pageContext.request.contextPath}/">
                        <span class="login100-form-title p-b-34 p-t-27"><fmt:message
                                key="service.deleteTourType"/></span>
                        <div class="wrap-input100">
                            <select name="tour_type">
                                <option disabled="disabled"><fmt:message
                                        key="tour.tourType"/></option>
                                <c:forEach items="${sessionScope.tour_types}"
                                           var="tourType">
                                    <option value="${tourType.tourTypeId}">${tourType.type}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit"
                                    name="command" value="delete_tour_type">
                                OK
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

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
    <footer>
        <div class="copyrights wrapper">
            <ctg:copyrightTag/>
        </div>
    </footer>
    </body>
    </html>
</fmt:bundle>
