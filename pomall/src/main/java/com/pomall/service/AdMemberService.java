package com.pomall.service;

import java.util.List;

import com.pomall.domain.Criteria;
import com.pomall.domain.MemberVO;

public interface AdMemberService {

	// 회원목록
	List<MemberVO> memberList(Criteria cri, String create_sDate, String create_eDate, String up_sDate, String up_eDate);
		
	// 회원 목록 데이터 개수
	int getTotalCount(Criteria cri, String create_sDate, String create_eDate, String up_sDate, String up_eDate);
	
	// 회원 상세 보기
	List<MemberVO> memberDetail(String mb_id);
	
	// 탈퇴 회원
	List<MemberVO> member_inactive(Criteria cri, String inactive_sDate, String inactive_eDate); 
			
	// 탈퇴 회원 목록 데이터 개수
	int getTotalCount2(Criteria cri, String inactive_sDate, String inactive_eDate);
}
