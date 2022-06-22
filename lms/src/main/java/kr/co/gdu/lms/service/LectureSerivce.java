package kr.co.gdu.lms.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.LectureMapper;
import kr.co.gdu.lms.vo.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class LectureSerivce {
	@Autowired private LectureMapper lectureMapper;
	
	// 강의개설시 필요한 드롭다운 메뉴 : 강사 / 매니저 / 강의실 / 정원(강의실)
	public HashMap<String, Object> getMakeLectureNeed() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		// 강사 가져오기
		List<Teacher> teacherList = lectureMapper.selectTeacher();
		
		// 매니저 가져오기
		List<Manager> managerList = lectureMapper.selectManager();
		
		// 강의실, 정원 가져오기
		List<LectureRoom> lectureRoomList = lectureMapper.selectLectureRoom();
		
		log.debug(CF.JYI+"서비스 - 강사 : "+teacherList+CF.RS);
		log.debug(CF.JYI+"서비스 - 매니저 : "+managerList+CF.RS);
		log.debug(CF.JYI+"서비스 - 강의실 : "+lectureRoomList+CF.RS);
		
		//내가 필요한건 이름의 리스트지...
		// 강사의 이름만 추출하자
		List<String> teacherNameList = new ArrayList<String>();
		for(Teacher t : teacherList) {
			teacherNameList.add(t.getTeacherName());
		}
		//매니저 이름만 추출
		List<String> managerNameList = new ArrayList<String>();
		for(Manager t : managerList) {
			managerNameList.add(t.getManagerName());
		}
		//강의실 이름은 두개를 추출해야 강의실 / 정원인데 걍 넣자
		
		map.put("teacherNameList", teacherNameList);
		map.put("managerNameList", managerNameList);
		map.put("lectureRoomList", lectureRoomList);
		
		return map;
	}
	
	public void addLecutre(Lecture lecture) {
		lectureMapper.insertLecture(lecture);
		
	}
	
	public List<Lecture> selectLectureList() {
		List<Lecture> lectList = lectureMapper.selectLectureList();
		log.debug(CF.JYI+"LectureService.selectLectureList LectureList : "+lectList+CF.RS);
		
		return lectList;
	}
	
	public List<Lecture> selectNotAcceptLectureList() {
		List<Lecture> lectList = lectureMapper.selectNotAcceptLectureList();
		log.debug(CF.JYI+"LectureService.selectNotAcceptLectureList LectureList : "+lectList+CF.RS);
		
		return lectList;
	}
	
	public void updateLectureActive(String lectureName) {
		lectureMapper.updateLectureActive(lectureName);
	}
	
	// 과목리스트 디스틴트로 가져오는 서비스
	public List<String> selectSubejctList() {
		List<String> subList = lectureMapper.selectSubejctList();
		
		return subList;
	}
	
	// 강의에 과목리스트 넣기
	public void insertSubjectList(List<String> subList, String lectureName) {
		for(String s : subList) {
			lectureMapper.insertSubject(s, lectureName);
		}
		
	}
}
