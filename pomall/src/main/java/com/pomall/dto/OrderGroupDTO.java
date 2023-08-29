package com.pomall.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderGroupDTO {

	private Long ord_code;
	private List<OrderListDTO> order_list;
	
	public OrderGroupDTO(Long ord_code, List<OrderListDTO> order_list) {
		this.ord_code = ord_code;
		this.order_list = order_list;
	}
}
