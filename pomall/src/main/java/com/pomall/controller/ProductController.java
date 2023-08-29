package com.pomall.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pomall.domain.CategoryVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.MemberVO;
import com.pomall.domain.ProductVO;
import com.pomall.dto.PageDTO;
import com.pomall.service.ProductService;
import com.pomall.util.FileUtils;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/product/*")
@Controller
public class ProductController {

	@Setter(onMethod_ = {@Autowired})
	private ProductService productService;
	
	@Resource(name = "uploadPath") // servlet-context.xml 참조.
	private String uploadPath;  // C:\\dev\\upload\\pds\\
	
	@ResponseBody // jackson-databind 라이브러리 필수
	@GetMapping("/subCategory/{cg_code}") // 주소의 값을 쓰고 싶으면 {}. product/subCategory/1 안쓰면 쿼리스트링 주소씀
	public ResponseEntity<List<CategoryVO>> subCategory(@PathVariable("cg_code") Integer cg_code) {
		
		ResponseEntity<List<CategoryVO>> entity = null;
		
		// 2차 카테고리를 보내줘야 하므로 success 안씀. 쓰이는 경우는 update, delete
		entity = new ResponseEntity<List<CategoryVO>>
			(productService.subCategoryList(cg_code), HttpStatus.OK);
		
		return entity;
	}
	
	// 상품 목록
	@GetMapping("/pro_list/{cg_code}/{cg_name}")
	// 메소드를 void로 주면 jsp명이 되므로 String을 줌
	public String pro_list(@ModelAttribute("cri") Criteria cri,
						   @PathVariable("cg_code") Integer cg_code, // 2차 카테고리 코드로 들어옴
						   @PathVariable("cg_name") String cg_name,
						   Model model) {
		
		List<ProductVO> pro_list = productService.pro_list(cg_code, cri);
		
		pro_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		model.addAttribute("pro_list", pro_list);
		
		int count = productService.pro_count(cg_code, cri);
		PageDTO pageDTO = new PageDTO(count, cri);
		
		model.addAttribute("pageMaker", pageDTO);
		
		return "product/pro_list";
	}
	
	// 이미지 보여주기
	@ResponseBody
	@GetMapping("/displayImage")
	public ResponseEntity<byte[]> displayFile(String folderName, String fileName) throws IOException {
		
		//      C:\\dev\\upload\\pds\\
		return FileUtils.getFile(uploadPath + folderName, fileName);
	}
	
	// 상품 상세 보기. 관리자 상품의 수정폼과 맥락은 비슷
	@GetMapping("/pro_detail")
	public void pro_detail(Integer pro_code, @ModelAttribute("cri") Criteria cri, Model model) {
		
		ProductVO vo = productService.pro_detail(pro_code);
		vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
			
		model.addAttribute("productVO", vo);
	
	}
	
}




























