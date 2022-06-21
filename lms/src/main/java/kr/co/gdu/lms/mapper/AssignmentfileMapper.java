package kr.co.gdu.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.AssignmentFile;

@Mapper
public interface AssignmentfileMapper {
	void insertAssingmentfile(AssignmentFile assignmentfile);
}
