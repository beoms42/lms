package kr.co.gdu.lms.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.Teacher;

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
	
	int selectIdList(String id);
}
