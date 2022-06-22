package kr.co.gdu.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.ManagerMapper;
import kr.co.gdu.lms.mapper.TeacherMapper;
import kr.co.gdu.lms.service.MemberService;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired private MemberService memberService;

	@Autowired  private ManagerMapper managerMapper;
	@Autowired  private TeacherMapper teacherMapper;
	
	
	/* 학생정보 상세보기
	@GetMapping("/loginCheck/getStudentOne")
	public String getStudentOne (Model model, HttpSession session) {
		// 세션을 통해 로그인ID 받아오기
		String loginId = (String) session.getAttribute("sessionId");
		log.debug(CF.GDH+"MemberController.getStudentOne loginId : "+loginId+CF.RS);
		
		// 맵에 로그인ID 담기
		Map<String, Object> returnMap = memberService.getStudentOne(loginId);
		log.debug(CF.GDH+"MemberController.getStudentOne student : "+student+CF.RS);
		model.addAttribute("student", student);
		return "member/getStudentOne";
	}
	*/
	
	// 학생정보 수정폼
	@GetMapping("/loginCheck/modifyStudent")
	public String modifyStudent (Model model, Student student) {
		log.debug(CF.GDH+"MemberController.modifyStudent.Get student : "+student+CF.RS);
		model.addAttribute("student", student);
		return "member/modifyStudent";
	}
	
	// 학생정보 수정액션
	@PostMapping("/loginCheck/modifyStudent")
	public String modifyStudent (Student student) {
		// 받아온 매개변수 디버깅
		log.debug(CF.GDH+"MemberController.modifyStudent.Post student : "+student+CF.RS);
		
		//서비스로 보내기
		int row = memberService.modifyStudent(student);
		
		return "redirect:/loginCheck/getStudentOne?loginId=" + student.getLoginId();
	}
	
	// 매니저 목록 리스트
			@GetMapping("/loginCheck/getmanagerList")
			 public String getManagerList(Model model) {
				 log.debug(CF.PSH+"MemberController.getManagerList :"+ CF.RS);
				 List<Manager> list = memberService.getManagerList();
				 model.addAttribute("manager", list );
			 return "getManagerList";   
			 }
			 
			// 매니저 상세보기
			 @GetMapping("/loginCheck/getmanagerOne")
			 public String getManagerOne(Model model, HttpSession session) {
					String loginId = (String) session.getAttribute("sessionId");
				 	Manager manager = new Manager();
				 	manager = memberService.getManagerOne(loginId);
				 	log.debug(CF.PSH+"MemberController.getManagerOne :"+manager+CF.RS);
				 	model.addAttribute("manager", manager);
				 	return "getManagerOne";
					
				}
			 
			 // 매니저 정보 수정액션
			 @PostMapping("/loginCheck/modifyManager")
			 public String modifyManager(Model model, Manager manager) {
				 int row = 0;
				 row = memberService.modifyManager(manager);
			 	 log.debug(CF.PSH+"MemberController.modifyManager :"+manager+CF.RS);
				 return "getmanagerList"; //리다
			 }
			 
			 
			 // 매니저 회원탈퇴
			 @GetMapping("/loginCheck/deleteManager")
			 public String deleteManager(Model model, String loginId) {
				 int row = 0;
				 row = memberService.deleteManager(loginId);
			 	 log.debug(CF.PSH+"MemberController.deleteManager :"+loginId+CF.RS);
				 return "getmanagerList";
			 
			 
			 }
			 // 강사 목록 리스트
			 @GetMapping("/loginCheck/getTeacherList")
			 public String getTeacherList(Model model) {
				 List<Teacher>list = memberService.getTeacherList();
				 log.debug(CF.PSH+"MemberController.getTeacherList :"+ CF.RS);
				 model.addAttribute("teacher", list);
			 return "getTeacherList";
			 }
			 
			 
			// 강사 정보 상세보기
			 @GetMapping("/loginCheck/getTeacherOne")
			 public String getTeacherOne(Model model, HttpSession session) {
					String loginId = (String) session.getAttribute("sessionId");
				 	Teacher teacher = new Teacher();
				 	teacher = memberService.getTeacherOne(loginId);
				 	log.debug(CF.PSH+"MemberController.getManagerOne :"+teacher+CF.RS);
				 	model.addAttribute("teacher", teacher);
				 	return "getTeacherOne";
				}
			 
			 
			 // 강사 정보 수정하기 
			 @PostMapping("/loginCheck/modifyTeacher")
			 public String modifyTeacher(Model model, Teacher teacher) {
				 int row = 0;
				 row = memberService.modifyTeacher(teacher);
			 	 log.debug(CF.PSH+"MemberController.modifyTeacher :"+teacher+CF.RS);
				 return "getTeacherList";
			 }
			 
			 // 강사 회원탈퇴
			 @GetMapping("/loginCheck/deleteTeacher")
			 public String deleteTeacher(Model model, String loginId) {
				 int row = 0;
				 row = memberService.deleteTeacher(loginId);
			 	 log.debug(CF.PSH+"MemberController.deleteTeacher :"+loginId+CF.RS);
				 return "getTeacherList";
			 }
		 }


	
