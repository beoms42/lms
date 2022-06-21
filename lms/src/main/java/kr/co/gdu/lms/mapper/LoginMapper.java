package kr.co.gdu.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.Teacher;

@Mapper
public interface LoginMapper {

	// 학생 아이디 찾기
	Student selectStudentLoginId(Student student);
	
	// 매니저 아이디 찾기
	Manager selectManagerLoginId(Manager manager);
	
	// 강사 아이디 찾기
	Teacher selectTeacherLoginId(Teacher teacher);
	
	// 로그인 아이디와 비밀번호 확인하고 맞다면 해당 level 출력 
	Login loginAndSelectLevel(Login loginTest);
}
