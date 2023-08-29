<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
  <h5 class="my-0 mr-md-auto font-weight-normal"><a href="/" style="color: black; text-decoration: none">PoMall</a></h5>
  <c:if test="${sessionScope.loginStatus != null }">
  <span>${sessionScope.loginStatus.mb_name} 님</span> /
  <span>포인트 : ${sessionScope.loginStatus.mb_point} </span> /
  <span>최근접속 : 
  	<fmt:formatDate value="${sessionScope.loginStatus.last_date}" pattern="yyyy-MM-dd HH:mm"/>
   </span>
  </c:if>
  <nav class="my-2 my-md-0 mr-md-3">
    <!-- 로그인 이전 표시 -->
    <c:if test="${sessionScope.loginStatus == null }">
    <a class="p-2 text-dark" href="/member/login">로그인</a>
    <a class="p-2 text-dark" href="/member/join">가입하기</a>
    </c:if>
    <!--  로그인 이후 표시 -->
    <c:if test="${sessionScope.loginStatus != null }">
    <a class="p-2 text-dark" href="/member/logout">로그아웃</a>
    <a class="p-2 text-dark" href="/member/modify">회원수정</a>
    </c:if>
    
    <a class="p-2 text-dark" href="/member/mypage">마이페이지</a>
    <a class="p-2 text-dark" href="/order/order_list">주문내역</a>
    <a class="p-2 text-dark" href="/cart/cart_list">장바구니</a>
    <a class="p-2 text-dark" href="/board/list">게시판</a>
    <a class="p-2 text-dark" href="/board/notice_list">공지사항</a>
</div>