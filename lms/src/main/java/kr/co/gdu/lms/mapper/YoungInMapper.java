package kr.co.gdu.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YoungInMapper {
	
	List<String> selectLearningStudentName();
	
	void insertStudentInLecture(String lectureName, String loginId);
	
}
