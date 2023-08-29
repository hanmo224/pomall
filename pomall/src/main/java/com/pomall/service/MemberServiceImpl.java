package com.pomall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomall.domain.MemberVO;
import com.pomall.mapper.MemberMapper;

import lombok.Setter;

@Service
public class MemberServiceImpl implements MemberService{

	@Setter(onMethod_ = {@Autowired})
	private MemberMapper memberMapper;
	
	@Override
	public String idCheck(String mb_id) {
		// TODO Auto-generated method stub
		return memberMapper.idCheck(mb_id);
	}

	@Override
	public String nickCheck(String mb_nick) {
		// TODO Auto-generated method stub
		return memberMapper.nickCheck(mb_nick);
	}
	
	@Override
	public void join(MemberVO vo) {
		// TODO Auto-generated method stub
		memberMapper.join(vo);
	}

	@Override
	public MemberVO login(String mb_id) {
		// TODO Auto-generated method stub
		return memberMapper.login(mb_id);
	}

	@Override
	public void now_visit(String mb_id) {
		// TODO Auto-generated method stub
		memberMapper.now_visit(mb_id);
	}

	@Override
	public void modify(MemberVO vo) {
		// TODO Auto-generated method stub
		memberMapper.modify(vo);
	}

	@Override
	public void pwchange(String mb_id, String new_mb_pw) {
		// TODO Auto-generated method stub
		memberMapper.pwchange(mb_id, new_mb_pw);
	}

	@Override
	public void delete(String mb_id, String string) {
		// TODO Auto-generated method stub
		memberMapper.delete(mb_id, string);
	}

	@Override
	public void findID1(MemberVO vo) {
		// TODO Auto-generated method stub
		memberMapper.findID1(vo);
	}

	@Override
	public String findID2(String mb_name, String mb_email) {
		// TODO Auto-generated method stub
		return memberMapper.findID2(mb_name, mb_email);
	}

	@Override
	public void findPW1(MemberVO vo) {
		// TODO Auto-generated method stub
		memberMapper.findPW1(vo);
	}

	@Override
	public boolean checkIdAndEmail(String sendTPW, String pw_email) {
		// TODO Auto-generated method stub
		return memberMapper.checkIdAndEmail(sendTPW, pw_email);
	}

	@Override
	public void updatePassword(String pw_email, String enc_pw) {
		// TODO Auto-generated method stub
		memberMapper.updatePassword(pw_email, enc_pw);
	}
}


