package com.pomall.mapper;

import java.util.List;

import com.pomall.domain.AdBoardVO;
import com.pomall.domain.Criteria;

public interface AdBoardMapper {

	// 공지사항 저장
	public void insert(AdBoardVO adboard);
	
	// 페이징 기능
	public List<AdBoardVO> getListWithPaging(Criteria cri);
	
	// 목록 개수
	public int getTotalCount(Criteria cri);
	
	// 공지사항 읽기
	public AdBoardVO get(Long adbo_num);
	
	// 공지사항 수정
	public void modify(AdBoardVO adboard);
	
	// 공지사항 삭제
	public void delete(Long adbo_num);
}
