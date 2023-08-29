package com.pomall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pomall.domain.Criteria;
import com.pomall.domain.MemberVO;
import com.pomall.dto.PageDTO;
import com.pomall.service.AdMemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/admin/member/*")
@Controller
public class AdMemberController {

	@Setter(onMethod_ = {@Autowired})
	private AdMemberService adMemberService;
	
	// 회원목록
	@GetMapping("/member_list")
	public void memberList(@ModelAttribute("cri") Criteria cri, 
						   @ModelAttribute("create_sDate") String create_sDate, 
						   @ModelAttribute("create_eDate") String create_eDate, 
						   @ModelAttribute("up_sDate") String up_sDate, 
						   @ModelAttribute("up_eDate") String up_eDate, 
						   Model model) {
		
		List<MemberVO> memberList = adMemberService.memberList(cri, create_sDate, create_eDate, up_sDate, up_eDate);
		model.addAttribute("memberList", memberList);
		
		int totalCount = adMemberService.getTotalCount(cri, create_sDate, create_eDate, up_sDate, up_eDate);
		model.addAttribute("pageMaker", new PageDTO(totalCount, cri));
	}
	
	// 회원 상세 정보
	@ResponseBody
	@GetMapping("/member_detail")
	public ResponseEntity<List<MemberVO>> memberDetail(String mb_id) {
		
		ResponseEntity<List<MemberVO>> entity = null;
		
		entity = new ResponseEntity<List<MemberVO>>(adMemberService.memberDetail(mb_id), HttpStatus.OK);
		
		return entity;
	}
	
	// 탈퇴 회원
	@GetMapping("/member_inactive")
	public void member_inactive(@ModelAttribute("cri") Criteria cri, 
							    @ModelAttribute("inactive_sDate") String inactive_sDate, 
							    @ModelAttribute("inactive_eDate") String inactive_eDate, 
								Model model) {
		
		List<MemberVO> member_inactive = adMemberService.member_inactive(cri, inactive_sDate, inactive_sDate);
		model.addAttribute("member_inactive", member_inactive);
		
		int totalCount = adMemberService.getTotalCount2(cri, inactive_sDate, inactive_sDate);
		model.addAttribute("pageMaker", new PageDTO(totalCount, cri));
	}
}



















