<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <%@ taglib prefix="ctg" uri="customTags" %>
    <html lang="en">
    <head>
        <title><fmt:message key="list.title"/></title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/table.css">
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/mainPage.css">
    </head>
    <header style="min-width: 1320px">
        <c:import url="/WEB-INF/view/header.jsp"/>
    </header>

    <section class="hero">
        <jsp:useBean id="users" class="java.util.ArrayList" scope="request"/>
        <div class="wr" style="max-width: 100%">
            <div class="div-table">
                <table>
                    <tr>
                        <th><fmt:message key="list.userId"/></th>
                        <th><fmt:message key="list.login"/></th>
                        <th><fmt:message key="list.password"/></th>
                        <th><fmt:message key="list.name"/></th>
                        <th><fmt:message key="list.surname"/></th>
                        <th><fmt:message key="list.userDiscount"/></th>
                        <th><fmt:message key="list.cash"/></th>
                        <th><fmt:message key="list.phone"/></th>
                        <th><fmt:message key="list.role"/></th>
                        <th><fmt:message key="usersList.viewOrders"/></th>
                        <th><fmt:message key="usersList.blockOrUblock"/></th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td><c:out value="${user.userId}"/></td>
                            <td><c:out value="${user.login}"/></td>
                            <td><c:out value="${user.passwordStr}"/></td>
                            <td><c:out value="${user.name}"/></td>
                            <td><c:out value="${user.surname}"/></td>
                            <td><c:out
                                    value="${user.discount.discountSize}"/></td>
                            <td><c:out value="${user.cash}"/></td>
                            <td><c:out value="${user.phone}"/></td>
                            <td><c:out value="${user.role}"/></td>
                            <td>
                                <form method="get">
                                    <button class="bot1" type="submit"
                                            name="command"
                                            value="GET_ORDERS_BY_USER_ID">
                                        <input type="hidden" name="user_id"
                                               value="${user.userId}"/>
                                        <fmt:message key="button.view"/>
                                    </button>
                                </form>
                            </td>
                            <td>
                                <form method="post"
                                      action="${pageContext.request.contextPath}/">
                                    <c:if test="${user.role =='BLOCKED'}">
                                        <button class="bot1" type="submit"
                                                name="command"
                                                value="unblock_client">
                                            <input type="hidden" name="user_id"
                                                   value="${user.userId}"/>
                                            <fmt:message key="button.unblock"/>
                                        </button>
                                    </c:if>
                                    <c:if test="${user.role =='CLIENT'}">
                                        <button class="bot1" type="submit"
                                                name="command"
                                                value="block_client">
                                            <input type="hidden" name="user_id"
                                                   value="${user.userId}"/>
                                            <fmt:message key="button.block"/>
                                        </button>
                                    </c:if>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </section>
    </html>
</fmt:bundle>

