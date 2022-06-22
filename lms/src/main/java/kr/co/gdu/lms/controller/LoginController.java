package kr.co.gdu.lms.controller;

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
	
	// 회원가입 폼
	@GetMapping("/addMember")
	public String addMemeber(Model model
			, @RequestParam(value="addChk", defaultValue="student") String addChk) {
		
		log.debug(CF.OHI+"LoginController.addMember addChk : "+addChk+CF.RS);
		
		model.addAttribute("addChk",addChk);
		return "login/addMember";
	}
	
	@PostMapping("/addMember")
	public String addMember(Login login
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
		int row = 0;
		if(addChk.equals("manager")) {
			//row = loginService;
			
		} else if(addChk.equals("teacher")) {
			//row = loginService;
			
		} else {
			//row = loginService;
			
		}
		
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
