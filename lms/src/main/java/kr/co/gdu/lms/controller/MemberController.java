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
	
	// 학생정보 수정폼
	@GetMapping("/loginCheck/modifyStudent")
	public String modifyStudent (Model model, Student student) {
		log.debug(CF.GDH+"MemberController.modifyStudent student : "+student+CF.RS);
		model.addAttribute("student", student);
		return "member/modifyStudent";
	}
	
	// 학생정보 수정액션
	@PostMapping("/loginCheck/modifyStudent")
	public String modifyStudent (Model model, String loginId) {
		return "member/modifyStudent";
	}
	
		// managerList
		 public List<Manager> getManagerList() {
			 List<Manager> list = managerMapper.selectManagerList();
		 return list;   
		 }
		 
		// managerOne
		 public Manager getManagerOne(String loginId) {
			 	Manager manager = new Manager();
			 	manager = managerMapper.selectManagerOne(loginId);
				return manager;
			}
		 
		 // updateManager
		 
		 
		 
		 
		 // deleteManager
		 
		 
		 // teacherList
		 public List<Teacher> getTeacherList() {
			 List<Teacher>list = teacherMapper.selectTeacherList();
		 return list;
		 }
		 
		 
		// teacherOne
		 public Teacher getTeacherOne(String loginId) {
			 	Teacher teacher = new Teacher();
			 	teacher = teacherMapper.selectTeacherOne(loginId);
				return teacher;
			}
		 
		 
		 // updateteacher
		 
		 
		 
		 
	}

	
