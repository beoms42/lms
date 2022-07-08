package kr.co.gdu.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatsController {
	@GetMapping("/loginCheck/gradeStats")
	public String addGrade() {
		return "stats/gradeStats";
	}
	@GetMapping("/loginCheck/dropRecordStats")
	public String addDropRecords() {
		return "stats/dropRecordStats";
	}
	@GetMapping("/loginCheck/stats_main")
	public String addStatsMain() {
		return "stats/stats_main";
	}
	@GetMapping("/loginCheck/ageAvgStats")
	public String addAgeAvg() {
		return "stats/classAgeAvgStats";
	}
	@GetMapping("/loginCheck/genderStats")
	public String addGenderStats() {
		return "stats/genderRateStats";
	}
	@GetMapping("/loginCheck/perClass")
	public String addPerClass() {
		return "stats/perClassStatas";
	}
	@GetMapping("/loginCheck/graduate")
	public String addGraduate() {
		return "/stats/graduateStats";
	}
}
