package kr.co.gdu.lms.rest;

import java.util.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureRestController {
	
	@PostMapping("/getSubejctState")
	public String getSubejctStateByLectureId(String lectureId) {
		
		return null;
	}
}
