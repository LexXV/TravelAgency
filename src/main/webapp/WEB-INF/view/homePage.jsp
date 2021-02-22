<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <%@ taglib prefix="ctg" uri="customTags" %>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <title><fmt:message key="homepage.title"/></title>
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/mainPage.css">
    </head>
    <body>
    <section class="hero">
        <c:import url="/WEB-INF/view/header.jsp"/>
        <c:if test="${sessionScope.role =='ADMIN'}">
            <h1 align="center"><fmt:message
                    key="homepage.greeting"/>, ${sessionScope.user.name}!</h1>
        </c:if>
        <c:if test="${sessionScope.role =='MANAGER'}">
            <h1 align="center"><fmt:message
                    key="homepage.greeting"/>, ${sessionScope.user.name}!</h1>
        </c:if>
        <c:if test="${sessionScope.role =='CLIENT'}">
            <h2 align="center"><fmt:message
                    key="homepage.greeting"/>, ${sessionScope.user.name}!</h2>
        </c:if>
        <section class="caption">
            <h2 class="caption"><fmt:message key="homepage.caption"/></h2>
        </section>
    </section>
    </body>
    <footer>
        <div class="copyrights wrapper">
            <ctg:copyrightTag/>
        </div>
    </footer>
    </html>
</fmt:bundle>