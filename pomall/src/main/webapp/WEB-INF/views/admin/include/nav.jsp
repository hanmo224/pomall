<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<aside class="main-sidebar">
  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar">
    <!-- Sidebar user panel (optional) -->
    <div class="user-panel">
      <div class="pull-left image">
        <img
          src="/dist/img/user2-160x160.jpg"
          class="img-circle"
          alt="User Image"
        />
      </div>
      <div class="pull-left info">
        <p>Alexander Pierce</p>
        <!-- Status -->
        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
      </div>
    </div>

    <!-- search form (Optional) -->
    <form action="#" method="get" class="sidebar-form">
      <div class="input-group">
        <input
          type="text"
          name="q"
          class="form-control"
          placeholder="Search..."
        />
        <span class="input-group-btn">
          <button
            type="submit"
            name="search"
            id="search-btn"
            class="btn btn-flat"
          >
            <i class="fa fa-search"></i>
          </button>
        </span>
      </div>
    </form>
    <!-- /.search form -->

    <!-- Sidebar Menu -->
    <ul class="sidebar-menu tree" data-widget="tree">
      <li class="header">목록</li>
      <!-- Optionally, you can add icons to the links 
        <li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>
        <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>
      -->

      <!-- 각 기능별 메뉴를 만들고 싶을때 복사 -->
      <!-- 상품관리 -->
      <li class="treeview">
        <a href="#"
          ><i class="fa fa-link"></i> <span>상품관리</span>
          <span class="pull-right-container">
            <i class="fa fa-angle-left pull-right"></i>
          </span>
        </a>

        <ul class="treeview-menu">
          <li><a href="/admin/product/pro_insert">상품등록</a></li>
          <li><a href="/admin/product/pro_list">상품목록</a></li>
        </ul>
      </li>

      <!-- 주문관리 -->
      <li class="treeview">
        <a href="#"
          ><i class="fa fa-link"></i> <span>주문관리</span>
          <span class="pull-right-container">
            <i class="fa fa-angle-left pull-right"></i>
          </span>
        </a>

        <ul class="treeview-menu">
          <li><a href="/admin/order/order_list">주문목록</a></li>
          <li><a href="/admin/order/order_cancel">주문취소</a></li>
        </ul>
      </li>

      <!-- 회원관리 -->
      <li class="treeview">
        <a href="#"
          ><i class="fa fa-link"></i> <span>회원관리</span>
          <span class="pull-right-container">
            <i class="fa fa-angle-left pull-right"></i>
          </span>
        </a>

        <ul class="treeview-menu">
          <li><a href="/admin/member/member_list">회원목록</a></li>
          <li><a href="/admin/member/member_inactive">탈퇴회원목록</a></li>
        </ul>
      </li>

      <!-- 게시판관리 -->
      <li class="treeview">
        <a href="#"
          ><i class="fa fa-link"></i> <span>게시판관리</span>
          <span class="pull-right-container">
            <i class="fa fa-angle-left pull-right"></i>
          </span>
        </a>

        <ul class="treeview-menu">
          <li><a href="/admin/board/insert">공지사항등록</a></li>
          <li><a href="/admin/board/list">공지사항목록</a></li>
          <li><a href="/admin/board/user_board_list">게시판목록</a></li>
        </ul>
      </li>

      <!-- 통계관리 -->
      <li class="treeview">
        <a href="#"
          ><i class="fa fa-link"></i> <span>통계관리</span>
          <span class="pull-right-container">
            <i class="fa fa-angle-left pull-right"></i>
          </span>
        </a>

        <ul class="treeview-menu">
          <li><a href="/admin/chart/overall">통계확인</a></li>
        </ul>
      </li>
    </ul>
    <!-- /.sidebar-menu -->
  </section>
  <!-- /.sidebar -->
</aside>
