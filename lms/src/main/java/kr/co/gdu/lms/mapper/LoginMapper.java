package kr.co.gdu.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Login;

@Mapper
public interface LoginMapper {

	// 로그인 아이디와 비밀번호 확인하고 맞다면 해당 level 출력 
	Login loginAndSelectLevel(Login loginTest);
}
