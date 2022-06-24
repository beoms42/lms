package kr.co.gdu.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Dept;
import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.Position;

@Mapper
public interface ManagerMapper {
	//매니저 리스트
	List<Manager> selectManagerList();
	
	//매니저 정보 상세보기
	Map<String,Object> selectManagerOne(String loginId);
	
	//매니저 정보 수정
	int updateManager(Manager manager);
	
	//매니저 정보 삭제
	int deleteManager(String loginId);
	
	//매니저 수정/삭제를 위한 패스워드 접근
	int pwCheck(Login login);
	
	//매니저 - 부서 이름 번호 
	List<Dept> selectDept();
		
	//매니저  - 직급 이름 번호
	List<Position> selectPosition();
	
}
