<!DOCTYPE html>
<meta charset="utf-8">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customTags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <html lang="en">
    <head>
        <title><fmt:message key="tours.title"/></title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <meta name="viewport"
              content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900"
              rel="stylesheet">
        <link rel="stylesheet"
              href="${root}/resources/css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="${root}/resources/css/animate.css">
        <link rel="stylesheet"
              href="${root}/resources/css/owl.carousel.min.css">
        <link rel="stylesheet"
              href="${root}/resources/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="${root}/resources/css/magnific-popup.css">
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/table.css">
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/mainPage.css">
        <link rel="stylesheet" href="${root}/resources/css/aos.css">
        <link rel="stylesheet" href="${root}/resources/css/ionicons.min.css">
        <link rel="stylesheet"
              href="${root}/resources/css/bootstrap-datepicker.css">
        <link rel="stylesheet"
              href="${root}/resources/css/jquery.timepicker.css">
        <link rel="stylesheet" href="${root}/resources/css/flaticon.css">
        <link rel="stylesheet" href="${root}/resources/css/icomoon.css">
        <link rel="stylesheet" href="${root}/resources/css/style.css">
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/navigation.css">
    </head>
    <header>
        <c:import url="/WEB-INF/view/header.jsp"/>
    </header>
    <body>
    <jsp:useBean id="cities" scope="session"
    	type="java.util.List"/>
    <jsp:useBean id="hotels" scope="session"
        type="java.util.List"/>
    <jsp:useBean id="tour_types" scope="session"
        type="java.util.List"/>
        <c:if test="${sessionScope.role =='CLIENT'}">
        	<div class="nanvnav">
            	<nav>
                	<ul class="dropdown">
                    	<li class="drop"><a><fmt:message key="tour.cities"/></a>
                        	<ul class="sub_menu">
                            	<li>
                                	<c:forEach items="${cities}" var="city">
                                    	<form method="get">
                                        	<button class="btn btn-outline-info btn-lg btn-block"
                                                	aria-label="Cities"
                                                	type="submit"
                                                	name="command"
                                                	value="GET_BY_CITY"><input
                                                	type="hidden"
                                                	name="city_id"
                                                	value="${city.cityId}"/>
                                                	${city.city}
                                        	</button>
                                    	</form>
                                	</c:forEach>
                            	</li>
                        	</ul>
                    	</li>
                    	<li class="drop"><a><fmt:message key="tour.hotels"/></a>
                        	<ul class="sub_menu">
                            	<li>
                                	<c:forEach items="${hotels}" var="hotel">
                                    	<form method="get">
                                        	<button class="btn btn-outline-info btn-lg btn-block"
                                                	aria-label="Hotels"
                                                	type="submit"
                                                	name="command"
                                                	value="GET_BY_HOTEL"><input
                                                	type="hidden"
                                                	name="hotel_id"
                                                	value="${hotel.hotelId}"/>
                                                	${hotel.hotel}
                                        	</button>
                                    	</form>
                                	</c:forEach>
                            	</li>
                        	</ul>
                    	</li>
                    	<li class="drop"><a><fmt:message key="tour.types"/></a>
                        	<ul class="sub_menu">
                            	<li>
                                	<c:forEach items="${tour_types}" var="tourType">
                                    	<form method="get">
                                        	<button class="btn btn-outline-info btn-lg btn-block"
                                                	aria-label="TourType"
                                                	type="submit"
                                                	name="command"
                                                	value="GET_BY_TOUR_TYPE"><input
                                                	type="hidden"
                                                	name="tour_type_id"
                                                	value="${tourType.tourTypeId}"/>
                                                	${tourType.type}
                                        	</button>
                                    	</form>
                                	</c:forEach>
                            	</li>
                        	</ul>
                    	</li>
                	</ul>
                	<form method="get">
                    	<button class="btn btn-info btn-lg"
                            	aria-label="Hot"
                            	type="submit"
                            	name="command"
                            	value="GET_HOT_TOURS"><fmt:message key="tour.hot"/>
                    	</button>
                	</form>
            	</nav>
        	</div>
    	</c:if>
    	<c:if test="${sessionScope.role =='GUEST'}">
        	<div class="nanvnav">
            	<nav>
                	<ul class="dropdown">
                    	<li class="drop"><a><fmt:message key="tour.cities"/></a>
                        	<ul class="sub_menu">
                            	<li>
                                	<c:forEach items="${cities}" var="city">
                                    	<form method="get">
                                        	<button class="btn btn-outline-info btn-lg btn-block"
                                                	aria-label="Cities"
                                                	type="submit"
                                                	name="command"
                                                	value="GET_BY_CITY"><input
                                                	type="hidden"
                                                	name="city_id"
                                                	value="${city.cityId}"/>
                                                	${city.city}
                                        	</button>
                                    	</form>
                                	</c:forEach>
                            	</li>
                        	</ul>
                    	</li>
                    	<li class="drop"><a><fmt:message key="tour.hotels"/></a>
                        	<ul class="sub_menu">
                            	<li>
                                	<c:forEach items="${hotels}" var="hotel">
                                    	<form method="get">
                                        	<button class="btn btn-outline-info btn-lg btn-block"
                                                	aria-label="Hotels"
                                                	type="submit"
                                                	name="command"
                                                	value="GET_BY_HOTEL"><input
                                                	type="hidden"
                                                	name="hotel_id"
                                                	value="${hotel.hotelId}"/>
                                                	${hotel.hotel}
                                        	</button>
                                    	</form>
                                	</c:forEach>
                            	</li>
                        	</ul>
                    	</li>
                    	<li class="drop"><a><fmt:message key="tour.types"/></a>
                        	<ul class="sub_menu">
                            	<li>
                                	<c:forEach items="${tour_types}" var="tourType">
                                    	<form method="get">
                                        	<button class="btn btn-outline-info btn-lg btn-block"
                                                	aria-label="TourType"
                                                	type="submit"
                                                	name="command"
                                                	value="GET_BY_TOUR_TYPE"><input
                                                	type="hidden"
                                                	name="tour_type_id"
                                                	value="${tourType.tourTypeId}"/>
                                                	${tourType.type}
                                        	</button>
                                    	</form>
                                	</c:forEach>
                            	</li>
                        	</ul>
                    	</li>
                	</ul>
                	<form method="get">
                    	<button class="btn btn-info btn-lg"
                            	aria-label="Hot"
                            	type="submit"
                            	name="command"
                            	value="GET_HOT_TOURS"><fmt:message key="tour.hot"/>
                    	</button>
                	</form>
            	</nav>
        	</div>
    	</c:if>
    <jsp:useBean id="tours" class="java.util.ArrayList"
                 scope="session"/>
    <c:if test="${empty tours}">
        <section class="hero">
            <h1 class="caption"><fmt:message key="tour.no"/></h1>
        </section>
    </c:if>
    <c:if test="${not empty tours}">
        <section class="ftco-section">
            <div class="container">
                <div class="row">
                    <c:forEach items="${tours}" var="tour">
                        <c:if test="${sessionScope.role =='ADMIN'}">
                            <div class="col-md-6 col-lg-4 ftco-animate">
                                <div class="project">
                                    <div class="text">
                                        <h4 class="price"><c:out
                                                value="${tour.cost}$"/></h4>
                                        <span><c:out
                                                value="${tour.days}"/> <fmt:message
                                                key="tour.days"/></span>
                                        <h3><c:out
                                                value="${tour.name} to ${tour.city.city}"/>
                                        </h3>
                                        <div class="star d-flex clearfix">
                                            <div class="mr-auto float-left">
                                                <span class="rate">${tour.hotel.hotel}</span>
                                            </div>
                                        </div>
                                        <div class="star d-flex clearfix">
                                            <div class="mr-auto float-left">
                                                <span class="rate">${tour.departureDate}</span>
                                            </div>
                                        </div>
                                        <div class="star d-flex clearfix">
                                            <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="${tour.tourType.type}"/></span>
                                            </div>
                                        </div>
                                        <div class="star d-flex clearfix">
                                            <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="${tour.transport.type}"/></span>
                                            </div>
                                        </div>
                                        <div class="star d-flex clearfix">
                                            <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="from ${tour.departureCity.city}"/></span>
                                            </div>
                                        </div>
                                        <div style="padding-top: 5px">
                                            <form method="get">
                                                <c:if test="${tour.tourStatus=='HOT'}">
                                                    <button class="btn btn-info"
                                                            aria-label="Hot"
                                                            type="submit"
                                                            name="command"
                                                            value="un_hot_tour">
                                                        <input type="hidden"
                                                               name="tour_id"
                                                               value="${tour.tourId}"/>
                                                        <fmt:message
                                                                key="tour.doNotHot"/>
                                                    </button>
                                                </c:if>
                                                <c:if test="${tour.tourStatus!='HOT'}">
                                                    <c:if test="${tour.tourStatus!='ARCHIVAL'}">
                                                        <button class="btn btn-info"
                                                                aria-label="UnHot"
                                                                type="submit"
                                                                name="command"
                                                                value="set_hot_tour">
                                                            <input type="hidden"
                                                                   name="tour_id"
                                                                   value="${tour.tourId}"/>
                                                            <fmt:message
                                                                    key="tour.doHot"/>
                                                        </button>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${tour.tourStatus=='ARCHIVAL'}">
                                                    <button type="button"
                                                            class="btn btn-lg btn-primary"
                                                            disabled>
                                                        <fmt:message
                                                                key="tour.archival"/>
                                                    </button>
                                                </c:if>
                                                <button class="btn btn-danger"
                                                       	type="submit"
                                                       	name="command"
                                                       	value="delete_tour">
                                                   	<input type="hidden"
                                                           name="tour_id"
                                                           value="${tour.tourId}"/>
                                                   	<i class="fa fa-trash-o fa-lg"></i>
                                                   	<fmt:message
                                                           key="tour.delete"/>
                                                </button>
                                                <c:if test="${tour.tourStatus!='ARCHIVAL'}">
                                                   	<button class="btn btn-default btn-sm"
                                                           	type="submit"
                                                           	name="command"
                                                           	value="redirect_to_update_tour_page">
                                                       	<input type="hidden"
                                                               name="tour_id"
                                                               value="${tour.tourId}"/>
                                                       	<i class="fa fa-cog"></i>
                                                       	<fmt:message
                                                               key="tour.update"/>
                                                   	</button>
                                                </c:if>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.role =='MANAGER'}">
                            <div class="col-md-6 col-lg-4 ftco-animate">
                                <div class="project">
                                    <div class="text">
                                        <h4 class="price"><c:out
                                                value="${tour.cost}$"/></h4>
                                        <span><c:out
                                                value="${tour.days}"/> <fmt:message
                                                key="tour.days"/></span>
                                        <h3><c:out
                                                value="${tour.name} to ${tour.city.city}"/>
                                        </h3>
                                        <div class="star d-flex clearfix">
                                            <div class="mr-auto float-left">
                                                <span class="rate">${tour.hotel.hotel}</span>
                                            </div>
                                        </div>
                                        <div class="star d-flex clearfix">
                                            <div class="mr-auto float-left">
                                                <span class="rate">${tour.departureDate}</span>
                                            </div>
                                        </div>
                                        <div class="star d-flex clearfix">
                                            <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="${tour.tourType.type}"/></span>
                                            </div>
                                        </div>
                                        <div class="star d-flex clearfix">
                                            <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="${tour.transport.type}"/></span>
                                            </div>
                                        </div>
                                        <div class="star d-flex clearfix">
                                            <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="from ${tour.departureCity.city}"/></span>
                                            </div>
                                        </div>
                                        <div style="padding-top: 5px">
                                            <form method="get">
                                                <c:if test="${tour.tourStatus=='HOT'}">
                                                    <button class="btn btn-info"
                                                            aria-label="Hot"
                                                            type="submit"
                                                            name="command"
                                                            value="un_hot_tour">
                                                        <input type="hidden"
                                                               name="tour_id"
                                                               value="${tour.tourId}"/>
                                                        <fmt:message
                                                                key="tour.doNotHot"/>
                                                    </button>
                                                </c:if>
                                                <c:if test="${tour.tourStatus!='HOT'}">
                                                    <c:if test="${tour.tourStatus!='ARCHIVAL'}">
                                                        <button class="btn btn-info"
                                                                aria-label="UnHot"
                                                                type="submit"
                                                                name="command"
                                                                value="set_hot_tour">
                                                            <input type="hidden"
                                                                   name="tour_id"
                                                                   value="${tour.tourId}"/>
                                                            <fmt:message
                                                                    key="tour.doHot"/>
                                                        </button>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${tour.tourStatus=='ARCHIVAL'}">
                                                    <button type="button"
                                                            class="btn btn-lg btn-primary"
                                                            disabled>
                                                        <fmt:message
                                                                key="tour.archival"/>
                                                    </button>
                                                </c:if>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.role =='CLIENT'}">
                            <c:if test="${tour.tourStatus!='ARCHIVAL' && tour.places!=0}">
                                <div class="col-md-6 col-lg-4 ftco-animate">
                                    <div class="project">
                                        <div class="text">
                                            <h4 class="price"><c:out
                                                    value="${tour.cost}$"/></h4>
                                            <span><c:out
                                                    value="${tour.days}"/> <fmt:message
                                                    key="tour.days"/></span>
                                            <h3><c:out
                                                    value="${tour.name} to ${tour.city.city}"/>
                                            </h3>
                                            <div class="star d-flex clearfix">
                                                <div class="mr-auto float-left">
                                                    <span class="rate">${tour.hotel.hotel}</span>
                                                </div>
                                            </div>
                                            <div class="star d-flex clearfix">
                                                <div class="mr-auto float-left">
                                                    <span class="rate">${tour.departureDate}</span>
                                                </div>
                                            </div>
                                            <div class="star d-flex clearfix">
                                                <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="${tour.tourType.type}"/></span>
                                                </div>
                                            </div>
                                            <div class="star d-flex clearfix">
                                                <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="${tour.transport.type}"/></span>
                                                </div>
                                            </div>
                                            <div class="star d-flex clearfix">
                                                <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="from ${tour.departureCity.city}"/></span>
                                                </div>
                                            </div>
                                            <div style="padding-top: 5px">
                                            	<form method="get"
                                                	  action="${pageContext.request.contextPath}/">
                                                      <button class="btn btn-info"
                                                      		  type="submit"
                                                              name="command"
                                                              value="REDIRECT_TO_INPUT_TOUR_NUMBER_PAGE">
                                                     	<input type="hidden"
                                                               name="tour_id"
                                                               value="${tour.tourId}"/>
                                                        	<fmt:message
                                                            	key="tour.buy"/>
                                                      </button>
                                              	</form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:if>
                        <c:if test="${sessionScope.role =='GUEST'}">
                            <c:if test="${tour.tourStatus!='ARCHIVAL' && tour.places!=0}">
                                <div class="col-md-6 col-lg-4 ftco-animate">
                                    <div class="project">
                                        <div class="text">
                                            <h4 class="price"><c:out
                                                    value="${tour.cost}$"/></h4>
                                            <span><c:out
                                                    value="${tour.days}"/> <fmt:message
                                                    key="tour.days"/></span>
                                            <h3><c:out
                                                    value="${tour.name} to ${tour.city.city}"/>
                                            </h3>
                                            <div class="star d-flex clearfix">
                                                <div class="mr-auto float-left">
                                                    <span class="rate">${tour.hotel.hotel}</span>
                                                </div>
                                            </div>
                                            <div class="star d-flex clearfix">
                                                <div class="mr-auto float-left">
                                                    <span class="rate">${tour.departureDate}</span>
                                                </div>
                                            </div>
                                            <div class="star d-flex clearfix">
                                                <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="${tour.tourType.type}"/></span>
                                                </div>
                                            </div>
                                            <div class="star d-flex clearfix">
                                                <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="${tour.transport.type}"/></span>
                                                </div>
                                            </div>
                                            <div class="star d-flex clearfix">
                                                <div class="mr-auto float-left">
                                                <span class="ion-ios-star"><c:out
                                                        value="from ${tour.departureCity.city}"/></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </section>
    </c:if>
    <div id="ftco-loader" class="show fullscreen">
        <svg class="circular" width="48px" height="48px">
            <circle class="path-bg" cx="24" cy="24" r="22" fill="none"
                    stroke-width="4" stroke="#eeeeee"></circle>
            <circle class="path" cx="24" cy="24" r="22" fill="none"
                    stroke-width="4"
                    stroke-miterlimit="10" stroke="#F96D00"></circle>
        </svg>
    </div>

    <script src="${root}/resources/js/jquery.min.js"></script>
    <script src="${root}/resources/js/jquery-migrate-3.0.1.min.js"></script>
    <script src="${root}/resources/js/popper.min.js"></script>
    <script src="${root}/resources/js/bootstrap.min.js"></script>
    <script src="${root}/resources/js/jquery.waypoints.min.js"></script>
    <script src="${root}/resources/js/jquery.stellar.min.js"></script>
    <script src="${root}/resources/js/owl.carousel.min.js"></script>
    <script src="${root}/resources/js/jquery.magnific-popup.min.js"></script>
    <script src="${root}/resources/js/aos.js"></script>
    <script src="${root}/resources/js/jquery.animateNumber.min.js"></script>
    <script src="${root}/resources/js/bootstrap-datepicker.js"></script>
    <script src="${root}/resources/js/scrollax.min.js"></script>
    <script src="${root}/resources/js/main.js"></script>
    <script src="${root}/resources/js/nav.js"></script>
    <footer>
        <div class="copyrights wrapper">
            <ctg:copyrightTag/>
        </div>
    </footer>
    </body>
    </html>
</fmt:bundle>