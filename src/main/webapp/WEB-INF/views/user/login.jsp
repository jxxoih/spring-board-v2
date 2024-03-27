<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
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
<a id="kakao-login-btn" href="javascript:kakaoLogin()">
    <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222"
         alt="카카오 로그인 버튼" />
</a>

<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
    Kakao.init('${kakaoSDKCode}'); // 사용하려는 앱의 JavaScript 키 입력

    function kakaoLogin() {
        Kakao.Auth.login({
            scope: 'profile_nickname, account_email',
            success: function (authObj) {
                Kakao.Auth.setAccessToken(authObj.access_token);
                console.log("kakaoToken::", Kakao.Auth.getAccessToken());
                Kakao.API.request({
                    url: '/v2/user/me',
                    success: res => {
                        const kakao_account = res.kakao_account;
                        kakaoData = {
                            kakao_account: kakao_account,
                            authObj: authObj
                        }
                        reqPostData(kakaoData);
                    }
                });
            }
        });
    }

    function reqPostData(kakaoData) {
        const formData = new FormData();
        formData.append('email', kakaoData.kakao_account.email);
        formData.append('accessToken', kakaoData.authObj.access_token)



        let xhr = new XMLHttpRequest();
        xhr.open('POST', '/user/login/oauth/code/kakao');
        xhr.onreadystatechange = () => {
            if(xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    if(!!xhr.response) {
                        window.location.href="/board/list";
                    }
                } else {
                    console.log("failure")
                }
            }
        };
        xhr.send(
            formData
        );

        return null;
    }
</script>

</body>
</html>