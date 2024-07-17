<%-- 
    Document   : AddComment
    Created on : Mar 10, 2023, 3:50:04 PM
    Author     : Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add comment</title>
    </head>
    <body>
        <form action="addComment" method="post">
            <p>Content: <input type="text" name="content"></p>
            <p>Post date: <input type="date" name="postdate"></p>
            <p>Comment writer Id: <input type="number" name="writer"></p>
            <p>Article Id: <input type="number" name="article"></p>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
