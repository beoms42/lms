package kr.co.gdu.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatsController {
	@GetMapping("/loginCheck/stats")
	public String addGrade() {
		return "stats/stats";
	}

}
