package com.pomall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomall.domain.AdminVO;
import com.pomall.mapper.AdminMapper;

import lombok.Setter;

@Service
public class AdminServiceImpl implements AdminService{

	@Setter(onMethod_ = {@Autowired})
	private AdminMapper adminMapper;

	@Override
	public AdminVO admin_ok(String admin_ok) {
		// TODO Auto-generated method stub
		return adminMapper.admin_ok(admin_ok);
	}

	@Override
	public void now_visit(String admin_id) {
		// TODO Auto-generated method stub
		adminMapper.now_visit(admin_id);
	}
		
}
















