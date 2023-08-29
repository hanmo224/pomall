package com.pomall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pomall.domain.OrderVO;
import com.pomall.domain.PaymentVO;
import com.pomall.dto.OrderListDTO;

public interface OrderMapper {

	// 주문정보
	void order_save(OrderVO o_vo);
	
	// 주문상세 : 장바구니 테이블 이용
	void order_detail_save(Long ord_code);
	
	// 결제정보
	void payment_save(PaymentVO p_vo);
	
	// 주문내역
	List<OrderListDTO> order_list(String mb_id);
	
	// 주문 취소
	void order_cancel(@Param("ord_code") Long ord_code, @Param("string") String string);
	void order_cancel2(@Param("ord_code") Long ord_code, @Param("string") String string);
}
