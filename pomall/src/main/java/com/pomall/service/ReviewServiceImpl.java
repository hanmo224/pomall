package com.pomall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomall.domain.Criteria;
import com.pomall.domain.ReviewVO;
import com.pomall.mapper.ReviewMapper;

import lombok.Setter;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Setter(onMethod_ = {@Autowired})
	private ReviewMapper reviewMapper;

	@Override
	public List<ReviewVO> review_list(Criteria cri, int pro_code) {
		// TODO Auto-generated method stub
		return reviewMapper.review_list(cri, pro_code);
	}

	@Override
	public int review_count(int pro_code) {
		// TODO Auto-generated method stub
		return reviewMapper.review_count(pro_code);
	}

	@Override
	public void create(ReviewVO vo) {
		// TODO Auto-generated method stub
		reviewMapper.create(vo);
	}

	@Override
	public void modify(ReviewVO vo) {
		// TODO Auto-generated method stub
		reviewMapper.modify(vo);
	}

	@Override
	public void delete(Long rev_num) {
		// TODO Auto-generated method stub
		reviewMapper.delete(rev_num);
	}
	
}


















