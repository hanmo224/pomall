package com.pomall.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pomall.domain.AdOrderDetailVO;
import com.pomall.domain.AdorderCancelVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.OrderVO;

public interface AdOrderService {

	// 주문 목록
	List<OrderVO> orderList(Criteria cri, String sDate, String eDate);
			
	// 목록 데이터 개수
	int getTotalCount(Criteria cri, String sDate, String eDate);
	
	// 주문 상세 보기
	List<AdOrderDetailVO> orderDetail(Long ord_code);
	
	// 주문상세(목록/취소) 주문상품 삭제
	void orderDetailProductDelete(Long ord_code, Integer pro_code);
	
	// 주문상세(목록/취소) 선택상품 삭제
	void orderDetailCheckProductDelete(Long ord_code, Integer pro_code);
	
	// 주문목록에서 삭제
	void orderListDelete(Long ord_code);
	
	// 주문취소에서 삭제 - 주문코드
	void orderCancelDelete(Long ord_code);
	
	// 주문(목록/취소)에서 선택 삭제
	void orderCheckedDelete(List<Long> ord_code_arr);
	
	// 주문 취소
	List<OrderVO> order_cancel(Criteria cri, String sDdate, String eDate, String cancel_sDate, String cancel_eDate);
		
	// 주문 취소 목록 데이터 개수
	int getTotalCount2(Criteria cri, String sDdate, String eDate, String cancel_sDate, String cancel_eDate);

}










