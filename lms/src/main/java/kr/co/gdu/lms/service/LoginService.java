package kr.co.gdu.lms.service;

import java.util.Map;

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
	
	// 학생 아이디 찾기
		public String searchLoginIdByStudent(Map<String, Object> map) {
			log.debug(CF.PHW+"LoginService.searchLoginIdByStudent map : "+map+CF.RS );
			return loginMapper.selectStudentLoginId(map);
		}
		
		// 강사 아이디 찾기 
		public String searchLoginIdByTeacher(Map<String, Object> map) {
			log.debug(CF.PHW+"LoginService.searchLoginIdByTeacher map : "+map+CF.RS );
			return loginMapper.selectTeacherLoginId(map);
		}
		
		// 매니저 아이디 찾기
		public String searchLoginIdByManager(Map<String, Object> map) {
			log.debug(CF.PHW+"LoginService.searchLoginIdByManager map : "+map+CF.RS );
			return loginMapper.selectManagerLoginId(map);
		}
	
	
	public Login login(Login loginTest) {
		log.debug(CF.OHI+"LoginService.login loginTest : "+loginTest+CF.RS);
		
		// 로그인 정보 넣어서 맞다면 로그인아이디와 level 들고오기
		Login login = loginMapper.loginAndSelectLevel(loginTest);
		
		log.debug(CF.OHI+"LoginService.login login : "+login+CF.RS);
		
		return login;
	}
	
	public int idCheck(String id) {
		
		// 해당 아이디 있는지 체크해서 일치하는 개수 받아오기
		int count = loginMapper.selectIdList(id);
		
		return count;
	}
}
