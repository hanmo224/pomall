package com.pomall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomall.domain.CartVO;
import com.pomall.dto.CartListDTO;
import com.pomall.mapper.CartMapper;

import lombok.Setter;

@Service
public class CartServiceImpl implements CartService{

	@Setter(onMethod_ = {@Autowired})
	private CartMapper cartMapper;

	@Override
	public int cart_add(CartVO vo) {
		// TODO Auto-generated method stub
		return cartMapper.cart_add(vo);
	}

	@Override
	public List<CartListDTO> cart_list(String mb_id) {
		// TODO Auto-generated method stub
		return cartMapper.cart_list(mb_id);
	}

	@Override
	public int cart_amount_change(Long ca_code, int ca_amount) {
		// TODO Auto-generated method stub
		return cartMapper.cart_amount_change(ca_code, ca_amount);
	}

	@Override
	public int cart_delete(Long ca_code) {
		// TODO Auto-generated method stub
		return cartMapper.cart_delete(ca_code);
	}

	@Override
	public int cart_check_delete(List<Long> ca_code) {
		// TODO Auto-generated method stub
		return cartMapper.cart_check_delete(ca_code);
	}
	
	@Override
	public Integer cart_tot_price(String mb_id) {
		// TODO Auto-generated method stub
		return cartMapper.cart_tot_price(mb_id);
	}

	@Override
	public void cart_empty(String mb_id) {
		// TODO Auto-generated method stub
		cartMapper.cart_empty(mb_id);
	}

	@Override
	public int CartItemCount(String mb_id) {
		// TODO Auto-generated method stub
		return cartMapper.CartItemCount(mb_id);
	}

}















