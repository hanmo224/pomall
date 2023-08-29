package com.pomall.service;

import com.pomall.domain.MemberVO;

public interface MemberService {

	// 아이디 중복체크
	String idCheck(String mb_id);
	
	// 닉네임 중복체크
	String nickCheck(String mb_nick);
	
	// 회원가입 폼
	void join(MemberVO vo);
	
	// 로그인 폼
	MemberVO login(String mb_id);
	
	// 현재 로그인 시간 업데이트
	void now_visit(String mb_id);
	
	// 회원 수정
	void modify(MemberVO vo);
	
	// 비밀번호 변경
	void pwchange(String mb_id, String new_mb_pw);
	
	// 회원탈퇴
	void delete(String mb_id, String string);
	
	// 아이디 찾기 폼
	void findID1(MemberVO vo);
	
	// 아이디 찾기
	String findID2(String mb_name, String mb_email);
	
	// 비밀번호 찾기 폼
	void findPW1(MemberVO vo);
	
	// 아이디와 이메일 일치 확인
	boolean checkIdAndEmail(String sendTPW, String pw_email);

	// 임시 비밀번호 DB업데이트
	void updatePassword(String pw_email, String enc_pw);
}
