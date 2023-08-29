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

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  <h4 class="display-4">주문내역</h4>
</div>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <c:forEach items="${order_groups}" var="orderGroupDTO">
        <table class="table table-bordered">
          <thead>
            <tr>
              <th colspan="6">주문번호: ${orderGroupDTO.ord_code}
                <button type="button" name="btn_order_cancel" class="btn btn-link order-cancel-btn" style="float: right; color: black;" data-ord_code="${orderGroupDTO.ord_code}">취소</button>
            </th>                              
            </tr>
            <tr>
              <th style="width: 30%">상품명</th>
              <th style="width: 10%">수량</th>
              <th style="width: 8%">포인트</th>
              <th style="width: 10%">배송비</th>
              <th style="width: 10%">가격</th>
              <th style="width: 15%">주문일</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${orderGroupDTO.order_list}" var="orderListDTO">
              <tr>
                <td>
                  <a class="move" href= "/product/pro_detail?pageNum=1&amount=10&pro_code=${orderListDTO.pro_code}"><img src="/order/displayImage?folderName=${orderListDTO.pro_up_folder }&fileName=s_${orderListDTO.pro_img}" /></a>
                  <a class="move" href="/product/pro_detail?pageNum=1&amount=10&pro_code=${orderListDTO.pro_code}"><c:out value="${orderListDTO.pro_name }" ></c:out></a>
                  <input type="hidden" name="ord_code" value="${orderListDTO.ord_code }">
                </td>
                <td>
                  ${orderListDTO.dt_amount}개
                </td>
                <!-- 취소부분의 레이아웃이 아래로 밀려 내려오는 현상이 있어서 추가 -->
                <td>0</td> 
                <td>
                  무료배송
                </td>
                <td><fmt:formatNumber type="currency" pattern="#,###" value="${orderListDTO.unitprice }" /></td>
                <td><fmt:formatDate pattern="MM. dd. '결제'" value="${orderListDTO.ord_regdate}" /></td>
              </tr>
            </c:forEach>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="7" class="text-center">
                총 구매금액 : <fmt:formatNumber type="currency" pattern="￦#,###" value="${order_group_totals[orderGroupDTO.ord_code]}" />
              </td>
            </tr>
          </tfoot>
        </table>
      </c:forEach>
    </div>
  </div>

<%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>

<script>
  // 주문 취소
  $(document).on("click", ".order-cancel-btn", function() {

    if (!confirm("주문을 취소 하시겠습니까?")) return;

    let ord_code = $(this).attr("data-ord_code");

    $.ajax({
      url: "/order/order_cancel",
      type: "get",
      data: { ord_code: ord_code },
      dataType: "text",
      success: function(result) {
        if (result == "success") {
          alert("주문이 취소 되었습니다.");
          // 페이지를 새로고침하여 취소된 주문을 목록에서 제거합니다.
          location.reload();
        } else {
          alert("주문 취소에 실패하였습니다. 다시 시도해주세요.");
        }
      }
    });
  });

</script>

  </body>
</html>