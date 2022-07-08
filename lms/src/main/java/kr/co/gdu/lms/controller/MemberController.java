package kr.co.gdu.lms.controller;

import java.util.HashMap;
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
import kr.co.gdu.lms.service.LoginService;
import kr.co.gdu.lms.service.MemberService;
import kr.co.gdu.lms.vo.Dept;
import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.Position;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
   @Autowired private MemberService memberService;
   @Autowired private LoginService loginService;

   // 회원 목록
   @GetMapping("/loginCheck/getMemberList")
   public String getMemberList(Model model
		   						,@RequestParam (name="msg") String msg) {
	   // msg 확인
	   log.debug(CF.GDH + "MemberController.getMemberList msg : " + msg + CF.RS);
       
	   // msg분기별로 정보받아오기
	   if("student".equals(msg)) {
		   List<Student> studentList = memberService.getStudentList();
		   log.debug(CF.GDH+"MemberController.getMemberList studentList : " + studentList + CF.RS);
		   model.addAttribute("msg", msg);
		   model.addAttribute("studentList", studentList);
	   } else if("teacher".equals(msg)) {
		   List<Teacher> teacherList = memberService.getTeacherList();
		   log.debug(CF.GDH+"MemberController.getMemberList teacherList : " + teacherList + CF.RS);
		   model.addAttribute("msg", msg);
		   model.addAttribute("teacherList", teacherList);
	   } else if("manager".equals(msg)) {
		   List<Manager> managerList = memberService.getManagerList();
	       log.debug(CF.GDH+"MemberController.getMemberList managerList : " + managerList + CF.RS);
	       model.addAttribute("msg", msg);
	       model.addAttribute("managerList", managerList);
	   }
       
       return "member/getMemberList";
   }

   // 멤버정보 상세보기
   @GetMapping("/loginCheck/getMemberOne")
   public String getMemberOne(Model model, @RequestParam(name="loginId") String loginId) {
	  // 받아온 로그인Id 디버깅
	  log.debug(CF.GDH + "MemberController.getMemberOne loginId : " + loginId + CF.RS);
	   
	  // 레벨 받아오기
      int level = loginService.getLevelByLoginId(loginId);
      log.debug(CF.GDH + "MemberController.getMemberOne level : " + level + CF.RS);
      
      // 로그인VO에 담기
      Login login = new Login();
      login.setLevel(level);
      login.setLoginId(loginId);
      
      Map<String, Object> returnMap = new HashMap<String, Object>();
   
      // 멤버서비스로부터 맵에 담아서 들고오기
      returnMap = memberService.getMemberOne(login);
      log.debug(CF.GDH + "MemberController.getMemberOne returnMap : " + returnMap + CF.RS);
      
      // 모델에 담아서 jsp로 보내기
      model.addAttribute("level", level);
      model.addAttribute("member", returnMap.get("member"));
      model.addAttribute("memberFile", returnMap.get("memberFile"));
      return "member/getMemberOne";
   }

   // 멤버정보 수정폼
   @GetMapping("/loginCheck/modifyMember")
   public String modifyMember(Model model, HttpSession session) {
      
      // 세션을 통해 로그인ID 받아오기
      String loginId = (String) session.getAttribute("sessionId");
      log.debug(CF.GDH + "MemberController.modifyStudent loginId : " + loginId + CF.RS);
      
      // 레벨 받아오기
      int level = (int)session.getAttribute("sessionLv");
      log.debug(CF.GDH + "MemberController.modifyStudent level : " + level + CF.RS);
      
      Login login = new Login();
      
      login.setLoginId(loginId);
      login.setLevel(level);
      
      // 정보 담을 Map 생성
      Map<String, Object> returnMap = new HashMap<String, Object>();
      
      if(level==3) { // 매니저정보 수정폼
         // 부서와 직책 띄울 리스트 받아오기
         List<Dept> deptList = memberService.getDeptList();
         List<Position> positionList = memberService.getPositions();
         log.debug(CF.PSH+"MemberController.modifyManager :"+deptList+CF.RS);
          log.debug(CF.PSH+"MemberController.modifyManager :"+positionList+CF.RS);
         
          // 필요한 정보들을 모델에 담기
         model.addAttribute("deptList", deptList);
         model.addAttribute("positionList", positionList);
      } 
      
      // 받아온 아이디로 수정폼에 띄울 학생정보 상세보기 returnMap에 담기
      returnMap = memberService.getMemberOne(login);
      log.debug(CF.GDH + "MemberController.modifyStudent.Get returnMap : " + returnMap + CF.RS);
               
      // returnMap에 담긴 회원정보와 사진파일을 모델에 담기
      model.addAttribute("member", returnMap.get("member"));
      model.addAttribute("memberFile", returnMap.get("memberFile"));
      return "member/modifyMember";
   }
   
   // 회원정보 수정액션
   @PostMapping("/loginCheck/modifyMember")
   public String modifyMember(HttpSession session
								, Student student
								, Teacher teacher
								, Manager manager
								, @RequestParam(name="incomeAddress") String incomeAddress
								, @RequestParam(name="writtenAddress", defaultValue = "none") String writtenAddress) {
	   // 세션을 통해 레벨 받아오기
	   int level = (int) session.getAttribute("sessionLv");
	   
	   // 세션을 통해 아이디 받아오기
	   String loginId = (String) session.getAttribute("sessionId");
	   log.debug(CF.GDH + "MemberController.modifyStudent.Post loginId : " + loginId + CF.RS);
	   
	   if(level == 1) {
		  // student에 아이디 넣어주기
		  student.setLoginId(loginId);
		  
	      // 받아온 매개변수 디버깅
	      log.debug(CF.GDH + "MemberController.modifyStudent.Post student : " + student + CF.RS);
	      log.debug(CF.GDH + "MemberController.modifyStudent.Post incomeAddress : " + incomeAddress + CF.RS);
	      log.debug(CF.GDH + "MemberController.modifyStudent.Post writtenAddress : " + writtenAddress + CF.RS);
	      
	      if(writtenAddress.equals("none")) {
	    	  student.setAddress(incomeAddress);
	    	  log.debug(CF.GDH + "MemberController.modifyStudent.Post student.setAddress(incomeAddress) : " + student + CF.RS);
	      } else {
	    	  student.setAddress(writtenAddress);
	    	  log.debug(CF.GDH + "MemberController.modifyStudent.Post student.setAddress(writtenAddress) : " + student + CF.RS);
	      }
	      
	      // 서비스 실행
	      int row = memberService.modifyStudent(student);
	      
	      if(row==1) {
	    	  log.debug(CF.GDH + "MemberController.modifyStudent.Post modifyStudent : 수정성공"+ CF.RS);
	    	  return "redirect:/loginCheck/getMemberOne?loginId="+loginId;
	      } else { 
	    	  log.debug(CF.GDH + "MemberController.modifyStudent.Post modifyStudent : 수정실패"+ CF.RS);
	    	  return "redirect:/loginCheck/modifyStudent?loginId="+loginId;
	      }
	   } else if(level == 2) {
		  // teacher에 아이디 넣어주기
		  teacher.setLoginId(loginId);
		  
	      // 받아온 매개변수 디버깅
	      log.debug(CF.GDH + "MemberController.modifyTeacher.Post teacher : " + teacher + CF.RS);
	      log.debug(CF.GDH + "MemberController.modifyTeacher.Post incomeAddress : " + incomeAddress + CF.RS);
	      log.debug(CF.GDH + "MemberController.modifyTeacher.Post writtenAddress : " + writtenAddress + CF.RS);
	      
	      if(writtenAddress.equals("none")) {
	    	  teacher.setAddress(incomeAddress);
	    	  log.debug(CF.GDH + "MemberController.modifyTeacher.Post teacher.setAddress(incomeAddress) : " + teacher + CF.RS);
	      } else {
	    	  teacher.setAddress(writtenAddress);
	    	  log.debug(CF.GDH + "MemberController.modifyTeacher.Post teacher.setAddress(writtenAddress) : " + teacher + CF.RS);
	      }
	      
	      // 서비스 실행
	      int row = memberService.modifyTeacher(teacher);
	      
	      if(row==1) {
	    	  log.debug(CF.GDH + "MemberController.modifyTeacher.Post modifyTeacher : 수정성공"+ CF.RS);
	    	  return "redirect:/loginCheck/getMemberOne?loginId="+loginId;
	      } else { 
	    	  log.debug(CF.GDH + "MemberController.modifyTeacher.Post modifyTeacher : 수정실패"+ CF.RS);
	    	  return "redirect:/loginCheck/modifyTeacher?loginId="+loginId;
	      }
	   } else if(level == 3) {
		  // manager에 아이디 넣어주기
		  manager.setLoginId(loginId);
		
		// 받아온 매개변수 디버깅
		log.debug(CF.GDH + "MemberController.modifyManager.Post manager : " + manager + CF.RS);
		log.debug(CF.GDH + "MemberController.modifyManager.Post incomeAddress : " + incomeAddress + CF.RS);
		log.debug(CF.GDH + "MemberController.modifyManager.Post writtenAddress : " + writtenAddress + CF.RS);
		
		if(writtenAddress.equals("none")) {
			manager.setAddress(incomeAddress);
			log.debug(CF.GDH + "MemberController.modifyManager.Post manager.setAddress(incomeAddress) : " + manager + CF.RS);
		} else {
			manager.setAddress(writtenAddress);
			log.debug(CF.GDH + "MemberController.modifyManager.Post manager.setAddress(writtenAddress) : " + manager + CF.RS);
		}
		
		// 서비스 실행
		int row = memberService.modifyManager(manager);
		
		if(row==1) {
			log.debug(CF.GDH + "MemberController.modifyManager.Post modifyManager : 수정성공"+ CF.RS);
			return "redirect:/loginCheck/getMemberOne?loginId="+loginId;
		} else { 
			log.debug(CF.GDH + "MemberController.modifyManager.Post modifyManager : 수정실패"+ CF.RS);
			return "redirect:/loginCheck/modifyManager?loginId="+loginId;
		}
	  } else {
		  return "redirect:/loginCheck/main";
	  }
   }
   
   // 비밀번호 체크 폼
   @GetMapping("/loginCheck/pwCheck")
   public String pwCheck(Model model
		   				, HttpSession session
		   				, @RequestParam(name="msg") String msg) {
	   
	   // 세션을 통해 로그인Id 받아오기
	   String loginId = (String) session.getAttribute("sessionId");
	   log.debug(CF.GDH + "MemberController.pwCheck.Get loginId : " + loginId + CF.RS);
	   
	   // 모델에 loginId랑 msg 담아서 넘기기
	   model.addAttribute("loginId", loginId);
	   model.addAttribute("msg", msg);
	   
	   if("modifyPwCheck".equals(msg)) {
		   return "member/pwCheck";
	   } else if("removePwCheck".equals(msg)) {
		   return "member/pwCheck";
	   } else {
		   return "member/pwCheck";
	   }
	   
   }
   
   // 비밀번호 체크 액션
   @PostMapping("/loginCheck/pwCheck")
   public String pwCheck(Model model
		   				 , HttpSession session
		   				 , Login login
		   				 , @RequestParam(name = "loginId") String loginId
						 , @RequestParam(name = "loginPw") String loginPw
		   				 , @RequestParam(name="msg") String msg) {
	   
	   log.debug("MemberController.pwCheck loginId:" + loginId);
	   log.debug("MemberController.pwCheck loginPw:" + loginPw);
	   
	   // 로그인 객체에 Id, Pw 담기
	   login.setLoginId(loginId);
	   login.setLoginPw(loginPw);
	   
	   // 확인 실패시 넘길 loginId와 msg 담기
	   model.addAttribute("loginId", loginId);
	   model.addAttribute("msg", msg);
	   
	   // 패스워드가 일치하는지 확인
	   int row = memberService.getPwCheck(login);
	   log.debug("memberService.getPwCheck row:" + row);
	   
	   if("modifyPwCheck".equals(msg)) {
	       if (row == 1) {
	    	   log.debug("비밀번호 맞음: 회원수정 폼창 띄우기 성공");	
	    	   return "redirect:/loginCheck/modifyMember";
	       } else {
	    	   log.debug("비밀번호 틀림: 회원수정 폼창 띄우기 실패");	
	          return "member/pwCheck";
	       }
	   } else if("removePwCheck".equals(msg)) {
		   // 레벨 받아오기
		   int level = (int)session.getAttribute("sessionLv");
		   log.debug(CF.GDH + "MemberController.removePwCheck level : " + level + CF.RS);
		      
		   // 세션으로 나누어서 진행
		   if (row == 1) {
		      if(level == 1) {
		     	 memberService.removeStudent(login);
		      } else if(level == 2) {
		       	 memberService.removeTeacher(login);
		      } else if(level == 3) {
		       	 memberService.removeManager(login);
              }
	             return "redirect:/login";
		   } else {
		       return "/member/pwCheck";
		   }
	   } else {
		   if(row == 1) {
			   return "member/modifyPw";
		   } else {
			   return "member/pwCheck";
		   }
	   }
   }
   
   // 멤버 비밀번호 수정 액션
   @PostMapping("/loginCheck/modifyPw")
   public String modifyPw(@RequestParam(name = "loginId") String loginId
		   					,@RequestParam(name = "loginPw") String loginPw) {
	   
	   // 로그인 객체 생성
	   Login login =  new Login();
	   login.setLoginId(loginId);
	   login.setLoginPw(loginPw);
	   
	   // 서비스에서 비밀번호 변경
	   int row = memberService.modifyPw(login);
	   if(row == 1) {
		   return "redirect:/loginCheck/getMemberOne?loginId="+loginId;
	   } else {
		   return "redirect:/loginCheck/modifyPw?";
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
                           , HttpServletRequest request
                           , @RequestParam(name="deleteMemberFileName") String memberFileName
                           , @RequestParam(name="insertMemberFile") MultipartFile insertMemberFile) {
      
         log.debug(CF.GDH + "MemberController.modifyMemberFile.Post deleteMemberFileName : " + memberFileName + CF.RS);
         log.debug(CF.GDH + "MemberController.modifyMemberFile.Post insertMemberFile : " + insertMemberFile + CF.RS);
      
         //세션으로 부터 아이디 받아오기
         String loginId = (String) session.getAttribute("sessionId");
         
         // 지울 파일 경로설정
         String path = request.getServletContext().getRealPath("/file/memberPhoto/");
         log.debug(CF.GDH + "MemberController.modifyMemberFile.Post path : " + path + CF.RS);
         
         // 1) 폴더에서 사진삭제
         // 2) DB에서 사진삭제 및 입력
         int row = memberService.modifyMemberFile(loginId, memberFileName, insertMemberFile, path);
         
         if(row == 1) { 
            return "redirect:/loginCheck/main"; 
         } else { 
            return "redirect:/loginCheck/getStudentOne"; }
   }
   
}
