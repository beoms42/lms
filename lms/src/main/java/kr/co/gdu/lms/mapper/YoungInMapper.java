package kr.co.gdu.lms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.LectureSubject;
import kr.co.gdu.lms.vo.Qna;
import kr.co.gdu.lms.vo.QnaFile;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.SubjectTextbook;
import kr.co.gdu.lms.vo.Textbook;
import kr.co.gdu.lms.vo.TextbookRecord;

@Mapper
public interface YoungInMapper {
	//----------------------------------------강의------------------------------------------
	
	List<String> selectLearningStudentName();
	
	void insertStudentInLecture(String lectureName, String loginId);
	
	// 강의에 배정된 학생가져오기
	List<Student> selectStudentListByLectureName(String lectureName);
	
	//강의별 배정인원수 / 인원수 
	//해시맵으로 넣을건데,, 키 > 강의명으로 값 > 인원수
	List<HashMap<String, Object>> selectStudentGroup();
	
	//textbook insert
	void insertTextbook(Textbook tx);
	
	//textbook select
	List<Textbook> selectTextbookList();
	
	//subject select
	List<String> selectSubjectList();
	
	//subject_textbook insert
	void insertSubjectTextbook(SubjectTextbook subjectTextbook);
	
	// 이름으로 과목명을 받아오기
	List<String> selectSubjectListByLectureNameYoungIn(String lectureName);
	
	// subjectName / lecture로 현재 어떤 책을 수령했는지?
	Map<String, Object> selectRecordBook(Map<String, Object> map);
	
	// 이름으로 강의이름 받아오기
	String selectLectureNameByLoginId(String loginId);
	
	// 로그인id로 educationNo 받아오기
	int selectEducationNoByLoginId(String loginId);
	
	// lecture_name + subejctName으로 lecture_subject_no 얻기
	int selectLectSubNo(LectureSubject lectureSubject);
	
	// insertTextbookRecord
	void insertTextbookRecord(TextbookRecord textbookRecord);
	
	// map에 넣을 textSignFilename얻기
	String selectSignfileName(int educationNo, int lectureSubjectNo);
	//----------------------------------------커뮤니티------------------------------------------
	
	// addQna 하고 제네레이트키값으로 qnaNo반환
	int addQnaAction(Qna qna);
	
	// qnaFile foreach insert
	void addQnaFileInsert(QnaFile qnaFile);
	
	// qnaNo로 하나보기 - 문의
	Qna selectQnaByQnaNo(int qnaNo);
	
	// qnaNo로 하나보기 - 답변
	Qna selectQnaByQnaNoAnswer(int qnaNo);
	
	// qnaNo로 파일리스트가져오기
	List<String> selectQnaFileByQnaNo(int qnaNo);
	
	// qna답변 insert
	void insertQnaAnswer(Qna qna);
	
	// qna 답변의 원글 답변완료로 변경 메서드
	void updateByinsertQnaAnswer(int qnaNo);
	
	// qna file 삭제
	void deleteByQnaFileNo(int qnaNo);
	
	// qna 삭제
	void deleteByQnaNo(int qnaNo);
	
	
	
}
