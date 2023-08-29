<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Starter</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="/WEB-INF/views/admin/include/plugin1.jsp"%>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <!-- Main Header -->
  <%@include file="/WEB-INF/views/admin/include/header.jsp" %>
  <!-- Left side column. contains the logo and sidebar -->
  <%@include file="/WEB-INF/views/admin/include/nav.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        사용자 게시판 목록
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
        <li class="active">Here</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

      <!--------------------------
        | Your Page Content Here |
        -------------------------->
        
        <div class="row">
        	<div class="col-md-12">
        		<div class="box">
              <div class="box-header with-border">
                <form id="searchForm" action="/admin/board/user_board_list" method="get">
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
                      <td><c:out value="${list.mb_id }"></c:out></td>
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
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <%@include file="/WEB-INF/views/admin/include/footer.jsp" %>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane active" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="pull-right-container">
                    <span class="label label-danger pull-right">70%</span>
                  </span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Some information about this general settings option
            </p>
          </div>
          <!-- /.form-group -->
        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<%@include file="/WEB-INF/views/admin/include/plugin2.jsp"%>

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
      
      actionForm.attr("action", "/admin/board/user_board_get");
  
      actionForm.submit();
    });
  
    // 페이지번호, 이전, 다음 클릭
    $("ul.pagination a").on("click", function(e) {
      e.preventDefault(); 

        // actionForm의 정보를 초기화 시켜주는 작업
      actionForm.attr("action", "/admin/board/user_board_list");
      
      actionForm.find("input[name='pageNum']").val($(this).attr("href"));

      actionForm.submit();
    });
  });
  </script>
</body>
</html>