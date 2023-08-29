package com.pomall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pomall.domain.Criteria;
import com.pomall.domain.MemberVO;

public interface AdMemberMapper {

	// 회원목록
	List<MemberVO> memberList(@Param("cri") Criteria cri, 
							  @Param("create_sDate") String create_sDate, 
							  @Param("create_eDate") String create_eDate,
							  @Param("up_sDate") String up_sDate, 
							  @Param("up_eDate") String up_eDate);
	
	// 회원 목록 데이터 개수
	int getTotalCount(@Param("cri") Criteria cri, 
					  @Param("create_sDate") String create_sDate, 
					  @Param("create_eDate") String create_eDate,
					  @Param("up_sDate") String up_sDate, 
					  @Param("up_eDate") String up_eDate);
	
	// 회원 상세 보기
	List<MemberVO> memberDetail(String mb_id);
	
	// 탈퇴 회원
	List<MemberVO> member_inactive(@Param("cri") Criteria cri, 
								   @Param("inactive_sDate") String inactive_sDate,
								   @Param("inactive_eDate") String inactive_eDate);
	
	// 탈퇴 회원 목록 데이터 개수
	int getTotalCount2(@Param("cri") Criteria cri, 
					   @Param("inactive_sDate") String inactive_sDate,
					   @Param("inactive_eDate") String inactive_eDate);
}
