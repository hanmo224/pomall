package com.pomall.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderListDTO {

	private int num;
	private Long ord_code;
	private int pro_code;
	private int dt_amount;
	private int dt_price;
	private Date ord_regdate;
	
	private String pro_name;
	private String pro_up_folder;
	private String pro_img;
	
	private int unitprice;
}
