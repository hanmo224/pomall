package com.pomall.service;

import java.util.List;

import com.pomall.domain.BoardVO;
import com.pomall.domain.Criteria;

public interface BoardService {

	// 글 저장
	public void insert(BoardVO board);
	
	// 페이징 기능
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	// 목록 개수
	public int getTotalCount(Criteria cri);
	
	// 글 읽기
	public BoardVO get(Long user_num);
	
	// 수정
	public void modify(BoardVO board);
	
	// 삭제
	public void delete(Long user_num);
}
