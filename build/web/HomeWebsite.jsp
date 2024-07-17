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
        <title>Home website</title>
        <link href="./style.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <header>
            <h1> <a href="home" title="Chúng ta" class="logo"><img
                        src="https://static.thanhnien.com.vn/thanhnien.vn/image/logo.svg" alt="Chúng ta"></a>
            </h1>
            <c:if test="${sessionScope.user == null}">
                <div class="header-infor">
                    <a href="login">Login here</a>
                </div>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <div class="header-infor">
                    <a href="updateUser">User: ${sessionScope.user.name} |&nbsp;&nbsp;&nbsp;</a>
                    <a href="logout">Log out</a>
                </div>
            </c:if>
            <div>
                <nav>
                    <ul>
                        <li><a href="home">Home</a></li>
                            <c:forEach var="c" items="${requestScope.cates}">
                            <li><a href="home?cid=${c.id}">${c.name}</a></li>
                            </c:forEach>
                            <c:if test="${sessionScope.user.role}">
                            <li><a href="manager">Quản lý</a></li>
                            </c:if>
                    </ul>
                </nav>
            </div>
            <c:if test="${sessionScope.cid == null}">
                <div class="find">
                    <form action="home">
                        <input type="text" placeholder="Tìm kiếm theo tiêu đề..." name="find">
                        <input type="submit" value="Tìm">
                    </form>
                </div>
            </c:if>
        </header>
        <section class="container" style="min-height: 255px;">  
            <div><h2>${requestScope.none}</h2></div>
            <div>
                <c:forEach var="a" items="${requestScope.articles}">
                    <article class="art_item">
                        <div class="thumb_art">
                            <a href="detail?aid=${a.id}" onclick="sendRequest('count?aid=${a.id}')" title="${a.title}"> 
                                <img class="title-image"
                                     src="${a.image}"
                                     onerror="this.onerror=null; 
                                     this.src='https://lh4.ggpht.com/-PtwFBckvv78/V3aBB39xD9I/AAAAAAAAHFA/EXKKalIB8IkvyJjUzGrDVQCzLMs5Alx9QCLcB/s1600/anh-blogspot-khong-hien-thi.png';">                                
                            </a>
                        </div>
                        <div class="content">
                            <h3 class="title-news">
                                <a href="detail?aid=${a.id}" onclick="sendRequest('count?aid=${a.id}')"
                                   title="${a.title}">${a.title}</a>
                            </h3>
                            <p class="post-truncate">${a.content}</p>

                            <p class="tag_Favor">${a.category.name}</p>
                            <c:forEach var="v" items="${requestScope.dem}">
                                <c:if test="${v.article.id == a.id}">
                                    <p>View: ${v.dem}</p>
                                </c:if>
                            </c:forEach>
                        </div>
                    </article>
                </c:forEach>
            </div>
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
        <script>
            function sendRequest(url) {
                var xhttp = new XMLHttpRequest();
                xhttp.open("GET", url, true);
                xhttp.send();
            }
        </script>
    </body>
</html>
