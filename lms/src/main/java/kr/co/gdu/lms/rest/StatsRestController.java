package kr.co.gdu.lms.rest;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@GetMapping("/ageAvg")
	public List<Map<String,Object>> ageAvg(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = statsservice.selectClassAverAge();
		return list;
	}
	@GetMapping("/genderStats")
	public List<Map<String,Object>> genderStats(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = statsservice.genderRate();
		return list;
	}

	@GetMapping("/addPerClass")
	public List<Map<String,Object>> addPerClass(){
		List<Map<String,Object>> classList = new ArrayList<Map<String,Object>>();
		classList = statsservice.addPerClass();
		return classList;
	}
	@GetMapping("/graduate")
	public List<Map<String,Object>> addGraduate(){
		List<Map<String,Object>> graduateList = new ArrayList<Map<String,Object>>();
		graduateList = statsservice.addGraduate();
		return graduateList;
	}
	@GetMapping("/employmet")
	public List<Map<String,Object>> addEmployment(@RequestParam (name="lectureName",defaultValue="12312") String lectureName
												  ,HttpSession session){
		String loginId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		
		List<Map<String,Object>> employeeList = new ArrayList<Map<String,Object>>();
		employeeList = statsservice.addemployment(lectureName);
		return employeeList;
	}
	
}
