<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
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

로그인 완료 리스트.
<a href="javascript:kakaoLogout()">
    로그아웃
</a>


</body>
</html>