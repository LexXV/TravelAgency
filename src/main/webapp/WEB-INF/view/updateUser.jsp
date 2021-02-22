<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="locale">
    <%@ taglib prefix="ctg" uri="customTags" %>
    <html lang="en">
    <head>
        <title><fmt:message key="updateUser.title"/></title>
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
                    <c:if test="${sessionScope.role =='ADMIN'}"><fmt:message
                            key="updateUser.admin"/></c:if>
                    <c:if test="${sessionScope.role =='MANAGER'}"><fmt:message
                            key="updateUser.manager"/></c:if>
                    <c:if test="${sessionScope.role =='CLIENT'}"><fmt:message
                            key="updateUser.client"/></c:if>
                 </span>

                    <c:if test="${sessionScope.role =='ADMIN'}">
                        <div class="wrap-input100">
                            <input class="input100" type="text" name="name"
                                   required
                                   placeholder="<fmt:message key="updateUser.newName"/>"
                                   pattern="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$">
                            <span class="focus-input100"
                                  data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="wrap-input100">
                            <input class="input100" type="text" name="surname"
                                   required
                                   placeholder="<fmt:message key="updateUser.newSurname"/>"
                                   pattern="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$">
                            <span class="focus-input100"
                                  data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="wrap-input100">
                            <input class="input100" type="text" name="phone"
                                   placeholder="<fmt:message key="updateUser.newPhone"/>"
                                   pattern="[0-9*#+() -]*">
                            <span class="focus-input100"
                                  data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit"
                                    name="command" value="UPDATE_ADMIN">OK
                            </button>
                        </div>
                    </c:if>
                    
                    <c:if test="${sessionScope.role =='MANAGER'}">
                        <div class="wrap-input100">
                            <input class="input100" type="text" name="name"
                                   required
                                   placeholder="<fmt:message key="updateUser.newName"/>"
                                   pattern="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$">
                            <span class="focus-input100"
                                  data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="wrap-input100">
                            <input class="input100" type="text" name="surname"
                                   required
                                   placeholder="<fmt:message key="updateUser.newSurname"/>"
                                   pattern="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$">
                            <span class="focus-input100"
                                  data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="wrap-input100">
                            <input class="input100" type="text" name="phone"
                                   placeholder="<fmt:message key="updateUser.newPhone"/>"
                                   pattern="[0-9*#+() -]*">
                            <span class="focus-input100"
                                  data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit"
                                    name="command" value="UPDATE_MANAGER">OK
                            </button>
                        </div>
                    </c:if>

                    <c:if test="${sessionScope.role =='CLIENT'}">
                        <div class="wrap-input100">
                            <input class="input100" type="text" name="name"
                                   required
                                   placeholder="<fmt:message key="updateUser.newName"/>"
                                   pattern="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$">
                            <span class="focus-input100"
                                  data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="wrap-input100">
                            <input class="input100" type="text" name="surname"
                                   required
                                   placeholder="<fmt:message key="updateUser.newSurname"/>"
                                   pattern="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$">
                            <span class="focus-input100"
                                  data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="wrap-input100">
                            <input class="input100" type="text" name="cash"
                                   required
                                   placeholder="<fmt:message key="updateUser.newCash"/>"
                                   pattern="^(\d*\.)?\d+$">
                            <span class="focus-input100"
                                  data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="wrap-input100">
                            <input class="input100" type="text" name="phone"
                                   placeholder="<fmt:message key="updateUser.newPhone"/>"
                                   pattern="[0-9*#+() -]*">
                            <span class="focus-input100"
                                  data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit"
                                    name="command" value="UPDATE_CLIENT">OK
                            </button>
                        </div>
                    </c:if>
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
