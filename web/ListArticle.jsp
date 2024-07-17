<%-- 
    Document   : ListArticle
    Created on : Mar 8, 2023, 9:44:04 PM
    Author     : Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List article</title>
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
        <h1>List of Articles</h1>
        <table border="1px">
            <tr>
                <th>Id</th>
                <th>Title</th>          
                <th>Image</th>
                <th>Post date</th>
                <th>Writer</th>
                <th>Category name</th>
                <th>Delete</th>
            </tr>
            <c:if test="${sessionScope.user.id == 1}">
                <c:forEach var="a" items="${requestScope.articles}">
                    <tr>
                        <td>
                            <a href="detailArticle?id=${a.id}" class="table_detail">${a.id}</a>
                        </td>
                        <td><a class="table_detail" href="detail?aid=${a.id}">${a.title}</a></td>
                        <td>${a.image}</td>
                        <td>${a.postDate}</td>
                        <td>${a.user.name}</td>     
                        <td>${a.category.name}</td>  
                        <td>                
                            <span onclick="confirmDelete(${a.id})"><img style="width: 20px" src="https://cdn-icons-png.flaticon.com/512/3096/3096687.png"></span>
                            <a style="display: none;" id="link_delete_${a.id}" href="deleteArticle?id=${a.id}">Delete</a>
                        </td>
                    </tr> 
                </c:forEach>
            </c:if>
            <c:if test="${sessionScope.user.id != 1}">
                <c:forEach var="a" items="${requestScope.articles2}">
                    <tr>
                        <td>
                            <a href="detailArticle?id=${a.id}" class="table_detail">${a.id}</a>
                        </td>
                        <td><a class="table_detail" href="detail?aid=${a.id}">${a.title}</a></td>
                        <td>${a.image}</td>
                        <td>${a.postDate}</td>
                        <td>${a.user.name}</td>     
                        <td>${a.category.name}</td>  
                        <td>                
                            <span onclick="confirmDelete(${a.id})"><img style="width: 20px" src="https://cdn-icons-png.flaticon.com/512/3096/3096687.png"></span>
                            <a style="display: none;" id="link_delete_${a.id}" href="deleteArticle?id=${a.id}">Delete</a>
                        </td>
                    </tr> 
                     
                </c:forEach>
            </c:if>
        </table>
        <div><h3>${sessionScope.successAddarticle}</h3></div>
        <%
            request.getSession().removeAttribute("successAddarticle");
        %>
        <br><a href="addArticle"><input type="button" value="Add new article"></a>
        <footer>
            <div class="footer-div">
                <a href="home"><img
                        src="https://st.chungta.vn/v355/chungta/images/graphics/logo_chungta.svg" alt="Chúng ta"></a>
                <p>© 2023 Chungta, chuyên trang nội bộ của Tập đoàn FPT. Chungta giữ bản quyền nội
                    dung trên website này.</p>
                <p> Edited by namtqhe170981</p>
                <p class="product">
                    <span>Một sản phẩm của </span>
                    <span><img src="https://st.chungta.vn/v355/chungta/images/graphics/logo_fpt.svg"
                               alt="Fpt"></span>
                </p>
            </div>
        </footer>
        <script>
            function confirmDelete(id) {
                if (confirm('Are you sure do delete this article? Comments related to it will be deleted too.')) {
                    var linkDelete = document.getElementById('link_delete_' + id);
                    if (linkDelete) {
                        linkDelete.click();
                    }
                }
            }
        </script>          
    </body>
</html>
