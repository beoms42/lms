






package kr.co.gdu.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.AssignmentAddForm;
import kr.co.gdu.lms.vo.AssignmentExam;
import kr.co.gdu.lms.vo.AssignmentSubmit;
import kr.co.gdu.lms.vo.Lecture;

@Mapper
public interface AssignmentMapper {
	List<AssignmentExam> selectAssignmentExam(Map<String,Object> map);
	int selectAssignmentTotalCount();
	int insertAssignmentExam(AssignmentAddForm assignmentAddForm);
	List<AssignmentExam> selectAssignmentOne(int assignmentExamNo);
	int selectassignmentExamNo();
	int selectEducationNo(String loginId);
	void insertAssignmentSubmit(AssignmentSubmit assignmentsubmit);
	List<AssignmentSubmit> selectAssignmentSubmit(Map<String,Object> map);
	List<AssignmentSubmit> selectAssignmentSubmitTeacher(Map<String,Object> map);
	void updateScore(Map<String,Object> paramMap);
	void updateAssignmentExam(AssignmentExam assignmentexm);
	void deleteAssignment(int assignmentExamNo);
	String selectLectureName(String loginId);
	List<Lecture> selectLectureNameList();
}
