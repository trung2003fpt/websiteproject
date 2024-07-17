<%-- 
    Document   : Login
    Created on : Mar 10, 2023, 9:57:43 PM
    Author     : Nam
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="style.css" type="text/css" rel="stylesheet">
    </head>
    <body style="background-color: #ffba8e;">
        <header>
            <h1> 
                <a href="home" title="Chúng ta" class="logo">
                    <img src="https://st.chungta.vn/v355/chungta/images/graphics/logo_chungta.svg" alt="Chúng ta">
                </a>
            </h1>
        </header>
        <section>
            <div class="login-form">
                <form action="login" method="post">
                    <h2 class="text-center">Log in</h2>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Username" required="required" name="name">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" required="required" name="pass">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">Login</button>
                    </div>
                    <h2 class="text-center" style="color: red">${sessionScope.errorLogin}</h2>
                    <%
                        request.getSession().removeAttribute("errorLogin");
                    %>
                    <h2 class="text-center">${sessionScope.successRegist1}</h2>
                    <%
                        request.getSession().removeAttribute("successRegist1");
                    %>
                    <div class="clearfix">
                        <label class="float-left form-check-label"><scan>Don't have an account?</scan></label>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="Register.jsp" class="float-right" style="text-decoration: none;">Register here</a>
                    </div>
                </form>
            </div>
        </section>
        <section class="foot">
           
            <p class="product">
                <span>Một sản phẩm của </span>
                <span><img src="https://st.chungta.vn/v355/chungta/images/graphics/logo_fpt.svg" alt="Fpt"></span>
            </p>
        </section>
    </body>

</html>
