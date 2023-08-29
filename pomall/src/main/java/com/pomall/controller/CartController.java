package com.pomall.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pomall.domain.CartVO;
import com.pomall.domain.MemberVO;
import com.pomall.dto.CartListDTO;
import com.pomall.service.CartService;
import com.pomall.util.FileUtils;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/cart/*")
public class CartController {

	@Setter(onMethod_ = {@Autowired})
	private CartService cartService;
	
	@Resource(name = "uploadPath") // servlet-context.xml 참조.
	private String uploadPath;  // C:\\dev\\upload\\pds\\
	
	// 장바구니 담기
	@ResponseBody
	@PostMapping("/cart_add")
	public ResponseEntity<String> cart_add(CartVO vo, HttpSession session) {
		
		ResponseEntity<String> entity = null;
		
		// 사용자 아이디를 받아오기
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
		vo.setMb_id(mb_id);
		
		if(cartService.cart_add(vo) == 1) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		}else {
			entity = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		
		return entity;
	}
	
	// 바로구매시 장바구니 담기 작업
	@GetMapping("/direct_cart_add")
	public String direct_cart_add(CartVO vo, HttpSession session) {
		
		// 사용자 아이디를 받아오기
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
		vo.setMb_id(mb_id);
		
		cartService.cart_add(vo);
		
		return "redirect:/order/order_info";
	}
	
	// 장바구니 리스트. 페이징 기능을 추가 하고 싶다면 @ModelAttribute와 쿼리 수정하기
	@GetMapping("/cart_list")
	public void cart_list(HttpSession session, Model model) {
		
		// 사용자 아이디 받아오기
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
		
		List<CartListDTO> cart_list = cartService.cart_list(mb_id);
		
		cart_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		model.addAttribute("cart_list", cart_list);
		
		// 장바구니 총 금액 구하기
		if(cart_list.size() != 0) {
			model.addAttribute("cart_tot_price", cartService.cart_tot_price(mb_id));
		}
	}
	
	// 이미지 보여주기
	@ResponseBody
	@GetMapping("/displayImage")
	public ResponseEntity<byte[]> displayFile(String folderName, String fileName) throws IOException {
				
		// uploadPath : 들어있는 경로 C:\\dev\\upload\\pds\\
		return FileUtils.getFile(uploadPath + folderName, fileName);
	}
	
	// 장바구니 수량 변경
	@ResponseBody
	// @PostMapping("/cart_amount_change")
	@GetMapping("/cart_amount_change")
	public ResponseEntity<String> cart_amount_change(Long ca_code, int ca_amount) {
		
		ResponseEntity<String> entity = null;
		
		if(cartService.cart_amount_change(ca_code, ca_amount) == 1) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		}else {
			entity = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		
		return entity;
	}
	
	// 장바구니 상품  삭제
	@ResponseBody
	@PostMapping("/cart_delete")
	public ResponseEntity<String> cart_delete(Long ca_code) {
		
		ResponseEntity<String> entity = null;
		
		if(cartService.cart_delete(ca_code) == 1) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		}else {
			entity = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		
		return entity;
	}
	
	// 장바구니 선택 상품 삭제
	@ResponseBody
	@PostMapping("/cart_check_delete")
	public ResponseEntity<String> cart_check_delete(@RequestParam("ca_code[]") List<Long> ca_code) {
		
		ResponseEntity<String> entity = null;
		
		cartService.cart_check_delete(ca_code);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	// 장바구니 비우기
	@GetMapping("/cart_empty")
	public String cart_empty(HttpSession session) {
		
		// 사용자 아이디 확보
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
		
		cartService.cart_empty(mb_id);
		
		return "redirect:/cart/cart_list";
	}
	
	// 장바구니 개수 카운트
	@GetMapping("/cartItemCount")
	@ResponseBody
	public ResponseEntity<Integer> CartItemCount(@RequestParam("mb_id") String mb_id, HttpSession session) {
	   // 사용자가 로그인되어 있는지 확인하고 ID에 기반하여 장바구니 상품 개수를 반환합니다
	   if (session.getAttribute("loginStatus") != null) {
	     int itemCount = cartService.CartItemCount(mb_id);
	     return ResponseEntity.ok(itemCount);
	   } else {
		   
	     return ResponseEntity.ok(0); // 사용자가 로그인되어 있지 않으므로 장바구니 상품 개수는 0입니다
	   }
	   
	}
}







































