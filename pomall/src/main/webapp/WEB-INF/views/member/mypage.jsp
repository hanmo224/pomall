<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta
      name="author"
      content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
    />
    <meta name="generator" content="Hugo 0.101.0" />
    <title>Carousel Template · Bootstrap v4.6</title>

    <link
      rel="canonical"
      href="https://getbootstrap.com/docs/4.6/examples/pricing/"
    />

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
    <meta name="theme-color" content="#563d7c" />

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

        background: -webkit-gradient(
          linear,
          left bottom,
          right top,
          from(#92b5db),
          to(#1d466c)
        );
        background: -webkit-linear-gradient(
          bottom left,
          #92b5db 0%,
          #1d466c 100%
        );
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
        box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      }
    </style>
  </head>
  <body>
    <!-- header -->
    <%@ include file="/WEB-INF/views/include/header.jsp" %>

    <main role="main">
      <div class="container">
        <div class="input-form-backgroud row">
          <div class="input-form col-md-12 mx-auto">
            <h4 class="mb-3" style="text-align: center">마이페이지</h4>
            <form
              class="validation-form"
              id="joinForm"
              method="post"
              action="/member/join"
            >
              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="mb_name">이름</label>
                  <input
                    type="text"
                    class="form-control"
                    name="mb_name"
                    id="mb_name"
                    value="${memberVO.mb_name}"
                    readonly
                  />
                </div>
                <div class="col-md-6 mb-3">
                  <label for="mb_rrn">주민번호 앞자리</label>
                  <input
                    type="text"
                    class="form-control"
                    name="mb_rrn"
                    id="mb_rrn"
                    value="${memberVO.mb_rrn.substring(0,6)}"
                    readonly
                  />
                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="mb_id">아이디</label>
                  <input
                    type="text"
                    class="form-control"
                    name="mb_id"
                    id="mb_id"
                    value="${memberVO.mb_id}"
                    readonly
                  />
                </div>
                <div class="col-md-6 mb-3">
                  <label for="mb_nick">닉네임</label>
                  <input
                    type="text"
                    class="form-control"
                    name="mb_nick"
                    id="mb_nick"
                    value="${memberVO.mb_nick}"
                    readonly
                  />
                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="mb_email">이메일</label>
                  <input
                    type="email"
                    class="form-control"
                    name="mb_email"
                    id="mb_email"
                    value="${memberVO.mb_email}"
                    readonly
                  />
                </div>
                <div class="col-md-6 mb-3">
                  <label for="mb_phone">연락처</label>
                  <input
                    type="text"
                    class="form-control"
                    name="mb_phone"
                    id="mb_phone"
                    value="${memberVO.mb_phone}"
                    readonly
                  />
                </div>
              </div>

              <div class="row">
                <div class="col-md-2 mb-3">
                  <label for="sample2_postcode">우편번호</label>
                  <input
                    type="text"
                    class="form-control"
                    name="mb_zipcode"
                    id="sample2_postcode"
                    value="${memberVO.mb_zipcode}"
                    readonly
                  />
                </div>
                <div class="col-md-5 mb-3">
                  <label for="sample2_address">주소</label>
                  <input
                    type="text"
                    class="form-control"
                    name="mb_addr"
                    id="sample2_address"
                    value="${memberVO.mb_addr}"
                    readonly
                  />
                </div>
                <div class="col-md-5 mb-3">
                  <label for="sample2_detailAddress"
                    >상세주소<span class="text-muted"
                      >&nbsp;(필수 아님)</span
                    ></label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="mb_detaddr"
                    id="sample2_detailAddress"
                    value="${memberVO.mb_detaddr}"
                    readonly
                  />
                  <input
                    type="hidden"
                    id="sample2_extraAddress"
                    placeholder="참고항목"
                  />
                </div>
              </div>

              <hr class="mb-4" />
              <div class="row">
                <div class="col-md-12 mb-3">
                  <button type="button" class="btn btn-link" id="btn_modify">
                    회원정보수정
                  </button>
                  <button
                    type="button"
                    class="btn btn-link"
                    data-toggle="modal"
                    data-target="#deleteModal"
                  >
                    회원탈퇴
                  </button>
                  <button
                    type="button"
                    class="btn btn-link"
                    data-toggle="modal"
                    data-target="#pwchangeModal"
                  >
                    비밀번호 변경
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
        <!-- FOOTER -->
        <%@include file="/WEB-INF/views/include/footer.jsp" %>
      </div>
    </main>

    <!-- Modal -->
    <div
      class="modal fade"
      id="pwchangeModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">비밀번호 변경</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col-md-4 mb-3">
                <label for="old_mb_pw">기존비밀번호</label>
                <input type="password" class="form-control" id="old_mb_pw" />
              </div>
              <div class="col-md-4 mb-3">
                <label for="new_mb_pw">신규비밀번호</label>
                <input type="password" class="form-control" id="new_mb_pw" />
              </div>
              <div class="col-md-4 mb-3">
                <label for="re_mb_pw">비밀번호확인</label>
                <input type="password" class="form-control" id="re_mb_pw" />
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-dismiss="modal"
            >
              취소
            </button>
            <button type="button" class="btn btn-primary" id="btn_pwchange">
              변경
            </button>
          </div>
        </div>
      </div>
    </div>

    <div
      class="modal fade"
      id="deleteModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">회원탈퇴</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col-md-4 mb-3">
                <label for="mb_pw">비밀번호</label>
                <input type="password" class="form-control" id="mb_pw" />
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-dismiss="modal"
            >
              취소
            </button>
            <button type="button" class="btn btn-primary" id="btn_delete">
              탈퇴
            </button>
          </div>
        </div>
      </div>
    </div>

    <script>
      $(document).ready(function () {
        // 비밀번호 변경
        $("#btn_pwchange").click(function () {
          if ($("#new_mb_pw").val() != $("#re_mb_pw").val()) {
            alert("비밀번호가 일치하지 않습니다.");
            $("#new_mb_pw").val("");
            $("#new_mb_pw").focus();
            return;
          }

          $.ajax({
            url: "/member/pwchange",
            type: "post",
            dataType: "text",
            data: {
              old_mb_pw: $("#old_mb_pw").val(),
              new_mb_pw: $("#new_mb_pw").val(),
            },
            success: function (result) {
              if (result == "success") {
                alert("비밀번호 변경이 되었습니다.");
                $("#old_mb_pw").val("");
                $("#new_mb_pw").val("");
                $("#re_mb_pw").val("");

                //$('#pwchangeModal').modal('hide'); // 변경 버튼 누르면 화면에서 창이 사라지게 한다.

                location.href = "/member/login";
              } else if (result == "failPW") {
                alert("기존 비밀번호가 틀립니다. \n확인바랍니다.");
                $("#old_mb_pw").val(""); // 입력했던 비번 초기화
                $("#old_mb_pw").focus();
              }
            },
          });
        });

        // 회원정보 수정
        $("#btn_modify").click(function () {
          location.href = "/member/modify";
        });

        // 회원 탈퇴
        $("#btn_delete").click(function () {
          $.ajax({
            url: "/member/delete",
            type: "post",
            data: { mb_pw: $("#mb_pw").val() },
            dataType: "text",
            success: function (result) {
              if (result == "success") {
                alert("회원탈퇴 처리가 되었습니다.");
                $("#deleteModal").modal("hide"); // 취소 버튼 누르면 화면에서 창이 사라지게 한다.

                //console.log($("#mb_pw").val());

                location.href = "/";
              } else if (result == "failPW") {
                alert("기존 비밀번호가 틀립니다. \n확인바랍니다.");
                $("#mb_pw").val("");
                $("#mb_pw").focus();
              }
            },
          });
        });
      });
    </script>
  </body>
</html>
