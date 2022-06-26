package kr.co.gdu.lms.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.CommunityService;
import kr.co.gdu.lms.service.LectureSerivce;
import kr.co.gdu.lms.vo.LectureRoom;
import kr.co.gdu.lms.vo.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommunityController {
	@Autowired private CommunityService communityService;
	
	// 영인 - get방식 qnaList호출
	@GetMapping("/loginCheck/qnaList")
	public String addLecture(Model model
			, HttpSession session) {
		List<Qna> qnaList = communityService.selectQnaList();
		
		log.debug(CF.JYI+"CommunityController.qnaList.get qnaList : "+qnaList+CF.RS);
		
		model.addAttribute("qnaList", qnaList);
		return "community/qnaList";
	}
}
