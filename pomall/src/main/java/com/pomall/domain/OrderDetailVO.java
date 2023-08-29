package com.pomall.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderDetailVO {

	private Long ord_code;
	private int pro_code;
	private int dt_amount;
	private int dt_price;
	
	private String status;
	private Date cancel_date;
}
