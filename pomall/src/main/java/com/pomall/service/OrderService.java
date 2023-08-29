package com.pomall.service;

import java.util.List;

import com.pomall.domain.OrderVO;
import com.pomall.domain.PaymentVO;
import com.pomall.dto.OrderListDTO;

public interface OrderService {

	// 주문정보
	void order_save(OrderVO o_vo, PaymentVO p_vo);
	
	// 주문내역
	List<OrderListDTO> order_list(String mb_id);
	
	// 주문 취소
	void order_cancel(Long ord_code, String string);
	void order_cancel2(Long ord_code, String string);
}
