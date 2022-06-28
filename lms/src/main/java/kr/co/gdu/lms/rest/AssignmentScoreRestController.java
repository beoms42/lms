package kr.co.gdu.lms.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.AssignmentService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Transactional
@Slf4j
public class AssignmentScoreRestController {
	@Autowired private AssignmentService assignmentservice;
	@GetMapping("/submitScore")
	public String submitScore(@RequestParam (name="assignmentSubmitScore") int assignmentSubmitScore
						  ,@RequestParam (name="assignmentExamNo") int assignmentExamNo
						  ,@RequestParam (name="educationNo") int educationNo) {
		log.debug(CF.GMC+"submitScoreRestController assignmentSubmitScore" + assignmentSubmitScore+CF.RS);
		log.debug(CF.GMC+"submitScoreRestController assignmentExamNo" + assignmentExamNo+CF.RS);
		log.debug(CF.GMC+"submitScoreRestController educationNo" + educationNo+CF.RS);
		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("assignmentSubmitScore", assignmentSubmitScore);
		paramMap.put("assignmentExamNo", assignmentExamNo);
		paramMap.put("educationNo", educationNo);
		assignmentservice.updateScore(paramMap);
		return "true";
	}
	
}
