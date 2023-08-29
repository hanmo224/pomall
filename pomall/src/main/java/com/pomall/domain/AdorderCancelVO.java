package com.pomall.domain;

import java.util.Date;

import lombok.Data;

@Data
public class AdorderCancelVO {

	private int num;
	private Long ord_code;
	private Integer pro_code;
	private int dt_amount;
	private int dt_price;
	private String mb_id;
	private int ord_price;
	private String status;
	private Date cancel_date;
	private Date ord_regdate;
	private String ord_name;
	
	private String pro_up_folder;
	private String pro_img;
}
