package com.pomall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomall.domain.AdBoardVO;
import com.pomall.mapper.AdBoardMapper;
import com.pomall.mapper.BoardMapper;

import lombok.Setter;

@Service
public class AdBoardServiceImpl implements AdBoardService {

	@Setter(onMethod_ = {@Autowired})
	private AdBoardMapper adBoardMapper;
	
	// 사용자 게시판 주입
	@Setter(onMethod_ = {@Autowired})
	private BoardMapper boardMapper;

	@Override
	public void insert(AdBoardVO adboard) {
		// TODO Auto-generated method stub
		adBoardMapper.insert(adboard);
	}

	@Override
	public List<AdBoardVO> getListWithPaging(com.pomall.domain.Criteria cri) {
		// TODO Auto-generated method stub
		return adBoardMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotalCount(com.pomall.domain.Criteria cri) {
		// TODO Auto-generated method stub
		return adBoardMapper.getTotalCount(cri);
	}

	@Override
	public AdBoardVO get(Long adbo_num) {
		// TODO Auto-generated method stub
		return adBoardMapper.get(adbo_num);
	}

	@Override
	public void modify(AdBoardVO adboard) {
		// TODO Auto-generated method stub
		adBoardMapper.modify(adboard);
	}

	@Override
	public void delete(Long adbo_num) {
		// TODO Auto-generated method stub
		adBoardMapper.delete(adbo_num);
	}

}














