package kr.co.gdu.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	@Autowired
	private MemberService memberService;
	@Autowired
	private ManagerMapper managerMapper;
	@Autowired
	private TeacherMapper teacherMapper;

	@GetMapping("/loginCheck/getStudentList")
	public String getStudentList(Model model) {
		log.debug(CF.PSH + "MemberController.getStudentList :" + CF.RS);
		List<Student> studentlist = memberService.getStudentList();
		model.addAttribute("studentlist", studentlist);
		return "member/getStudentList";
	}

	// 학생정보 상세보기
	@GetMapping("/loginCheck/getStudentOne")
	public String getStudentOne(Model model, HttpSession session) {
		// 세션을 통해 로그인ID 받아오기
		String loginId = (String) session.getAttribute("sessionId");
		log.debug(CF.GDH + "MemberController.getStudentOne loginId : " + loginId + CF.RS);

		// 멤버서비스로 부터 맵에 담아서 들고오기
		Map<String, Object> returnMap = memberService.getStudentOne(loginId);
		log.debug(CF.GDH + "MemberController.getStudentOne returnMap : " + returnMap + CF.RS);

		model.addAttribute("student", returnMap.get("student"));
		model.addAttribute("memberFile", returnMap.get("memberFile"));
		return "member/getStudentOne";
	}

	// 학생정보 수정폼
	@GetMapping("/loginCheck/modifyStudent")
	public String modifyStudent(Model model, HttpSession session) {
		// 세션을 통해 로그인ID 받아오기
		String loginId = (String) session.getAttribute("sessionId");
		// 받아온 아이디로 수정폼에 띄울 학생정보 상세보기 returnMap에 담기
		Map<String, Object> returnMap = memberService.getStudentOne(loginId);
		log.debug(CF.GDH + "MemberController.modifyStudent.Get returnMap : " + returnMap + CF.RS);

		// returnMap에 담긴 학생정보와 사진파일을 모델에 담기
		model.addAttribute("student", returnMap.get("student"));
		model.addAttribute("memberFile", returnMap.get("memberFile"));

		return "member/modifyStudent";
	}

	// 학생정보 수정액션
	@PostMapping("/loginCheck/modifyStudent")
	public String modifyStudent(Student student) {
		// 받아온 매개변수 디버깅
		log.debug(CF.GDH + "MemberController.modifyStudent.Post student : " + student + CF.RS);

		// 서비스로 보내기
		int row = memberService.modifyStudent(student);

		return "redirect:/loginCheck/getStudentOne?loginId=" + student.getLoginId();
	}

	// 학생정보 수정시 비밀번호 체크폼
	@GetMapping("/loginCheck/modifyPwCheck")
	public String modifyPwCheck(Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("sessionId");
		model.addAttribute("loginId", loginId);
		return "member/modifyPwCheck";
	}

	// 학생정보 수정시 비밀번호 체크액션
	@PostMapping("/loginCheck/modifyPwCheck")
	public String modifyPwCheck(Login login) {
		int row = memberService.getPwCheck(login);

		if (row == 1) {
			return "redirect:/loginCheck/modifyStudent";
		} else {
			return "redirect:/loginCheck/modifyPwCheck";
		}
	}

	// 학생정보 삭제시 비밀번호 체크폼
	@GetMapping("/loginCheck/removePwCheck")
	public String removePwCheck(Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("sessionId");
		model.addAttribute("loginId", loginId);
		return "member/removePwCheck";
	}

	// 학생정보 삭제시 비밀번호 체크액션
	@PostMapping("/loginCheck/removePwCheck")
	public String removePwCheck(Login login) {

		// 패스워드가 일치하는지 확인
		int row = memberService.getPwCheck(login);

		if (row == 1) {
			memberService.removeStudent(login);
			return "redirect:/login";
		} else {
			return "redirect:/loginCheck/removePwCheck";
		}
	}

	// 멤버 사진 수정 폼
	@GetMapping("/loginCheck/modifyMemberFile")
	public String modifyMemberFile(Model model, @RequestParam(name = "memberFileName") String memberFileName) {
		log.debug(CF.GDH + "MemberController.modifyStudent.Post memberFileName : " + memberFileName + CF.RS);
		model.addAttribute("memberFileName", memberFileName);

		return "member/modifyMemberFile";
	}

	
	// 멤버 사진 수정 액션
	@PostMapping("/loginCheck/modifyMemberFile") 
	public String modifyMemberFile (HttpSession session
									, @RequestParam(name="deleteMemberFileName") String memberFileName
									, @RequestParam(name="insertMemberFile") MultipartFile insertMemberFile) {
		
	  String loginId = (String) session.getAttribute("sessionId");
	  
	  // 수정이 됐는지 확인 
	  int row = memberService.modifyMemberFile(loginId, memberFileName);
	 
	  if(row == 1) { 
		  return "redirect:/loginCheck/main"; 
	  } else { 
		  return "redirect:/loginCheck/modifyMemberFile"; }
	}
	 
	// 매니저 목록 리스트
	@GetMapping("/loginCheck/getmanagerList")
	public String getManagerList(Model model) {
		log.debug(CF.PSH + "MemberController.getManagerList :" + CF.RS);
		List<Manager> managerlist = memberService.getManagerList();
		model.addAttribute("managerlist", managerlist);
		return "member/getManagerList";
	}

	// 매니저 상세보기
	@GetMapping("/loginCheck/getmanagerOne")
	public String getManagerOne(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(name = "loginId") String loginId) {
		String path = request.getServletContext().getRealPath("/images/member/");
		Manager manager = new Manager();
		manager = memberService.getManagerOne(loginId);
		String fileName = memberService.selectMemberFileOne(loginId);
		log.debug(CF.PSH + "MemberController.getManagerOne :" + manager + CF.RS);
		model.addAttribute("manager", manager);
		model.addAttribute("fileName", fileName);
		return "member/getManagerOne";

	}

	// 매니저 정보 수정액션
	@PostMapping("/loginCheck/modifyManager")
	public String modifyManager(Model model, Manager manager) {
		int row = 0;
		row = memberService.modifyManager(manager);
		log.debug(CF.PSH + "MemberController.modifyManager :" + manager + CF.RS);
		return "member/getmanagerList"; // 리다
	}

//				// 매니저 정보 수정폼
//					@GetMapping("/loginCheck/modifyManager")
//					public String modifyManager (Model model, HttpSession session) {
//						String loginId = (String) session.getAttribute("sessionId");
//						Map<String, Object> returnMap = memberService.getManagerOne(loginId);
//					 	 log.debug(CF.PSH+"MemberController.modifyManager :"+returnMap+CF.RS);
//						
//						model.addAttribute("manager", returnMap.get("manager"));
//						model.addAttribute("memberFile", returnMap.get("memberFile"));
//						return "member/modifyManager";
//					}

	// 매니저 회원탈퇴
	@GetMapping("/loginCheck/deleteManager")
	public String deleteManager(Model model, String loginId) {
		int row = 0;
		row = memberService.deleteManager(loginId);
		log.debug(CF.PSH + "MemberController.deleteManager :" + loginId + CF.RS);
		return "member/getmanagerList";

	}

	// 강사 목록 리스트
	@GetMapping("/loginCheck/getTeacherList")
	public String getTeacherList(Model model) {
		List<Teacher> teacherlist = memberService.getTeacherList();
		log.debug(CF.PSH + "MemberController.getTeacherList :" + CF.RS);
		model.addAttribute("teacherlist", teacherlist);
		return "member/getTeacherList";
	}

	// 강사 정보 상세보기
	@GetMapping("/loginCheck/getTeacherOne")
	public String getTeacherOne(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(name = "loginId") String loginId) {
		String path = request.getServletContext().getRealPath("/images/member/");
		Teacher teacher = new Teacher();
		String fileName = memberService.selectMemberFileOne(loginId);
		teacher = memberService.getTeacherOne(loginId);
		log.debug(CF.PSH + "MemberController.getTeacherOne :" + teacher + CF.RS);
		model.addAttribute("teacher", teacher);
		model.addAttribute("fileName", fileName);
		return "member/getTeacherOne";
	}

	// 강사 정보 수정하기
	@PostMapping("/loginCheck/modifyTeacher")
	public String modifyTeacher(Model model, Teacher teacher) {
		int row = 0;
		row = memberService.modifyTeacher(teacher);
		log.debug(CF.PSH + "MemberController.modifyTeacher :" + teacher + CF.RS);
		return "member/getTeacherList";
	}

	// 매니저 정보 수정폼
	@GetMapping("/loginCheck/modifyTeacher")
	public String modifyTeacher(Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("sessionId");
		Map<String, Object> returnMap = (Map<String, Object>) memberService.getTeacherOne(loginId);
		log.debug(CF.PSH + "MemberController.modifyManager :" + returnMap + CF.RS);

		model.addAttribute("teacher", returnMap.get("teacher"));
		model.addAttribute("memberFile", returnMap.get("memberFile"));
		return "member/modifyTeacher";
	}

	// 강사 회원탈퇴
	@GetMapping("/loginCheck/deleteTeacher")
	public String deleteTeacher(Model model, String loginId) {
		int row = 0;
		row = memberService.deleteTeacher(loginId);
		log.debug(CF.PSH + "MemberController.deleteTeacher :" + loginId + CF.RS);
		return "member/getTeacherList";
	}
}
