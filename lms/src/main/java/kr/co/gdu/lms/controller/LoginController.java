package kr.co.gdu.lms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.LoginService;
import kr.co.gdu.lms.vo.Login;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@Autowired private LoginService loginService;
	
	@GetMapping("/loginCheck/main")
	public String main() {
		return "login/main";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login/login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session
						, Login loginTest) {
		log.debug(CF.OHI+"LoginController.login.post login : "+loginTest+CF.RS);
		
		// 로그인 정보 넣어서 맞다면 로그인아이디와 level 들고오기
		Login login = loginService.login(loginTest);
		
		if(login == null) { // 로그인 정보가 일치하지 않으면
			log.debug(CF.OHI+"LoginController.login.post 로그인 정보가 일치하지 않습니다"+login+CF.RS);
			return "redirect:/login"; //다시 로그인 페이지로
		}
		
		// 세션에 로그인아이디와 로그인레벨 담기
		session.setAttribute("sessionId" , login.getLoginId());
		session.setAttribute("sessionLv" , login.getLevel());
		
		return "redirect:/loginCheck/main"; // 메인으로
	}
}
