package kr.co.gdu.lms.service;

import java.io.File;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.YoungInMapper;
import kr.co.gdu.lms.vo.Qna;
import kr.co.gdu.lms.vo.QnaFile;
import kr.co.gdu.lms.vo.Student;
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

	//----------------------------------------커뮤니티------------------------------------------
	
	// 업데이트 서비스
	public void addQnaAction(String path, List<MultipartFile> qnaFileList, Qna qna, String loginId) {
			QnaFile qnaFile = null;
			
			
			// Qna를 넣고나서 제네레이트 키값을 출력해서 qnaFile에 써먹어야함
			int qnaNo = youngInMapper.addQnaAction(qna);
			
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
					qnaFile.setQnaNo(qnaNo);
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
	
	
}
