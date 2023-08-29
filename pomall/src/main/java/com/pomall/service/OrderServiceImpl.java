package com.pomall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pomall.domain.OrderVO;
import com.pomall.domain.PaymentVO;
import com.pomall.dto.OrderListDTO;
import com.pomall.mapper.CartMapper;
import com.pomall.mapper.OrderMapper;

import lombok.Setter;

@Service
public class OrderServiceImpl implements OrderService{

	@Setter(onMethod_ = {@Autowired})
	private OrderMapper orderMapper;
	
	@Setter(onMethod_ = {@Autowired})
	private CartMapper cartmapper;

	@Transactional // 트랜잭션 적용. root-context.xml 파일에서 트랜잭션 설정 확인.
	@Override
	public void order_save(OrderVO o_vo, PaymentVO p_vo) {
		// 주문저장
		orderMapper.order_save(o_vo); // mapper에서 시퀀스를 통한 주문번호 확보
		
		// 주문 상세 정보 저장
		orderMapper.order_detail_save(o_vo.getOrd_code());
		
		// 결제 정보 저장
		p_vo.setOrd_code(o_vo.getOrd_code());
		orderMapper.payment_save(p_vo);
		
		// 장바구니 비우기
		cartmapper.cart_empty(o_vo.getMb_id());
	}

	@Override
	public List<OrderListDTO> order_list(String mb_id) {
		// TODO Auto-generated method stub
		return orderMapper.order_list(mb_id);
	}

	@Override
	public void order_cancel(Long ord_code, String string) {
		// TODO Auto-generated method stub
		orderMapper.order_cancel(ord_code,string);
	}

	@Override
	public void order_cancel2(Long ord_code, String string) {
		// TODO Auto-generated method stub
		orderMapper.order_cancel2(ord_code, string);
	}

}
















