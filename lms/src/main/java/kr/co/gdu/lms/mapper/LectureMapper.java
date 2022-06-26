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
	
	// 강의명으로 subject 가져오기
	List<String> selectSubjectListByLectureName(String lectureName);
	
	// 강의 이름으로 Lecture 하나 조회
	Lecture selectLectureOneByLectureName(String lectureName);
	
	// 강의이름으로 과목에 딸린 책 이름 가져오기
	List<Map<String, Object>> selectTextbookByLectureName(String lectureName);
	
	// 강의이름 + 과목이름으로 과목 하나 삭제
	void delSubjectOneByLectureName(String lectureName, String subjectName);
	
	// 업데이트 - 강의수정 액션
	void updateLecture(Lecture lecture);
	
// 시간표
	
	// 전체시간표리스트보기
	List<CalendarMap> selectScheduleList(Map<String, Object> map);
	
	// 멤버별시간표리스트보기
	List<CalendarMap> selectMemberSchedule(Map<String, Object> map);
	
	// 시간표추가
	int insertSchedule(Schedule schedule);
	
	// 시간표수정
	int updateSchedule(Schedule schedule);
	
	// 시간표삭제
	int deleteSchedule(int scheduleNo);
	
	// 강의과목리스트
	List<LectureSubject>selectLectureSubjecList();
	
	// 시간표상세보기
	Map<String, Object>selectScheduleOne(int scheduleNo);
	
}
