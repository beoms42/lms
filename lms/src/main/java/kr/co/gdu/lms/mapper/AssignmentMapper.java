package kr.co.gdu.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.AssignmentExam;

@Mapper
public interface AssignmentMapper {
	List<AssignmentExam> selectAssignmentExam(Map<String,Object> map);
	int selectAssignmentTotalCount();
	int insertAssignmentExam(AssignmentExam assignmentExam);
	List<AssignmentExam> selectAssignmentOne(int assignmentExamNo);
	int selectassignmentExamNo();
}
