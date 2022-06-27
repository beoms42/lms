package kr.co.gdu.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.NoticeService;
import kr.co.gdu.lms.vo.Notice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	@Autowired private NoticeService noticeService;
	
	@GetMapping("/getNoticeListByPage")
	public String getNoticeListByPage(Model model
									, @RequestParam(value="currentPage" , defaultValue="1") int currentPage
									, @RequestParam(value="rowPerPage" , defaultValue="10") int rowPerPage) {
		
		log.debug(CF.OHI+"NoticeController.getNoticeByPage.param currentPage : "+currentPage+CF.RS);
		
		Map<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("rowPerPage", rowPerPage);
		
		// 공지사항 리스트 페이징
		Map<String, Object> returnMap = noticeService.getNoticeListByPage(map);
		
		log.debug(CF.OHI+"NoticeController.getNoticeByPage list : "+returnMap+CF.RS);
		
		List<Notice> list = (List<Notice>) returnMap.get("list");
		int lastPage = (int) returnMap.get("lastPage");
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", list);
		model.addAttribute("lastPage", lastPage);
		return "notice/getNoticeListByPage";
	}
}
