package com.pomall.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartListDTO {

	private int num;
	private Long ca_code;
	private String pro_up_folder;
	private String pro_img;
	private int pro_code;
	private String pro_name;
	private int pro_price;
	private int ca_amount;
	private int unitprice;
}
