package kr.co.gdu.lms.controller;

import java.util.List;
import java.util.Map;

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
import kr.co.gdu.lms.vo.Login;
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
		// 세션을 통해 로그인ID 받아오기
		String loginId = (String) session.getAttribute("sessionId");
		log.debug(CF.GDH+"MemberController.getStudentOne loginId : "+loginId+CF.RS);
		
		// 멤버서비스로 부터 맵에 담아서 들고오기
		Map<String, Object> returnMap = memberService.getStudentOne(loginId);
		log.debug(CF.GDH+"MemberController.getStudentOne returnMap : " + returnMap + CF.RS);
		
		model.addAttribute("student", returnMap.get("student"));
		model.addAttribute("memberFile", returnMap.get("memberFile"));
		return "member/getStudentOne";
	}
	
	// 학생정보 수정폼
	@GetMapping("/loginCheck/modifyStudent")
	public String modifyStudent (Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("sessionId");
		Map<String, Object> returnMap = memberService.getStudentOne(loginId);
		log.debug(CF.GDH+"MemberController.modifyStudent.Get returnMap : "+returnMap+CF.RS);
		
		model.addAttribute("student", returnMap.get("student"));
		model.addAttribute("memberFile", returnMap.get("memberFile"));
		
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
	
	// 학생정보 수정시 비밀번호 체크폼
	@GetMapping("/loginCheck/modifyPwCheck")
	public String modifyPwCheck (Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("sessionId");
		model.addAttribute("loginId", loginId);
		return "member/modifyPwCheck";
	}
	
	// 학생정보 수정시 비밀번호 체크액션
	@PostMapping("/loginCheck/modifyPwCheck")
	public String modifyPwCheck (Login login) {
		int row = memberService.getPwCheck(login);
		
		if(row == 1) {
			return "redirect:/loginCheck/modifyStudent";
		} else {
			return "redirect:/loginCheck/modifyPwCheck";
		}
	}
		
	// 학생정보 삭제시 비밀번호 체크폼
	@GetMapping("/loginCheck/removePwCheck")
	public String removePwCheck (Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("sessionId");
		model.addAttribute("loginId", loginId);
		return "member/removePwCheck";
	}
	
	// 학생정보 삭제시 비밀번호 체크액션
	@PostMapping("/loginCheck/removePwCheck")
	public String removePwCheck (Login login) {
		
		// 패스워드가 일치하는지 확인
		int row = memberService.getPwCheck(login);
		
		if(row == 1) {
			memberService.removeStudent(login);
			return "redirect:/login";
		} else {
			return "redirect:/loginCheck/removePwCheck";
		}
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

	
