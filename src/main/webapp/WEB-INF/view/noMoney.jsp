<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customTags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <html lang="en">
    <head>
        <title><fmt:message key="noMoney.title"/></title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css"
              href="${root}/resources/css/mainPage.css">
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
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
            <div class="wrap-login100">
					<span class="login100-form-logo">
						<i class="zmdi zmdi-landscape"></i>
                    </span>
                <span class="login100-form-title p-b-34 p-t-27"><fmt:message
                        key="noMoney.text"/></span>
            </div>
        </div>
    </div>
    <div id="dropDownSelect1"></div>
    <footer>
        <div class="copyrights wrapper">
            <ctg:copyrightTag/>
        </div>
    </footer>
    </body>
    </html>
</fmt:bundle>