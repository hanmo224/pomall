package com.pomall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomall.domain.BoardVO;
import com.pomall.domain.Criteria;
import com.pomall.mapper.AdBoardMapper;
import com.pomall.mapper.BoardMapper;

import lombok.Setter;

@Service
public class BoardServiceImpl implements BoardService{

	@Setter(onMethod_ = {@Autowired})
	private BoardMapper boardMapper;

	// 공지사항 주입
	@Setter(onMethod_ = {@Autowired})
	private AdBoardMapper adBoardMapper;
	
	@Override
	public void insert(BoardVO board) {
		// TODO Auto-generated method stub
		boardMapper.insert(board);
	}

	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount(cri);
	}

	@Override
	public BoardVO get(Long user_num) {
		// TODO Auto-generated method stub
		return boardMapper.get(user_num);
	}

	@Override
	public void modify(BoardVO board) {
		// TODO Auto-generated method stub
		boardMapper.modify(board);
	}

	@Override
	public void delete(Long user_num) {
		// TODO Auto-generated method stub
		boardMapper.delete(user_num);
	}
}















