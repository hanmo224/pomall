package com.pomall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pomall.domain.MemberVO;
import com.pomall.dto.LoginDTO;
import com.pomall.service.EmailService;
import com.pomall.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Setter(onMethod_ = {@Autowired})
	private MemberService memberService;
	
	// 스프링시큐리티 암호화 클래스 객체 주입. spring-security.xml의 bcryptPasswordEncoder bean객체 주입
	@Setter(onMethod_ = {@Autowired})
	private PasswordEncoder passwordEncoder;
	
	// 이메일 주입
	@Setter(onMethod_ = {@Autowired})
	private EmailService emailService;
	
	// 아이디 중복체크(ajax사용)
	@ResponseBody
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String mb_id) {
		
		log.info("아이디: " + mb_id);
		
		ResponseEntity<String> entity = null;
		
		//idcheck 작업
		String isUse = "";
		if(memberService.idCheck(mb_id) != null) {
			isUse = "no";
		}else {
			isUse = "yes";
		}
		
		entity = new ResponseEntity<String>(isUse, HttpStatus.OK);
		
		return entity;
	}
	
	// 닉네임 중복 체크(ajax 사용)
	@ResponseBody
	@GetMapping("/nickCheck")
	public ResponseEntity<String> nickCheck(String mb_nick) {
			
		log.info("닉네임: " + mb_nick);
			
		ResponseEntity<String> entity = null;
			
		// nickCheck 작업
		String UseNick = "";
		if(memberService.nickCheck(mb_nick) != null) {
			UseNick = "no";
		}else {
			UseNick = "yes";
		}
			
		entity = new ResponseEntity<String>(UseNick, HttpStatus.OK);
			
		return entity;
	}

	// 회원 가입 폼
	@GetMapping("/join")
	public void join() {
		log.info("회원가입 폼");
	}
	
	// 회원 저장
	@PostMapping("/join")
	public String join(MemberVO vo) {
		
		log.info("회원정보: " + vo); // 암호화 되지 않은 상태
		
		// 평문 텍스트인 비밀번호를 스프링시큐리티 암호화클래스를 이용하여, 암호화시킴.
		vo.setMb_pw(passwordEncoder.encode(vo.getMb_pw()));
		
		log.info("회원정보: " + vo); // 암호화 된 상태
		
		memberService.join(vo);
		
		return "redirect:/member/login";
	}
	
	// 로그인 폼
	@GetMapping("/login")
	public void login() {
		log.info("로그인 폼");
	}
	
	// 로그인 주소
	@PostMapping("/login")
	public String login(LoginDTO dto, HttpSession session, RedirectAttributes rttr) {
		
		log.info("로그인 정보: " + dto);
		
		MemberVO vo = memberService.login(dto.getMb_id());
		
		String url = "";
		String msg = "";
		
		// 로그인 검사. 아이디만 제공
		if(vo != null) {
			// 사용자 입력 비번, 암호화된 비번
			if(passwordEncoder.matches(dto.getMb_pw(), vo.getMb_pw())) {
				session.setAttribute("loginStatus", vo); // 로그인한 사용자의 회원정보가 세션형태로 저장
				
				//로그인 시간 업데이트
				memberService.now_visit(dto.getMb_id());
				
				// 강제로 로그인 되기 이전 매핑주소와 정보 존재 유무 작업
				 String targetUrl = (String) session.getAttribute("dest");
				
				// 로그인이 만료되고 나서 재로그인을 하면 전 매핑주소로 간다
				url = (targetUrl != null) ? targetUrl : "/";
				 
				
				if(targetUrl != null) {
					session.removeAttribute("dest");
				}
				
			}else {
				//  비번이 틀린경우
				url = "/member/login";
				msg = "failPW";
			}
		}else {
			// 아이디가 틀린경우
			url = "/member/login";
			msg = "failID";
		}
		
		rttr.addFlashAttribute("msg", msg); // jsp에서 msg 이름을 사용가능
		
		return "redirect:" + url;
	}
	
	// 로그아웃 기능
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 사용자 세션관련정보가 모두 소멸.
		
		return "redirect:/";
	}
	
	// 수정 폼
	@GetMapping(value = {"/modify", "/mypage"})
	public void modify(Model model, HttpSession session, MemberVO vo) {
		
		// 세션객체로부터 사용자아이디 확보.
		// Object형태로 저장된 것을 다시 MemberVO형태로 받아옴
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
		
		// 로그인에서 사용한 것을 재사용 한 것
		model.addAttribute("memberVO", memberService.login(mb_id));
	}
	
	// 수정 정보 전송
	@PostMapping("/modify")
	public String modify(MemberVO vo, HttpSession session, RedirectAttributes rttr) {
		
		String url = "";
		String msg = "";
		
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
		
		// 암호화된 비밀번호를 db에서 읽어옴.
		String db_mb_pw = ((MemberVO) memberService.login(mb_id)).getMb_pw();
		
		if(passwordEncoder.matches(vo.getMb_pw(), db_mb_pw)) {
			// 회원정보수정작업
			memberService.modify(vo);
			
			msg = "modify";
			url = "/";
		}else {
			msg = "failPW";
			url = "/member/modify";
		}
			
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:" + url;
	}
	
	// 비밀번호 변경
	@ResponseBody
	@PostMapping("/pwchange")
	public ResponseEntity<String> pwchange(String old_mb_pw, String new_mb_pw, HttpSession session) {
		
		ResponseEntity<String> entity = null;
		String body = "";
		
		//기존 비번확인및 신규비번 변경작업
		log.info("기존비번: " + old_mb_pw); // 1234
		log.info("신규비번: " + new_mb_pw); // 4321
		
		// mbsp_id 뽑아오기
		MemberVO vo = (MemberVO) session.getAttribute("loginStatus");
		
		// 세션에 존재하고 있는 암호화된 비밀번호.  비번 1234 의 암호화
		//String db_mb_pw = vo.getMb_pw();
		
		// mb_id 뽑아오기
		String mb_id = vo.getMb_id();
		
		// 암호화된 비밀번호를 db에서 읽어옴.
		String db_mb_pw = ((MemberVO) memberService.login(mb_id)).getMb_pw();
			
		if(passwordEncoder.matches(old_mb_pw, db_mb_pw)) {
			//신규비밀번호를 암호화처리.  pwchange()메서드
			String enc_mb_pw = passwordEncoder.encode(new_mb_pw);
			memberService.pwchange(mb_id, enc_mb_pw);
			
			body = "success";
		}else {
			
			body = "failPW";
		}
		
		entity = new ResponseEntity<String>(body, HttpStatus.OK);
		
		return entity;
	}
	
	// 회원탈퇴
	@ResponseBody
	@PostMapping("/delete")
	public ResponseEntity<String> delete(String mb_pw, HttpSession session) {
		
		ResponseEntity<String> entity = null;
		String body = "";
		
		// 사용자 아이디 가져오기
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
		
		System.out.println("loginStatus :" + mb_id);
		
		// 암호화된 비밀번호 DB에서 가져오기
		String db_mb_pw = ((MemberVO) memberService.login(mb_id)).getMb_pw();
		
		if(passwordEncoder.matches(mb_pw, db_mb_pw)) {
			// 회원 탈퇴 작업
			memberService.delete(mb_id, "탈퇴회원");
			session.invalidate(); // 세션 초기화
			
			body = "success";
			
		}else {
			body = "failPW";
		}
		
		entity = new ResponseEntity<String>(body, HttpStatus.OK);
		
		return entity;
	}
	
	// 아이디 찾기 폼
	@GetMapping("/findID")
	public void finID() {
		
		log.info("아이디 찾기 폼");
	}
	
	// 아이디 찾기
	@ResponseBody
	@PostMapping("/findID")
	public ResponseEntity<String> findID(String mb_name, String mb_email) {
		
		ResponseEntity<String> entity = null;
		
		String foundID = memberService.findID2(mb_name, mb_email);
		
		if(foundID != null) {
			emailService.sendIDByEmail(mb_email, foundID);
			
			// "아이디 찾기 성공" 문자열을 포함한 ResponseEntity 객체 생성. 200번 반환
			entity = ResponseEntity.ok("success"); 
		} else {
			entity = ResponseEntity.notFound().build(); // 404번 반환
		}
		
		log.info("아이디 :" + foundID);
		
		return entity;
	}
	
	// 비밀번호 찾기 폼
	@GetMapping("/findPW")
	public void finPW() {
			
		log.info("비밀번호 찾기 폼");
	}
	
	// 비밀번호 찾기 - 임시비밀번호 보내기
	@ResponseBody
	@PostMapping("/findPW")
	public ResponseEntity<String> findPW(String mb_id, String mb_email, HttpSession session) {
		
		ResponseEntity<String> entity = null;
		
		// 아이디와 이메일이 일치하는지 확인
		boolean match = memberService.checkIdAndEmail(mb_id, mb_email);
		
		if(match) {
			// 임시비밀번호 생성. 10자리
			String tempPW = "";
			String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			for(int i=0; i<10; i++) {
			    int index = (int)(Math.random() * chars.length());
			    tempPW += chars.charAt(index);
			}
			
			session.setAttribute("tempPW", tempPW);
			
			// 임시 비밀번호 암호화
			String enc_pw = passwordEncoder.encode(tempPW);
			
			// 임시 비밀번호 데이터베이스에 업데이트
			memberService.updatePassword(mb_email, enc_pw);
			
			// 임시 비밀번호 메일전송.
			try {
				emailService.sendPWByEmail(mb_email, tempPW);
				entity = new ResponseEntity<String>("success", HttpStatus.OK); // 200
			}catch(Exception ex) {
				ex.printStackTrace();
				entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
			}
			
			return entity;
			
		}else {
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST); // 400
			return entity;
		}

	}

}





















