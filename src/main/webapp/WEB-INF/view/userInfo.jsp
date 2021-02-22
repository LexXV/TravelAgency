<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <%@ taglib prefix="ctg" uri="customTags" %>
    <html lang="en">
    <head>
        <title><fmt:message key="userInfo.title"/></title>
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
        <div class="div-table">
            <table>
                <c:if test="${sessionScope.role =='ADMIN'}">
                    <tr>
                        <th><fmt:message key="userInfo.parameter"/></th>
                        <th><fmt:message key="userInfo.info"/></th>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.userId"/></td>
                        <td><c:out value="${sessionScope.user.userId}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.login"/></td>
                        <td><c:out value="${sessionScope.user.login}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.password"/></td>
                        <td><c:out
                                value="${sessionScope.user.passwordStr}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.name"/></td>
                        <td><c:out value="${sessionScope.user.name}"/></td>
                    </tr>
                    <tr>
                        <td>S<fmt:message key="list.surname"/></td>
                        <td><c:out value="${sessionScope.user.surname}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.phone"/></td>
                        <td><c:out value="${sessionScope.user.phone}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.role"/></td>
                        <td><c:out value="${sessionScope.role}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="userInfo.update"/></td>
                        <td>
                            <form method="get">
                                <button type="submit" class="bot1"
                                        name="command"
                                        value="redirect"><fmt:message
                                        key="button.update"/>
                                </button>
                                <input type="hidden" name="address"
                                       value="UPDATE_USER_PAGE"/>
                            </form>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${sessionScope.role =='MANAGER'}">
                    <tr>
                        <th><fmt:message key="userInfo.parameter"/></th>
                        <th><fmt:message key="userInfo.info"/></th>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.userId"/></td>
                        <td><c:out value="${sessionScope.user.userId}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.login"/></td>
                        <td><c:out value="${sessionScope.user.login}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.password"/></td>
                        <td><c:out
                                value="${sessionScope.user.passwordStr}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.name"/></td>
                        <td><c:out value="${sessionScope.user.name}"/></td>
                    </tr>
                    <tr>
                        <td>S<fmt:message key="list.surname"/></td>
                        <td><c:out value="${sessionScope.user.surname}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.phone"/></td>
                        <td><c:out value="${sessionScope.user.phone}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.role"/></td>
                        <td><c:out value="${sessionScope.role}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="userInfo.update"/></td>
                        <td>
                            <form method="get">
                                <button type="submit" class="bot1"
                                        name="command"
                                        value="redirect"><fmt:message
                                        key="button.update"/>
                                </button>
                                <input type="hidden" name="address"
                                       value="UPDATE_USER_PAGE"/>
                            </form>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${sessionScope.role =='CLIENT'}">
                    <tr>
                        <th><fmt:message key="userInfo.parameter"/></th>
                        <th><fmt:message key="userInfo.info"/></th>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.login"/></td>
                        <td><c:out value="${sessionScope.user.login}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.password"/></td>
                        <td><c:out
                                value="${sessionScope.user.passwordStr}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.name"/></td>
                        <td><c:out value="${sessionScope.user.name}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.surname"/></td>
                        <td><c:out value="${sessionScope.user.surname}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.discount"/></td>
                        <td><c:out
                                value="${sessionScope.user.discount.discountSize}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.cash"/></td>
                        <td><c:out value="${sessionScope.user.cash}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.phone"/></td>
                        <td><c:out value="${sessionScope.user.phone}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="list.role"/></td>
                        <td><c:out value="${sessionScope.role}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="userInfo.update"/></td>
                        <td>
                            <form method="get">
                                <button type="submit" class="bot1"
                                        name="command"
                                        value="redirect"><fmt:message
                                        key="button.update"/>
                                </button>
                                <input type="hidden" name="address"
                                       value="UPDATE_USER_PAGE"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="userInfo.delete"/></td>
                        <td>
                            <form method="get">
                                <button class="bot1" type="submit"
                                        name="command"
                                        value="DELETE_CLIENT">
                                    <fmt:message key="button.delete"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:if>
            </table>
        </div>
    </section>
    </body>
    <footer>
        <div class="copyrights wrapper">
            <ctg:copyrightTag/>
        </div>
    </footer>
    </html>
</fmt:bundle>