<%-- 
    Document   : ListComment
    Created on : Mar 10, 2023, 3:34:37 PM
    Author     : Nam
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List comment</title>
        <link href="./style.css" type="text/css" rel="stylesheet">
    </head>
    <body>
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
        <div style="min-height: 252px;">
            <c:if test="${requestScope.comments == null}">    
                <h3>There is no comment left!</h3>
            </c:if>
            <c:if test="${requestScope.comments != null}">    
                <h3>List of comments</h3>
                <c:if test="${sessionScope.user.id == 1}">
                    <table border="1px">
                        <tr>
                            <th>Comment Id</th>
                            <th>Content</th>
                            <th>Post date</th>
                            <th>Comment writer</th>
                            <th>Article be commented</th>
                            <th>Delete</th>
                        </tr>
                        <c:forEach var="c" items="${requestScope.comments}">
                            <tr>
                                <td>${c.id}</td>
                                <td>${c.content}</td>
                                <td>${c.postDate}</td>
                                <td>${c.user.name}</td>
                                <td><a href="detail?aid=${c.article.id}">${c.article.title}</a></td> 
                                <td>
                                    <span onclick="confirmDelete(${c.id})"><img style="width: 20px" src="https://cdn-icons-png.flaticon.com/512/3096/3096687.png"></span>
                                    <a style="display: none;" id="link_delete_${c.id}" href="deleteComment?id=${c.id}">Delete</a>
                                </td>
                            </tr> 
                        </c:forEach>
                    </table>
                    <div><h3>${sessionScope.successRegist2}</h3></div>
                    <%
                        request.getSession().removeAttribute("successRegist2");
                    %>
                </c:if>
                <c:if test="${sessionScope.user.id != 1}">
                    <table border="1px">
                        <tr>
                            <th>Comment Id</th>
                            <th>Content</th>
                            <th>Post date</th>
                            <th>Comment writer</th>
                            <th>Article be commented</th>
                        </tr>
                        <c:forEach var="c" items="${requestScope.comments}">
                            <tr>
                                <td>${c.id}</td>
                                <td>${c.content}</td>
                                <td>${c.postDate}</td>
                                <td>${c.user.name}</td>
                                <td><a href="detail?aid=${c.article.id}">${c.article.title}</a></td> 
                            </tr> 
                        </c:forEach>
                    </table>
                </c:if>
            </c:if>

        </div>
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
                if (confirm('Are you sure do delete this comment?')) {
                    var linkDelete = document.getElementById('link_delete_' + id);
                    if (linkDelete) {
                        linkDelete.click();
                    }
                }
            }
        </script>
    </body>
</html>
