package kr.co.gdu.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatsController {
	@GetMapping("/loginCheck/gradeStats")
	public String grade() {
		return "stats/gradeStats";
	}
	@GetMapping("/loginCheck/dropRecordStats")
	public String dropRecords() {
		return "stats/dropRecordStats";
	}
	@GetMapping("/loginCheck/stats_main")
	public String statsMain() {
		return "stats/stats_main";
	}
	@GetMapping("/loginCheck/ageAvgStats")
	public String ageAvg() {
		return "stats/classAgeAvgStats";
	}
	@GetMapping("/loginCheck/genderStats")
	public String genderStats() {
		return "stats/genderRateStats";
	}
}
