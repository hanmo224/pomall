package com.pomall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pomall.domain.Criteria;
import com.pomall.domain.ReviewVO;

public interface ReviewMapper {

	// 리뷰 목록 및 페이징
	List<ReviewVO> review_list(@Param("cri") Criteria cri, @Param("pro_code") int pro_code);
	
	// 상품 데이터 개수
	int review_count(int pro_code);
	
	// 상품 후기 작성(저장)
	void create(ReviewVO vo);
	
	// 상품 후기 수정
	void modify(ReviewVO vo);
	
	// 상품 후기 삭제
	void delete(Long rev_num);
}
