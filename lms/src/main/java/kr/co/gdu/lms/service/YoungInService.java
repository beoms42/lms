package kr.co.gdu.lms.service;

import java.io.File;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.YoungInMapper;
import kr.co.gdu.lms.vo.LectureSubject;
import kr.co.gdu.lms.vo.Qna;
import kr.co.gdu.lms.vo.QnaFile;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.SubjectTextbook;
import kr.co.gdu.lms.vo.Textbook;
import kr.co.gdu.lms.vo.TextbookRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class YoungInService {
	@Autowired private YoungInMapper youngInMapper;
	
	//----------------------------------------강의------------------------------------------
	
	public List<String> selectLearningStudentName() {
		List<String> list = youngInMapper.selectLearningStudentName();
		
		return list;
	}
	
	public void insertStudentInLecture(List<String> loginIdList, String lectureName) {
		for(String s : loginIdList) {
			youngInMapper.insertStudentInLecture(lectureName, s);
		}
		
	}
	
	//강의에 배정된 학생
	public List<Student> selectStudentListByLectureName(String lectureName) {
		List<Student> list = youngInMapper.selectStudentListByLectureName(lectureName);
		
		return list;
	}
	
	//강의별 배정인원수 / 인원수 
	//해시맵으로 넣을건데,, 키 > 강의명으로 값 > 인원수
	public Map<String, Object> selectStudentGroup() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> useMapList = youngInMapper.selectStudentGroup();
		for(HashMap<String, Object> h : useMapList ) {
			returnMap.put(h.get("lectureName").toString(), h.get("cnt"));
		}
		
		return returnMap;
	
	}
	// 교재등록 =  textbook insert
	// 과목등록 = textbook > subject에 귀속시킴
	// 두개를 한번에 만드는 controller제작 > 관리자만 가능
	// 영인 - get방식 subjectTextbookInsert Form
	public Map<String, Object> subjectTextbookInsertNeed() {
		Map<String, Object> returnMap  = new HashMap<>();
		
		List<Textbook> bookList = youngInMapper.selectTextbookList();
		
		List<String> subjectList = youngInMapper.selectSubjectList();
		
		returnMap.put("bookList", bookList);
		returnMap.put("subjectList", subjectList);
		
		return returnMap;
	}
	
	// 교재등록 =  textbook insert
	public void textbookInsert(Textbook tx) {
		youngInMapper.insertTextbook(tx);
	}
	
	// 과목등록 = textbook > subject에 귀속시킴
	public void insertSubjectTextbook(SubjectTextbook sb) {
		youngInMapper.insertSubjectTextbook(sb);
	}
	
	// 책을 수령했는지 안했는지 판별가능한 HashMap의 ArrayList로 만들어주는 서비스
	public Map<String, Object> selectRecordBook(String loginId) {
		
		//학생의 로그인아이디로 강의명 알아내기
		String lectureName = youngInMapper.selectLectureNameByLoginId(loginId);
		log.debug(CF.JYI+"youngInService.selectRecordBook. lectureName 이름으로얻은강의명 : "+lectureName+CF.RS);
		// subjectName > 강의에 귀속된 subjectName(과목)의 List<String>값
		List<String> subjectName = youngInMapper.selectSubjectListByLectureNameYoungIn(lectureName);
		log.debug(CF.JYI+"youngInService.selectRecordBook. subjectName 서브젝트리스트 : "+subjectName+CF.RS);
		//인자값으로 넣을 해시맵
		Map<String, Object> inputMap = null;
		
		// 로그인아이디로 eduNo알아내기
		int eduNo = youngInMapper.selectEducationNoByLoginId(loginId);

		// SubjectName > db구현, cnt > 0또는 1인 HashMap의 ArrayList
		List<HashMap<String, Object>> returnList = new ArrayList<HashMap<String, Object>>();
		
		// subjectName으로 과목에 맞는 책을 수령했는지 판별하는식을 for문으로 돌려서 어레이리스트에 추가
		for(String s : subjectName) {
			inputMap = new HashMap<String, Object>();
			
			inputMap.put("subjectName", s);
			inputMap.put("loginId", loginId);
			Map<String, Object> map = youngInMapper.selectRecordBook(inputMap);
			// long으로 받아져서 int로 캐스팅해야 사용 가능
			long var = (long) map.get("cnt");
			int var2 = Long.valueOf(var).intValue();
			// cnt가 0일때도 과목명은 필요해서 추가해주는 메서드
			if(var2 == 0) {
				map.put("subjectName", s.toString());
			} else {
				LectureSubject lectSub = new LectureSubject();
				
				lectSub.setLectureName(lectureName);
				lectSub.setSubjectName(s);
				
				int lecture_subject_no = youngInMapper.selectLectSubNo(lectSub);
				
				String textbookSignfileName = youngInMapper.selectSignfileName(eduNo, lecture_subject_no);
				map.put("textbookSignfileName", textbookSignfileName);
			}
			
			
			log.debug(CF.JYI+"youngInService.selectRecordBook.youngInMapper.map : "+map+CF.RS);
			returnList.add((HashMap<String, Object>) map);
		}
		
		//강의명도 돌려줘야되는대..그럼 returnList / lectureName / subjectName 세개를 리턴해주자
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("returnList", returnList);
		returnMap.put("lectureName", lectureName);
		returnMap.put("subjectName", subjectName);
		
		
		return returnMap;
		
	}
	
	public Map<String, Object> getSignNeed(String loginId, String subjectName) {
		
		LectureSubject lectSub = new LectureSubject();
		
		String lectureName = youngInMapper.selectLectureNameByLoginId(loginId);
		
		lectSub.setLectureName(lectureName);
		lectSub.setSubjectName(subjectName);
		
		log.debug(CF.JYI+"youngInService.getSignNeed.getSignNeed.map : "+lectSub+CF.RS);
		
		int eduNo = youngInMapper.selectEducationNoByLoginId(loginId);
		int lecture_subject_no = youngInMapper.selectLectSubNo(lectSub);
		
		Map<String, Object> map = new HashMap<>();
		map.put("eduNo", eduNo);
		map.put("lecture_subject_no", lecture_subject_no);
		
		return map;
	}
	
	public void getSignAction(TextbookRecord tx) {
		
		youngInMapper.insertTextbookRecord(tx);
	}
	
	//----------------------------------------커뮤니티------------------------------------------
	
	// 업데이트 서비스
	public void addQnaAction(String path, List<MultipartFile> qnaFileList, Qna qna, String loginId) {
		
			QnaFile qnaFile = null;
			
			// Qna를 넣고나서 제네레이트 키값을 출력해서 qnaFile에 써먹어야함
			youngInMapper.addQnaAction(qna);
			
			// 새로운 파일을 업데이트 해야겠찌?
			if(qnaFileList.get(0).getSize() > 0 ) {
				for(MultipartFile mf : qnaFileList) {
					qnaFile = new QnaFile();
					String originName = mf.getOriginalFilename();
					String ext = originName.substring(originName.lastIndexOf("."));

					String fileName = UUID.randomUUID().toString();
					fileName = fileName.replace("-", "");
					fileName = fileName + ext;
					
					qnaFile.setQnaFileOriginName(originName);
					qnaFile.setQnaFileName(fileName);
					qnaFile.setQnaFileSize((int)mf.getSize());
					qnaFile.setQnaFileType(mf.getContentType());
					qnaFile.setQnaNo(qna.getQnaNo());
					qnaFile.setLoginId(loginId);
					log.debug(CF.JYI+"YoungInService.addQnaAction.get addQnaAction : "+qnaFile+CF.RS);
					//이건 그대로 갖다쓰면 될듯? 그냥 파일 넣는거라
					youngInMapper.addQnaFileInsert(qnaFile);
					
					try {
						mf.transferTo(new File(path+fileName)); // multipart에 사진저장
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(); // RuntimeException 또 트라이 캐치 하지 않기위한 예외처리 - 런타임익셉션
					}
				}
			}
	}
	
	// 영인 - get방식 addQna호출
	public Map<String, Object> selectQnaFileByQnaNo(int qnaNo) {
		
		//사진 리스트
		List<String> fileList = youngInMapper.selectQnaFileByQnaNo(qnaNo);
		//One 문의
		Qna qnaInquiry = youngInMapper.selectQnaByQnaNo(qnaNo);
		//One 답변
		Qna qnaAnswer = youngInMapper.selectQnaByQnaNoAnswer(qnaNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileList", fileList);
		map.put("qnaInquiry", qnaInquiry);
		map.put("qnaAnswer", qnaAnswer);
		
		return map;
	}
	
	// 영인 - post방식 qnaListOne 답변
	public void insertQnaAnswer(Qna qna) {
		//인서트에 반환값 없음
		youngInMapper.insertQnaAnswer(qna);
		youngInMapper.updateByinsertQnaAnswer(qna.getQnaNo());
		
	}
	
	// 영인 - post방식 deleteByQnaNo 삭제
	public void deleteByQnaNo(int qnaNo, String path) {
		
		//파일 삭제
		List<String> fList = youngInMapper.selectQnaFileByQnaNo(qnaNo);
		
		for(String s : fList) {
			File f = new File(path + s);
	        f.delete();
		}
		
		//디비 qna파일삭제
		youngInMapper.deleteByQnaFileNo(qnaNo);
		//디비 qna삭제
		youngInMapper.deleteByQnaNo(qnaNo);
	}
}
