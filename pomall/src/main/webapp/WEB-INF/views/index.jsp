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

    


<!-- Favicons 
<link rel="apple-touch-icon" href="/docs/4.6/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/4.6/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/4.6/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon.ico">
<meta name="msapplication-config" content="/docs/4.6/assets/img/favicons/browserconfig.xml">
-->
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
    
	<script>
    
    	let msg = '${msg}';
    	if(msg == 'modify') {
    		alert("회원정보가 수정되었습니다.");
    	}
    
    </script>
    
    
  </head>
  <body>
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<%@ include file="/WEB-INF/views/include/categoryMenu.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  <h1 class="display-4">이벤트</h1>
  <p class="lead">아래 이벤트 상품들을 확인 하시고 싶으시면 사진을 클릭 해주세요.</p>
</div>

<div class="container">
  <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
    <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
    <a href="http://localhost:8081/product/pro_list/6/%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4">
    	<img src="resources/image/beauty.jpg" class="d-block w-100" style="height: 350px;">
    </a>
      <div class="carousel-caption d-none d-md-block">
        <h5>5월 이벤트 스킨케어 상품!!</h5>
        <a href="http://localhost:8081/product/pro_list/6/%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4" style="color: white;">
        	<p>스킨케어 제품 보러 가기</p>
        </a>
      </div>
    </div>
    <div class="carousel-item">
      <a href="http://localhost:8081/product/pro_list/11/%EA%B3%BC%EC%9D%BC">
    	<img src="resources/image/apples.jpg" class="d-block w-100" style="height: 350px;">
      </a>
      <div class="carousel-caption d-none d-md-block">
        <h5>신선한 제철 과일이 가득!!</h5>
        <a href="http://localhost:8081/product/pro_list/11/%EA%B3%BC%EC%9D%BC" style="color: white;">
        	<p>과일 보러 가기</p>
        </a>
      </div>
    </div>
    <div class="carousel-item">
      <a href="http://localhost:8081/product/pro_list/20/4">
    	<img src="resources/image/kitchen.jpg" class="d-block w-100" style="height: 350px;">
      </a>
      <div class="carousel-caption d-none d-md-block">
        <h5>5월 이벤트 주방용품 할인!!</h5>
        <a href="http://localhost:8081/product/pro_list/20/4" style="color: white;">
        	<p>주방가전 및 용품 보러 가기</p>
        </a>
      </div>
  </div>
  <button class="carousel-control-prev" type="button" data-target="#carouselExampleCaptions" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-target="#carouselExampleCaptions" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </button>
</div>

  <%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>
</div>

  </body>
</html>