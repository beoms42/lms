package kr.co.gdu.lms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LectureController {

	@GetMapping("/createLecture")
	public String createLecture() {
		
		return "lecture/createLecture";
	}

}
