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

<table>
    <thead>
    <tr>
        <th>제목</th>
        <td>${vo.article.board_title}</td>
        <th>작성자</th>
        <td>${vo.article.board_writer}</td>
        <th>작성일시</th>
        <td>${vo.article.formattedTimestamp}</td>
        <th>조회수</th>
        <td>${vo.article.view_count}</td>
    </tr>
    </thead>
    <tbody>
    <tr>
       <td colspan="8">
           ${vo.article.board_content}
       </td>
    </tr>
    </tbody>
    <tfoot>
        <tr>
            <td colspan="8">
                <a href="/board/list/${param.p}">목록</a>
                <c:if test="${vo.user.email == vo.article.board_writer}">
                    <a href="/board/delete/${vo.article.board_id}">글삭제</a>
                    <a href="/board/edit/${vo.article.board_id}">글수정</a>
                </c:if>

            </td>
        </tr>
    </tfoot>
</table>

</body>
</html>