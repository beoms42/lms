package kr.co.gdu.lms.controller;


import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.LectureSerivce;
import kr.co.gdu.lms.vo.Lecture;
import kr.co.gdu.lms.vo.LectureRoom;
import kr.co.gdu.lms.vo.Login;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LectureController {
	@Autowired private LectureSerivce lectureService;
	
	@GetMapping("/loginCheck/addLecture")
	public String addLecture(Model model
			, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map = lectureService.getMakeLectureNeed();
		List<String> teacherNameList = (List<String>)map.get("teacherNameList");
		List<String> managerNameList = (List<String>)map.get("managerNameList");
		List<LectureRoom> lectureRoomList = (List<LectureRoom>) map.get("lectureRoomList");
		model.addAttribute("teacherNameList", teacherNameList); //얘는 List<String>
		model.addAttribute("managerNameList", managerNameList); //얘도 List<String>
		model.addAttribute("lectureRoomList", lectureRoomList); //얘가,, List<LectureRoom>
		
		log.debug(CF.JYI+"LectureService.addLecture.get teacherNameList : "+teacherNameList+CF.RS);
		log.debug(CF.JYI+"LectureService.addLecture.get managerNameList : "+managerNameList+CF.RS);
		log.debug(CF.JYI+"LectureService.addLecture.get lectureRoomList : "+lectureRoomList+CF.RS);
		
		String loginId = (String) session.getAttribute("sessionId");
		
		log.debug(CF.JYI+"LectureService.addLecture.get loginId : "+loginId+CF.RS);
		
		model.addAttribute("loginId", loginId);
		return "lecture/addLecture";
	}
	
	@PostMapping("/loginCheck/addLecture")
	public String addLecture(Model model
			, @RequestParam(name = "lectureName") String lectureName
			, @RequestParam(name = "lectureStartDate") String lectureStartDate
			, @RequestParam(name = "lectureEndDate") String lectureEndDate
			, @RequestParam(name = "teacherName") String teacherName
			, @RequestParam(name = "managerName") String managerName
			, @RequestParam(name = "lectureRoomName") String lectureRoomName
			, @RequestParam(name = "maxStudent") int maxStudent
			, @RequestParam(name = "loginId") String loginId) {
		Lecture lect = new Lecture();
		lect.setLectureName(lectureName);
		lect.setLectureStartDate(lectureStartDate);
		lect.setLectureEndDate(lectureEndDate);
		lect.setTeacher(teacherName);
		lect.setManager(managerName);
		lect.setLectureRoomName(lectureRoomName);
		lect.setLectureStudentCapacity(maxStudent);
		lect.setLoginId(loginId);
		
		log.debug(CF.JYI+"LectureService.addLecture.post Lecture : "+lect+CF.RS);
		
		// 강의 추가
		lectureService.addLecutre(lect);
		
		return "redirect:/loginCheck/acceptLecture";
	}
	
	@GetMapping("/loginCheck/manageLecture")
	public String manageLecture(Model model
			, HttpSession session) {
		List<Lecture> lectList = lectureService.selectLectureList();
		

		log.debug(CF.JYI+"LectureService.manageLecture.get LectureList : "+lectList+CF.RS);
		model.addAttribute("lectList", lectList);
		return "lecture/manageLecture";
	}
	
	@GetMapping("/loginCheck/acceptLecture")
	public String acceptLecture(Model model
			, HttpSession session) {
		List<Lecture> lectList = lectureService.selectNotAcceptLectureList();
		
		log.debug(CF.JYI+"LectureService.acceptLecture.get lectList : "+lectList+CF.RS);
		
		model.addAttribute("lectList", lectList);
		return "lecture/acceptLecture";
	}
	
	@GetMapping("/loginCheck/acceptActionLecture")
	public String acceptActionLecture(Model model
			, HttpSession session
			, @RequestParam(name = "lectureName") String lectureName) {
		
		//이름을 받아서 같은이름의 enum을 1로 변경
		lectureService.updateLectureActive(lectureName);
		
		//원래 미승인 리스트
		List<Lecture> lectList = lectureService.selectNotAcceptLectureList();
		
		model.addAttribute("lectList", lectList);
		return "lecture/acceptLecture";
	}
	
	// 강의관리 - 삭제버튼을 눌렀을때
	@GetMapping("/loginCheck/deleteLecture")
	public String deleteLectureModel(Model model
			, HttpSession session
			, @RequestParam(name = "lectureName") String lectureName) {
			
		return "";
	}
	
	// 강의관리 - 과목설정 버튼 클릭시
	@GetMapping("/loginCheck/addSubjectInLecture")
	public String addSubjectInLecture(Model model
			, HttpSession session
			, @RequestParam(name = "lectureName") String lectureName) {
			
		//과목명 뽑아올거
		List<String> subList = lectureService.selectSubejctList();
		List<String> haveSubList = lectureService.selectSubjectListByLectureName(lectureName);
		
		log.debug(CF.JYI+"LectureService.addSubjectInLecture.get subList : "+subList+CF.RS);
		log.debug(CF.JYI+"LectureService.addSubjectInLecture.get haveSubList : "+haveSubList+CF.RS);
		
		model.addAttribute("subList", subList);
		model.addAttribute("lectureName", lectureName);
		model.addAttribute("haveSubList", haveSubList);
		return "lecture/addSubejctInLecture";
	}
		
	// 강의관리 - 상세 과목 받아서 페이지에 넣기
	@GetMapping("/loginCheck/addSubjectInLectureAction")
	public String addSubjectInLectureAction(Model model
			, HttpSession session
			, @RequestParam(name = "checkedSubject") List<String> checkedSubject
			, @RequestParam(name = "lectureName") String lectureName) {
			
		//과목리스트를 넣을땐 인서트를 여러번 해야함. 그래서 서비스에서 포문으로 강의명 + 과목명으로 돌려야함
		lectureService.insertSubjectList(checkedSubject, lectureName);
		log.debug(CF.JYI+"LectureService.addSubjectInLectureAction.get checkedSubject : "+lectureName+CF.RS);
		log.debug(CF.JYI+"LectureService.addSubjectInLectureAction.get checkedSubject : "+checkedSubject+CF.RS);
		return "redirect:/loginCheck/manageLecture";
	}
	
	// 강의관리 - 수정 폼(강의개설 -> 데이터 입력 + 과목데이터 추가)
	@GetMapping("/loginCheck/updateLectureForm")
	public String updateLectureForm(Model model
			, HttpSession session
			, @RequestParam(name = "lectureName") String lectureName) {
		//이름을 받아서 그걸로 셀렉 (강의추가할때 input값으로 들어갈 강의데이터)
		Lecture lect = lectureService.selectLectureOneByLectureName(lectureName);
		model.addAttribute("lect", lect);
		
		//원래 가져와야하는 값들 : (원래 addLecture 밑에 있는것들)
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map = lectureService.getMakeLectureNeed();
		List<String> teacherNameList = (List<String>)map.get("teacherNameList");
		List<String> managerNameList = (List<String>)map.get("managerNameList");
		List<LectureRoom> lectureRoomList = (List<LectureRoom>) map.get("lectureRoomList");
		model.addAttribute("teacherNameList", teacherNameList); //얘는 List<String>
		model.addAttribute("managerNameList", managerNameList); //얘도 List<String>
		model.addAttribute("lectureRoomList", lectureRoomList); //얘가,, List<LectureRoom>
		
		log.debug(CF.JYI+"LectureService.addLecture.get teacherNameList : "+teacherNameList+CF.RS);
		log.debug(CF.JYI+"LectureService.addLecture.get managerNameList : "+managerNameList+CF.RS);
		log.debug(CF.JYI+"LectureService.addLecture.get lectureRoomList : "+lectureRoomList+CF.RS);
		
		String loginId = (String) session.getAttribute("sessionId");
		
		log.debug(CF.JYI+"LectureService.addLecture.get loginId : "+loginId+CF.RS);
		
		model.addAttribute("loginId", loginId);
		
		// 리스트로 강의과목을 받아서 개별 삭제하게 하는..그런
		
		List<String> sublist = lectureService.selectSubjectListByLectureName(lectureName);
		model.addAttribute("sublist", sublist);
		return "lecture/updateLectureForm";
	}
	
}
