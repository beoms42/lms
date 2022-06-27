package kr.co.gdu.lms.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.LectureSerivce;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LectureRestController {
	@Autowired private LectureSerivce lectureService;
	
	@PostMapping("/isExistLeuctre")
	public String isExistLeuctre(@RequestParam(value="lectureName") String lectureName) {
		
		//row = count(*)의 값 단일함수라 0 또는 1만나옴(유니크값 조회)
		int row = lectureService.selectLectureName(lectureName);
		
		log.debug(CF.JYI+"LectureRestController.isExistLeuctre row : "+row+CF.RS);
		
		if(row == 1) {
			return "false";
		}
		
		return lectureName;
	}
}
