package com.pomall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pomall.dto.EmailDTO;
import com.pomall.service.EmailService;
import com.pomall.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/email/*")
public class EmailController {

	@Setter(onMethod_ = {@Autowired})
	private EmailService emailService;
	
	//스프링시큐리티 암호화 클래스 객체 주입. spring-security.xml의 bcryptPasswordEncoder bean객체 주입
	@Setter(onMethod_ = {@Autowired})
	private PasswordEncoder passwordEncoder;
	
	@Setter(onMethod_ = {@Autowired})
	private MemberService memberService;
	
	//메일인증코드 요청주소.
	@GetMapping("/send") // 1)EmailDTO클래스의 기본생성자 호출, 사용자의 EMail주소는 receiverMail필드에 저장
	public ResponseEntity<String> send(EmailDTO dto, HttpSession session) {
		
		log.info("메일정보: " + dto);
		
		ResponseEntity<String> entity = null;
		
		//인증코드 생성. 6자리
		String authCode = "";
		for(int i=0; i<6; i++) {
			authCode += String.valueOf((int)(Math.random() * 10));
		}
		
		log.info("인증코드: " + authCode);
		
		// 인증코드 (session으로)저장. 20분간 유효
		// 사용자가 인증코드를 입력시 비교하기위하여, 발급해준 인증코드를 세션형태로 저장해두어야 한다.
		session.setAttribute("authCode", authCode);
		
		//인증코드 메일발송
		try {
			emailService.sendMail(dto, authCode);
			entity = new ResponseEntity<String>("success", HttpStatus.OK); // 200
		}catch(Exception ex) {
			ex.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	
		return entity;
	}
	
	// 인증코드 확인
	@GetMapping("/confirmAuthcode")
	public ResponseEntity<String> confirmAuthCode(String authCode, HttpSession session) {
		
		ResponseEntity<String> entity = null;
		
		// 사용자가 발급받은 인증코드
		String confirm_authCode = (String) session.getAttribute("authCode");
		
		if(authCode.equals(confirm_authCode)) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
			// 세션 제거 작업. 서버측 메모리 낭비 방지
			session.removeAttribute("authCode");
		}else {
			entity = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		
		return entity;
	}
	
	// 아이디 보내기
	@GetMapping("/sendID")
	public ResponseEntity<String> sendIDByEmail(String id_email, String foundID) {
	    
		ResponseEntity<String> entity = null;
	    
		try {
	        emailService.sendIDByEmail(id_email, foundID);
	        entity = new ResponseEntity<String>("success", HttpStatus.OK); // 200번 상태 코드 반환
	    
		} catch (Exception ex) {
	        ex.printStackTrace();
	        entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); // 500번 상태 코드 반환
	    }
	    
		return entity;
	}
	
}























