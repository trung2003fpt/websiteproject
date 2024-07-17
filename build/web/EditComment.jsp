<%-- 
    Document   : EditComment
    Created on : Mar 10, 2023, 4:54:13 PM
    Author     : Nam
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit comment</title>
    </head>
    <body style="background-color: #ffba8e;">
        <h3>Edit comment</h3>
        <c:set var="c" value="${requestScope.comment}"/>
        <form action="editComment" method="post">
            <p>Content: <input type="text" name="content" value="${c.content}"></p>
            <input type="hidden" value="${c.id}" name="id">
            <input type="hidden" value="${c.article.id}" name="aid">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
