package com.pomall.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pomall.domain.MemberVO;
import com.pomall.domain.OrderVO;
import com.pomall.domain.PaymentVO;
import com.pomall.dto.CartListDTO;
import com.pomall.dto.OrderGroupDTO;
import com.pomall.dto.OrderListDTO;
import com.pomall.kakaopay.ApproveResponse;
import com.pomall.kakaopay.ReadyResponse;
import com.pomall.service.CartService;
import com.pomall.service.KakaoPayService;
import com.pomall.service.MemberService;
import com.pomall.service.OrderService;
import com.pomall.util.FileUtils;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/order/*")
public class OrderController {

	@Setter(onMethod_ = {@Autowired})
	private OrderService orderService;
	
	// 회원 정보를 가져오기 위해 주입작업
	@Setter(onMethod_ = {@Autowired})
	private MemberService memberService;

	// 카카오페이 주입 작업
	@Setter(onMethod_ = {@Autowired})
	private KakaoPayService kakaoPayService;
		
	// 가맹점 코드 참조
	@Resource(name = "cid")
	private String cid;
	
	// 장바구니 주입작업
	@Setter (onMethod_ = {@Autowired})
	private CartService cartService;
	
	// 이미지 주입
	@Resource(name = "uploadPath") // servlet-context.xml 참조.
	private String uploadPath;  // C:\\dev\\upload\\pds\\

	// 주문 내역에 페이지를 위함
	private String mb_id;
	
	// 바로구매(주문정보 입력폼. 장바구니에 담김)
	@GetMapping("/order_info")
	public void order_info(HttpSession session, Model model) {
		
		// 사용자 아이디 받아오기
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
		
		// 장바구니 목록(상품)
		List<CartListDTO> cart_list = cartService.cart_list(mb_id);
				
		cart_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		// 주문 상품명
		String order_productName = cart_list.get(0).getPro_name() + "외 " + (cart_list.size() - 1) + "건 ";
		model.addAttribute("order_productName", order_productName);
				
		// 주문 목록
		model.addAttribute("cart_list", cart_list);
		
		// 주문 총금액
		model.addAttribute("cart_tot_price", cartService.cart_tot_price(mb_id));
		
		// 주문자 정보 -> membercontroller에서 주입을 받아옴
		model.addAttribute("memberVO", memberService.login(mb_id));
	}
	
	// 카카오페이 결제 준비 요청 : 
	@ResponseBody // -> ajax 호출을 위한 성격
	@GetMapping("/orderbuy")
	// 가지고 있던 정보가 클라이언트로 넘어간다.
	public ReadyResponse orderkakaoPay(String pay_type, String order_productName, OrderVO o_vo, PaymentVO p_vo, HttpSession session) {
			
		ReadyResponse readyResponse = new ReadyResponse();
			
		// 사용자 아이디 받기
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
			
		o_vo.setMb_id(mb_id); // 아이디 저장.
		p_vo.setMb_id(mb_id); // 아이디 저장.
		
		System.out.println("무통장주문정보: " + o_vo);
			
		log.info("결제방식: " + pay_type);
		log.info("무통장주문정보: " + o_vo);
		log.info("무통장결제정보: " + p_vo);
			
		// 주문정보, 주문상세정보, 결제정보, 장바구니 비우기
		if(pay_type.equals("bank")) {
				
			orderService.order_save(o_vo, p_vo); // 무통장 처리
		}
			
		// 카카오페이 결제 작업
		if(pay_type.equals("kakaopay")) {
				
			p_vo.setPay_user(p_vo.getMb_id());
			p_vo.setPay_bank("kakaopay");
				
			log.info("카카오페이 결제 정보: " + p_vo);
				
			orderService.order_save(o_vo, p_vo); // 카카오페이 결제처리
				
			// 결제 준비 요청 성공시 사용 주소
			String approval_url = "http://localhost:8081/order/orderApproval";
				
			// 결제 준비 요청 실패시 사용 주소
			String fail_url = "http://localhost:8081/order/orderCancel";
				
			// 결제 실패시 사용 주소
			String cancel_url = "http://localhost:8081/order/orderFail";
				
			// 결제 준비 요청 작업 및 응답 데이터
			readyResponse = kakaoPayService.payReady(cid, o_vo.getOrd_code(), mb_id, order_productName, 1, o_vo.getOrd_price(), 0, approval_url, fail_url, cancel_url);
				
			log.info("결제 준비 요청: " + readyResponse);
			log.info("결제 고유 번호(tid): " + readyResponse.getTid());
			log.info("결제  요청 URL(QR코드): " + readyResponse.getNext_redirect_pc_url());
				
			session.setAttribute("tid", readyResponse.getTid());
			session.setAttribute("ord_code", o_vo.getOrd_code());
		} 
			
		return readyResponse;
	}
	
	// 결제 승인 요청. 카카오페이 API서버에서 다음 주소가 호출이 됨
	@GetMapping("/orderApproval")
	public String orderApproval(String pg_token, HttpSession session) {
		
		String tid = ((String) session.getAttribute("tid"));
		session.removeAttribute("tid");
		
		Long ord_code = (Long) session.getAttribute("ord_code");
		session.removeAttribute("ord_code");
		
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
		
		// 결제 승인 요청 작업
		ApproveResponse approveResponse = kakaoPayService.payApprove(cid, tid, ord_code, mb_id, pg_token);
		
		return "redirect:/order/orderComplete"; // 결제 성공 결과 페이지 주소
	}
	
	// 결제 완료 
	@GetMapping("/orderComplete")
	public String orderComplete() {
		
		return "/order/orderComplete";
	}
	
	// 카카오 페이 결제 취소
	@GetMapping("/orderCancel")
	public void orderCancel() {
			
	}
		
	// 카카오 페이 결제 실패 
	@GetMapping("/orderFail")
	public void orderFail() {
			
	}
	
	// 주문내역
	@GetMapping("/order_list")
	public void order_list(HttpSession session, Model model) {

		// 사용자 아이디를 받아오기
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();

	    List<OrderListDTO> order_list = orderService.order_list(mb_id);
		
		order_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		// 주문 코드별로 그룹화
		Map<Long, List<OrderListDTO>> orderGroupsMap = order_list.stream()
				.collect(Collectors.groupingBy(OrderListDTO::getOrd_code));
		
		// 그룹별 총 주문금액 계산
	    Map<Long, Integer> orderGroupTotals = new HashMap<>();

	    for(Map.Entry<Long, List<OrderListDTO>> entry : orderGroupsMap.entrySet()) {
	        long groupTotal = entry.getValue().stream()
	            .mapToLong(OrderListDTO::getUnitprice)
	            .sum();
	        orderGroupTotals.put(entry.getKey(), (int)groupTotal);
	    }
				
		// Map을 List로 변환
		List<OrderGroupDTO> order_groups = orderGroupsMap.entrySet().stream()
				.map(entry -> new OrderGroupDTO(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
		
		// 주문일로 그룹 정렬 (내림차순)
	    Collections.sort(order_groups, new Comparator<OrderGroupDTO>() {
	        @Override
	        public int compare(OrderGroupDTO o1, OrderGroupDTO o2) {
	            return o2.getOrder_list().get(0).getOrd_regdate().compareTo(o1.getOrder_list().get(0).getOrd_regdate());
	        }
	    });
		
		// 모델에 추가
		model.addAttribute("order_groups", order_groups);
		model.addAttribute("order_group_totals", orderGroupTotals);
	}
	
	// 이미지 보여주기
	@ResponseBody
	@GetMapping("/displayImage")
	public ResponseEntity<byte[]> displayFile(String folderName, String fileName) throws IOException {
				
		// uploadPath : 들어있는 경로 C:\\dev\\upload\\pds\\
		return FileUtils.getFile(uploadPath + folderName, fileName);
	}
	
	// 주문 취소
	@ResponseBody
	@GetMapping("/order_cancel")
	public ResponseEntity<String> order_cancel(Long ord_code) {
		
		ResponseEntity<String> entity = null;
		
		orderService.order_cancel(ord_code, "주문취소");
		orderService.order_cancel2(ord_code, "주문취소");
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
}




















