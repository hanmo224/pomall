package com.pomall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pomall.domain.AdOrderDetailVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.OrderVO;

public interface AdOrderMapper {

	// 주문 목록
	List<OrderVO> orderList(@Param("cri") Criteria cri, @Param("sDate") String sDate, @Param("eDate") String eDate);
		
	// 목록 데이터 개수
	int getTotalCount(@Param("cri") Criteria cri, @Param("sDate") String sDate, @Param("eDate") String eDate);
	
	// 주문 상세 보기
	List<AdOrderDetailVO> orderDetail(Long ord_code);
	
	// 주문상세(목록/취소) 주문상품 삭제
	void orderDetailProductDelete(@Param("ord_code") Long ord_code, @Param("pro_code") Integer pro_code);
	
	// 주문상세(목록/취소) 선택상품 삭제
	void orderDetailCheckProductDelete(@Param("ord_code") Long ord_code, @Param("pro_code") Integer pro_code);
	
	// 주문목록에서 삭제 - 주문코드
	void orderListDelete(Long ord_code);
	// 주문목록에서 삭제 - 상품코드
	void orderProDelete(Long ord_code);
	// 주문목록에서 삭제 - 결제코드
	void orderPayDelete(Long ord_code);
	
	// 주문취소에서 삭제 - 주문코드
	void orderCancelDelete(Long ord_code);
	// 주문취소에서 삭제 - 상품코드
	void orderProCancelDelete(Long ord_code);
	// 주문취소에서 삭제 - 결제코드
	void orderPayCancelDelete(Long ord_code);
	
	// 주문(목록/취소)에서 선택 삭제
	void orderCheckedDelete(List<Long> ord_code_arr);
	// 주문(목록/취소)에서 선택 삭제
	void orderCheckedProDelete(List<Long> ord_code_arr);
	// 주문(목록/취소)에서 선택 삭제
	void orderCheckedPayDelete(List<Long> ord_code_arr);
	
	// 주문 취소
	List<OrderVO> order_cancel(@Param("cri") Criteria cri, 
							   @Param("sDate") String sDate,
							   @Param("eDate") String eDate,
						       @Param("cancel_sDate") String cancel_sDate,
						       @Param("cancel_eDate") String cancel_eDate);
	
	// 주문 취소 목록 데이터 개수
	int getTotalCount2(@Param("cri") Criteria cri, 
					   @Param("sDate") String sDate,
			   		   @Param("eDate") String eDate,
					   @Param("cancel_sDate") String cancel_sDate,
					   @Param("cancel_eDate") String cancel_eDate);
}









