package kr.co.gdu.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.AssignmentExam;

@Mapper
public interface AssignmentMapper {
	List<AssignmentExam> getAssignment();
	int getAssignmentTotalCount();
}
