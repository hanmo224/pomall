<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>Pricing example · Bootstrap v4.6</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/pricing/">

    <%@ include file="/WEB-INF/views/include/config.jsp" %>

<!-- Favicons 
<link rel="apple-touch-icon" href="/docs/4.6/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/4.6/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/4.6/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon.ico">
<meta name="msapplication-config" content="/docs/4.6/assets/img/favicons/browserconfig.xml">
-->
<meta name="theme-color" content="#563d7c">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      
      body {
      min-height: 100vh;

      background: -webkit-gradient(linear, left bottom, right top, from(#92b5db), to(#1d466c));
      background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
    }

    .input-form {
      max-width: 680px;

      margin-top: 80px;
      padding: 32px;

      background: #fff;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
      -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
    }
    </style>
    
    <script>
      $(document).ready(function() {

        let msg = '${msg}';

        if(msg == 'failID') {
          alert("아이디 확인바람");
          $("#mb_id").focus();
        }else if(msg == 'failPW') {
          alert("비밀번호 확인바람");
          $("#mb_pw").focus();
        }
      
        // 저장된 사용자 ID가 있는지 확인하고 입력 값을 설정
        if (localStorage.getItem("savedUserID")) {
          $("#mb_id").val(localStorage.getItem("savedUserID"));

          // 체크박스 상태 유지
          $("input[type=checkbox]").prop("checked", true);
        }
      
        // 저장 선택을하면 사용자 ID 저장
        $("input[type=checkbox]").on("click", function() {
          if ($(this).is(":checked")) {
            localStorage.setItem("savedUserID", $("#mb_id").val());
          }
          else {
            localStorage.removeItem("savedUserID");
          }
        });

         // 새로운 아이디값이 입력이 돼면 기존 값은 삭제
        $("#mb_id").on("input", function() {
          if ($(this).val() !== localStorage.getItem("savedUserID")) {
            localStorage.removeItem("savedUserID");
            // 체크상태 제거
            $("input[type=checkbox]").prop("checked", false);
          }
        });
      
        $("#btn_findID").on("click", function() {
          location.href = "/member/findID";
        });
      
        $("#btn_findPW").on("click", function() {
          location.href = "/member/findPW";
        });
        
      });
    </script>
      
  </head>
  <body>
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>


<div class="container">
   <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-3 text-center">로그인</h4>
            <!-- form start -->
            <form class="form-horizontal" method="post" action="/member/login">
              <div class="box-body">
                <div class="form-group">
                  <label for="mb_id" class="col-sm-2 control-label">ID</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="mb_id" id="mb_id" placeholder="아이디를 입력하세요.">
                  </div>
                </div>
                <div class="form-group">
                  <label for="mb_pw" class="col-sm-2 control-label">Password</label>

                  <div class="col-sm-10">
                    <input type="password" class="form-control" name="mb_pw" id="mb_pw" placeholder="비밀번호를 입력하세요.">
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                      <label>
                        <input type="checkbox"> 아이디 저장하기
                      </label>
                    </div>
                  </div>
                </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer text-center">
                <button type="submit" class="btn btn-info pull-right">로그인</button>
                <button type="button" class="btn btn-info pull-right" id="btn_findID">아이디 찾기</button>
                <button type="button" class="btn btn-info pull-right" id="btn_findPW">비밀번호 찾기</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>
      </div>
    </div>
  <%@include file="/WEB-INF/views/include/footer.jsp" %>


  </body>
</html>

