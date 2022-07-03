package kr.co.gdu.lms.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HyeinMapper {
	
	// 강사별 맡고있는 강의의 학생이름, 출결, 강의 이름 , 출결날짜 뽑기
	Map<String, Object> selectAttendance(Map<String, Object> map);
}
