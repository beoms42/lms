package kr.co.gdu.lms.rest;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	// 자료실 파일 삭제
	@GetMapping("/removeReferenceFile")
	public String removeReferenceFile(HttpServletRequest request
									, @RequestParam (name="referenceFileNo") int referenceFileNo
									, @RequestParam (name="referenceFileName") String referenceFileName) {
		String path = request.getServletContext().getRealPath("/file/referenceFile/");
		
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.removeReferenceFile referenceFileNo : "+referenceFileNo+CF.RS);
		log.debug(CF.HJI+"LectureController.removeReferenceFile referenceFileName : "+referenceFileName+CF.RS);
		log.debug(CF.HJI+"LectureController.removeReferenceFile path : "+path+CF.RS);
		
		// 실행
		int row = lectureService.removeReferenceFile(referenceFileNo, path, referenceFileName);
		if(row == 0) {
			log.debug(CF.HJI+"LectureController.removeReferenceFile row : "+row+CF.RS);
		}
		
		return row+"";
	}
	
	@GetMapping("/getReferenceOne")
	public Map<String,Object> getReferenceOne(@RequestParam(name="scheduleNo") int scheduleNo) {
		
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.getReferenceOne scheduleNo : "+scheduleNo+CF.RS);
		
		// 실행
		Map<String,Object> map = lectureService.getScheduleOne(scheduleNo);
		log.debug(CF.HJI+"LectureController.getReferenceOne map : "+map+CF.RS);
		
		return map;
	}
	
}
