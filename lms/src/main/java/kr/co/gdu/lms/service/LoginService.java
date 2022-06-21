package kr.co.gdu.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.LoginMapper;
import kr.co.gdu.lms.vo.Login;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class LoginService {
	@Autowired private LoginMapper loginMapper;
	
	public Login login(Login loginTest) {
		log.debug(CF.OHI+"LoginService.login loginTest : "+loginTest+CF.RS);
		
		// 로그인 정보 넣어서 맞다면 로그인아이디와 level 들고오기
		Login login = loginMapper.loginAndSelectLevel(loginTest);
		
		log.debug(CF.OHI+"LoginService.login login : "+login+CF.RS);
		
		return login;
	}
}
