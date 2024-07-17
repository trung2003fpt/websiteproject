<%-- 
    Document   : DetailArticle
    Created on : Mar 10, 2023, 8:09:08 PM
    Author     : Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail website</title>
        <link href="style.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <header>
            <h1> <a href="home" title="Chúng ta" class="logo"><img
                        src="https://st.chungta.vn/v355/chungta/images/graphics/logo_chungta.svg" alt="Chúng ta"></a>
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
            <div style="display: block;">
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
        </header>
        <section class="container">
            <c:set var="a" value="${requestScope.arti}"/>
            <div class="header-detail">
                <div>
                    <p>${requestScope.cate.name}</p>
                </div>
                <h1> ${a.title}</h1>
                <p style="color: gray">Ngày đăng: ${a.postDate}</p>
                <span></span>      
                <article>
                    <table align="center" border="0" cellpadding="3" cellspacing="0" class="tplCaption">
                        <tbody>
                            <tr>

                        <img class="detail-image"
                             src="${a.image}" data-natural-width="620"
                             onerror="this.onerror=null; 
                             this.src='https://lh4.ggpht.com/-PtwFBckvv78/V3aBB39xD9I/AAAAAAAAHFA/EXKKalIB8IkvyJjUzGrDVQCzLMs5Alx9QCLcB/s1600/anh-blogspot-khong-hien-thi.png';">                                

                        </tr>
                        </tbody>
                    </table>
                    <p class="Normal" style="text-align:justify;">${arti.content}</span></p>
                    <p class="Normal" style="text-align:right;"> <strong>${arti.user.name}</strong></p>
                </article>
                <div class="comment_side">
                    <h2>Comment</h2>

                    <form action="detail" method="post">
                        <textarea cols="70" rows="3" placeholder="Bình luận tại đây" style="float:left" name="contentCom" required></textarea>
                        <input type="submit"  style="margin-left: 20px">
                        <div style="clear: both"><input type="hidden" value="${requestScope.arti.id}" name="aid"></div>
                    </form>
                    <c:forEach items="${requestScope.coms}" var="c">
                        <div>
                            <span style="font-size: 30px; font-weight: bold; color: #ed5e05">
                                ${c.user.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </span>
                            <span>${c.postDate}</span>
                            <p>${c.content}</p>
                            <c:if test="${sessionScope.user.id == c.user.id}">
                                <form id="form_delete_${c.id}" action="deleteComment" method="post" style="display: inline">
                                    <input type="hidden" value="${requestScope.arti.id}" name="aid">
                                    <input type="hidden" name="cid" value="${c.id}">
                                    <button type="button" onclick="confirmDelete(${c.id})">Xóa</button>
                                </form>
                                <form action="editComment" style="float: right; margin-right: 500px">
                                    <input type="hidden" value="${requestScope.arti.id}" name="aid">
                                    <input type="hidden" name="cid" value="${c.id}">
                                    <input type="submit" value="Sửa"> 
                                </form>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="sidebar">
                <div class="part-content">
                    <h2>Tin cùng chuyên mục</h2>
                    <div class="part">
                        <c:forEach var="as" items="${requestScope.categorizedArticles}">
                            <h3 class="title-news">
                                <a href="detail?aid=${as.id}">
                                    ${as.title}</a>
                            </h3>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <footer>
            <div class="footer-div">
                <a href="home"><img src="https://static.thanhnien.com.vn/thanhnien.vn/image/logo.svg"
                                                                alt="Chúng ta"></a>
                <p>Giấy phép xuất bản số 110/GP - BTTTT cấp ngày 24.3.2020 © </br>
                   2003-2024 Bản quyền thuộc về Báo Thanh Niên. Cấm sao chép </br>
                   dưới mọi hình thức nếu không có sự chấp thuận bằng văn bản.</p>
                
                <p class="product">
                    <span>Một sản phẩm của </span>
                    <span><img src="https://st.chungta.vn/v355/chungta/images/graphics/logo_fpt.svg" alt="Fpt"></span>
                </p>
            </div>
        </footer>
    </body>
    <script>
        function confirmDelete(id) {
            if (confirm('Are you sure do delete this comment?')) {
                var formDelete = document.getElementById('form_delete_' + id);
                if (formDelete) {
                    formDelete.submit();
                }
            }
        }
    </script>
</html>
