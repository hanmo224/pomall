<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>

<div id="categoryMenu">
  <ul class="nav justify-content-center" id="mainCategory">
    <c:forEach items="${cateList }" var="categoryVO">
      <li class="nav-item">
        <a
          class="nav-link"
          style="color: black; text-decoration: none"
          href="${categoryVO.cg_code }"
          >${categoryVO.cg_name }</a
        >
      </li>
    </c:forEach>
  </ul>
</div>

<script>
  // 1차 카테고리 클릭
  $("ul.nav li.nav-item a").on("mouseover", function (e) {
    e.preventDefault(); // a태그가 가지고 있는 링크기능을 제거

    console.log("1차 카테고리 선택");

    let selectedCategory = $(this);
    let cg_code = $(this).attr("href"); // 카테고리 코드 값을 가져옴
    //let cg_name =  "<b>" + $(this).html + "</b>";
    let url = "/product/subCategory/" + cg_code; // 서브 카테고리를 가져옴 productController 씀

    $.getJSON(url, function (subCategory) {
      console.log(subCategory);

      let subCategoryStr =
        '<ul class="nav justify-content-center" id="subCategory">';

      for (let i = 0; i < subCategory.length; i++) {
        subCategoryStr += '<li class="nav-item">';
        subCategoryStr +=
          '<a class="nav-link" href="' +
          subCategory[i].cg_code +
          '">' +
          subCategory[i].cg_name +
          "</a>";
        subCategoryStr += "</li>";
      }

      subCategoryStr += "</ul>";

      console.log(subCategoryStr);

      selectedCategory.parent().parent().next().remove();
      // a태그          li태그   ul태그
      selectedCategory.parent().parent().after(subCategoryStr);

      // 1차 카테고리에 마우스가 올려지면 2차 카테고리 보이기
      $("#subCategory").hover(function () {
        // 2차 카테고리 보이기
        $(this).show();
      });

      // 2차 카테고리에 마우스가 벗어나면 2차 카테고리 숨기기
      $("#subCategory").mouseleave(function () {
        // 2차 카테고리 숨기기
        $(this).hide();
      });

      // 2차 카테고리의 글씨 색을 검은색으로 설정
      $("#subCategory a").css("color", "black");
    });

    // 2차 카테고리 선택
    // on("click", "2차 카테고리 선택자", function()
    $("div#categoryMenu").on(
      "click",
      "ul#subCategory li.nav-item a",
      function (e) {
        e.preventDefault();

        //console.log("2차 카테고리 선택");

        // encodeURIComponent() : 특수문자 인코딩
        let url =
          "/product/pro_list/" +
          $(this).attr("href") +
          "/" +
          encodeURIComponent($(this).html());
        location.href = url;
      }
    );
  });
</script>
