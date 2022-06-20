package kr.co.gdu.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.gdu.lms.vo.Login;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		
		return "login/login";
	}
	
	@PostMapping("/login")
	public String login(Login login) {
		
		return ""; // 메인으로
	}
}
