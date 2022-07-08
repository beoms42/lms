package kr.co.gdu.lms.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gdu.lms.service.YoungInService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class YoungInRestController {
	@Autowired YoungInService youngInService;
	
	@GetMapping("/militaryStatus")
	public List<Map<String,Object>> militaryStatus(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = youngInService.selectMilitaryStatus();
		return list;
	}
}
