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
	
	// 학생 비밀번호 찾기
	public int searchLoginPwByStudent(Map<String, Object> map) {
		log.debug(CF.PHW+"LoginService.searchLoginPwByStudent map : "+map+CF.RS );
		return loginMapper.selectStudentPw(map);
	}
	// 강사 비밀번호 찾기
	public int searchLoginPwByTeacher(Map<String, Object> map) {
		log.debug(CF.PHW+"LoginService.searchLoginPwByStudent map : "+map+CF.RS );
		return loginMapper.selectTeacherPw(map);
	}
	// 매니저 비밀번호 찾기
	public int searchLoginPwByManager(Map<String, Object> map) {
		log.debug(CF.PHW+"LoginService.searchLoginPwByStudent map : "+map+CF.RS );
		return loginMapper.selectManagerPw(map);
	}

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
		int count = loginMapper.selectIdCnt(id);
		
		return count;
	}
	
	// 회원가입
	public void addMember(Map<String, Object> map) {
		// 디버깅
		log.debug(CF.OHI+"LoginService.addMember map : "+map+CF.RS );
		int row =  0;
	
		row = loginMapper.insertMember(map);
		
		/*
		if(map.get("addChk").equals("manager")) { // 매니저 회원가입이면
		
		}
		
		
		 else if(map.get("addChk").equals("teacher")) { // 선생님 회원가입이면
			row = loginMapper.insertTeacher(map);
		} else { // 학생 회원가입이면
			row = loginMapper.insertStudent(map);
		}
		*/
		// 성공여부
		log.debug(CF.OHI+"LoginService.addMember row : "+row+CF.RS );
		
		
	}
	
	
	
}
