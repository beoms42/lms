package kr.co.gdu.lms.controller;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.LectureSerivce;
import kr.co.gdu.lms.service.MemberService;
import kr.co.gdu.lms.vo.CalendarMap;
import kr.co.gdu.lms.vo.Lecture;
import kr.co.gdu.lms.vo.LectureRoom;
import kr.co.gdu.lms.vo.LectureSubject;
import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Schedule;
import kr.co.gdu.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LectureController {
	@Autowired private LectureSerivce lectureService;
	@Autowired private MemberService memberService;
	@GetMapping("/loginCheck/addLecture")
	public String addLecture(Model model
			, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map = lectureService.getMakeLectureNeed();
		List<String> teacherNameList = (List<String>)map.get("teacherNameList");
		List<String> managerNameList = (List<String>)map.get("managerNameList");
		List<LectureRoom> lectureRoomList = (List<LectureRoom>) map.get("lectureRoomList");
		
		
		log.debug(CF.JYI+"LectureService.addLecture.get teacherNameList : "+teacherNameList+CF.RS);
		log.debug(CF.JYI+"LectureService.addLecture.get managerNameList : "+managerNameList+CF.RS);
		log.debug(CF.JYI+"LectureService.addLecture.get lectureRoomList : "+lectureRoomList+CF.RS);
		
		String loginId = (String) session.getAttribute("sessionId");
		
		log.debug(CF.JYI+"LectureService.addLecture.get loginId : "+loginId+CF.RS);
		
		
		model.addAttribute("teacherNameList", teacherNameList); //얘는 List<String>
		model.addAttribute("managerNameList", managerNameList); //얘도 List<String>
		model.addAttribute("lectureRoomList", lectureRoomList); //얘가,, List<LectureRoom>
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
		
		
		
		
		
		//데이터 중복 제거하려면 subList에서 haveSubList를 빼야함
		List<String> removed = new ArrayList<String>();
		
		for(String s : subList) {
			for(String h : haveSubList) {
				if(h.equals(s)) {
					removed.add(h);
				}
			}
		}
		
		for(String s : removed) {
			subList.remove(s);
		}
		
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
		
		// 리스트로 강의과목을 받아서 개별 삭제하게 하는..
		List<String> sublist = lectureService.selectSubjectListByLectureName(lectureName);
		model.addAttribute("sublist", sublist);
		
		
		// 리스트 과목관리에 포함된 책 보기
		List<Map<String, Object>> bookMapList = lectureService.selectTextbookByLectureName(lectureName);
		model.addAttribute("bookMapList", bookMapList);
		log.debug(CF.JYI+"LectureService.updateLectureForm.get bookMap : "+bookMapList+CF.RS);
		return "lecture/updateLectureForm";
	}
	
	// 강의관리 - 과목 삭제(Get방식 단일삭제)
	@GetMapping("/loginCheck/delSubjectOneByLectureName")
	public String delSubjectOneByLectureName(Model model
			, HttpSession session
			, @RequestParam(name = "lectureName") String lectureName
			, @RequestParam(name = "subjectName") String subjectName) {
			
		// 강의이름 + 과목이름으로 과목 하나 삭제
		
		return "redirect:/loginCheck/manageLecture";
	}
	
	// 강의관리 - Post 수정 액션
	@PostMapping("/loginCheck/updateLectureAction")
	public String updateLectureAction(Model model
			, HttpSession session
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
		
		log.debug(CF.JYI+"LectureService.updateLectureAction.post lect : "+lect+CF.RS);
		
		lectureService.updateLecture(lect);
		
		return "redirect:/loginCheck/manageLecture";
	}
	
	// 전체 시간표리스트 보기
	@GetMapping("/loginCheck/getSheduleListByMonth")
	public String getSheduleListByMonth(Model model,  HttpSession session
										,@RequestParam(name="m" ,defaultValue = "-1")int m
										,@RequestParam(name="y",defaultValue = "-1") int y
										) {
		int loginLv = (int)session.getAttribute("sessionLv");
		String loginId = (String)session.getAttribute("sessionId");
		// 디버깅
		log.debug(CF.HJI+"LectureController.request y : "+y+CF.RS);
		log.debug(CF.HJI+"LectureController.request m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.request level : "+loginLv+CF.RS);
		log.debug(CF.HJI+"LectureController.request loginId : "+loginId+CF.RS);
		
		Map<String, Object> scheduleList = lectureService.getSheduleListByMonth(y, m, loginLv, loginId);
		List<CalendarMap> list = (List<CalendarMap>)scheduleList.get("list");
		List<LectureSubject> lectureSubjectList = (List<LectureSubject>)scheduleList.get("lectureSubjectList");
		int startBlank = (int)scheduleList.get("startBlank");
		int endDay = (int)scheduleList.get("endDay");
		int endBlank = (int)scheduleList.get("endBlank");
		int totalTd = (int)scheduleList.get("totalTd");
		// 서비스에서 년월 세팅다시하고 변수받기
		m = (int)scheduleList.get("m");
		y = (int)scheduleList.get("y");
		
		// 디버깅
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth listlectureSubjectList : "+lectureSubjectList+CF.RS);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth list : "+list+CF.RS);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth startBlank : "+startBlank+CF.RS);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth endDay : "+endDay+CF.RS);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth endBlank : "+endBlank+CF.RS);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth totalTd : "+totalTd+CF.RS);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth y : "+y+CF.RS);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth sessionId : "+loginId+CF.RS);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth sessionLv : "+loginLv+CF.RS);
		
		
		// model로 보내기
		model.addAttribute("lectureSubjectList",lectureSubjectList);
		model.addAttribute("list",list);
		model.addAttribute("startBlank",startBlank);
		model.addAttribute("endDay",endDay);
		model.addAttribute("endBlank",endBlank);
		model.addAttribute("totalTd",totalTd);
		model.addAttribute("m",m);
		model.addAttribute("y",y);
		model.addAttribute("LoginLv",loginLv);
		model.addAttribute("LoginId",loginId);
	return "/lecture/getSheduleListByMonth";
	}
	
	// 시간표 추가
	@PostMapping("/loginCheck/addSchedule")
	public String addSchedule(@RequestParam(name="scheduleStartDate")Date scheduleStartDate
								,@RequestParam(name="scheduleEndDate")Date scheduleEndDate
								,@RequestParam(name="lectureSubjectNo")int lectureSubjectNo
								,@RequestParam(name="m")int m
								,@RequestParam(name="y")int y
								) {
		log.debug(CF.HJI+"LectureController.addShedule scheduleStartDate : "+scheduleStartDate+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule scheduleEndDate : "+scheduleEndDate+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule lectureSubjectNo : "+lectureSubjectNo+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule y : "+y+CF.RS);
		
		 long elapsedms = scheduleEndDate.getTime() - scheduleStartDate.getTime();
         long diff = TimeUnit.MINUTES.convert(elapsedms, TimeUnit.MILLISECONDS);
         diff = (diff/(60*24));
         log.debug(CF.HJI+"LectureController.addShedule diff : "+diff+CF.RS);
         
         for(int i=0; i <= diff; i++) {
        	 Calendar cal = Calendar.getInstance();
        	 cal.setTime(scheduleStartDate);
        	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        	 cal.add(Calendar.DATE, i);
        	 int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        	 log.debug(CF.HJI+"LectureController.addShedule dayOfWeek : "+dayOfWeek+CF.RS);
        	 if(dayOfWeek != 1 && dayOfWeek != 7) {
        	 SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        		 String scheduleDate = fm.format(cal.getTime());
            	 log.debug(CF.HJI+"LectureController.addShedule scheduleDate : "+scheduleDate+CF.RS);
            	 Schedule schedule = new Schedule();
         		schedule.setScheduleDate(scheduleDate);
         		schedule.setLectureSubjectNo(lectureSubjectNo);
         		int row = lectureService.addSchedule(schedule);
         		if(row == 1) {
        			log.debug(CF.HJI+"LectureController.addShedule 성공! : "+row+CF.RS);
        		} else {
        			log.debug(CF.HJI+"LectureController.addShedule 실패! : "+row+CF.RS);
        		} 
        	 }
		}

		
	return "redirect:/loginCheck/getSheduleListByMonth?m="+m+"&y="+y;
	}
	
	// 시간표 상세보기
	@GetMapping("/loginCheck/getScheduleOne")
	public String getScheduleOne(Model model
								,@RequestParam(name="scheduleNo") int scheduleNo
								,@RequestParam(name="m")int m
								,@RequestParam(name="y")int y) {
		log.debug(CF.HJI+"LectureController.getScheduleOne scheduleNo : "+scheduleNo+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule y : "+y+CF.RS);
		
		Map<String,Object> getScheduleOne = lectureService.getScheduleOne(scheduleNo);
		log.debug(CF.HJI+"LectureController.getScheduleOne getScheduleOne : "+getScheduleOne+CF.RS);
	
		// model로 보내기
		model.addAttribute("getScheduleOne",getScheduleOne);
		model.addAttribute("m",m);
		model.addAttribute("y",y);
	return "/lecture/getScheduleOne";
	}
	
	// 시간표 삭제
	@GetMapping("/loginCheck/removeSchedule")
	public String removeSchedule(
								@RequestParam(name="scheduleNo") int scheduleNo
								,@RequestParam(name="m")int m
								,@RequestParam(name="y")int y
								) {		
		log.debug(CF.HJI+"LectureController.removeSchedule scheduleNo : "+scheduleNo+CF.RS);
		log.debug(CF.HJI+"LectureController.removeSchedule m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.removeSchedule y : "+y+CF.RS);
		
		int row = lectureService.removeSchedule(scheduleNo);
		if(row == 1) {
			log.debug(CF.HJI+"LectureController.removeSchedule 성공! : "+row+CF.RS);
		} else {
			log.debug(CF.HJI+"LectureController.removeSchedule 실패! : "+row+CF.RS);
		}
	return "redirect:/loginCheck/getSheduleListByMonth?m="+m+"&y="+y;
	}
	
	// 시간표 수정폼
	@GetMapping("/loginCheck/modifySchedule")
	public String modifySchedule(Model model, HttpSession session
								,@RequestParam(name="scheduleNo") int scheduleNo
								,@RequestParam(name="m")int m
								,@RequestParam(name="y")int y) {
		int loginLv = (int)session.getAttribute("sessionLv");
		String loginId = (String)session.getAttribute("sessionId");
		log.debug(CF.HJI+"LectureController.modifyScheduleForm scheduleNo : "+scheduleNo+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleForm m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleForm y : "+y+CF.RS);
		
		// 모델값 구하기
		Map<String,Object> modifyScheduleForm = lectureService.modifyScheduleForm(scheduleNo);
		
		// 분리
		Map<String,Object> selectScheduleOne = (Map<String,Object>)modifyScheduleForm.get("selectScheduleOne");
		List<LectureSubject> lectureSubjectList = (List<LectureSubject>)modifyScheduleForm.get("lectureSubjectList"); 
		Date scheduleDate = (Date)selectScheduleOne.get("scheduleDate");
		int lectureSubjectNo = (int)selectScheduleOne.get("lectureSubjectNo");
		// 디버깅
		log.debug(CF.HJI+"LectureController.modifyScheduleForm selectScheduleOne : "+selectScheduleOne+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleForm scheduleNo : "+scheduleNo+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleForm lectureSubjectNo : "+lectureSubjectNo+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleForm shceduleDate : "+scheduleDate+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleForm m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleForm y : "+y+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleForm loginLv : "+loginLv+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleForm loginId : "+loginId+CF.RS);
		
		// 모델로 보개기
		model.addAttribute("lectureSubjectList", lectureSubjectList);
		model.addAttribute("scheduleNo", scheduleNo);
		model.addAttribute("lectureSubjectNo", lectureSubjectNo);
		model.addAttribute("scheduleDate", scheduleDate);
		model.addAttribute("m",m);
		model.addAttribute("y",y);
		model.addAttribute("loginLv", loginLv);
		model.addAttribute("loginLv",loginId);
	return "/lecture/modifySchedule";
	}
	
	// 시간표 수정액션
	@PostMapping("/loginCheck/modifySchedule")
	public String modifySchedule(Schedule schedule
								,@RequestParam(name="m")int m
								,@RequestParam(name="y")int y
								,HttpSession session) {
		log.debug(CF.HJI+"LectureController.modifyScheduleAction schedule : "+schedule+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleAction m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleAction y : "+y+CF.RS);
			
		int row = lectureService.modifyScheduleAction(schedule);
		int scheduleNo = schedule.getScheduleNo();
		if(row == 1) {
			log.debug(CF.HJI+"LectureController.modifyScheduleAction 성공! : "+row+CF.RS);
		} else {
			log.debug(CF.HJI+"LectureController.modifyScheduleAction 실패! : "+row+CF.RS);
		}
	return "redirect:/loginCheck/getScheduleOne?scheduleNo="+scheduleNo+"&m="+m+"&y="+y;
	} 
	
// 자료실
	
	// 자료실 리스트
	@GetMapping("/loginCheck/getLectureReferenceList")
	public String getLectureReferenceList() {
		
	return "/lecture/getLectureReferenceList";
	}
	
	// 자료실 상세보기
	@GetMapping("/loginCheck/getReferenceOne")
	public String getReferenceOne() {
		
	return "/lecture/getReferenceOne";
	}
	
	// 자료실 입력
	@GetMapping("/loginCheck/addReferenceForm")
	public String addReferenceForm() {
	
	return "/lecture/addReferenceForm";
	}
	
	@PostMapping("/addReferenceAction")
	public String addReferenceAction() {
		
	return "redirect:/loginCheck/getLectureReferenceList";
	}
	
	// 자료실 수정
	@GetMapping("/updateAddReferenceForm")
	public String updateAddReferenceForm() {
	
	return "/lecture/updateAddReferenceForm";
	}
	
	@PostMapping("/updateAddReferenceAction")
	public String updateAddReferenceAction() {
	
	return "redirect:/loginCheck/getReferenceOne";
	}
	
	// 자료실 삭제
	@GetMapping("/removeReference")
	public String removeReference() {
	
	return "/lecture/getReferenceOne";
	}
	
	// 자료실 파일 삭제
	@PostMapping("/removeReferenceFile")
	public String removeReferenceFile() {
	
	return "redirect:/loginCheck/updateAddReferenceForm";
	}
	
	// 자료실 파일 수정
	@PostMapping("/updateReferencefile")
	public String updateReferencefile() {
	
	return "redirect:/loginCheck/updateAddReferenceForm";
	}
}
