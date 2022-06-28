package kr.co.gdu.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Admin;

@Mapper
public interface AdminMapper {
	// 관리자 정보 상세보기
	Admin selectAdminOne(String loginId);
}
