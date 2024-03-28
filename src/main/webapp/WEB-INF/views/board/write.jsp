<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Title</title>

</head>
<body>

<form method="post">
    <div>
        <input type="text" placeholder="제목을 입력하세요" name="board_title" value="${vo.board_title}">
    </div>
    <div>
        <textarea maxlength="1000" placeholder="내용을 입력하세요" name="board_content">${vo.board_content}</textarea>
    </div>
    <div class="action">
        <input type="submit" value="글작성">
    </div>
</form>

</body>
</html>