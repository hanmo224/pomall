package com.pomall.service;

import java.util.List;

import com.pomall.domain.Criteria;
import com.pomall.domain.ReviewVO;

public interface ReviewService {

	// 리뷰 목록 및 페이징
	List<ReviewVO> review_list(Criteria cri, int pro_code);
		
	// 상품 데이터 개수
	int review_count(int pro_code);
	
	// 상품 후기 작성(저장)
	void create(ReviewVO vo);
	
	// 상품 후기 수정
	void modify(ReviewVO vo);
	
	// 상품 후기 삭제
	void delete(Long rev_num);
}
