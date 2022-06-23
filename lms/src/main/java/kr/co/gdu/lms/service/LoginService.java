package kr.co.gdu.lms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.LoginMapper;
import kr.co.gdu.lms.mapper.ManagerMapper;
import kr.co.gdu.lms.vo.AddMemberForm;
import kr.co.gdu.lms.vo.Dept;
import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Position;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class LoginService {
	@Autowired private LoginMapper loginMapper;
	@Autowired private ManagerMapper managerMapper;
	
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
		int row = loginMapper.updateLastLoginDate(loginTest);
		
		log.debug(CF.OHI+"LoginService.login.selectLevel login : "+login+CF.RS);
		log.debug(CF.OHI+"LoginService.login.updateLoginDate row : "+row+CF.RS);
		
		return login;
	}
	
	public int idCheck(String id) {
		
		// 해당 아이디 있는지 체크해서 일치하는 개수 받아오기
		int count = loginMapper.selectIdCnt(id);
		log.debug(CF.OHI+"LoginService.idCheck count : "+count+CF.RS);
		
		return count;
	}
	
	// 회원가입 - post 
	public void addMember(AddMemberForm addMemberForm) {
		
		log.debug(CF.OHI+"LoginService.addMember map : "+addMemberForm+CF.RS );

		// 레벨 구분할 변수 선언
		String level = "";
		
		if("student".equals(addMemberForm.getAddChk())) {// 강사인지 학생인지 매니저인지 구별하는 addChk가 학생이라면
			level = "1";
		} else if("teacher".equals(addMemberForm.getAddChk())) { // 강사라면
			level = "2";
		} else { // 둘다 아니라면 - 매니저
			level = "3";
		}
	
		// level vo에 값 지정
		addMemberForm.setLevel(level);
		
		loginMapper.insertLogin(addMemberForm);
		int row = loginMapper.insertMember(addMemberForm);
		
		// 성공여부
		log.debug(CF.OHI+"LoginService.addMember row : "+row+CF.RS );
		
	}
	
	// 회원가입 - get ( 매니저 회원가입시 부서정보랑 직급 정보 뽑기)
	public Map<String, Object> addMemberGetDeptAndPosition() {
		//리턴할 맵 선언
		Map<String, Object> map = new HashMap<>();
		
		Dept dept = managerMapper.selectDept();
		Position position = managerMapper.selectPosition();
		
		//맵에 담기
		map.put("dept", dept);
		map.put("position", position);
		return map;
		
	}
}
