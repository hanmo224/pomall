package com.pomall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pomall.domain.AdOrderDetailVO;
import com.pomall.domain.AdorderCancelVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.OrderVO;
import com.pomall.mapper.AdOrderMapper;

import lombok.Setter;

@Service
public class AdOrderServiceImpl implements AdOrderService{

	@Setter(onMethod_ = {@Autowired})
	private AdOrderMapper adOrderMapper;
	
	@Override
	public List<OrderVO> orderList(Criteria cri, String sDate, String eDate) {
		// TODO Auto-generated method stub
		return adOrderMapper.orderList(cri, sDate, eDate);
	}

	@Override
	public int getTotalCount(Criteria cri, String sDate, String eDate) {
		// TODO Auto-generated method stub
		return adOrderMapper.getTotalCount(cri, sDate, eDate);
	}
	
	@Override
	public List<AdOrderDetailVO> orderDetail(Long ord_code) {
		// TODO Auto-generated method stub
		return adOrderMapper.orderDetail(ord_code);
	}

	@Override
	public void orderDetailProductDelete(Long ord_code, Integer pro_code) {
		// TODO Auto-generated method stub
		adOrderMapper.orderDetailProductDelete(ord_code, pro_code);
	}
	
	@Override
	public void orderDetailCheckProductDelete(Long ord_code, Integer pro_code) {
		// TODO Auto-generated method stub
		adOrderMapper.orderDetailCheckProductDelete(ord_code, pro_code);
	}

	@Transactional
	@Override
	public void orderListDelete(Long ord_code) {
		// TODO Auto-generated method stub
		
		// 결제코드
		adOrderMapper.orderPayDelete(ord_code);
		
		// 상품코드
		adOrderMapper.orderProDelete(ord_code);
		
		// 주문코드
		adOrderMapper.orderListDelete(ord_code);
	}
	
	@Transactional
	@Override
	public void orderCancelDelete(Long ord_code) {
		// TODO Auto-generated method stub
		
		// 결제코드
		adOrderMapper.orderPayCancelDelete(ord_code);
		
		// 상품코드
		adOrderMapper.orderProCancelDelete(ord_code);
		
		// 주문코드
		adOrderMapper.orderCancelDelete(ord_code);
	}

	@Transactional
	@Override
	public void orderCheckedDelete(List<Long> ord_code_arr) {
		// TODO Auto-generated method stub
		
		// 결제코드
		adOrderMapper.orderCheckedPayDelete(ord_code_arr);
		
		// 상품코드
		adOrderMapper.orderCheckedProDelete(ord_code_arr);
		
		// 주문코드 삭제
		adOrderMapper.orderCheckedDelete(ord_code_arr);

	}

	@Override
	public List<OrderVO> order_cancel(Criteria cri, String sDate, String eDate, String cancel_sDate, String cancel_eDate) {
		// TODO Auto-generated method stub
		return adOrderMapper.order_cancel(cri, sDate, eDate, cancel_sDate, cancel_eDate);
	}

	@Override
	public int getTotalCount2(Criteria cri, String sDate, String eDate, String cancel_sDate, String cancel_eDate) {
		// TODO Auto-generated method stub
		return adOrderMapper.getTotalCount2(cri, sDate, eDate, cancel_sDate, cancel_eDate);
	}

	

}













