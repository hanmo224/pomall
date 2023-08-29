package com.pomall.domain;

import java.util.Date;

import lombok.Data;

@Data
public class AdOrderDetailVO {

	private Long ord_code;
	private Integer pro_code;
	private int dt_amount;
	private int dt_price;
	private String pro_name;
	private String pro_img;
	private String pro_up_folder;
	
	private int unitprice;
	private String status;
	private Date cancel_date;
}
