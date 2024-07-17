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
        <title>Manager</title>
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
        <section>  
            <div class="manage-atten">
                <h3>Vui lòng chọn bảng quản lý</h3>
            </div>
        </section>
        <section>
            <nav class="manage-nav">
                <ul>
                    <li><a href="article"><img src="https://cdn-icons-png.flaticon.com/128/81/81460.png"></a></li>
                    <li><a href="user"><img src="https://cdn-icons-png.flaticon.com/128/747/747376.png"></a></li>                 
                    <li><a href="comment"><img src="https://cdn-icons-png.flaticon.com/128/4249/4249907.png"></a></li>  
                </ul>
            </nav>
        </section>
        <section>
            <nav class="manage-nav">
                <ul>
                    <li><a href="article">Article</a></li>
                    <li><a href="user">User</a></li>
                    <li><a href="comment">Comment</a></li>
                </ul>
            </nav>
        </section>
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
