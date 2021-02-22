<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <%@ taglib prefix="ctg" uri="customTags" %>
    <html lang="en">
    <head>
        <title><fmt:message key="allOrders.title"/></title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/table.css">
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/mainPage.css">
    </head>
    <header>
        <c:import url="/WEB-INF/view/header.jsp"/>
    </header>
    <body>
    <section class="hero">
        <jsp:useBean id="orders" class="java.util.ArrayList" scope="request"/>
        <div class="div-table">
            <c:if test="${sessionScope.role=='CLIENT'}">
                <table>
                    <tr>
                        <th><fmt:message key="allOrders.orderId"/></th>
                        <th><fmt:message key="allOrders.tourId"/></th>
                        <th><fmt:message key="allOrders.tourName"/></th>
                        <th><fmt:message key="allOrders.departureDate"/></th>
                        <th><fmt:message key="allOrders.tourNumber"/></th>
                        <th><fmt:message key="allOrders.price"/></th>
                        <th><fmt:message key="allOrders.cancel"/></th>
                    </tr>
                    <c:forEach items="${orders}" var="order">
                        <c:if test="${order.user.userId==sessionScope.user.userId}">
                            <tr>
                                <td><c:out value="${order.orderId}"/></td>
                                <td><c:out value="${order.tour.tourId}"/></td>
                                <td><c:out value="${order.tour.name}"/></td>
                                <td><c:out
                                        value="${order.tour.departureDate}"/></td>
                                <td><c:out value="${order.number}"/></td>
                                <td><c:out value="${order.price}"/></td>
                                <td>
                                    <c:if test="${order.orderStatus=='ACTIVE'}">
                                        <form method="get">
                                            <button class="bot1" type="submit"
                                                    name="command"
                                                    value="CANCEL_BUYING_TOUR">
                                                <input type="hidden"
                                                       name="order_id"
                                                       value="${order.orderId}"/>
                                                <fmt:message
                                                        key="button.cancel"/>
                                            </button>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${sessionScope.role=='ADMIN'}">
                <table>
                    <tr>
                        <th><fmt:message key="allOrders.orderId"/></th>
                        <th><fmt:message key="allOrders.userId"/></th>
                        <th><fmt:message key="allOrders.userLogin"/></th>
                        <th><fmt:message key="allOrders.tourId"/></th>
                        <th><fmt:message key="allOrders.tourName"/></th>
                        <th><fmt:message key="allOrders.departureDate"/></th>
                        <th><fmt:message key="allOrders.tourNumber"/></th>
                        <th><fmt:message key="allOrders.price"/></th>
                        <th><fmt:message key="allOrders.orderStatus"/></th>
                    </tr>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td><c:out value="${order.orderId}"/></td>
                            <td><c:out value="${order.user.userId}"/></td>
                            <td><c:out value="${order.user.login}"/></td>
                            <td><c:out value="${order.tour.tourId}"/></td>
                            <td><c:out value="${order.tour.name}"/></td>
                            <td><c:out
                                    value="${order.tour.departureDate}"/></td>
                            <td><c:out value="${order.number}"/></td>
                            <td><c:out value="${order.price}"/></td>
                            <td><c:out value="${order.orderStatus}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${sessionScope.role=='MANAGER'}">
                <table>
                    <tr>
                        <th><fmt:message key="allOrders.orderId"/></th>
                        <th><fmt:message key="allOrders.userId"/></th>
                        <th><fmt:message key="allOrders.userLogin"/></th>
                        <th><fmt:message key="allOrders.tourId"/></th>
                        <th><fmt:message key="allOrders.tourName"/></th>
                        <th><fmt:message key="allOrders.departureDate"/></th>
                        <th><fmt:message key="allOrders.tourNumber"/></th>
                        <th><fmt:message key="allOrders.price"/></th>
                        <th><fmt:message key="allOrders.orderStatus"/></th>
                    </tr>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td><c:out value="${order.orderId}"/></td>
                            <td><c:out value="${order.user.userId}"/></td>
                            <td><c:out value="${order.user.login}"/></td>
                            <td><c:out value="${order.tour.tourId}"/></td>
                            <td><c:out value="${order.tour.name}"/></td>
                            <td><c:out
                                    value="${order.tour.departureDate}"/></td>
                            <td><c:out value="${order.number}"/></td>
                            <td><c:out value="${order.price}"/></td>
                            <td><c:out value="${order.orderStatus}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </section>
    </body>
    </html>
</fmt:bundle>