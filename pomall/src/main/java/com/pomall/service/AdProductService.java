package com.pomall.service;

import java.util.List;

import com.pomall.domain.CategoryVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.ProductVO;

//Category Mapper + Product Mapper
public interface AdProductService {
	
	// 1차 카테고리 목록 보여주기
	List<CategoryVO> getCategoryList();
	
	// 2차 카테고리 목록 보여주기
	List<CategoryVO> subCategoryList(Integer cg_code);
	
	//상품 테이블 등록 작업
	void pro_insert(ProductVO vo);
	
	// 1)페이징기능
	List<ProductVO> getListWithPaging(Criteria cri, Integer cg_code);   //  처음 pageNum = 1, amount = 10, type=null, keyword=null
	
	// 2)페이징기능
	int getTotalCount(Criteria cri, Integer cg_code);
	
	ProductVO modify(Integer pro_code);
	
	CategoryVO get(int cg_code);
	
	// 상품 수정 작업
	void update(ProductVO vo);
	
	// 상품 삭제 작업
	void delete(Integer pro_code);
	
	// 선택 상품 수정 - 1
	void pro_checked_modify(List<ProductVO> pro_list);
	
	// 선택 상품 수정 - 2. 포문에 반복된 수 만큼 처리
	//void pro_checked_modify2(Integer pro_code, int pro_price, String pro_buy);
	
	// 선택 상품 삭제
	void pro_checked_delete(List<Integer> pro_code_arr);
}















