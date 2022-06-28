package kr.co.gdu.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Student;

@Mapper
public interface YoungInMapper {
	
	List<String> selectLearningStudentName();
	
	void insertStudentInLecture(String lectureName, String loginId);
	
	// 강의에 배정된 학생가져오기
	List<Student> selectStudentListByLectureName(String lectureName);
}
