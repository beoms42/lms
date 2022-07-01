package kr.co.gdu.lms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.gdu.lms.service.HyeinService;

@Controller
public class HyeinController {
	@Autowired HyeinService hyeinService;
	
	// 출결관리 - 강사 이상만
	@GetMapping("/loginCheck/attendance")
	public String attendance(HttpSession session) {
		
		// 강사 이상 아니면 메인으로
		if((int)session.getAttribute("sessionLv") < 2) {
			return "redirect:/loginCheck/main";
		}
		
		
		return "lecture/attendance";
	}
	
	
}
