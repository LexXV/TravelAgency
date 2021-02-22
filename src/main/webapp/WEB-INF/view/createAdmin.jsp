<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customTags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <html lang="en">
    <head>
        <title><fmt:message key="createAdmin.title"/></title>
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
             style="background-image: url('${pageContext.request.contextPath}/resources/img/bg-01.jpg')">
            <div class="wrap-login100">
                <form method="post"
                      action="${pageContext.request.contextPath}/">
                    <span class="login100-form-logo">
						<i class="zmdi zmdi-landscape"></i>
                    </span>
                    <span class="login100-form-title p-b-34 p-t-27">
                        <fmt:message key="createAdmin.title"/>
                    </span>
                    <div class="wrap-input100">
                        <input class="input100" type="text" name="login"
                               pattern="[A-Za-z0-9_]+"
                               required placeholder=<fmt:message
                                key="user.login"/>>
                        <span class="focus-input100"
                              data-placeholder="&#xf207;"></span>
                    </div>

                    <div class="wrap-input100">
                        <input class="input100" type="password" name="password"
                               pattern="^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$"
                               required placeholder=<fmt:message
                                key="user.password"/>>
                        <span class="focus-input100"
                              data-placeholder="&#xf191;"></span>
                    </div>

                    <div class="wrap-input100">
                        <input class="input100" type="text" name="name"
                               pattern="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"
                               required placeholder=<fmt:message
                                key="user.name"/>>
                        <span class="focus-input100"
                              data-placeholder="&#xf207;"></span>
                    </div>

                    <div class="wrap-input100">
                        <input class="input100" type="text" name="surname"
                               pattern="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"
                               required placeholder=<fmt:message
                                key="user.surname"/>>
                        <span class="focus-input100"
                              data-placeholder="&#xf207;"></span>
                    </div>

                    <div class="wrap-input100">
                        <input class="input100" type="text" name="phone"
                               pattern="[0-9*#+() -]*"
                               placeholder=<fmt:message key="user.phone"/>>
                        <span class="focus-input100"
                              data-placeholder="&#xf207;"></span>
                    </div>

                    <div class="container-login100-form-btn">
                        <button class="login100-form-btn" type="submit"
                                name="command" value="CREATE_ADMIN">OK
                        </button>
                    </div>
                </form>
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