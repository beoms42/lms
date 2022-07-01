package kr.co.gdu.lms.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.gdu.lms.mapper.StatsMapper;

@Controller
public class StatsController {
	@Autowired private StatsMapper statsmapper; 
	@GetMapping("/stats")
	public String stats(Model model) {
		List<Integer> avgList = statsmapper.selectAvgScore();
		model.addAttribute("avgList",avgList);
		return "/stats/stats";
	}
}
