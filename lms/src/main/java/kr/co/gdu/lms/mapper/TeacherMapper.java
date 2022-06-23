package kr.co.gdu.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Teacher;

@Mapper
public interface TeacherMapper {
	//강사 리스트
	List<Teacher>selectTeacherList();
	
	//강사 정보 상세보기
	Teacher selectTeacherOne(String loginId);
	
	//강사 정보 수정
	int updateTeacher(Teacher teacher);
	
	//강사 정보 삭제
	int deleteTeacher(String loginId);
	
	//강사 수정/삭제를 위한 패스워드 접근
	int pwCheck(Login login);
}

