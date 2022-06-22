package kr.co.gdu.lms.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Login;

@Mapper
public interface LoginMapper {

	// 학생 아이디 찾기
	String selectStudentLoginId(Map<String, Object> map);
		
	// 매니저 아이디 찾기
	String selectManagerLoginId(Map<String, Object> map);
		
	// 강사 아이디 찾기
	String selectTeacherLoginId(Map<String, Object> map);
	
	// 로그인 아이디와 비밀번호 확인하고 맞다면 해당 level 출력 
	Login loginAndSelectLevel(Login loginTest);
	
	// id 중복 체크 위해 id 리스트 받기
	int selectIdCnt(String id);
	
	// 매니저 회원가입
	int insertMember(Map<String, Object> map);
	
	// 회원가입시 로그인 테이블에도 추가
	int insertLogin(Map<String, Object> map);
	
	// 강사 회원가입
	//int insertTeacher(Map<String, Object> map);
	
	// 학생 회원가입
	// int insertStudent(Map<String, Object> map);
	
}
