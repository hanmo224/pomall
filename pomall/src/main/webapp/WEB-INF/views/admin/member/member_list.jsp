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

<script>

  let msg = "${msg}";
  if(msg == 'delete') {
    alert("삭제되었습니다.");
  };

</script>

</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
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
      <h1>회원목록</h1>
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
                <div class="form-group row">
                  <div class="col-md-12">
                  <form id="searchForm" action="" method="get">
                    가입일 : <input type="date" name="create_sDate" value="${create_sDate}"> ~ <input type="date" name="create_eDate" value="${create_eDate}">
                    수정일 : <input type="date" name="up_sDate" value="${up_sDate}"> ~ <input type="date" name="up_eDate" value="${up_eDate}">
                    <select name="type">
                      <option value="" <c:out value="${pageMaker.cri.type == null? 'selected':'' }" />>-----</option>
                      <option value="N" <c:out value="${pageMaker.cri.type eq 'N'? 'selected':'' }" />>닉네임</option>
                      <option value="NA" <c:out value="${pageMaker.cri.type eq 'NA'? 'selected':'' }" />>이름</option>    
                      <option value="G" <c:out value="${pageMaker.cri.type eq 'G'? 'selected':'' }" />>성별</option>
                    </select>
                    <input type="text" name="keyword" placeholder="검색어를 입력하세요." value='<c:out value="${pageMaker.cri.keyword }" />'>
                    <input type="hidden" name="pageNum" value="1">
                    <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                    <button type="button" id="btn_member_search" class="btn btn-success">검색</button>
                   </form>
                  </div>
                </div>
             
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tbody>
                  <tr>
                    <th style="width: 5%">이름</th>
                    <th style="width: 5%">닉네임</th>
                    <th style="width: 10%">이메일 주소</th>
                    <th style="width: 10%">전화번호</th>
                    <th style="width: 10%">가입일</th>
                    <th style="width: 10%">수정일</th>
                    <th style="width: 5%">수신동의</th>
                    <th style="width: 5%">관리</th>
                  </tr>
                  <c:forEach items="${memberList }" var="memberVO">
                  <tr>
                    <td>${memberVO.mb_name }</td>
                    <td>${memberVO.mb_nick }</td>
                    <td>${memberVO.mb_email }</td>
                    <td>${memberVO.mb_phone }</td>
                    <td><fmt:formatDate value="${memberVO.join_date }" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><fmt:formatDate value="${memberVO.up_date }" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${memberVO.mb_receive }</td>
                    <td>
                      <button type="button" class="btn btn-link" name="btn_memberdetail" data-mb_id="${memberVO.mb_id}">상세</button>
                    </td>
                  </tr>
                </c:forEach> 
              </tbody>
            </table>
            </div>
            <!-- /.box-body -->
            <!-- [이전] 1 2 3 4 5 [다음] 출력작업 -->
            <div class="box-footer clearfix" style="display: flex; justify-content: center; align-items: center;">
              <ul class="pagination pagination-sm no-margin">
                <c:if test="${pageMaker.prev}">
                  <li><a href="${pageMaker.startPage - 1}">[prev]</a></li>
                </c:if>
                
                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
                  <li ${pageMaker.cri.pageNum == pageNum ? "class='active'": ""}><a href="${pageNum}">${pageNum}</a></li>
                </c:forEach>
                
                <c:if test="${pageMaker.next}">
                  <li><a href="${pageMaker.endPage + 1}">[next]</a></li>
                </c:if>
              </ul>
              <!-- 페이징정보. Criteria 클래스 필드정보작업 -->
              <form id="actionForm" action="" method="">
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
<!-- 1)Include Handlebars from a CDN -->
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<!-- 2)handlebars template. 댓글UI -->
<script id="memberDetailView" type="text/x-handlebars-template">
 <table class="table table-bordered">
 <tbody>
  <tr>
   <th style="width: 10%">이름</th>
   <th style="width: 10%">아이디</th>
   <th style="width: 8%">성별</th>
   <th style="width: 10%">포인트</th>
   <th style="width: 7%">주소</th>
   <th style="width: 15%">기본주소</th>
   <th style="width: 15%">상세주소</th>
   <th style="width: 15%">상태</th>
 </tr>
 {{#each .}}
   <tr>
   <td style="width: 10%">{{mb_name}}</td>
   <td style="width: 10%">{{mb_id}}</td>
   <td style="width: 8%">{{mb_gender}}</td>
   <td style="width: 10%">{{mb_point}}</td>
   <td style="width: 7%">{{mb_zipcode}}</td>
   <td style="width: 15%">{{mb_addr}}</td>
   <td style="width: 15%">{{mb_detaddr}}</td>
   <td style="width: 15%">{{status}}</td>
   </tr>	
 {{/each}}
 </tbody>
 </table>
</script>

<script>
  $(document).ready(function() {

      // 목록에서 상세 버튼을 클릭 
      $("button[name='btn_memberdetail']").on("click", function() {

        // ajax요청에서 주문상세정보 json형태로 받는 작업
        
        let mb_id = $(this).data("mb_id");

        let url = "/admin/member/member_detail?mb_id=" + mb_id;

        $.getJSON(url, function(data) {
        
          console.log(data);  
          
          let templateObj = Handlebars.compile($("#memberDetailView").html());
          let memberDetailVwHtml = templateObj(data); // 테이블과 댓글데이타가 바인딩된 결과
          
          // 추가된 기존 상세정보를 제거.
          // 동적으로 가져오는 데이터는 계속 누적이 되므로 비워준후 추가 시킨다.
          $("#modalDetailView").empty();

          $("#modalDetailView").append(memberDetailVwHtml);

          $('#memberDetailModal').modal('show');
        });
      });

     // 검색 클릭
     let searchForm = $("#searchForm");
     $("#btn_member_search").on("click", function() {
      searchForm.attr("action", "/admin/member/member_list");
      searchForm.find("input[name='pageNum']").val(1);
      searchForm.submit();
     });

     // 페이징
     let actionForm = $("#actionForm");

     $("ul.pagination a").on("click", function(e) {
      e.preventDefault(); // a태그의 하이퍼링크 기능이 취소.

      // 현재 페이지번호 변경작업
      // form태그의 정보  
      // <input type="hidden" name="pageNum" value="1">
      // <input type="hidden" name="amount" value="10">
      actionForm.attr("action", "/admin/member/member_list");
      actionForm.find("input[name='pageNum']").val($(this).attr("href"));

      actionForm.find("input[name='creata_sDate']").remove();
      actionForm.find("input[name='creata_eDate']").remove();
      actionForm.find("input[name='up_sDate']").remove();
      actionForm.find("input[name='up_eDate']").remove();

      actionForm.append("<input type='hidden' name='creata_sDate' value='${creata_sDate}'>");
      actionForm.append("<input type='hidden' name='creata_eDate' value='${creata_eDate}'>");
      actionForm.append("<input type='hidden' name='up_eDate' value='${up_eDate}'>");
      actionForm.append("<input type='hidden' name='up_eDate' value='${up_eDate}'>");

      actionForm.submit();

      });

  }); // jquery ready event end
</script>
<!-- Button trigger modal 
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
  Launch demo modal
</button>
-->
<!-- Modal -->
<div class="modal fade" id="memberDetailModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">회원상세내역</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="modalDetailView">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>        
      </div>
    </div>
  </div>
</div>

</body>
</html>