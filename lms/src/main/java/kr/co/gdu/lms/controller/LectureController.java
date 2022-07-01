package kr.co.gdu.lms.controller;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.LectureSerivce;
import kr.co.gdu.lms.vo.CalendarMap;
import kr.co.gdu.lms.vo.Lecture;
import kr.co.gdu.lms.vo.LectureRoom;
import kr.co.gdu.lms.vo.LectureSubject;
import kr.co.gdu.lms.vo.Reference;
import kr.co.gdu.lms.vo.ReferenceForm;
import kr.co.gdu.lms.vo.Schedule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LectureController {
	@Autowired private LectureSerivce lectureService;
	
	// 출결관리 - 강사 이상만
	@GetMapping("/loginCheck/attendance")
	public String attendance(HttpSession session) {
		
		// 강사 이상 아니면 메인으로
		if((int)session.getAttribute("sessionLv") < 2) {
			return "redirect:/loginCheck/main";
		}
		
		return "lecture/attendance";
	}
	
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
		// 세션에서 login정보 들고 오기
		int loginLv = (int)session.getAttribute("sessionLv");
		String loginId = (String)session.getAttribute("sessionId");
		
		//  요청값 디버깅
		log.debug(CF.HJI+"LectureController.request y : "+y+CF.RS);
		log.debug(CF.HJI+"LectureController.request m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth sessionId : "+loginId+CF.RS);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth sessionLv : "+loginLv+CF.RS);
		
		// 실행
		Map<String, Object> scheduleMap = lectureService.getSheduleListByMonth(y, m, loginLv, loginId);
		log.debug(CF.HJI+"LectureController.getSheduleListByMonth scheduleList : "+scheduleMap+CF.RS);
		
		
		// model로 보내기
		model.addAttribute("lectureSubjectList",scheduleMap.get("lectureSubjectList"));
		model.addAttribute("list",scheduleMap.get("list"));
		model.addAttribute("startBlank",scheduleMap.get("startBlank"));
		model.addAttribute("endDay",scheduleMap.get("endDay"));
		model.addAttribute("endBlank",scheduleMap.get("endBlank"));
		model.addAttribute("totalTd",scheduleMap.get("totalTd"));
		model.addAttribute("m",scheduleMap.get("m"));
		model.addAttribute("y",scheduleMap.get("y"));
		
		return "/lecture/getSheduleListByMonth";
	}
	
	// 시간표 추가
	@PostMapping("/loginCheck/addSchedule")
	public String addSchedule(@RequestParam(name="scheduleStartDate")Date scheduleStartDate
								,@RequestParam(name="scheduleEndDate")Date scheduleEndDate
								,@RequestParam(name="lectureSubjectNo")int lectureSubjectNo
								,@RequestParam(name="m")int m
								,@RequestParam(name="y")int y) {
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.addShedule scheduleStartDate : "+scheduleStartDate+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule scheduleEndDate : "+scheduleEndDate+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule lectureSubjectNo : "+lectureSubjectNo+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule y : "+y+CF.RS);
		
		// 날짜끼리의 차이 구하기
		long elapsedms = scheduleEndDate.getTime() - scheduleStartDate.getTime();
        long diff = TimeUnit.MINUTES.convert(elapsedms, TimeUnit.MILLISECONDS);
         
         // 분으로 계산되어서 나누어주기
         diff = (diff/(60*24));
         log.debug(CF.HJI+"LectureController.addShedule diff : "+diff+CF.RS);
         
         for(int i=0; i <= diff; i++) {
        	 // 켈린더로 변환
        	 Calendar cal = Calendar.getInstance();
        	 // 변환
        	 cal.setTime(scheduleStartDate);
        	 // 형식 바꾸기
        	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        	 cal.add(Calendar.DATE, i);
        	 // 일주일에서 주말은 제외하기 위해 dayOfWeek 선언
        	 int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        	 log.debug(CF.HJI+"LectureController.addShedule dayOfWeek : "+dayOfWeek+CF.RS);
        	 // 토일이 아니면
        	 if(dayOfWeek != 1 && dayOfWeek != 7) {
        		 
        		// String으로 변환하기 위해
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
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.getScheduleOne scheduleNo : "+scheduleNo+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.addShedule y : "+y+CF.RS);
		
		// 실행
		Map<String,Object> getScheduleOneMap = lectureService.getScheduleOne(scheduleNo);
		log.debug(CF.HJI+"LectureController.getScheduleOne getScheduleOne : "+getScheduleOneMap+CF.RS);
	
		// model로 리턴
		model.addAttribute("getScheduleOne",getScheduleOneMap);
		model.addAttribute("m",m);
		model.addAttribute("y",y);
		
		return "/lecture/getScheduleOne";
	}
	
	// 시간표 삭제
	@GetMapping("/loginCheck/removeSchedule")
	public String removeSchedule(@RequestParam(name="scheduleNo") int scheduleNo
								,@RequestParam(name="m")int m
								,@RequestParam(name="y")int y
								) {
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.removeSchedule scheduleNo : "+scheduleNo+CF.RS);
		log.debug(CF.HJI+"LectureController.removeSchedule m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.removeSchedule y : "+y+CF.RS);
		
		// 실행
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
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.modifyScheduleForm scheduleNo : "+scheduleNo+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleForm m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleForm y : "+y+CF.RS);
		
		// 실행
		Map<String,Object> modifyScheduleForm = lectureService.modifyScheduleForm(scheduleNo);
		// 분리
		Map<String,Object> selectScheduleOne = (Map<String,Object>)modifyScheduleForm.get("selectScheduleOne");
		
		// 디버깅
		log.debug(CF.HJI+"LectureController.modifyScheduleForm selectScheduleOne : "+selectScheduleOne+CF.RS);
		
		// model로 리턴
		model.addAttribute("lectureSubjectList", modifyScheduleForm.get("lectureSubjectList"));
		model.addAttribute("scheduleNo", scheduleNo);
		model.addAttribute("lectureSubjectNo", selectScheduleOne.get("lectureSubjectNo"));
		model.addAttribute("scheduleDate", selectScheduleOne.get("scheduleDate"));
		model.addAttribute("m",m);
		model.addAttribute("y",y);
		
		return "/lecture/modifySchedule";
	}
	
	// 시간표 수정액션
	@PostMapping("/loginCheck/modifySchedule")
	public String modifySchedule(Schedule schedule
								,@RequestParam(name="m")int m
								,@RequestParam(name="y")int y) {
		// 요청값 분석
		log.debug(CF.HJI+"LectureController.modifyScheduleAction schedule : "+schedule+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleAction m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureController.modifyScheduleAction y : "+y+CF.RS);
		
		// 실행
		int row = lectureService.modifyScheduleAction(schedule);
		
		// 상세보기 가기 위해 번호 추출
		int scheduleNo = schedule.getScheduleNo();
		// 디버깅
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
	public String getLectureReferenceList(Model model, HttpSession session
										, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage
										, @RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		String loginId = (String)session.getAttribute("sessionId");
		int loginLv = (int)session.getAttribute("sessionLv");
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.getLectureReferenceList currentPage : "+currentPage+CF.RS);
		log.debug(CF.HJI+"LectureController.getLectureReferenceList rowPerPage : "+rowPerPage+CF.RS);
		log.debug(CF.HJI+"LectureController.getLectureReferenceList rowPerPage : "+loginId+CF.RS);
		log.debug(CF.HJI+"LectureController.getLectureReferenceList rowPerPage : "+loginLv+CF.RS);
		
		// 맵에 넣기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage);
		map.put("rowPerPage", rowPerPage);
		map.put("loginId", loginId);
		map.put("loginLv", loginLv);
		
		// 실행
		Map<String, Object> returnMap = lectureService.getLectureReferenceList(map);		
		log.debug(CF.HJI+"LectureController.getLectureReferenceList returnMap : "+returnMap+CF.RS);
		
		// model로 리턴 
		model.addAttribute("lectureName", returnMap.get("lectureName"));
		model.addAttribute("list", returnMap.get("list"));
		model.addAttribute("lastPage", returnMap.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		return "/lecture/getLectureReferenceList";
	}
	
	// 자료실 상세보기
	@GetMapping("/loginCheck/getReferenceOne")
	public String getReferenceOne(Model model
								,@RequestParam(name="referenceNo") int referenceNo) {
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.getReferenceOne referenceNo : "+referenceNo+CF.RS);
		
		// 실행
		Map<String,Object> map = new HashMap<String,Object>();
		map = lectureService.getReferenceOne(referenceNo);
		log.debug(CF.HJI+"LectureController.getReferenceOne map : "+map+CF.RS);
		
		// model로 리턴
		model.addAttribute("referenceFileList",map.get("referenceFilelist"));
		model.addAttribute("reference",map.get("reference"));
		
		return "/lecture/getReferenceOne";
	}
	
	// 자료실 입력
	@GetMapping("/loginCheck/addReference")
	public String addReferenceForm(Model model
									,@RequestParam(name="lectureName")String lectureName) {
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.getReferenceOne lectureName : "+lectureName+CF.RS);
		
		// model로 리턴
		model.addAttribute("lectureName",lectureName);
		
		return "/lecture/addReference";
	}
	
	@PostMapping("/loginCheck/addReference")
	public String addReferenceAction(HttpServletRequest request
			, ReferenceForm referenceForm) {
		String path = request.getServletContext().getRealPath("/file/referenceFile/");
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.addReferenceAction referenceForm : "+referenceForm+CF.RS);
		log.debug(CF.HJI+"LectureController.addReferenceAction path : "+path+CF.RS);
		
		// 파일리스트 꺼내기
		List<MultipartFile> referenceFileList = referenceForm.getReferenceFileList();
		
		// 파일 리스트가 있다면 추가
		if(referenceFileList != null && referenceFileList.get(0).getSize() > 0) {
			for(MultipartFile mf : referenceFileList) {
				log.debug("NoticeController.addNotice() fileName : " + mf.getOriginalFilename());
			}
			lectureService.addReference(referenceForm, path);
		}
		
		return "redirect:/loginCheck/getLectureReferenceList";
	}
	
	// 자료실 수정
	@GetMapping("/updateReference")
	public String updateReferenceForm(Model model
										,@RequestParam(name = "referenceNo") int referenceNo) {
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.updateAddReferenceForm referenceNo : "+referenceNo+CF.RS);
		
		// 실행
		Map<String, Object> map = lectureService.getReferenceOne(referenceNo);
		log.debug(CF.HJI+"LectureController.updateReferenceForm map : "+map+CF.RS);

		// model 로 보내기
		model.addAttribute("referenceFilelist", map.get("referenceFilelist"));
		model.addAttribute("reference", map.get("reference"));
		
		return "/lecture/updateReference";
	}
	
	@PostMapping("/updateReference")
	public String updateReferenceAction(Model model, HttpServletRequest request
											,@RequestParam(name = "referenceNo") int referenceNo
											,ReferenceForm referenceForm) {
		String path = request.getServletContext().getRealPath("/file/refeneceFile/");
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.updateReferenceAction referenceNo : "+referenceNo+CF.RS);
		log.debug(CF.HJI+"LectureController.updateReferenceAction referenceForm : "+referenceForm+CF.RS);
		log.debug(CF.HJI+"LectureController.updateReferenceAction path : "+path+CF.RS);
		
		// 추가사진까지 내용 수정
		Reference reference = new Reference();
		reference.setReferenceTitle(referenceForm.getReferenceTitle());
		reference.setLectureName(referenceForm.getLectureName());
		reference.setReferenceContent(referenceForm.getReferenceContent());
		List<MultipartFile> updateAddReferencefileList = referenceForm.getReferenceFileList();
		
		// 이미지 디버깅
		if(updateAddReferencefileList != null && updateAddReferencefileList.get(0).getSize() > 0) {
			for(MultipartFile mf : updateAddReferencefileList) {
				log.debug("NoticeController.addNotice() fileName : " + mf.getOriginalFilename());
			}
		}
		
		// 실행
		lectureService.updateAddReference(referenceForm, referenceNo, path, reference);
		
		return "redirect:/loginCheck/getReferenceOne?referenceNo="+referenceNo;
	}
	
	// 자료실 파일 삭제
	@PostMapping("/removeReferenceFile")
	public String removeReferenceFile(Model model,HttpServletRequest request
										,@RequestParam(name = "referenceNo") int referenceNo
										,@RequestParam(name = "referenceFileNo") int referenceFileNo) {
		String path = request.getServletContext().getRealPath("/file/refeneceFile/");
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureController.removeReferenceFile referenceNo : "+referenceNo+CF.RS);
		log.debug(CF.HJI+"LectureController.removeReferenceFile referenceFileNo : "+referenceFileNo+CF.RS);
		log.debug(CF.HJI+"LectureController.removeReferenceFile path : "+path+CF.RS);
		
		// 실행
		Map<String, Object> map = lectureService.removeReferenceFile(referenceFileNo, path, referenceNo);
		log.debug(CF.HJI+"LectureController.removeReferenceFile map : "+map+CF.RS);
		
		// model 로 보내기
		model.addAttribute("referenceFilelist", map.get("referenceFilelist"));
		model.addAttribute("reference", map.get("reference"));
		return "redirect:/loginCheck/updateAddReference?referenceNo="+referenceNo;
	}
	
	// 자료실 삭제
	@GetMapping("/removeReference")
	public String removeReference(HttpServletRequest request
									,@RequestParam(name="referenceNo")int referenceNo) {
		String path = request.getServletContext().getRealPath("/file/refeneceFile/");
		// 요청값 추출
		log.debug(CF.HJI+"LectureController.removeReference referenceNo : "+referenceNo+CF.RS);
		log.debug(CF.HJI+"LectureController.removeReference path : "+path+CF.RS);
		
		// 실행
		lectureService.removeReference(referenceNo, path);
		
		return "/lecture/getLectureReferenceList";
	}
}
