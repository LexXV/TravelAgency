<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="error.title"/><</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body class="w3-light-grey">
    <div class="w3-container w3-center">
        <p><fmt:message key="error.text1"/></p>
        <p><fmt:message
                key="error.status"/> ${pageContext.errorData.statusCode}</p>
        <p><fmt:message
                key="error.requestURI"/> ${pageContext.errorData.requestURI}</p>
    </div>
    <div class="w3-container w3-opacity w3-right-align w3-padding">
        <button class="w3-btn w3-round-large w3-hover-pale-yellow"
                onclick="location.href='/TravelAgency'">
            <fmt:message key="error.backToHome"/></button>
    </div>
    </body>
    </html>
</fmt:bundle>