<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
    </style>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/include/header.jsp" %> <%@ include
    file="/WEB-INF/views/include/categoryMenu.jsp" %>

    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
      <h4 class="display-4">주문내역확인</h4>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h4 style="text-align: center">주문이 정상적으로 처리되었습니다.</h4>
        </div>
      </div>

      <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <button type="button" class="btn btn-dark" id="order_list_Check">
          주문내역 확인
        </button>
      </div>

      <%@include file="/WEB-INF/views/include/footer.jsp" %>
    </div>

    <script>
      $("#order_list_Check").click(function () {
        location.href = "/order/order_list";
      });
    </script>
  </body>
</html>
