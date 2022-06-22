package kr.co.gdu.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.Teacher;

@Mapper
public interface StudentMapper {
	// 학생정보 상세보기 SELECT
	Student selectStudentOne(String loginId);
	
	// 학생정보 수정하기 UPDATE
	int updateStudent(Student student);
	
	// 학생 목록 리스트
	List<Student>selectStudentList();
	
	// 학생정보 삭제하기 DELETE(회원탈퇴)
	int deleteStudent(Login login);
	
	// 학생수준 수정하기 UPDATE(회원탈퇴)
	int updateStudentActive(Login login);
	
	// 학생정보 수정/삭제를 위한 패스워드 접근
	int pwCheck(Login login);
	
}
