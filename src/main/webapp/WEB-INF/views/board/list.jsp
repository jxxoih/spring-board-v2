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
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
        function kakaoLogout() {
            Kakao.init('${kakaoSDKCode}');
            console.log(Kakao.Auth.getAccessToken())

            if(!!Kakao.Auth.getAccessToken()) {
                Kakao.Auth.logout();
            }

            window.location.href="/user/logout";
        }
    </script>
</head>
<body>

<a href="javascript:kakaoLogout()">
    로그아웃
</a>
<a href="/board/write">
    글작성
</a>

<table>
    <thead>
    <tr>
        <th>제목</th>
        <th>내용</th>
        <th>작성자</th>
        <th>작성일시</th>
        <th>조회수</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="article" items="${vo.articles}">
            <tr onclick="window.location.href='/board/read/${article.board_id}?p=${vo.page}'">
                <td>${article.board_title}</td>
                <td>${article.board_content}</td>
                <td>${article.board_writer}</td>
                <td>${article.formattedTimestamp}</td>
                <td>${article.view_count}</td>
            </tr>
        </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="5">
            <c:if test="${vo.maxPage > 1 && vo.page != 1}">
                <span>
                    <a href="/board/list/1">&lt;&lt;</a>
                </span>
            </c:if>
            <c:forEach var="i" begin="${vo.leftPage}" end="${vo.rightPage}" step="1">
                <c:if test="${i == vo.page}">
                    <span>
                        <strong>
                            <a>${i}</a>
                        </strong>
                    </span>
                </c:if>
                <c:if test="${i != vo.page}">
                    <span>
                        <a href="/board/list/${i}">${i}</a>
                    </span>
                </c:if>
            </c:forEach>
            <c:if test="${vo.maxPage > 1 && vo.page != vo.maxPage}">
                <span>
                    <a href="/board/list/${vo.maxPage}">&gt;&gt;</a>
                </span>
            </c:if>
        </td>

    </tr>

    </tfoot>
</table>


</body>
</html>