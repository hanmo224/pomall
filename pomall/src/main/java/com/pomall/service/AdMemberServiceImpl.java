package com.pomall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomall.domain.Criteria;
import com.pomall.domain.MemberVO;
import com.pomall.mapper.AdMemberMapper;

import lombok.Setter;

@Service
public class AdMemberServiceImpl implements AdMemberService{

	@Setter(onMethod_ = {@Autowired})
	private AdMemberMapper adMemberMapper;

	@Override
	public List<MemberVO> memberList(Criteria cri, String create_sDate, String create_eDate, String up_sDate, String up_eDate) {
		// TODO Auto-generated method stub
		return adMemberMapper.memberList(cri, create_sDate, create_eDate, up_sDate, up_eDate);
	}

	@Override
	public int getTotalCount(Criteria cri, String create_sDate, String create_eDate, String up_sDate, String up_eDate) {
		// TODO Auto-generated method stub
		return adMemberMapper.getTotalCount(cri, create_sDate, create_eDate, up_sDate, up_eDate);
	}

	@Override
	public List<MemberVO> memberDetail(String mb_id) {
		// TODO Auto-generated method stub
		return adMemberMapper.memberDetail(mb_id);
	}

	@Override
	public List<MemberVO> member_inactive(Criteria cri, String inactive_sDate, String inactive_eDate) {
		// TODO Auto-generated method stub
		return adMemberMapper.member_inactive(cri, inactive_sDate, inactive_eDate);
	}

	@Override
	public int getTotalCount2(Criteria cri, String inactive_sDate, String inactive_eDate) {
		// TODO Auto-generated method stub
		return adMemberMapper.getTotalCount2(cri, inactive_sDate, inactive_eDate);
	}

	
}















