package kr.co.gdu.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.NoticeService;
import kr.co.gdu.lms.vo.NoticeForm;
import kr.co.gdu.lms.vo.Notice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	@Autowired private NoticeService noticeService;
	
	// 공지사항 전체 리스트
	@GetMapping("/loginCheck/getNoticeListByPage")
	public String getNoticeListByPage(Model model
									, @RequestParam(value="currentPage" , defaultValue="1") int currentPage
									, @RequestParam(value="rowPerPage" , defaultValue="10") int rowPerPage) {
		
		log.debug(CF.OHI+"NoticeController.getNoticeByPage.param currentPage : "+currentPage+CF.RS);
		
		Map<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("rowPerPage", rowPerPage);
		
		// 공지사항 리스트 페이징
		Map<String, Object> returnMap = noticeService.getNoticeListByPage(map);
		
		log.debug(CF.OHI+"NoticeController.getNoticeByPage returnMap : "+returnMap+CF.RS);
		
		List<Notice> list = (List<Notice>) returnMap.get("list");
		int lastPage = (int) returnMap.get("lastPage");
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", list);
		model.addAttribute("lastPage", lastPage);
		return "notice/getNoticeListByPage";
	}
	
	// 공지사항 상세보기
	@GetMapping("/loginCheck/getNoticeOne")
	public String getNoticeOne(Model model
							, @RequestParam(value="noticeNo") int noticeNo) {
		
		log.debug(CF.OHI+"NoticeController.getNoticeOne.param noticeNo : "+noticeNo+CF.RS);
		
		// 해당 noticeNo의 공지사항 상세보기
		Map<String, Object> map = noticeService.getNoticeOne(noticeNo);
		log.debug(CF.OHI+"NoticeController.getNoticeOne map : "+map+CF.RS);
		
		model.addAttribute("notice", map.get("notice"));
		model.addAttribute("fileList", map.get("list"));
		
		return "notice/getNoticeOne";
	}
	
	// 공지사항 입력 폼
	@GetMapping("/loginCheck/adminManagerCheck/addNotice")
	public String addNotice(HttpSession session) {
		
		return "notice/addNotice";
	}
	
	// 공지사항 입력 액션
	@PostMapping("/loginCheck/addNotice")
	public String addNotice(HttpSession session
						, HttpServletRequest request
						, NoticeForm noticeForm) {
		
		log.debug(CF.OHI+"NoticeController.addNotice.param noticeForm : "+noticeForm+CF.RS);
		
		//세션에 담긴 아이디 담기
		String loginId = (String) session.getAttribute("sessionId");
		noticeForm.setLoginId(loginId);
		log.debug(CF.OHI+"NoticeController.addNotice loginId"+loginId+CF.RS);
		
		// 파일 담을 경로
		String path = request.getServletContext().getRealPath("/file/noticeFile/"); 
				
		int row = noticeService.addNotice(noticeForm, path);
		if(row == 1) {
			return "redirect:/loginCheck/getNoticeListByPage";
		} else {
			return "redirect:/loginCheck/addNotice";
		}
	}
	
	// 공지사항 수정 폼
	@GetMapping("/loginCheck/adminManagerCheck/modifyNotice")
	public String modifyNotice(Model model
							, HttpSession session
							, @RequestParam(value="noticeNo") int noticeNo) {
		
		// 주소통해 학생, 강사 들어왔을시 다시 리스트로 보내기
		if((int)session.getAttribute("sessionLv") < 3) {
			return "redirect:/loginCheck/getNoticeListByPage";
		}
		
		log.debug(CF.OHI+"NoticeController.modifyNotice.param noticeNo : "+noticeNo+CF.RS);
		
		// 해당 noticeNo의 공지사항 상세보기
		Map<String, Object> map = noticeService.getNoticeOne(noticeNo);
		log.debug(CF.OHI+"NoticeController.modifyNotice map : "+map+CF.RS);
		
		model.addAttribute("notice", map.get("notice"));
		model.addAttribute("fileList", map.get("list"));
		
		return "notice/modifyNotice";
	}
	
	// 공지사항 수정 액션
	@PostMapping("/loginCheck/modifyNotice")
	public String modifyNotice(HttpSession session 
							, HttpServletRequest request
							, NoticeForm noticeForm) {
	
		log.debug(CF.OHI+"NoticeController.modifyNotice.param noticeForm : "+noticeForm+CF.RS);
		
		//세션에 담긴 아이디 담기
		String loginId = (String) session.getAttribute("sessionId");
		noticeForm.setLoginId(loginId);
		log.debug(CF.OHI+"NoticeController.modifyNotice loginId"+loginId+CF.RS);
		
		// 파일 담을 경로
		String path = request.getServletContext().getRealPath("/file/noticeFile/");
		
		int row = noticeService.modifyNotice(noticeForm, path);
		log.debug(CF.OHI+"NoticeController modifyNotice row : "+row+CF.RS);
		
		if(row == 1) { // 수정 성공했다면 상세보기로
			return "redirect:/loginCheck/getNoticeOne?noticeNo="+noticeForm.getNoticeNo();
		} else { // 실패했다면 다시 수정 폼으로
			return "redirec:/loginCheck/modifyNotice?noticeNo=-"+noticeForm.getNoticeNo();
		}
		
	}
	
	// 공지사항 삭제 액션
	@GetMapping("/loginCheck/adminManagerCheck/deleteNotice")
	public String deleteNotice(HttpSession session
							, HttpServletRequest request
							, @RequestParam(value="noticeNo") int noticeNo) {
		
		log.debug(CF.OHI+"NoticeController.deleteNotice.param noticeNo : "+noticeNo+CF.RS);
		
		// 파일 담을 경로
		String path = request.getServletContext().getRealPath("/file/noticeFile/");
		
		int row = noticeService.deleteNotice(noticeNo, path);
		log.debug(CF.OHI+"NoticeController deleteNotice row : "+row+CF.RS);
		
		if(row == 1) { // 삭제 성공했다면 공지사항 리스트로
			return "redirect:/loginCheck/getNoticeListByPage";
		} else { // 실패했다면 상세보기로
			return "redirect:/loginCheck/getNoticeOne?noticeNo="+noticeNo;
		}
		
	}
}
