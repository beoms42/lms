package kr.co.gdu.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.HyeinService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HyeinController {
	@Autowired HyeinService hyeinService;
	
	// 출결관리 - 강사 이상만
	@GetMapping("/loginCheck/getAttendanceList")
	public String getAttendanceList(Model model
									, HttpSession session
									, @RequestParam(value="scheduleDate", defaultValue="") String scheduleDate) {
		// 강사 이상 아니면 메인으로
		if((int)session.getAttribute("sessionLv") < 2) {
			return "redirect:/loginCheck/main";
		}
		
		log.debug(CF.OHI+"HyeinController.getAttendance.get scheduleDate : "+scheduleDate+CF.RS);
		
		Map<String, Object> map = new HashMap<>();
		map.put("loginId", session.getAttribute("sessionId"));
		map.put("scheduleDate", scheduleDate);
		
		List<Map<String, Object>> returnList = hyeinService.getAttendanceList(map);
		
		model.addAttribute("list", returnList);
		
		return "lecture/getAttendanceList";
	}

	@PostMapping("/loginCheck/getAttendanceList")
	public String getAttendanceList(@RequestParam(value="list") List<String> list
									, @RequestParam(value="scheduleDate") String scheduleDate) {
		
		log.debug(CF.OHI+"HyeinController.getAttendList.post list : "+list+CF.RS);
		
		hyeinService.modifyAttendace(list);
		
		return "redirect:/loginCheck/getAttendanceList?scheduleDate="+scheduleDate;
	}
}
