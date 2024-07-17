<%-- 
    Document   : DemoFrontend
    Created on : Mar 10, 2023, 7:53:35 PM
    Author     : Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Try Comment</title>
        <link href="./style.css" type="text/css" rel="stylesheet">
    </head>
    <body style="background-color: #ed5e05">
        <header>
            <h1> <a href="home" title="Chúng ta" class="logo"><img
                        src="https://st.chungta.vn/v355/chungta/images/graphics/logo_chungta.svg" alt="Chúng ta"></a>
            </h1>
        </header>
        <section>  
            <div class="manage-atten">
                <h1>Không được đâu cậu ơi. Muốn comment thì phải Login chứ..</h1>
            </div>
            <div class="manage-atten">
                <p>(Nhấn vào nút Login bên dưới để bắt đầu nhé.)</p>
            </div>
            <div class="manage-atten">
                <p>Không thì mình về trang chủ xem chùa thôi nha (Nhấn vào logo nhé)</p>
            </div>
            <div class="manage-atten">
                <form action="login">
                    <input type="submit" value="Login">
                </form>
            </div>
        </section>
    </body>
</html>
