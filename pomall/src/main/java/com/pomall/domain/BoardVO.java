package com.pomall.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardVO {

	private Long user_num;
	private String user_title;
	private String mb_id;
	private String user_content;
	private Date user_reg_date;
	private Date user_up_date;

}
