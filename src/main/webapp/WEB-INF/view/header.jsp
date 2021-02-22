<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <fmt:setLocale value="${locale}"/>
    <link rel="stylesheet" type="text/css"
          href="${root}/resources/css/mainPage.css">
</head>
<fmt:bundle basename="locale">
    <header>
        <div class="wrapper">
            <nav>
                <c:if test="${sessionScope.role=='GUEST'}">
                    <form method="get">
                        <button class="login_btn" name="command"
                                value="redirect"><fmt:message
                                key="button.signIn"/>
                            <input type="hidden" name="address"
                                   value="SIGN_IN_PAGE"/>
                        </button>
                    </form>
                    <form method="get">
                        <button class="login_btn" name="command"
                                value="redirect"><fmt:message
                                key="button.signUp"/>
                            <input type="hidden" name="address"
                                   value="SIGN_UP_PAGE"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role!='GUEST'}">
                    <form method="post"
                          action="${pageContext.request.contextPath}/">
                        <button type="submit" class="login_btn"
                                name="command" value="SIGN_OUT">
                            <fmt:message
                                    key="button.signOut"/>
                        </button>
                    </form>
                    <form method="get">
                        <button class="just_btn" name="command"
                                value="redirect"><fmt:message
                                key="button.userInfo"/>
                            <input type="hidden" name="address"
                                   value="USER_INFO_PAGE"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role =='CLIENT'}">
                    <form method="get">
                        <button class="just_btn" name="command"
                                value="redirect"><fmt:message
                                key="button.contact"/>
                            <input type="hidden" name="address"
                                   value="CONTACT_PAGE"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role =='GUEST'}">
                    <form method="get">
                        <button class="just_btn" name="command"
                                value="redirect"><fmt:message
                                key="button.contact"/>
                            <input type="hidden" name="address"
                                   value="CONTACT_PAGE"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role !='GUEST'}">
                    <form method="get">
                        <button type="submit" class="just_btn" name="command"
                                value="GET_ORDERS"><fmt:message
                                key="button.orders"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role =='ADMIN'}">
                    <form method="get">
                        <button type="submit" class="just_btn" name="command"
                                value="GET_USERS_LIST"><fmt:message
                                key="button.listOfUsers"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role =='MANAGER'}">
                    <form method="get">
                        <button type="submit" class="just_btn" name="command"
                                value="GET_USERS_LIST"><fmt:message
                                key="button.listOfUsers"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role =='CLIENT'}">
                    <form method="get">
                        <button class="just_btn" name="command"
                                value="redirect"><fmt:message
                                key="button.about"/>
                            <input type="hidden" name="address"
                                   value="ABOUT_PAGE"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role =='GUEST'}">
                    <form method="get">
                        <button class="just_btn" name="command"
                                value="redirect"><fmt:message
                                key="button.about"/>
                            <input type="hidden" name="address"
                                   value="ABOUT_PAGE"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role =='ADMIN'}">
                    <form method="get">
                        <button class="just_btn" name="command"
                                value="redirect"><fmt:message
                                key="button.createAdmin"/>
                        </button>
                        <input type="hidden" name="address"
                               value="CREATE_ADMIN_PAGE"/>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role =='ADMIN'}">
                    <form method="get">
                        <button class="just_btn" name="command"
                                value="redirect"><fmt:message
                                key="button.createManager"/>
                        </button>
                        <input type="hidden" name="address"
                               value="CREATE_MANAGER_PAGE"/>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role =='CLIENT'}">
                    <form method="get">
                        <button class="just_btn" name="command"
                                value="redirect"><fmt:message
                                key="button.search"/>
                        </button>
                        <input type="hidden" name="address"
                               value="SEARCH_PAGE"/>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role =='GUEST'}">
                    <form method="get">
                        <button class="just_btn" name="command"
                                value="redirect"><fmt:message
                                key="button.search"/>
                        </button>
                        <input type="hidden" name="address"
                               value="SEARCH_PAGE"/>
                    </form>
                </c:if>
                <form method="get">
                    <button type="submit" class="just_btn" name="command"
                            value="GET_TOURS_LIST"><fmt:message
                            key="button.tours"/>
                    </button>
                </form>
                <c:if test="${sessionScope.role=='ADMIN'}">
                	<form method="get">
                    	<button class="just_btn" name="command"
                            	value="redirect"><fmt:message key="button.service"/>
                        	<input type="hidden" name="address"
                               	   value="SERVICE_PAGE"/>
                    	</button>
                	</form>
                </c:if>
                <form method="get">
                    <button type="submit" class="just_btn" name="command"
                            value="redirect"><fmt:message key="button.home"/>
                        <input type="hidden" name="address" value="HOME_PAGE"/>
                    </button>
                </form>
                <form method="get">
                    <button name="language" type="submit" class="just_btn"
                            value="EN" style="padding: 12px 0 0 0;float: left;">
                        EN |
                    </button>
                </form>
                <form method="get">
                    <button name="language" type="submit" class="just_btn"
                            value="RU"
                            style="padding: 12px 0 0 3px;float: left;">RU
                    </button>
                </form>
            </nav>
        </div>
    </header>
    </html>
</fmt:bundle>