package com.pomall.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberVO {

	private String mb_id;
	private String mb_gender;
	private String mb_name;
	private String mb_rrn;
	private String mb_pw;
	private String mb_nick;
	private String mb_phone;
	private String mb_email;
	private String mb_zipcode;
	private String mb_addr;
	private String mb_detaddr;
	private String mb_receive;
	private int mb_point;
	private Date last_date;
	private Date join_date;
	private Date up_date;
	
	private String status;
	private Date inactive_date;
}
