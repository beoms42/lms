package kr.co.gdu.lms.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Student;

@Mapper
public interface YoungInMapper {
	
	List<String> selectLearningStudentName();
	
	void insertStudentInLecture(String lectureName, String loginId);
	
	// 강의에 배정된 학생가져오기
	List<Student> selectStudentListByLectureName(String lectureName);
	
	//강의별 배정인원수 / 인원수 
	//해시맵으로 넣을건데,, 키 > 강의명으로 값 > 인원수
	List<HashMap<String, Object>> selectStudentGroup();
}
