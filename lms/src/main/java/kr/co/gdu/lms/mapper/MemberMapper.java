package kr.co.gdu.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Student;

@Mapper
public interface MemberMapper {
	// 학생정보 상세보기 SELECT
	Student selectStudentOne(String loginId);
	
	// 학생정보 수정하기 UPDATE
	int updateStudent(Student student);
	
	// 학생정보 삭제하기 DELETE(회원탈퇴)
	int deleteStudent(Student student);
	
	// 학생정보 수정/삭제를 위한 패스워드 접근
	int pwCheck(Login login);
	
}
