<%-- 
    Document   : AddUser
    Created on : Mar 13, 2023, 7:14:32 PM
    Author     : Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add user</title>
        <link href="./style.css" type="text/css" rel="stylesheet">
    </head>
    <body style="background-color: #ffba8e">
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
                        <li><a href="manager">Quản lý</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <div>
            <h3 style="color: red">${sessionScope.errorRegist2}</h3>
            <%
                request.getSession().removeAttribute("errorRegist2");
            %>
            <h1>Add new user:</h1>
            <form action="addUser">
                <p>Name: <input type="text" name="name" required=""></p>
                <p>Account: <input type="text" name="acc" required=""></p>
                <p>Password: <input type="text" name="pass" required=""></p>
                <p>Email: <input type="text" name="email" required=""></p>
                <p>Role: 
                    <input type="radio" name="role" value="1">Author
                    <input type="radio" name="role" value="0" checked="">User      
                </p>

                <input type="submit" value="Add">
            </form>
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
