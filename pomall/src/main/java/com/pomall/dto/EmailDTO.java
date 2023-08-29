package com.pomall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class EmailDTO {

	private String senderName; // 발신자 이름
	private String senderMail; // 발신자 메일주소
	private String receiverMail; // 수신자 메일주소, 즉 회원메일 주소로 사용
	private String subject; // 제목
	private String message; // 본문내용
	
	// 인증 코드 발송
	public EmailDTO() {
		this.senderName = "PoMall";
		this.senderMail = "PoMall";
		this.subject = "PoMall 메일 인증코드입니다.";
		this.message = "인증코드를 발송했습니다.";
	}
	
	// 아이디 발송
	public void sendID(String senderName, String senderMail, String receiverMail, String subject, String message) {
	    this.senderName = "PoMall";
	    this.senderMail = "PoMall";
	    this.receiverMail = "thd9780@naver.com";
	    this.subject = "PoMall 아이디 찾기 결과 입니다.";
	    this.message = "회원님의 아이디를 발송했습니다.";
	}
	
	// 임시 비밀번호 발송
	public void sendTempPassword(String senderName, String senderMail, String receiverMail, String subject, String message) {
	    this.senderName = "PoMall";
	    this.senderMail = "PoMall";
	    this.receiverMail = "thd9780@naver.com";
	    this.subject = "PoMall 임시 비밀번호 입니다.";
	    this.message = "임시비밀번호를 보냈습니다. 확인 해주세요.";
	}


	
}
