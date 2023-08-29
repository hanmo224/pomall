<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>AdminLTE 2 | Starter</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta
      content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
      name="viewport"
    />
    <%@include file="/WEB-INF/views/admin/include/plugin1.jsp"%>

    <style>
      #user_content {
        max-width: 100%;
        height: auto;
      }
    </style>
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
          <h1>게시판 관리</h1>
          <ol class="breadcrumb">
            <li>
              <a href="#"><i class="fa fa-dashboard"></i> Level</a>
            </li>
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
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">상세보기</h3>
                </div>
                <!-- /.box-header -->
                <!-- Modify or Delete 클릭시 사용 -->
                <form id="operForm" action="" method="">
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
                <form role="form" method="post" action="">
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
                        value='<c:out value="${board.mb_id }" />'
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
                        <c:out
                          value="${board.user_content}"
                          escapeXml="false"
                        />
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

                  <div class="box-footer row">
                    <div class="col-md-12">
                      <button
                        type="button"
                        id="btn_delete"
                        class="btn btn-primary"
                      >
                        삭제
                      </button>
                    </div>
                  </div>
                </form>
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
          <li class="active">
            <a href="#control-sidebar-home-tab" data-toggle="tab"
              ><i class="fa fa-home"></i
            ></a>
          </li>
          <li>
            <a href="#control-sidebar-settings-tab" data-toggle="tab"
              ><i class="fa fa-gears"></i
            ></a>
          </li>
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
                    <h4 class="control-sidebar-subheading">
                      Langdon's Birthday
                    </h4>

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
                    <div
                      class="progress-bar progress-bar-danger"
                      style="width: 70%"
                    ></div>
                  </div>
                </a>
              </li>
            </ul>
            <!-- /.control-sidebar-menu -->
          </div>
          <!-- /.tab-pane -->
          <!-- Stats tab content -->
          <div class="tab-pane" id="control-sidebar-stats-tab">
            Stats Tab Content
          </div>
          <!-- /.tab-pane -->
          <!-- Settings tab content -->
          <div class="tab-pane" id="control-sidebar-settings-tab">
            <form method="post">
              <h3 class="control-sidebar-heading">General Settings</h3>

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Report panel usage
                  <input type="checkbox" class="pull-right" checked />
                </label>

                <p>Some information about this general settings option</p>
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

    <script src="/bower_components/ckeditor/ckeditor.js"></script>

    <script>
      $(document).ready(function () {
        let operForm = $("#operForm");

        //삭제 클릭시
        $("#btn_delete").on("click", function () {
          if (!confirm("삭제 하시겠습니까?")) return;

          operForm.attr("action", "/admin/board/user_board_delete");
          operForm.submit();
        });
      });
    </script>
  </body>
</html>
