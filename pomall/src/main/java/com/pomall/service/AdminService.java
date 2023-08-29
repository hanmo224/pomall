package com.pomall.service;

import com.pomall.domain.AdminVO;

public interface AdminService {

	// 관리자 로그인
	AdminVO admin_ok(String admin_ok);
	
	// 관리자 로그인 시간 업데이트
	void now_visit(String admin_id);
}
