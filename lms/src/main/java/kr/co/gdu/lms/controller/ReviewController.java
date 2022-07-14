package kr.co.gdu.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.ReviewService;
import kr.co.gdu.lms.vo.EducationReview;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReviewController {
	@Autowired private ReviewService  reviewservice;
	//종강한 강의 리뷰 리스트	 
	@GetMapping("/loginCheck/addReview")
	public String review(Model model
									  ,@RequestParam (name="lectureName",defaultValue="12312") String lectureName
									  ,HttpSession session
									  ,EducationReview educationreview) {
		String loginId = (String) session.getAttribute("sessionId");
		Map<String,Object> paramMap = new HashMap<>();
		List<Map<String,Object>>list= reviewservice.selectLectureReviewList(lectureName);
		model.addAttribute("list", list);
		paramMap.put("loginId", loginId);
		paramMap.put("lectureName", lectureName);
		
		
	    log.debug(CF.PSH+"ReviewController.selectLectureReviewList:"+list+CF.RS);
		log.debug(CF.GMC + loginId+CF.RS);
		log.debug(CF.GMC + lectureName+CF.RS);
		
		model.addAttribute("lectureName",lectureName);
		
		
		return"review/review";
	}
	
	 
	@PostMapping("/loginCheck/addReview")
	public String review(Model model
						  ,HttpSession session
						  ,EducationReview educationreview
						  ,@RequestParam (name="lectureName") String lectureName) {
		String loginId = (String) session.getAttribute("sessionId");
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginId",loginId );
		paramMap.put("lectureName",lectureName);
		int educationNo = reviewservice.selectEducationNo(paramMap);
		educationreview.setEducationNo(educationNo);
		reviewservice.insertReview(educationreview);
		
		return "redirect:/loginCheck/addReview";
	}
	
}

