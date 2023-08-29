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
   
    
  </head>
  <body>
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ include file="/WEB-INF/views/include/categoryMenu.jsp" %>


<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  <h4 class="display-4">장바구니</h4>
</div>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <table class="table table-bordered">
        <tbody>
          <tr>
            <th style="width: 2%"><input type="checkbox" id="checkAll"></th>
            <th style="width: 40%">상품명</th>
            <th style="width: 10%">수량</th>
            <th style="width: 8%">포인트</th>
            <th style="width: 10%">배송비</th>
            <th style="width: 10%">가격</th>
            <th style="width: 10%">취소</th>
          </tr>
          <c:forEach items="${cart_list}" var="cartListDTO">
            <tr>
              <td style="width: 2%">
                <input type="checkbox" name="checkItem" value="${cartListDTO.ca_code}">
              </td>
              <td>
                <a class="move" href= "/product/pro_detail?pageNum=1&amount=10&pro_code=${cartListDTO.pro_code}"><img src="/cart/displayImage?folderName=${cartListDTO.pro_up_folder }&fileName=s_${cartListDTO.pro_img}" /></a>
                <a class="move" href="/product/pro_detail?pageNum=1&amount=10&pro_code=${cartListDTO.pro_code}"><c:out value="${cartListDTO.pro_name }" ></c:out></a>
                <input type="hidden" name="ca_code" value="${cartListDTO.ca_code }">
              </td>
              <td>
                <input type="text" name="ca_amount" style="width:100px" value="${cartListDTO.ca_amount }">
                <button type="button" name="btn_cart_amount_change" class="btn btn-link">수량변경</button>
              </td>
              <!-- 취소부분의 레이아웃이 아래로 밀려 내려오는 현상이 있어서 추가 -->
              <td>0</td> 
              <td>
                무료배송
              </td>
              <td><input type="text" name="unitprice" style="width:100px" value="${cartListDTO.unitprice }"></td>
              <td><button type="button" name="btn_cart_del" class="btn btn-link">삭제</button></td>
            </tr>
          </c:forEach>
        </tbody>
  
        <!-- 데이터가 비어있지 않을 경우에만 진행 -->
        <c:if test="${!empty cart_list}">
          <tfoot>
            <tr>
              <td colspan="7" class="text-center">
                총 구매금액 : <fmt:formatNumber type="currency" pattern="￦#,###" value="${cart_tot_price}" />
              </td>
            </tr>
          </tfoot>
        </c:if>
  
        <c:if test="${empty cart_list}">
          <tfoot>
            <tr>
              <td colspan="7" class="text-center">
                장바구니에 상품을 담아주세요.
              </td>
            </tr>
          </tfoot>
        </c:if>
      </table>
    </div>
  </div>
  
 <div class="row">
    <div class="col-md-12 text-center">
      <button type="button" id="btn_cart_empty" class="btn btn-light">장바구니 비우기</button>
      <button type="button" id="btn_shopping" class="btn btn-light">계속 쇼핑하기</button>
      <button type="button" id="btn_order_check_delete" class="btn btn-dark">선택삭제</button>
      <button type="button" id="btn_order" class="btn btn-dark">주문하기</button>
    </div>
 </div>
 
  <%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>
   
<script>

  $(document).ready(function() {

    // 장바구니 수량변경
    $("button[name='btn_cart_amount_change']").on("click",function() {
      let cur_amount_change = $(this);

      // 장바구니 코드값 td(), tr()
      let ca_code = cur_amount_change.parent().parent().find("input[name='ca_code']").val();

      // 변경 수량 td()
      let ca_amount = cur_amount_change.parent().find("input[name='ca_amount']").val();

      $.ajax({
        url: '/cart/cart_amount_change',
        type: 'get',
        data: {ca_code: ca_code, ca_amount: ca_amount}, // 파라미터: 변수
        dataType: 'text',
        beforeSend: function(xhr) {
          xhr.setRequestHeader("AJAX", "true");
        },
        success: function(result) {
          if(result == "success")
            alert("수량이 변경 되었습니다.");

            // 1) 새로 읽어오기
            location.href = "/cart/cart_list"; // 수량변경후 다시 새로 읽어오기(가격도 바뀌게)

            // 2) 현재 위치에서 작업을 통하여 변경 하기
        },
        error: function(xhr, status, error) {
          //console.log("status: " + status);
          //alert("status: " + status);
          alert("로그인이 만료 되었습니다. \n다시 로그인 해주세요.");
          location.href = "/member/login";
        }
      })
    });

    // 장바구니 삭제
    $("button[name='btn_cart_del']").on("click", function() {

      if(!confirm("해당 상품을 삭제하시겠습니까?")) return;

      let ca_code = $(this).parent().parent().find("input[name='ca_code']").val();

      $.ajax({
        url: '/cart/cart_delete',
        type: 'post',
        data: {ca_code: ca_code}, // 파라미터: 변수
        dataType: 'text',
        success: function(result) {
          if(result == "success")
            alert("해당 상품이 삭제 되었습니다.");
            
            // 1) 새로 읽어오기
            location.href = "/cart/cart_list"; // 수량변경후 다시 새로 읽어오기(가격도 바뀌게)
        }
      })
    });

    // 리스트에서 제목행 체크 박스 선택 -> attr 사용 하면 안먹는다.
    $(document).on("click", "#checkAll", function() {
      // 데이터 행 checkbox 모두 선택
      $("input[name='checkItem']").prop("checked", this.checked);
    });

    $(document).on("click", "input[name='checkItem']", function() {
      let isCheckAll = true;

      // 데이터 행의 체크박스 상태 확인
      $("input[name='checkItem']").each(function() {
        if (!$(this).is(":checked")) {
          isCheckAll = false;
          return false;
        }
      });

      // 제목행 checkbox
      $("#checkAll").prop("checked", isCheckAll);
    });

    // 장바구니 선택 삭제
    $("#btn_order_check_delete").on("click", function() {

      if($("input[name='checkItem']:checked").length == 0) {
        alert("삭제할 상품을 체크하세요.");
        return;
      }

      if(!confirm("선택한 상품을 삭제 하시겠습니까?")) return;

      let ca_code = [];

      $("input[name='checkItem']:checked").each(function() {
        ca_code.push($(this).val()); // this는 체크된 것들을 가리킴

        console.log("선택카트코드: " + ca_code);

        $.ajax({
          url: '/cart/cart_check_delete',
          type:'post',
          data: {ca_code : ca_code},
          dataType: 'text',
          success: function(result) {
            if(result == "success")
              alert("선택하신 상품이 삭제 되었습니다.");
              
              // 1) 새로 읽어오기
              location.href = "/cart/cart_list"; // 수량변경후 다시 새로 읽어오기(가격도 바뀌게)
          }
        })
      });
    });

    // 장바구니 비우기
    $("#btn_cart_empty").on("click", function() {
        if (!confirm("장바구니를 비우시겠습니까?")) return;

        $.ajax({
            url: "/cart/cartItemCount",
            type: "get",
            data: {mb_id: "$(sessionScope(mb_id))"},
            dataType: 'text',
            success: function(response) {
                if (response < 1) {
                    alert("먼저 장바구니를 채워주세요.");
                } else {
                    // Ajax 요청을 수행하여 장바구니를 비우는 처리를 서버에 요청합니다.
                    $.ajax({
                        url: "/cart/cart_empty",
                        type: "POST",
                        data: {mb_id: "${sessionScope.mb_id}"},
                        success: function(response) {
                            alert("장바구니를 비웠습니다.");
                            // 성공적으로 비워진 후에 필요한 동작을 수행합니다.
                            
                            location.href = "/cart/cart_empty";
                        },
                        error: function() {
                            alert("장바구니 비우기를 실패했습니다.");
                        }
                    });
                }
            },
            error: function() {
                alert("장바구니 상품 개수를 가져오는데 실패했습니다.");
            }
        });
    });

    // 주문하기 버튼 클릭
    $("#btn_order").on("click", function() {
		
        location.href = "/order/order_info";
    });

    // 계속 쇼핑하기 버튼 클릭
    $("#btn_shopping").on("click", function() {

      location.href = "/";
    });

  });

</script>

  </body>
</html>