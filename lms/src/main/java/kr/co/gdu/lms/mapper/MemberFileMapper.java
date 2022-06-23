package kr.co.gdu.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.MemberFile;

@Mapper
public interface MemberFileMapper {
	// 파일선택
	MemberFile selectMemberFile(String loginId);
	
	// 파일수정
	MemberFile updateMemberFile(String loginId, String memberFileName);
	
}