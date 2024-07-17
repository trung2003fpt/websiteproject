<%-- 
    Document   : UpdateUser
    Created on : Mar 10, 2023, 11:00:03 AM
    Author     : Nam
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update user for user</title>
        <link href="./style.css" type="text/css" rel="stylesheet">
    </head>
    <body style="background-color: #ffba8e;">
        <header>
            <h1> <a href="home" title="Chúng ta" class="logo"><img
                        src="https://st.chungta.vn/v355/chungta/images/graphics/logo_chungta.svg" alt="Chúng ta"></a>
            </h1>
            <div class="header-infor">
                <a href="updateUser">User: ${sessionScope.user.name} |&nbsp;&nbsp;&nbsp;</a>
                <a href="logout">Log out</a>
            </div>
            <div>
                <nav>
                    <ul>
                        <li><a href="home">Home</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <div style="margin-left: 10px">
            <h3>Detail & update user:</h3>
            <p>(You can only update name or password)</p>
            <c:set var="u" value="${sessionScope.user}"/>
            <form action="updateUser" method="post">
                <p>ID: <input type="text" name="id" value="${u.id}" readonly></p>
                <p>Name: <input type="text" name="name" value="${u.name}" required=""></p>
                <p>Account: <input type="text" name="acc" value="${u.account}"  readonly=""></p>
                <p>Password: <input type="text" name="pass" value="${u.password}" required=""></p>
                <p>Email: <input type="text" name="email" value="${u.email}" readonly=""></p>
                <input type="hidden" name="role" value="${u.role}">
                <input type="submit" value="Update">
            </form>
            <h3>${sessionScope.ok}</h3>
            <%
                request.getSession().removeAttribute("ok");
            %>
        </div>
        <footer>
            <div class="footer-div">
                <a href="home"><img
                        src="https://static.thanhnien.com.vn/thanhnien.vn/image/logo.svg" alt="Chúng ta"></a>
                 <p>Giấy phép xuất bản số 110/GP - BTTTT cấp ngày 24.3.2020 © </br>
                   2003-2024 Bản quyền thuộc về Báo Thanh Niên. Cấm sao chép </br>
                   dưới mọi hình thức nếu không có sự chấp thuận bằng văn bản.</p>
                
                <p class="product">
                    <span>Một sản phẩm của </span>
                    <span><img src="https://st.chungta.vn/v355/chungta/images/graphics/logo_fpt.svg"
                               alt="Fpt"></span>
                </p>
            </div>
        </footer>
    </body>
</html>
