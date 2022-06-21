package kr.co.gdu.lms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.MemberService;
import kr.co.gdu.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired private MemberService memberService;
	
	// 학생정보 상세보기
	@GetMapping("/loginCheck/getStudentOne")
	public String getStudentOne (Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("sessionId");
		log.debug(CF.GDH+"MemberController.getStudentOne loginId : "+loginId+CF.RS);
		Student student = memberService.getStudentOne(loginId);
		log.debug(CF.GDH+"MemberController.getStudentOne student : "+student+CF.RS);
		model.addAttribute("student", student);
		return "member/getStudentOne";
	}
}
