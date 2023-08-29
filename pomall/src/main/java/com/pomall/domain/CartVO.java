package com.pomall.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CartVO {
	
	private Long ca_code;
	private Integer pro_code;
	private String mb_id;
	private int ca_amount;
}
