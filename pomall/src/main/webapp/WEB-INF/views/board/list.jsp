<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
    </style>

  </head>
  <body>
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ include file="/WEB-INF/views/include/categoryMenu.jsp" %>

<div class="container" style="margin: 0 auto;">
  <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center"> 
    <h4 class="display-4">게시판</h4> 
  </div>
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header with-border">
          <form id="searchForm" action="/board/list" method="get">
              <div class="form-group" style="display: flex; justify-content: space-between; align-items: center;">
                  <div>
                      <select name="type">
                          <option value="" <c:out value="${pageMaker.cri.type == null? 'selected':'' }" />>-----</option>
                          <option value="T" <c:out value="${pageMaker.cri.type eq 'T'? 'selected':'' }" />>제목</option>
                          <option value="M" <c:out value="${pageMaker.cri.type eq 'M'? 'selected':'' }" />>작성자</option>
                      </select>
                      <input type="text" name="keyword" placeholder="검색어를 입력하세요." value='<c:out value="${pageMaker.cri.keyword }" />'>
                      <input type="hidden" name="pageNum" value="1">
                      <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                      <button type="submit" class="btn btn-success">검색</button>
                  </div>
                  <div>
                      <button type="button" class="btn btn-success" id="btn_insert">글쓰기</button>
                  </div>
              </div>
          </form>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <table class="table table-bordered">
            <tbody>
              <tr>
                <th style="width: 8%">글번호</th>
                <th style="width: 8%">제목</th>
                <th style="width: 8%">작성자</th>
                <th style="width: 10%">등록일</th>
                <th style="width: 10%">수정일</th>
              </tr>

              <c:forEach items="${boardList }" var="list">
                <tr>
                  <td><c:out value="${list.user_num }"></c:out></td>
                  <td>
                    <a class="move" href="${list.user_num }"><c:out value="${list.user_title }" ></c:out></a>
                  </td>
                  <td><c:if test="${sessionScope.loginStatus.mb_id == list.mb_id}">
                        <c:out value="${sessionScope.loginStatus.mb_id}"></c:out>
                      </c:if>
                      <c:if test="${sessionScope.loginStatus.mb_id != list.mb_id}">
                        <c:out value="${list.mb_id}"></c:out>
                      </c:if>
                  </td>
                  <td><fmt:formatDate value="${list.user_reg_date }" pattern="yyyy-MM-dd HH:mm"/></td>
                  <td><fmt:formatDate value="${list.user_up_date }" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <!-- /.box-body -->
        <!-- [이전] 1 2 3 4 5 [다음] 출력작업 -->
        <div class="box-footer clearfix" style="display: flex; justify-content: center; align-items: center;">
          <ul class="pagination pagination-sm no-margin pull-right">
              <c:if test="${pageMaker.prev }">
                  <li><a href="${pageMaker.startPage - 1}">[prev]</a></li>
              </c:if>

              <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
                  <li ${pageMaker.cri.pageNum == pageNum ? "class='active'": ""}><a href="${pageNum }">${pageNum }</a></li>
              </c:forEach>

              <c:if test="${pageMaker.next }">
                  <li><a href="${pageMaker.endPage + 1}">[next]</a></li>
              </c:if>
          </ul>
          <!-- 페이징정보. Criteria 클래스 필드정보작업 -->
          <form id="actionForm" action="/board/list" method="get">
              <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
              <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
              <input type="hidden" name="type" value="${pageMaker.cri.type}">
              <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
          </form>
        </div>
      </div>
    </div>
  </div>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>

<script>
  // jQuery 기반으로 작업
  $(document).ready(function() {

    let actionForm = $("#actionForm");

    // 리스트(목록)에서 제목 클릭
    $("a.move").on("click", function(e) {
      e.preventDefault();
                
      let user_num = $(this).attr("href");

      // 이전 작업을 초기화 해주는 작업.
      actionForm.find("input[name= 'user_num']").remove(); 

      actionForm.append("<input type='hidden' name='user_num' value='" + user_num + "'>");
      
      actionForm.attr("action", "/board/get");
  
      actionForm.submit();
    });

    // 글쓰기 버튼 클릭
    $("#btn_insert").on("click", function() {
      location.href = "/board/insert";
    })
  
    // 페이지번호, 이전, 다음 클릭
    $("ul.pagination a").on("click", function(e) {
      e.preventDefault(); 

        // actionForm의 정보를 초기화 시켜주는 작업
      actionForm.attr("action", "/board/list");
      
      actionForm.find("input[name='pageNum']").val($(this).attr("href"));

      actionForm.submit();
    });
  });
  </script>

  </body>
</html>