<%-- 
    Document   : ListUser
    Created on : Mar 10, 2023, 9:41:36 AM
    Author     : Nam
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List user</title>
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
        <h3>List of users</h3>
        <c:if test="${sessionScope.user.id == 1}">
            <table border="1px" style="min-height: 250px">
                <tr>
                    <th>User Id</th>
                    <th>Name</th>
                    <th>Account</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="u" items="${requestScope.users}">
                    <c:if test="${u.id == 1}">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.name}</td>
                            <td>${u.account}</td>   
                            <td>${u.password}</td>
                            <td>${u.email}</td> 
                            <td>Admin</td>
                            <td></td>
                        </tr> 
                    </c:if>
                    <c:if test="${u.id != 1}">
                        <tr>                           
                            <td><a href="updateUser2?id=${u.id}" class="table_detail">${u.id}</a></td>
                            <td>${u.name}</td>
                            <td>${u.account}</td>   
                            <td>${u.password}</td>
                            <td>${u.email}</td> 
                            <c:if test="${u.role}">
                                <td>Author</td>
                            </c:if>
                            <c:if test="${!u.role}">
                                <td>User</td>
                            </c:if>  
                            <c:if test="${u.id != 1}">
                                <td>
                                    <span onclick="confirmDelete(${u.id})"><img style="width: 20px" src="https://cdn-icons-png.flaticon.com/512/3096/3096687.png"></span>
                                    <a style="display: none;" id="link_delete_${u.id}" href="deleteUser?id=${u.id}">Delete</a>
                                </td>
                            </c:if> 
                            <c:if test="${u.id == 1}">
                                <td></td>
                            </c:if> 
                        </tr> 
                    </c:if>
                </c:forEach>
            </table>
            <form action="AddUser.jsp" style="margin: 10px 0px"><input type="submit" value="Add a new user"></form>
            <div><h3>${sessionScope.successRegist2}</h3></div>
            <%
                request.getSession().removeAttribute("successRegist2");
            %>
        </c:if>
        <c:if test="${sessionScope.user.id != 1}">
            <table border="1px" style="min-height: 250px">
                <tr>
                    <th>User Id</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                </tr>
                <c:forEach var="u" items="${requestScope.writers}">
                    <c:if test="${u.id == 1}">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.name}</td>              
                            <td>${u.email}</td> 
                            <td>Admin</td>                     
                        </tr> 
                    </c:if>
                    <c:if test="${u.id != 1}">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.name}</td>                  
                            <td>${u.email}</td> 
                            <c:if test="${u.role}">
                                <td>Author</td>
                            </c:if>
                            <c:if test="${!u.role}">
                                <td>User</td>
                            </c:if>                        
                        </tr> 
                    </c:if>
                </c:forEach>
            </table>
        </c:if>
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
                if (confirm('Are you sure do delete this user? Articles, comments written by this people will be deleted too.')) {
                    var linkDelete = document.getElementById('link_delete_' + id);
                    if (linkDelete) {
                        linkDelete.click();
                    }
                }
            }
        </script>
    </body>
</html>
