package kr.co.gdu.lms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Qna;
import kr.co.gdu.lms.vo.QnaFile;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.SubjectTextbook;
import kr.co.gdu.lms.vo.Textbook;

@Mapper
public interface YoungInMapper {
	//----------------------------------------媛뺤쓽------------------------------------------
	
	List<String> selectLearningStudentName();
	
	void insertStudentInLecture(String lectureName, String loginId);
	
	// 媛뺤쓽�뿉 諛곗젙�맂 �븰�깮媛��졇�삤湲�
	List<Student> selectStudentListByLectureName(String lectureName);
	
	//媛뺤쓽蹂� 諛곗젙�씤�썝�닔 / �씤�썝�닔 
	//�빐�떆留듭쑝濡� �꽔�쓣嫄대뜲,, �궎 > 媛뺤쓽紐낆쑝濡� 媛� > �씤�썝�닔
	List<HashMap<String, Object>> selectStudentGroup();
	
	//textbook insert
	void insertTextbook(Textbook tx);
	
	//textbook select
	List<Textbook> selectTextbookList();
	
	//subject select
	List<String> selectSubjectList();
	
	//subject_textbook insert
	void insertSubjectTextbook(SubjectTextbook subjectTextbook);
	
	//媛뺤쓽蹂� 怨쇰ぉ 媛��졇�삤湲�
	List<String> selectSubjectListByLectureNameYoungIn(String lectureName);
	
	// subjectName�쑝濡� 怨쇰ぉ�뿉 留욌뒗 梨낆쓣 �닔�졊�뻽�뒗吏� �뙋蹂꾪븯�뒗�떇
	Map<String, Object> selectRecordBook(Map<String, Object> map);
	
	// �씠由꾩쑝濡� 媛뺤쓽紐� 媛��졇�삤湲�
	String selectLectureNameByLoginId(String loginId);
	//----------------------------------------而ㅻ�ㅻ땲�떚------------------------------------------
	
	// addQna �븯怨� �젣�꽕�젅�씠�듃�궎媛믪쑝濡� qnaNo諛섑솚
	int addQnaAction(Qna qna);
	
	// qnaFile foreach insert
	void addQnaFileInsert(QnaFile qnaFile);
	
	// qnaNo濡� �븯�굹蹂닿린 - 臾몄쓽
	Qna selectQnaByQnaNo(int qnaNo);
	
	// qnaNo濡� �븯�굹蹂닿린 - �떟蹂�
	Qna selectQnaByQnaNoAnswer(int qnaNo);
	
	// qnaNo濡� �뙆�씪由ъ뒪�듃媛��졇�삤湲�
	List<String> selectQnaFileByQnaNo(int qnaNo);
	
	// qna�떟蹂� insert
	void insertQnaAnswer(Qna qna);
	
	// qna �떟蹂��쓽 �썝湲� �떟蹂��셿猷뚮줈 蹂�寃� 硫붿꽌�뱶
	void updateByinsertQnaAnswer(int qnaNo);
	
	// qna file �궘�젣
	void deleteByQnaFileNo(int qnaNo);
	
	// qna �궘�젣
	void deleteByQnaNo(int qnaNo);
	
	
	
}
