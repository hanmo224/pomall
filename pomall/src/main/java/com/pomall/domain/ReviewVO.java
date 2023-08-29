package com.pomall.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {

	private Long rev_num;
	private String mb_id;
	private int pro_code;
	private String rev_content;
	private int rev_point;
	private Date rev_regdate;
}
