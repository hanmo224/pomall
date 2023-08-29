package com.pomall.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderVO {

	private Long ord_code;
	private String mb_id;
	private String ord_name;
	private String ord_addr_post;
	private String ord_addr_basic;
	private String ord_addr_detail;
	private String ord_tel;
	private int ord_price;
	private Date ord_regdate;
	
	// 추가 컬럼 
	private String pay_method;
	private String status;
	private Date cancel_date;
}
