package kr.co.gdu.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.AssignmentFile;

@Mapper
public interface AssignmentfileMapper {
	void insertAssingmentfile(AssignmentFile assignmentfile);
	List<AssignmentFile> selectAssinmetFile(Map<String,Object> map);
}
