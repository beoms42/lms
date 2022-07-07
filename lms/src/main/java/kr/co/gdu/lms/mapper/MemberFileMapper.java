package kr.co.gdu.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.MemberFile;

@Mapper
public interface MemberFileMapper {
	// 파일선택
	MemberFile selectMemberFile(String loginId);
	
	// 파일 삭제
	int deleteMemberFile(String loginId);
	
	// 파일 입력
	int insertMemberFile(MemberFile memberFile);
	
	// 비밀번호 수정
	int updateMemberPw(Login login);
}