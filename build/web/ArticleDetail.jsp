<%-- 
    Document   : ArticleDetail
    Created on : Mar 9, 2023, 10:23:52 AM
    Author     : Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail article</title>
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
        <c:set var="a" value="${requestScope.article}"/> 

        <div style="margin-left: 10px">
            <h3>Detail of the article:</h3>
            <form action="updateArticle" method="post">
                <p>ID:<input type="text" readonly value="${a.id}" name="id"></p>
                <p>Title: <textarea name="title" rows="2" cols="70">${a.title}</textarea></p>            
                <p>Content: <textarea name="content" rows="8" cols="70">${a.content}</textarea></p>
                <p>Image: <textarea name="image" rows="1" cols="70">${a.image}</textarea></p>
                <p>Post Date: <input type="date" value="${a.postDate}" name="postdate" readonly></p>
                <p>Writer ID: <input type="text" value="${a.user.id}" name="userid" readonly></p>
                <p>Category ID: 
                    <select name="cateid">
                        <c:forEach items="${requestScope.cates}" var="c">
                            <option ${(a.category.id==c.id)?'selected':''} value="${c.id}"> ${c.name}</option>
                        </c:forEach>
                    </select>
                </p>
                <br><input type="submit" value="Update">
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
