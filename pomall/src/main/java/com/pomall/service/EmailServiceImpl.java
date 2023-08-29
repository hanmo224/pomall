package com.pomall.service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pomall.dto.EmailDTO;

import lombok.Setter;

@Service
public class EmailServiceImpl implements EmailService{

	// email-config.xml의 mailSender bean주입을 받는다.
	@Setter(onMethod_ = {@Autowired})
	private JavaMailSender mailSender;

	@Override
	public void sendMail(EmailDTO dto, String message) {
		
		// 메일 구성 정보를 담당하는 객체 (받는 사람, 보내는 사람, 전자 우편 주소, 본문 내용)
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			// 받는 사람의 메일 주소
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiverMail()));
			
			// 보내는 사람(메일, 이름)
			msg.addFrom(new InternetAddress[] {new InternetAddress(dto.getSenderMail(), dto.getSenderName())});
			
			// 메일 제목
			msg.setSubject(dto.getSubject(), "utf-8");
			
			// 본문 내용
			msg.setText(message, "utf-8");
	
			mailSender.send(msg);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void sendIDByEmail(String id_email, String foundID) {
		
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			// 메일 제목 설정
			msg.setSubject("아이디 찾기 결과 안내", "utf-8");
			
			// 메일 내용 설정
			String htmlStr = "<h2>안녕하세요. PoMall입니다.</h2><br><br>" 
					+ "<p>요청하신 아이디를 보내드립니다.</p>"
					+ "<p>아이디는 <strong>" + foundID + "</strong> 입니다.</p>"
					+ "<a href='http://localhost:8081/member/login'>로그인 하러 가기</a><br><br>"
					+ "감사합니다.";
			
			msg.setText(htmlStr, "utf-8", "html");
			
			// 발신자 이메일 주소 설정
			msg.setFrom(new InternetAddress("PoMall@pomall.com", "PoMall"));
			
			// 수신자 이메일 주소 설정
			msg.addRecipient(RecipientType.TO, new InternetAddress(id_email));
			
			// 메일 전송
			mailSender.send(msg);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void sendPWByEmail(String pw_email, String tempPW) {
		// TODO Auto-generated method stub
		// 메일 객체 생성
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			// 메일 제목 설정
			msg.setSubject("임시 비밀번호 안내", "utf-8");
			
			// 메일 내용 설정
			String htmlStr = "<h2>안녕하세요. PoMall입니다.</h2><br><br>" 
					+ "<p>요청하신 임시 비밀번호를 보내드립니다.</p>"
					+ "<p>임시 비밀번호는 <strong>" + tempPW + "</strong> 입니다.</p>"
					+ "<p>로그인 후 비밀번호를 변경해 주세요.</p><br>"
					+ "<a href='http://localhost:8081/member/login'>로그인 하러 가기</a><br><br>"
					+ "감사합니다.";
			
			msg.setText(htmlStr, "utf-8", "html");
			
			// 발신자 이메일 주소 설정
			msg.setFrom(new InternetAddress("PoMall@pomall.com", "PoMall"));
			
			// 수신자 이메일 주소 설정
			msg.addRecipient(RecipientType.TO, new InternetAddress(pw_email));
			
			// 메일 전송
			mailSender.send(msg);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}

























