package com.pomall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pomall.domain.Criteria;
import com.pomall.domain.MemberVO;
import com.pomall.domain.ReviewVO;
import com.pomall.dto.PageDTO;
import com.pomall.service.ReviewService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/review/*")
public class ReviewController {

	@Setter(onMethod_ = {@Autowired})
	private ReviewService reviewService;
	
	// 리뷰
	// json 형식으로 쓰고 싶으면 responsebody 사용
	@GetMapping("/list/{pro_code}/{page}")
	public ResponseEntity<Map<String, Object>> 
			review_list(@PathVariable("pro_code") int pro_code, @PathVariable("page") int page) {
		
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		Criteria cri = new Criteria();
		cri.setPageNum(page);
		
		// 1) 상품 후기 목록
		List<ReviewVO> list = reviewService.review_list(cri, pro_code);
		map.put("list", list);
		
		// 2) 페이징 정보
		PageDTO pageMaker = new PageDTO(reviewService.review_count(pro_code), cri);
		map.put("pageMaker", pageMaker);
		
		entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
		return entity;
	}
	
	// 상품후기 저장. 클라이언트에서 json으로 데이터가 전송되면 @RequestBody 사용되어, ReviewVO vo 변환된다.
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReviewVO vo, HttpSession session) {
			
		log.info("상품후기: " + vo);
			
		ResponseEntity<String> entity = null;
		
		// 사용자 가져오기
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
		vo.setMb_id(mb_id);
			
		// db연동.
		reviewService.create(vo);
			
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
		return entity;
	}
	
	// 상품후기 수정
	@PatchMapping(value = "/modify", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReviewVO vo, HttpSession session) {
			
		ResponseEntity<String> entity = null;
			
		//db연동.
		reviewService.modify(vo);
			
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
		return entity;
	}
	
	// 상품후기 삭제
	@DeleteMapping(value = "/delete/{rev_num}")
	public ResponseEntity<String> delete(@PathVariable("rev_num") Long rev_num) {
		
		ResponseEntity<String> entity = null;
			
		reviewService.delete(rev_num);
			
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
		return entity;
	}
}





















