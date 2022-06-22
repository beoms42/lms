package kr.co.gdu.lms.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gdu.lms.service.LoginService;


@RestController
public class LoginRestController {

	@Autowired LoginService loginService;
	
	@PostMapping("/idCheck")
	public String idCheck(@RequestParam(value="id") String id) {
		
		int count = loginService.idCheck(id);
		
		if(count == 1) {
			return "false";
		}
		
		return id;
	}
}
