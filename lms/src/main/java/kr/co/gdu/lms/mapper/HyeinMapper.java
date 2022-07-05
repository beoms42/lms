package kr.co.gdu.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HyeinMapper {
	
	// 버튼 누르면 모두 출석으로
	int updateAttendanceListAll(int scheduleNo);
	
	// 강사별 맡고있는 강의의 학생이름, 출결, 강의 이름 , 출결날짜 뽑기
	List<Map<String, Object>> selectAttendance(Map<String, Object> map);
	
	// 해당 학생의 educationNo 뽑기
	int selectEducationNo(String loginId);
	
	// 출결사항 수정
	int updateAttendance(Map<String, Object> map);
	
}