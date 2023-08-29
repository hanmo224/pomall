package com.pomall.service;

import java.util.List;

import com.pomall.domain.AdBoardVO;
import com.pomall.domain.Criteria;

public interface AdBoardService {

	// 공지사항 저장
	public void insert(AdBoardVO adboard);
	
	// 페이징 기능
	public List<AdBoardVO> getListWithPaging(Criteria cri);
	
	// 페이징 기능
	public int getTotalCount(Criteria cri);
	
	// 공지사항 읽기
	public AdBoardVO get(Long adbo_num);
	
	// 공지사항 수정
	public void modify(AdBoardVO adboard);
	
	// 공지사항 삭제
	public void delete(Long adbo_num);
}
