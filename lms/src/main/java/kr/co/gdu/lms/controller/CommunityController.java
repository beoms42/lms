package kr.co.gdu.lms.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.CommunityService;
import kr.co.gdu.lms.vo.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommunityController {
	@Autowired private CommunityService communityService;
	
	// 영인 - get방식 qnaList호출
	@GetMapping("/loginCheck/qnaList")
	public String getQnaList(Model model) {
		List<Qna> qnaList = communityService.getQnaList();
		
		log.debug(CF.JYI+"CommunityController.qnaList.get qnaList : "+qnaList+CF.RS);
		
		model.addAttribute("qnaList", qnaList);
		return "community/qnaList";
	}
	
	@GetMapping("/loginCheck/communityList")
	public String getCommunityList(Model model
								, @RequestParam (name="currentPage", defaultValue = "1") int currentPage
								, @RequestParam (name="rowPerPage", defaultValue = "10") int rowPerPage) {
		log.debug(CF.PHW+"CommunityController.getCommunityList.get currentPage : "+currentPage+CF.RS );
		log.debug(CF.PHW+"CommunityController.getCommunityList.get rowPerPage : "+rowPerPage+CF.RS );
		
		Map<String, Object> map = communityService.getCommunityList(currentPage, rowPerPage);
		
		log.debug(CF.PHW+"CommunityController.getCommunityList.get communityList : "+map.get("communityList")+CF.RS );
		log.debug(CF.PHW+"CommunityController.getCommunityList.get lastPage : "+map.get("lastPage")+CF.RS );
		
		model.addAttribute("communityList", map.get("communityList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		return "community/communityList";
	}
	
}
