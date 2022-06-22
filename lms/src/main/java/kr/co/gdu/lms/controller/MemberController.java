package kr.co.gdu.lms.controller;

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
	
	// 매니저 목록 리스트
				@GetMapping("/loginCheck/getmanagerList")
				 public String getManagerList(Model model) {
					 log.debug(CF.PSH+"MemberController.getManagerList :"+ CF.RS);
					 List<Manager> managerlist = memberService.getManagerList();
					 model.addAttribute("managerlist", managerlist );
				 return "member/getManagerList";   
				 }
				 
				// 매니저 상세보기
				 @GetMapping("/loginCheck/getmanagerOne")
				 public String getManagerOne(Model model
						 ,HttpSession session
						 ,@RequestParam(name="loginId") String loginId) {
					 
					 	Manager manager = new Manager();
					 	manager = memberService.getManagerOne(loginId);

					 	log.debug(CF.PSH+"MemberController.getManagerOne :"+manager+CF.RS);
					 	model.addAttribute("manager", manager);
					 	return "member/getManagerOne";
						
					}
				 
				 // 매니저 정보 수정액션
				 @PostMapping("/loginCheck/modifyManager")
				 public String modifyManager(Model model, Manager manager) {
					 int row = 0;
					 row = memberService.modifyManager(manager);
				 	 log.debug(CF.PSH+"MemberController.modifyManager :"+manager+CF.RS);
					 return "member/getmanagerList"; //리다
				 }
				 
				 
				 // 매니저 회원탈퇴
				 @GetMapping("/loginCheck/deleteManager")
				 public String deleteManager(Model model, String loginId) {
					 int row = 0;
					 row = memberService.deleteManager(loginId);
				 	 log.debug(CF.PSH+"MemberController.deleteManager :"+loginId+CF.RS);
					 return "member/getmanagerList";
				 
				 
				 }
				 // 강사 목록 리스트
				 @GetMapping("/loginCheck/getTeacherList")
				 public String getTeacherList(Model model) {
					 List<Teacher>teacherlist = memberService.getTeacherList();
					 log.debug(CF.PSH+"MemberController.getTeacherList :"+ CF.RS);
					 model.addAttribute("teacherlist", teacherlist);
				 return "member/getTeacherList";
				 }
				 
				 
				// 강사 정보 상세보기
				 @GetMapping("/loginCheck/getTeacherOne")
				 public String getTeacherOne(Model model
						 , HttpSession session
	                     , @RequestParam(name="loginId") String loginId) {
					 
					 	Teacher teacher = new Teacher();
					 	teacher = memberService.getTeacherOne(loginId);
					 	log.debug(CF.PSH+"MemberController.getManagerOne :"+teacher+CF.RS);
					 	model.addAttribute("teacher", teacher);
					 	return "member/getTeacherOne";
					}
				 
				 
				 // 강사 정보 수정하기 
				 @PostMapping("/loginCheck/modifyTeacher")
				 public String modifyTeacher(Model model, Teacher teacher) {
					 int row = 0;
					 row = memberService.modifyTeacher(teacher);
				 	 log.debug(CF.PSH+"MemberController.modifyTeacher :"+teacher+CF.RS);
					 return "member/getTeacherList";
				 }
				 
				 // 강사 회원탈퇴
				 @GetMapping("/loginCheck/deleteTeacher")
				 public String deleteTeacher(Model model, String loginId) {
					 int row = 0;
					 row = memberService.deleteTeacher(loginId);
				 	 log.debug(CF.PSH+"MemberController.deleteTeacher :"+loginId+CF.RS);
					 return "member/getTeacherList";
				 }
			 }

