package com.pomall.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pomall.domain.AdOrderDetailVO;
import com.pomall.domain.AdorderCancelVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.OrderVO;
import com.pomall.dto.PageDTO;
import com.pomall.service.AdOrderService;
import com.pomall.util.FileUtils;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/admin/order/*")
@Controller
public class AdOrderController {

	@Setter(onMethod_ = {@Autowired})
	private AdOrderService adOrderService;
	
	//업로드폴더 주입
	@Resource(name = "uploadPath") // servlet-context.xml 참조.
	private String uploadPath;  // C:\\dev\\upload\\pds\\
	
	// 주문 목록
	@GetMapping("/order_list")
	public void orderList(@ModelAttribute("cri") Criteria cri, @ModelAttribute("sDate") String sDate, @ModelAttribute("eDate") String eDate, Model model) {
		
		log.info("시작날짜: " + sDate);
		log.info("종료날짜: " + eDate);
		
		List<OrderVO> orderList = adOrderService.orderList(cri, sDate, eDate);
		model.addAttribute("orderList", orderList);
		
		int totalCount = adOrderService.getTotalCount(cri, sDate, eDate);
		model.addAttribute("pageMaker", new PageDTO(totalCount, cri));
	}
	
	// 주문 상세 보기
	@ResponseBody
	@GetMapping("/order_detail")
	public ResponseEntity<List<AdOrderDetailVO>> order_detail(Long ord_code) {
		
		ResponseEntity<List<AdOrderDetailVO>> entity = null;
		
		entity = new ResponseEntity<List<AdOrderDetailVO>>(adOrderService.orderDetail(ord_code), HttpStatus.OK);
		
		return entity;
	}
	
	// 이미지 매핑
	@ResponseBody
	@GetMapping("/displayImage")
	public ResponseEntity<byte[]> displayFile(String folderName, String fileName) throws IOException {
		
		//      C:\\dev\\upload\\pds\\
		return FileUtils.getFile(uploadPath + folderName, fileName);
	}
	
	// 주문상세(목록/취소) 주문상품 삭제
	@ResponseBody
	@PostMapping("/order_detail_product_delete")
	public ResponseEntity<String> orderDetailProductDelete(Long ord_code, Integer pro_code) {
		
		ResponseEntity<String> entity = null;
		
		// db작업
		adOrderService.orderDetailProductDelete(ord_code, pro_code);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	// 주문상세(목록/취소) 선택상품 삭제
	@ResponseBody
	@PostMapping("/oreder_detail_check_product_delete")
	public ResponseEntity<String> orderDetailCheckProductDelete(Long ord_code, Integer pro_code) {
		
		ResponseEntity<String> entity = null;
		
		adOrderService.orderDetailCheckProductDelete(ord_code, pro_code);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	// 주문목록에서 삭제
	@PostMapping("/order_list_delete")
	public String orderListDelete(Long ord_code, Criteria cri, RedirectAttributes rttr) {
		
		adOrderService.orderListDelete(ord_code);
		
		rttr.addFlashAttribute("msg", "delete");
		
		return "redirect:/admin/order/order_list";
	}
	
	// 주문취소에서 삭제
	@PostMapping("/order_cancel_delete")
	public String orderCancelDelete(Long ord_code, Criteria cri, RedirectAttributes rttr) {
		
		adOrderService.orderCancelDelete(ord_code);
		
		rttr.addFlashAttribute("msg", "delete");
		
		return "redirect:/admin/order/order_cancel";
	}
	
	// 주문(목록/취소)에서 선택 삭제
	@ResponseBody
	@PostMapping("/order_checked_delete")
	public ResponseEntity<String> orderCheckedDelete(
			@RequestParam("ord_code_arr[]") List<Long> ord_code_arr,
			String pay_method) {
		
		ResponseEntity<String> entity = null;
		
		adOrderService.orderCheckedDelete(ord_code_arr);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	// 주문 취소
	@GetMapping("/order_cancel")
	public void order_cancel(@ModelAttribute("cri") Criteria cri,
							 @ModelAttribute("sDate") String sDate,
			 				 @ModelAttribute("eDate") String eDate,
							 @ModelAttribute("cancel_sDate") String cancel_sDate,
							 @ModelAttribute("cancel_eDate") String cancel_eDate,
							 Model model) {
		
		List<OrderVO> order_cancel = adOrderService.order_cancel(cri, sDate, eDate, cancel_sDate, cancel_eDate);
		model.addAttribute("order_cancel", order_cancel);
		
		int totalCount = adOrderService.getTotalCount2(cri, sDate, eDate, cancel_sDate, cancel_eDate);
		model.addAttribute("pageMaker", new PageDTO(totalCount, cri));
	}
}














