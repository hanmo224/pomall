package com.pomall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pomall.domain.CategoryVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.ProductVO;

public interface ProductMapper {

	// 1차 카테고리 목록 보여주기
	List<CategoryVO> getCategoryList();
			
	// 2차 카테고리 목록 보여주기
	List<CategoryVO> subCategoryList(Integer cg_code);
		
	// 상품 목록
	List<ProductVO> pro_list(@Param("cg_code") Integer cg_code, @Param("cri") Criteria cri);
		
	// 상품 개수
	int pro_count(@Param("cg_code") Integer cg_code, @Param("cri") Criteria cri);

	// 상품 상세 보기
	ProductVO pro_detail(Integer pro_code);
}






















