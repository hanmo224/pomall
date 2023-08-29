package com.pomall.service;

import com.pomall.dto.EmailDTO;

public interface EmailService {

	// 이메일 인증
	void sendMail(EmailDTO dto, String message);
	
	// 이메일로 아이디 보내기
	void sendIDByEmail(String id_email, String foundID);
	
	// 임시비밀번호 이메일로 보내기
	void sendPWByEmail(String pw_email, String tempPW);
}
