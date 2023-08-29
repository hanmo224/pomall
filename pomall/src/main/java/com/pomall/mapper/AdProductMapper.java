package com.pomall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pomall.domain.CategoryVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.ProductVO;

// Category Mapper + Product Mapper
public interface AdProductMapper {

	// 1차 카테고리 목록 보여주기
	List<CategoryVO> getCategoryList();
	
	// 2차 카테고리 목록 보여주기
	List<CategoryVO> subCategoryList(Integer cg_code);
	
	//상품 테이블 작업
	void pro_insert(ProductVO vo);
	
	// 1)페이징기능
	List<ProductVO> getListWithPaging(@Param("cri") Criteria cri, @Param("cg_code") Integer cg_code);   //  처음 pageNum = 1, amount = 10, type=null, keyword=null
	
	// 2)페이징기능
	int getTotalCount(@Param("cri") Criteria cri, @Param("cg_code") Integer cg_code);
	
	// 수정 폼
	ProductVO modify(Integer pro_code);
	
	// 카테고리 목록
	CategoryVO get(int cg_code);
	
	// 상품 수정 작업
	void update(ProductVO vo);
	
	// 상품 삭제 작업
	void delete(Integer pro_code);
	
	// 선택 상품 수정 - 1
	void pro_checked_modify(List<ProductVO> pro_list);
	
	// 선택 상품 수정 - 2. 포문에 반복된 수 만큼 처리
//	void pro_checked_modify2(@Param("pro_code") Integer pro_code,
//						     @Param("pro_price") int pro_price, 
//						     @Param("pro_buy") String pro_buy);
	
	// 선택 상품 삭제. 파라미터가 컬렉션일 경우 마이바티스 foreach 구문에서는 list, 배열일 경우엔 array
	void pro_checked_delete(List<Integer> pro_code_arr);
	
}





















