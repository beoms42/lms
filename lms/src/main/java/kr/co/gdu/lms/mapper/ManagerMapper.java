package kr.co.gdu.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Manager;

@Mapper
public interface ManagerMapper {
	//매니저 리스트
	List<Manager> selectManagerList();
	
	//매니저 정보 상세보기
	Manager selectManagerOne(String loginId);
	
	//매니저 정보 수정
	int updateManager(Manager manager);
	
	//매니저 정보 삭제
	int deleteManager(String loginId);
	
	
}
