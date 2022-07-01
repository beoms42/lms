package kr.co.gdu.lms.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Qna;
import kr.co.gdu.lms.vo.QnaFile;
import kr.co.gdu.lms.vo.Student;

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
	
	//qna 답변의 원글 답변완료로 변경 메서드
	void updateByinsertQnaAnswer(int qnaNo);
}
