package kr.co.gdu.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.MemberFile;

@Mapper
public interface MemberFileMapper {
	// 파일목록
	MemberFile selectMemberFile(String loginId);
}