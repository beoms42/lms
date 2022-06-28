package kr.co.gdu.lms.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.NoticeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class NoticeRestController {
	@Autowired NoticeService noticeService;
	
	// 공지사항 파일 삭제
	@GetMapping("/deleteNoticeFile")
	public String deleteNoticeFile(HttpServletRequest request
								, @RequestParam(value="noticeFileName") String noticeFileName) {
		
		String result = "false";
		
		log.debug(CF.OHI+"NoticeRestController.deleteNoticeFile.param noticeFileName : "+noticeFileName+CF.RS);

		String path = request.getServletContext().getRealPath("/file/noticeFile/");
		
		int row = noticeService.deleteNoticeFile(noticeFileName, path);
		
		if(row == 1) {
			result="true";
		}
		
		return result;
	}
}
