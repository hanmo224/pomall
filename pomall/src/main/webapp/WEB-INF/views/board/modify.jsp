<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>

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
    <title>Pricing example · Bootstrap v4.6</title>

    <link
      rel="canonical"
      href="https://getbootstrap.com/docs/4.6/examples/pricing/"
    />

    <%@ include file="/WEB-INF/views/include/config.jsp" %>

    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    />
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
    </style>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/include/header.jsp" %> <%@ include
    file="/WEB-INF/views/include/categoryMenu.jsp" %>

    <div class="container" style="margin: 0 auto">
      <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <h4 class="display-4">수정</h4>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="box box-primary">
            <!-- form start -->
            <form role="form" method="post" action="/board/modify">
              <div class="box-body">
                <div class="form-group">
                  <label for="user_title">제목</label>
                  <input
                    type="hidden"
                    name="pageNum"
                    value='<c:out value="${cri.pageNum }" />'
                  />
                  <input
                    type="hidden"
                    name="amount"
                    value='<c:out value="${cri.amount }" />'
                  />
                  <input
                    type="hidden"
                    name="type"
                    value='<c:out value="${cri.type }" />'
                  />
                  <input
                    type="hidden"
                    name="keyword"
                    value='<c:out value="${cri.keyword }" />'
                  />
                  <input
                    type="hidden"
                    name="user_num"
                    value='<c:out value="${board.user_num}" />'
                  />
                  <input
                    type="text"
                    class="form-control"
                    id="user_title"
                    name="user_title"
                    value='<c:out value="${board.user_title}" />'
                  />
                </div>
                <div class="form-group">
                  <label for="mb_id">작성자</label>
                  <input
                    type="text"
                    class="form-control"
                    id="mb_id"
                    name="mb_id"
                    value="${sessionScope.loginStatus.mb_id}"
                    readonly
                  />
                </div>
                <div class="form-group">
                  <label for="user_content">내용</label>
                  <textarea
                    class="form-control"
                    rows="3"
                    id="user_content"
                    name="user_content"
                  >
                    <c:out value="${board.user_content}" />
                  </textarea>
                </div>
              </div>
              <!-- /.box-body -->

              <div class="box-footer" style="text-align: center">
                <button type="submit" id="btn_modify" class="btn btn-primary">
                  수정
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <%@include file="/WEB-INF/views/include/footer.jsp" %>
    </div>

    <script src="/bower_components/ckeditor/ckeditor.js"></script>

    <script>
      $(document).ready(function () {
        // ckeditor 버전 4.12.1 환경설정. javascript object 구문사용
        let ckeditor_config = {
          resize_enabled: false,
          enterMode: CKEDITOR.ENTER_BR,
          shiftEnterMode: CKEDITOR.ENTER_P,
          toolbarCanCollpase: true,
          removePlugins: "elementspath",
          filebrowserUploadUrl: "/board/imageUpload", // 에디터란에 이미지를 클릭했을 때 업로드 탭이 표시된다. 서버 전송시 이 주소로 전송
        };

        CKEDITOR.replace("user_content", ckeditor_config);

        //수정 클릭시
        $("#btn_modify").on("click", function () {
          if (!confirm("수정 하시겠습니까?")) return;
        });
      });
    </script>
  </body>
</html>
