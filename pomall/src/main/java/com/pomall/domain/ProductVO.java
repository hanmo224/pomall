package com.pomall.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductVO {
	
	private Integer pro_code;
	private int cg_code;
	private String pro_name;
	private int pro_price;
	private int pro_dis;
	private String pro_publisher;
	private String pro_content;
	private String pro_up_folder;
	private String pro_img; // 상품 이미지 파일명
	private int pro_amount;
	private String pro_buy;
	private Date pro_regdate;
	private Date pro_update;
	
	// 파일 업로드 필드(상품이미지)
	// <input type="file" class="form-control" id="uploadFile" name="uploadFile">
	private MultipartFile uploadFile;
}
