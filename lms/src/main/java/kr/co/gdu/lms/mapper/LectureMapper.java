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
	
	// 강의 개설
	int insertLecture(Lecture lecture);
	
	// 강의 관리 - 액티브가 1인 강의리스트만 필요
	List<Lecture> selectLectureList();
	
	// 강의 승인 - 액티브가 0인 강의리스트만 필요
	List<Lecture> selectNotAcceptLectureList();
	
	// 강의 승인 - 업데이트 0을 1으로
	void updateLectureActive(String lectureName);
	
	// 강의 삭제
	void deleteLecutre(String lectureName);
	
	// 과목 리스트 가져오기
	List<String> selectSubejctList();
	
	// 강의 이름으로 subject 넣기
	void insertSubject(String subjectName, String lectureName);
}
