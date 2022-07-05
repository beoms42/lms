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
	
	// 출결상태 모두 출석으로 변경
	@GetMapping("/loginCheck/modifyAttendanceListAll")
	public String modifyAttendanceListAll(@RequestParam(value="scheduleNo") int scheduleNo
										, @RequestParam(value="scheduleDate") String scheduleDate) {
		
		log.debug(CF.OHI+"HyeinController.modifyAttendanceListAll scheduleNo : "+scheduleNo+CF.RS);
		
		hyeinService.modifyAttendanceListAll(scheduleNo);
		
		return "redirect:/loginCheck/getAttendanceList?scheduleDate="+scheduleDate;
	}
	// 출결리스트
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
		
		return "lecture/getAttendance";
	}
	
	// 출결관리 수정 폼- 강사 이상만
	@GetMapping("/loginCheck/modifyAttendanceList")
	public String modifyAttendanceList(Model model
										, HttpSession session
										, @RequestParam(value="scheduleDate", defaultValue="") String scheduleDate) {
		// 강사 이상 아니면 메인으로
		if((int)session.getAttribute("sessionLv") < 2) {
			return "redirect:/loginCheck/main";
		}
		
		log.debug(CF.OHI+"HyeinController.modifyAttendance.get scheduleDate : "+scheduleDate+CF.RS);
		
		Map<String, Object> map = new HashMap<>();
		map.put("loginId", session.getAttribute("sessionId"));
		map.put("scheduleDate", scheduleDate);
		
		List<Map<String, Object>> returnList = hyeinService.getAttendanceList(map);
		
		model.addAttribute("list", returnList);
		
		return "lecture/modifyAttendanceList";
	}

	// 출결관리 수정 액션
	@PostMapping("/loginCheck/modifyAttendanceList")
	public String modifyAttendanceList(@RequestParam(value="list") List<String> list
									, @RequestParam(value="scheduleDate") String scheduleDate) {
		
		log.debug(CF.OHI+"HyeinController.modifyAttendList.post list : "+list+CF.RS);
		
		hyeinService.modifyAttendace(list);
		
		return "redirect:/loginCheck/getAttendanceList?scheduleDate="+scheduleDate;
	}
}
