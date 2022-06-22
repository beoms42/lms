package kr.co.gdu.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.MemberFile;

@Mapper
public interface MemberFileMapper {
	// 파일목록을 나열
	List<MemberFile> selectMemberFileList(String loginId);
}
