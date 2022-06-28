package kr.co.gdu.lms.controller;

import java.util.ArrayList;
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
   @Autowired
   private MemberService memberService;

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
		   model.addAttribute("studentList", studentList);
	   } else if("teacher".equals(msg)) {
		   List<Teacher> teacherList = memberService.getTeacherList();
		   log.debug(CF.GDH+"MemberController.getMemberList teacherList : " + teacherList + CF.RS);
		   model.addAttribute("teacherList", teacherList);
	   } else if("manager".equals(msg)) {
		   List<Manager> managerList = memberService.getManagerList();
	       List<Position> positionList = memberService.getPositions();
	       log.debug(CF.GDH+"MemberController.getMemberList managerList : " + managerList + CF.RS);
	       log.debug(CF.GDH+"MemberController.getMemberList positionList : " + positionList + CF.RS);
	       model.addAttribute("managerList", managerList);
	       model.addAttribute("positionList", positionList );
	   }
       
       return "member/getMemberList";
   }

   // 멤버정보 상세보기
   @GetMapping("/loginCheck/getMemberOne")
   public String getMemberOne(Model model, HttpSession session) {
      // 레벨 받아오기
      int level = (int)session.getAttribute("sessionLv");
      log.debug(CF.GDH + "MemberController.getMemberOne level : " + level + CF.RS);
      
      // 로그인Id 받아오기
      String loginId = (String)session.getAttribute("sessionId");
      log.debug(CF.GDH + "MemberController.getMemberOne loginId : " + loginId + CF.RS);
      
      // 로그인VO에 담기
      Login login = new Login();
      login.setLevel(level);
      login.setLoginId(loginId);
      
      Map<String, Object> returnMap = new HashMap<String, Object>();
   
      // 멤버서비스로부터 맵에 담아서 들고오기
      returnMap = memberService.getMemberOne(login);
      log.debug(CF.GDH + "MemberController.getMemberOne returnMap : " + returnMap + CF.RS);
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

   // 학생정보 수정액션
   @PostMapping("/loginCheck/modifyStudent")
   public String modifyStudent(Student student) {
      // 받아온 매개변수 디버깅
      log.debug(CF.GDH + "MemberController.modifyStudent.Post student : " + student + CF.RS);

      // 서비스로 보내기
      int row = memberService.modifyStudent(student);

      return "redirect:/loginCheck/getStudentOne?loginId=" + student.getLoginId();
   }

   // 멤버정보 수정시 비밀번호 체크폼
   @GetMapping("/loginCheck/modifyPwCheck")
   public String modifyPwCheck(Model model, HttpSession session) {
      String loginId = (String) session.getAttribute("sessionId");
      model.addAttribute("loginId", loginId);
      return "member/modifyPwCheck";
   }

   // 멤버정보 수정시 비밀번호 체크액션
   @PostMapping("/loginCheck/modifyPwCheck")
   public String modifyPwCheck(Login login, HttpSession session) {
      int row = memberService.getPwCheck(login);
      
      if (row == 1) {
         return "redirect:/loginCheck/modifyMember";
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
                           , HttpServletRequest request
                           , @RequestParam(name="deleteMemberFileName") String memberFileName
                           , @RequestParam(name="insertMemberFile") MultipartFile insertMemberFile) {
      
         log.debug(CF.GDH + "MemberController.modifyMemberFile.Post deleteMemberFileName : " + memberFileName + CF.RS);
         log.debug(CF.GDH + "MemberController.modifyMemberFile.Post insertMemberFile : " + insertMemberFile + CF.RS);
      
         //세션으로 부터 아이디 받아오기
         String loginId = (String) session.getAttribute("sessionId");
         
         // 1) 폴더에서 사진삭제
         String path = request.getServletContext().getRealPath("/file/memberPhoto/");
         log.debug(CF.GDH + "MemberController.modifyMemberFile.Post path : " + path + CF.RS);
         memberService.removeMemberFile(path, memberFileName);
         
         // 2) DB에서 사진삭제 및 입력
         int row = memberService.modifyMemberFile(loginId, memberFileName, insertMemberFile, path);
         
         if(row == 1) { 
            return "redirect:/loginCheck/main"; 
         } else { 
            return "redirect:/loginCheck/getStudentOne"; }
   }
   
    // 강사 정보 수정액션
    @PostMapping("/loginCheck/modifyTeacher")
    public String modifyTeacher(Teacher teacher
          ,HttpSession session
          ,HttpServletRequest request
          ) {
       int row = 0;
       String loginId = teacher.getLoginId();
       row = memberService.modifyTeacher(teacher);
        log.debug(CF.PSH+"MemberController.modifyTeacher :"+teacher+CF.RS);
       return "redirect:/loginCheck/getTeacherOne?loginId="+loginId; 
    }
    
    // 강사 회원탈퇴
    @GetMapping("/loginCheck/deleteTeacher")
    public String deleteTeacher(Model model, String loginId) {
       int row = 0;
       row = memberService.deleteTeacher(loginId);
        log.debug(CF.PSH+"MemberController.deleteTeacher :"+loginId+CF.RS);
       return "member/getTeacherList";
    }
    
    // 매니저 정보 수정액션
    @PostMapping("/loginCheck/modifyManager")
    public String modifyManager(Manager manager
                         ,HttpSession session
                         ,HttpServletRequest request
                         ) {
          int row = 0;
          String loginId = manager.getLoginId();
          row = memberService.modifyManager(manager);
           log.debug(CF.PSH+"MemberController.modifyManager :"+manager+CF.RS);
          return "redirect:/loginCheck/getmanagerOne?loginId="+loginId; //리다
    }
    
    // 매니저 회원탈퇴
    @GetMapping("/loginCheck/deleteManager")
    public String deleteManager(Model model, String loginId) {
       int row = 0;
       row = memberService.deleteManager(loginId);
        log.debug(CF.PSH+"MemberController.deleteManager :"+loginId+CF.RS);
       return "member/getmanagerList";
    }
    
   
 }