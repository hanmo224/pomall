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
	
  </head>
  <body>
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container">
   <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-3 text-center">아이디 찾기</h4>
        <div class="box box-info">
            <!-- form start -->
            <form class="form-horizontal" method="post" action="/member/findID">
              <div class="box-body">
                <div class="form-group">
                  <label for="find_mb_name" class="col-sm-2 control-label">이름</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="find_mb_name" id="find_mb_name" placeholder="이름을 입력하세요.">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="find_mb_email" class="col-sm-2 col-form-label">이메일</label>
                  <div class="col-sm-4">
                      <input type="email" class="form-control" name="find_mb_email" id="find_mb_email" placeholder="you@example.com">
                  </div>
                  <label for="mb_email_authcode" class="col-sm-2 col-form-label">인증코드</label>
                  <div class="col-sm-4">
                      <button type="button" class="btn btn-primary" id="btn_authcode">받기</button>
                  </div>
                </div>

                <div class="form-group row">
                  <label for="email_auth_code" class="col-sm-2 col-form-label">인증코드</label>
                  <div class="col-sm-4">
                      <input type="text" class="form-control" name="email_auth_code" id="email_auth_code">
                  </div>
                  <label for="mb_mailconfirm" class="col-sm-2 col-form-label">인증코드</label>
                  <div class="col-sm-4">
                      <button type="button" class="btn btn-primary" id="btn_confirmAuthcode">확인</button>
                  </div>
                </div>
                  <!--
                  <div class="box-footer text-center">
                    <button type="button" class="btn btn-info pull-right" id="btn_findID">찾기</button>
                  </div>
                  -->
                </div>
              </form>
               </div> 
                </div>
              </div>
              <!-- /.box-body -->
              
              <!-- /.box-footer -->
            </div>
  <%@include file="/WEB-INF/views/include/footer.jsp" %>



<script>

  $(document).ready(function() {

    let isAuthcode = false;

    // 메일인증코드
    $("#btn_authcode").click(function(){
        if($("#find_mb_email").val() == "") {
          alert("메일주소를 입력하세요.");
          $("#find_mb_email").focus();
          return;
        }

        $.ajax({
          url: '/email/send',
          type: 'get',
          dataType: 'text',   // 스프링 메서드의 리턴타입. 'xml', 'html', 'json', 'text'
          data: {receiverMail : $("#find_mb_email").val() }, // Javascript Object문법. 스프링 메서드에 전달할 파라미터
          success : function(result) {
            if(result == "success") {
              alert("인증메일이 발송되었습니다. 메일 확인해주세요.");
            }
          }
        });
      });

    //인증확인
    $("#btn_confirmAuthcode").click(function() {
        if($("#email_auth_code").val() == "") {
            alert("인증코드를 입력하세요.");
            $("#email_auth_code").focus();
            return;
        }

        $.ajax({
            url: '/email/confirmAuthcode',
            type: 'get',
            dataType: 'text', // 스프링 메서드의 리턴타입. 'xml', 'html', 'json', 'text'
            data: {authCode : $("#email_auth_code").val() }, // Javascript Object문법. 스프링 메서드에 전달할 파라미터
            success : function(result) {
              if(result == "success") {
                  alert("인증코드가 확인 되었습니다.");

                  isAuthcode = true;

                  $.ajax({
                      url: '/member/findID',
                      type: 'post',
                      data: {mb_name: $("#find_mb_name").val(), mb_email: $("#find_mb_email").val()},
                      dataType: 'text',
                      success: function(data) {
                        if (data == "success") {
                              alert("아이디를 이메일로 보냈습니다. \n확인해주세요.");

                              location.href = "/member/login";
                          }
                      }
                  });
                }else if(result == "fail") {
                  alert("인증코드가 다릅니다. 인증코드 요청을 다시 해주세요.");
                  $("#email_auth_code").val(''); 
                  $("#email_auth_code").focus(); 
                }
              }
            });
          });

  });

</script>
  
    
  </body>
</html>

