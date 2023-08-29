package com.pomall.mapper;

import org.apache.ibatis.annotations.Param;

import com.pomall.domain.MemberVO;

public interface MemberMapper {

	// 아이디 중복체크
	String idCheck(String mb_id);
	
	// 닉네임 중복체크
	String nickCheck(String mb_nick);
	
	// 회원가입 폼
	void join(MemberVO vo);
	
	// 로그인 폼, 회원수정 폼
	MemberVO login(String mb_id);
	
	// 현재 로그인 시간 업데이트
	void now_visit(String mb_id);
	
	// 회원 수정
	void modify(MemberVO vo);
	
	// 비밀번호 변경
	void pwchange(@Param("mb_id") String mb_id, @Param("new_mb_pw") String new_mb_pw);
	
	// 회원탈퇴
	void delete(@Param("mb_id") String mb_id, @Param("string") String string);
	
	// 아이디 찾기 폼
	void findID1(MemberVO vo);
	
	// 아이디 찾기
	String findID2(@Param("mb_name") String mb_name, @Param("mb_email") String mb_email);
	
	// 비밀번호 찾기 폼
	void findPW1(MemberVO vo);
	
	// 아이디와 이메일 일치 확인
	boolean checkIdAndEmail(@Param("sendTPW") String sendTPW, @Param("pw_email") String pw_email);

	// 임시 비밀번호 DB업데이트
	void updatePassword(@Param("pw_email") String pw_email, @Param("enc_pw") String enc_pw);
}



















