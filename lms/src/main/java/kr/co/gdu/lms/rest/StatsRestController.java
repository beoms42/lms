package kr.co.gdu.lms.rest;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gdu.lms.service.StatsService;

@RestController
public class StatsRestController {
	@Autowired private StatsService statsservice;
	@GetMapping("/addAvgScore")
	public List<Map<String,Object>> addAvgScore(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = statsservice.selectAvgScore();
		return list;
	}
	@GetMapping("/addDropList")
	public List<Map<String,Object>> addDropList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = statsservice.addDropRecord();
		return list;
	}
}
