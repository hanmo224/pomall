<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

      #user_content {
        max-width: 100%;
        height: auto;
      }
    </style>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/include/header.jsp" %> <%@ include
    file="/WEB-INF/views/include/categoryMenu.jsp" %>

    <div class="container" style="margin: 0 auto">
      <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <h4 class="display-4">게시판 상세</h4>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="box box-primary">
            <form id="operForm" action="/board/modify" method="get">
              <input
                type="hidden"
                name="user_num"
                value='<c:out value="${board.user_num }" />'
              />
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
            </form>
            <!-- form start -->
            <form role="form" method="post" action="/board/insert">
              <div class="box-body">
                <div class="form-group">
                  <label for="user_num">번호</label>
                  <input
                    type="text"
                    class="form-control"
                    id="user_num"
                    name="user_num"
                    value='<c:out value="${board.user_num }" />'
                    readonly
                  />
                </div>
                <div class="form-group">
                  <label for="user_title">제목</label>
                  <input
                    type="text"
                    class="form-control"
                    id="user_title"
                    name="user_title"
                    value='<c:out value="${board.user_title }" />'
                    readonly
                  />
                </div>
                <div class="form-group">
                  <label for="mb_id">작성자</label>
                  <input
                    type="text"
                    class="form-control"
                    id="mb_id"
                    name="mb_id"
                    <c:choose>
                      <c:when test="${sessionScope.loginStatus.mb_id == board.mb_id}">
                        value="${sessionScope.loginStatus.mb_id}"
                      </c:when>
                      <c:otherwise>
                        value="${board.mb_id}"
                      </c:otherwise>
                    </c:choose>
                    readonly
                  />
                </div>
                <div class="form-group">
                  <label for="user_content">내용</label>
                  <div
                    class="form-control"
                    rows="3"
                    id="user_content"
                    name="user_content"
                    readonly
                    style="
                      word-wrap: break-word;
                      white-space: pre-line;
                      overflow-wrap: anywhere;
                    "
                  >
                    <c:out value="${board.user_content}" escapeXml="false" />
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-md-6">
                    <label for="user_reg_date">등록일</label>
                    <input
                      type="text"
                      class="form-control"
                      id="user_reg_date"
                      name="user_reg_date"
                      value='<fmt:formatDate value="${board.user_reg_date }" pattern="yyyy-MM-dd HH:mm"/>'
                      readonly
                    />
                  </div>
                  <div class="col-md-6">
                    <label for="user_up_date">수정일</label>
                    <input
                      type="text"
                      class="form-control"
                      id="user_up_date"
                      name="user_up_date"
                      value='<fmt:formatDate value="${board.user_up_date }" pattern="yyyy-MM-dd HH:mm"/>'
                      readonly
                    />
                  </div>
                </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer row" style="text-align: center">
                <c:set var="disableButtons" value="${sessionScope.loginStatus.mb_id != board.mb_id}" />
                <div class="col-md-12">
                  <c:if test="${not disableButtons}">
                    <button type="button" id="btn_modify" class="btn btn-primary">
                      수정
                    </button>
                    <button type="button" id="btn_delete" class="btn btn-primary">
                      삭제
                    </button>
                  </c:if>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <%@include file="/WEB-INF/views/include/footer.jsp" %>
    </div>

    <script>
      $(document).ready(function () {
        let operForm = $("#operForm");

        //삭제 클릭시
        $("#btn_delete").on("click", function () {
          if (!confirm("삭제 하시겠습니까?")) return;

          operForm.attr("action", "/board/delete");
          operForm.submit();
        });

        //수정 클릭시
        $("#btn_modify").on("click", function () {
          operForm.attr("action", "/board/modify");
          operForm.submit();
        });
      });
    </script>
  </body>
</html>
