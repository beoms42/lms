package kr.co.gdu.lms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import kr.co.gdu.lms.vo.Login;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@Autowired private LoginService loginService;
	
	// 비밀번호 변경 폼
	@GetMapping("/modifyLoginPw")
	public String modifyLoginPw() {
		
		return "login/modifyLoginPw";
	}
	
	// 비밀번호 찾기(확인)
	@PostMapping("/searchLoginPw")
	public String searchLoginPw(Model model
								, @RequestParam (name = "msg") String msg
								, @RequestParam (name = "loginId") String loginId
								, @RequestParam (name = "name") String name
								, @RequestParam (name = "email") String email) {
		log.debug(CF.PHW+"LoginController.searchLoginPw.post msg : "+msg+CF.RS );
		log.debug(CF.PHW+"LoginController.searchLoginPw.post loginId : "+loginId+CF.RS );
		log.debug(CF.PHW+"LoginController.searchLoginPw.post name : "+name+CF.RS );
		log.debug(CF.PHW+"LoginController.searchLoginPw.post email : "+email+CF.RS );
		
		Map<String, Object> map = new HashMap<>();
		map.put("loginId", loginId);
		map.put("name", name);
		map.put("email", email);
		
		int cnt = 0;
		
		if("student".equals(msg)) {
			cnt = loginService.searchLoginPwByStudent(map);
			log.debug(CF.PHW+"LoginController.searchLoginPw.post cnt : "+cnt+CF.RS );
			if(cnt == 1) {
				return "redirect:/modifyLoginPw";
			} else {
				return "redirect:/searchLoginPw";
			}
		} else if ("teacher".equals(msg)) {
			cnt = loginService.searchLoginPwByTeacher(map);
			log.debug(CF.PHW+"LoginController.searchLoginPw.post cnt : "+cnt+CF.RS );
			if(cnt == 1) {
				return "redirect:/modifyLoginPw";
			} else {
				return "redirect:/searchLoginPw";
			}
		} else if ("manager".equals(msg)) {
			cnt = loginService.searchLoginPwByManager(map);
			log.debug(CF.PHW+"LoginController.searchLoginPw.post cnt : "+cnt+CF.RS );
			if(cnt == 1) {
				return "redirect:/modifyLoginPw";
			} else {
				return "redirect:/searchLoginPw";
			}
		} else {
			log.debug(CF.PHW+"LoginController.searchLoginPw.post.if msg : msg값이 사라졌습니다! "+CF.RS );
		}
		
		return "";
		
	}
	
	
	// 학생, 강사, 매니저 비밀번호 찾기 폼
	@GetMapping("/searchLoginPw")
	public String searchLoginPw(Model model
								, @RequestParam (name = "msg", defaultValue = "student") String msg) {
		log.debug(CF.PHW+"LoginController.searchLoginPw.get msg : "+msg+CF.RS );

		model.addAttribute("msg", msg);

		return "login/searchLoginPw";

	}
	
	// 학생, 강사, 매니저 아이디 찾기 액션
	@PostMapping("/searchLoginId")
	public String searchLoginId(Model model
								, @RequestParam (name = "msg") String msg
								, @RequestParam (name = "name") String name
								, @RequestParam (name = "email") String email) {
		log.debug(CF.PHW+"LoginController.searchLoginId.post msg : "+msg+CF.RS );
		log.debug(CF.PHW+"LoginController.searchLoginId.post name : "+name+CF.RS );
		log.debug(CF.PHW+"LoginController.searchLoginId.post email : "+email+CF.RS );
		
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("email", email);
		
		String loginId = "";
		
		if("student".equals(msg)) {
			loginId = loginService.searchLoginIdByStudent(map);
			log.debug(CF.PHW+"LoginController.searchLoginId.post.if student loginId : "+loginId+CF.RS );
		} else if("teacher".equals(msg)) {
			loginId = loginService.searchLoginIdByTeacher(map);
			log.debug(CF.PHW+"LoginController.searchLoginId.post.if teacher loginId : "+loginId+CF.RS );
		} else if("manager".equals(msg)){
			loginId = loginService.searchLoginIdByManager(map);
			log.debug(CF.PHW+"LoginController.searchLoginId.post.if manager loginId : "+loginId+CF.RS );
		} else {
			log.debug(CF.PHW+"LoginController.searchLoginId.post.if msg : msg값이 사라졌습니다! "+CF.RS );
		}
			
		model.addAttribute("loginId", loginId);
		
		return "login/searchLoginId";
	}
	
	
	
	// 학생, 강사, 매니저 아이디 찾기 폼
	@GetMapping("/searchLoginId")
	public String searchLoginId(Model model
								, @RequestParam(value = "msg", defaultValue = "student") String msg) {
		log.debug(CF.PHW+"LoginController.searchLoginId.get msg : "+msg+CF.RS );
		
		model.addAttribute("msg", msg);
		
		return "login/searchLoginId";
	}
	
	

	// 회원가입 폼
	@GetMapping("/addMember")
	public String addMemeber(Model model
			, @RequestParam(value="addChk", defaultValue="student") String addChk) {
		
		log.debug(CF.OHI+"LoginController.addMember addChk : "+addChk+CF.RS);
		
		model.addAttribute("addChk",addChk);
		return "login/addMember";
	}
	
	@PostMapping("/addMember")
	public String addMember(HttpServletRequest request
			, Login login
			, @RequestParam(value="name") String name
			, @RequestParam(value="gender") String gender
			, @RequestParam(value="email") String email
			, @RequestParam(value="phone") String phone
			, @RequestParam(value="addr") String addr
			, @RequestParam(value="addr") String detailAddr
			, @RequestParam(value="customFile")MultipartFile customFile
			, @RequestParam(value="addChk") String addChk) {
		
		// 디버깅
		log.debug(CF.OHI+"LoginController.addMember.Post login : "+login+CF.RS);
		log.debug(CF.OHI+"LoginController.addMember.Post name : "+name+CF.RS);
		log.debug(CF.OHI+"LoginController.addMember.Post gender : "+gender+CF.RS);
		log.debug(CF.OHI+"LoginController.addMember.Post email : "+email+CF.RS);
		log.debug(CF.OHI+"LoginController.addMember.Post phone : "+phone+CF.RS);
		log.debug(CF.OHI+"LoginController.addMember.Post addr : "+addr+CF.RS);
		log.debug(CF.OHI+"LoginController.addMember.Post detailAddr : "+detailAddr+CF.RS);
		log.debug(CF.OHI+"LoginController.addMember.Post customFile : "+customFile+CF.RS);
		log.debug(CF.OHI+"LoginController.addMember.Post addChk : "+addChk+CF.RS);
	
		Map<String, Object> map = new HashMap<>();
		map.put("login", login);
		map.put("name", name);
		map.put("gender", gender);
		map.put("email", email);
		map.put("phone", phone);
		map.put("addr", addr);
		map.put("detailAddr", detailAddr);
		map.put("customFile", customFile);
		map.put("addChk", addChk);
		loginService.addMember(map);
		
		return "";
	}
	// 로그아웃
	@GetMapping("/loginCheck/logout")
	public String logout(HttpSession session) {
		
		// 로그아웃하면 세션 끊기
		session.invalidate();
		
		return "redirect:/login";
	}
	
	// 메인페이지
	@GetMapping("/loginCheck/main") 
	public String main() {
		return "login/main";
	}
	
	// 로그인
	@GetMapping("/login") 
	public String login(Model model
						, HttpServletRequest request) {
		
		String cookieId = null; // model에 담을 cookieId 선언 
		Cookie[] cookies = request.getCookies(); 
		
		for(Cookie c : cookies) { // 쿠키 배열들 돌리면서
			if("cookieId".equals(c.getName())) { // cookieId라는 이름 가진 쿠키 값 있다면
				cookieId = c.getValue(); // 쿠키아이디에 정보값 담아주기
			}
		}
		
		log.debug(CF.OHI+"LoginController.login.get cookieId : "+cookieId+CF.RS);
		
		model.addAttribute("cookieId",cookieId);
		return "login/login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session
						, HttpServletResponse response
						, Login loginTest
						, @RequestParam(value="idSave", defaultValue="") String idSave) {
		
		log.debug(CF.OHI+"LoginController.login.post login : "+loginTest+CF.RS);
		log.debug(CF.OHI+"LoginController.login.post idSave : "+idSave+CF.RS);
		
		// 로그인 정보 넣어서 맞다면 로그인아이디와 level 들고오기   
		Login login = loginService.login(loginTest);
		
		if(login == null) { // 로그인 정보가 일치하지 않으면
			log.debug(CF.OHI+"LoginController.login.post 로그인 정보가 일치하지 않습니다"+login+CF.RS);
			return "redirect:/login"; //다시 로그인 페이지로
		}
		if("on".equals(idSave)) {
			Cookie cookieId = new Cookie("cookieId", login.getLoginId()); // 현재 접속된 로그인 아이디 쿠키아이디값으로 저장
			cookieId.setMaxAge(60*60*24); // 쿠키 생명주기 하루로 설정
			response.addCookie(cookieId); // response에 쿠키 저장
		}
		
		// 세션에 로그인아이디와 로그인레벨 담기
		session.setAttribute("sessionId" , login.getLoginId());
		session.setAttribute("sessionLv" , login.getLevel());
		
		return "redirect:/loginCheck/main"; // 메인으로
	}
}
