package com.pomall.service;

import java.util.List;

import com.pomall.domain.CategoryVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.ProductVO;

public interface ProductService {

	// 1차 카테고리 목록 보여주기
	List<CategoryVO> getCategoryList();
				
	// 2차 카테고리 목록 보여주기
	List<CategoryVO> subCategoryList(Integer cg_code);
		
	// 상품 목록
	List<ProductVO> pro_list(Integer cg_code, Criteria cri);
			
	// 상품 개수
	int pro_count(Integer cg_code, Criteria cri);
	
	// 상품 상세 보기
	ProductVO pro_detail(Integer pro_code);
}
