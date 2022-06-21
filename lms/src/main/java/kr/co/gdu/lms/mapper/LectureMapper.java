package kr.co.gdu.lms.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.*;

@Mapper
public interface LectureMapper {

	// 강의개설시 필요한 드롭다운 메뉴 : 강사 / 매니저 / 강의실 / 정원(강의실)
	
	// 강사
	List<Teacher> selectTeacher();
	
	// 매니저
	List<Manager> selectManager();
	
	// 강의실
	List<LectureRoom> selectLectureRoom();
}
