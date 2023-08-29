package com.pomall.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pomall.domain.AdBoardVO;
import com.pomall.domain.BoardVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.MemberVO;
import com.pomall.dto.PageDTO;
import com.pomall.service.AdBoardService;
import com.pomall.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {

	@Setter(onMethod_ = {@Autowired})
	private BoardService boardService;
	
	// 공지사항 주입
	@Setter(onMethod_ = {@Autowired})
	private AdBoardService adBoardService;
	
	//CkEditor 업로드폴더
	@Resource(name = "uploadCkEditorPath") // 
	private String uploadCkEditorPath;  // C:\\dev\\upload\\ckeditor\\

	// 글쓰기 폼
	@GetMapping("/insert")
	private void insert() {
		
	}
	
	// 글 저장
	@PostMapping("/insert")
	private String insert(BoardVO board, HttpSession session) {
		
		String mb_id = ((MemberVO) session.getAttribute("loginStatus")).getMb_id();
		board.setMb_id(mb_id);
		
		boardService.insert(board);
		
		return "redirect:/board/list";
	}
	
	// 에디터
	@PostMapping("/imageUpload") // 스크립트의 주소랑 동일
	public void imageUpload(HttpServletRequest req, HttpServletResponse res, MultipartFile upload) {
		
		OutputStream out = null; // 출력 스트림
		PrintWriter printWriter = null; // 웹 상에서 서버에서 클라이언트에게 내용을 보낼 때 쓰는 객체
		
		// 클라이언트에게 보내는 정보에 설명.
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		try {
			// 1)업로드 작업
			String fileName = upload.getOriginalFilename(); // 클라이언트에서 보낸 파일이름.
			byte[] bytes = upload.getBytes(); // 클라이언트에서 보낸 업로드되는 파일을 바이트배열로 확보.
			
			String uploadPath = uploadCkEditorPath + fileName;
			
			log.info("파일업로드: " + uploadPath);
			
			out = new FileOutputStream(new File(uploadPath)); //0byte에 해당하는 파일 생성
			out.write(bytes); // 업로드된 파일
			out.flush();
			
			// 2)업로드된 파일정보를 CKEditor 에게 보내는 작업.
			printWriter = res.getWriter();
			
			// 1)톰캣 Context Path에서 Add External Web Module 작업을 해야 함.
			// Path : /upload, Document Base : C:\\dev\\upload\\ckeditor 설정
			// 2)Tomcat server.xml에서 <Context docBase="업로드경로" path="/upload" reloadable="true"/>
			
			String fileUrl = "/upload/" + fileName;
			// {"filename":"abc.gif", "uploaded":1, "url":"/upload/abc.gif"}
			printWriter.println("{\"filename\":\"" +  fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
			printWriter.flush();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if(printWriter != null) printWriter.close();
		}
	}
	
	// 글 목록
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		List<BoardVO> list = boardService.getListWithPaging(cri);
		model.addAttribute("boardList", list);
		
		int total = boardService.getTotalCount(cri);
		
		PageDTO pageDTO = new PageDTO(total, cri);
		model.addAttribute("pageMaker", pageDTO);
	}
	
	// 글 읽기, 수정폼
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("user_num") Long user_num, Model model) {
		
		model.addAttribute("board", boardService.get(user_num));
	}
	
	// 수정하기
	@PostMapping("/modify")
	public String modify(BoardVO board) {
		
		boardService.modify(board);
		
		return "redirect:/board/list";
	}
	
	// 삭제
	@GetMapping("/delete")
	public String delete(Long user_num) {
		
		boardService.delete(user_num);
		
		return "redirect:/board/list";
	}
	
	// 공지사항 목록
	@GetMapping("/notice_list")
	public void notice_list(Criteria cri, Model model) {
		
		List<AdBoardVO> list = adBoardService.getListWithPaging(cri);
		model.addAttribute("adboardList", list);
		
		int total = adBoardService.getTotalCount(cri);
		
		PageDTO pageDTO = new PageDTO(total, cri);
		model.addAttribute("pageMaker", pageDTO);
	}
	
	// 공지사항 읽기
	@GetMapping("/notice_get")			  
	public void notice_get(@RequestParam("adbo_num") Long adbo_num, Model model) {
		
		model.addAttribute("adboard", adBoardService.get(adbo_num));
	}
}













