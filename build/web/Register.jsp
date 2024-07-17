<%-- 
    Document   : Login
    Created on : Mar 10, 2023, 10:28:50 AM
    Author     : Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body style="background-color: #ffba8e;">
        <header>
            <h1> <a href="home" title="Chúng ta" class="logo"><img
                        src="https://st.chungta.vn/v355/chungta/images/graphics/logo_chungta.svg" alt="Chúng ta"></a>
            </h1>
        </header>
        <section>
            <div class="login-form">
                <form action="addUser" method="post">
                    <h2 class="text-center">Register</h2>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Name" required="required" name="name">
                    </div>              
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Username" required="required" name="acc">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Email" required="required" name="email">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" required="required" name="pass">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Confirm password" required="required" name="repass">
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">Sign up</button>
                    </div>
                    <h2 class="text-center" style="color: red">${sessionScope.errorRegist1}</h2>
                    <%
                        request.getSession().removeAttribute("errorRegist1");
                    %>
                    <div class="clearfix">
                        <label class="float-left form-check-label"><scan>Already have an account?</scan></label>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="login" class="float-right" style="text-decoration: none;">Login here.</a>
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
