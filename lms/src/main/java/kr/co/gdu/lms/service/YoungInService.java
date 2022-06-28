package kr.co.gdu.lms.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gdu.lms.mapper.YoungInMapper;
import kr.co.gdu.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class YoungInService {
	@Autowired private YoungInMapper youngInMapper;
	
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
}
