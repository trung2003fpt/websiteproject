<%-- 
    Document   : AddArticle
    Created on : Mar 9, 2023, 10:12:34 AM
    Author     : Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add article</title>
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
            <h3 style="color: red">${sessionScope.errorArticle}</h3>
            <%
                request.getSession().removeAttribute("errorArticle");
            %>
            <h1>Add new article:</h1>
            <form action="addArticle" method="post">
                <p>Title: <textarea name="title" rows="2" cols="70" required="">${requestScope.title}</textarea></p>            
                <p>Content: <textarea name="content" rows="8" cols="70"required="" >${requestScope.content}</textarea></p>
                <p>Image: <textarea name="image" rows="1" cols="70">${image}</textarea></p>
                <p>Post date: <input type="date" name="postDate" readonly="" value="${requestScope.date}"></p>
                <p>Writer ID: <input type="text" name="userID" value="${sessionScope.user.name}" readonly=""></p>

                <p>Category: 
                    <select name="cateID">
                        <c:forEach items="${requestScope.cates}" var="c">
                            <option value="${c.id}"> ${c.name}</option>
                        </c:forEach>
                    </select>
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
