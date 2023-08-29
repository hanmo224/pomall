package com.pomall.service;

import java.util.List;

import com.pomall.domain.CartVO;
import com.pomall.dto.CartListDTO;

public interface CartService {

	// 장바구니 담기
	int cart_add(CartVO vo);
	
	// 장바구니 리스트
	List<CartListDTO> cart_list(String mb_id);
	
	// 장바구니 수량 변경
	int cart_amount_change(Long ca_code, int ca_amount);
	
	// 장바구니 상품 삭제
	int cart_delete(Long ca_code);
	
	// 장바구니 선택 상품 삭제
	int cart_check_delete(List<Long> ca_code);
	
	// 장바구니 총 금액 구하기
	Integer cart_tot_price(String mb_id);
	
	// 장바구니 비우기
	void cart_empty(String mb_id);
	
	// 장바구니 개수 카운트
	int CartItemCount(String mb_id);
}
