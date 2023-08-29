package com.pomall.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AdBoardVO {

	private Long adbo_num;
	private String adbo_writer;
	private String adbo_title;
	private String adbo_content;
	private Date ad_reg_date;
	private Date ad_up_date;
}
