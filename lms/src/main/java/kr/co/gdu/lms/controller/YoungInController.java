package kr.co.gdu.lms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import kr.co.gdu.lms.service.MemberService;
import kr.co.gdu.lms.service.YoungInService;
import kr.co.gdu.lms.vo.CommunityForm;
import kr.co.gdu.lms.vo.Lecture;
import kr.co.gdu.lms.vo.Qna;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.SubjectTextbook;
import kr.co.gdu.lms.vo.Textbook;
import kr.co.gdu.lms.vo.TextbookRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class YoungInController {
	@Autowired private YoungInService youngInService;
	@Autowired private LectureSerivce lectureService;
	@Autowired private MemberService memberService;
	
	//----------------------------------------강의------------------------------------------
	
	// 학생목록
	@GetMapping("/loginCheck/addStudentInLectureForm")
	public String addStudentInLectureForm(Model model
										, @RequestParam(name = "lectureName") String lectureName) {
		
		List<Student> studentlist = memberService.getStudentList();

		//이미 배정된 학생은 빼야함
		List<String> learningStudentList = youngInService.selectLearningStudentName();
		
		
		Iterator<String> learnArr = learningStudentList.iterator();
		
		// List순회시 삭제할때 에러가 많이발생해서 무조건 이터레이터라는걸 써서 삭제를 해줘야함 참고 : https://ddolcat.tistory.com/924
		while(learnArr.hasNext()) {
			String Arr = learnArr.next();
			Iterator<Student> iterStu = studentlist.iterator();
			while(iterStu.hasNext()) {
				Student stu = iterStu.next();
				if(stu.getLoginId().equals(Arr)) {
					iterStu.remove();
				}
			}
		}
		
		model.addAttribute("lectureName", lectureName);
		model.addAttribute("studentlist", studentlist);
		log.debug(CF.JYI+"LectureService.addStudentInLectureForm.get studentlist : "+studentlist+CF.RS);
		log.debug(CF.JYI+"LectureService.addStudentInLectureForm.get learningStudentList : "+learningStudentList+CF.RS);
		return "lecture/addStudentInLectureForm";
	}
	
	// 학생 배정 액션
	@PostMapping("/loginCheck/addStudentInLectureAction")
	public String addStudentInLectureAction(Model model
				, @RequestParam(name = "loginIdList") List<String> loginIdList
				, @RequestParam(name = "lectureName") String lectureName) {
		
		youngInService.insertStudentInLecture(loginIdList, lectureName);
		log.debug(CF.JYI+"LectureService.addStudentInLectureAction.get loginIdList : "+loginIdList+CF.RS);
		
		return "lecture/manageLecture";
	}
	
	
	@GetMapping("/loginCheck/manageLectureOne")
	public String manageLectureOne(Model model
			, HttpSession session
			, @RequestParam(name = "lectureName") String lectureName) {
		//강의
		List<Lecture> lectList = lectureService.selectLectureList();
		model.addAttribute("lectList", lectList);
		
		//상세보기
		List<String> subList = lectureService.selectSubjectListByLectureName(lectureName);
		model.addAttribute("lectureName",lectureName);
		model.addAttribute("subList", subList);
		
		//강의 배정학생
		List<Student> studentList = youngInService.selectStudentListByLectureName(lectureName);
		model.addAttribute("studentList",studentList);
		
		//강의별 배정인원수 / 인원수 
		
		List<HashMap<String, Object>> realLectList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = null;
		
		// 키 : 렉처명 값: 학생수
		Map<String, Object> numberStudent = youngInService.selectStudentGroup();
		
		// 강의의 List<Vo>를 List<HashMap<k,v>>로 분해
		for(Lecture lect : lectList) {
			map = new HashMap<>();
			map.put("lectureName", lect.getLectureName());
			map.put("teacher", lect.getTeacher());
			map.put("manager", lect.getManager());
			map.put("lectureStartDate", lect.getLectureStartDate());
			map.put("lectureEndDate", lect.getLectureEndDate());
			map.put("lectureRoomName", lect.getLectureRoomName());
			map.put("lectureStudentCapacity", lect.getLectureStudentCapacity());
			map.put("createDate", lect.getLoginId());
			map.put("lectureActive", lect.getLectureActive());
			map.put("createDate", lect.getCreateDate());
			map.put("updateDate", lect.getUpdateDate());
			
			map.put("maxStudentInLecture", numberStudent.get(lect.getLectureName()));
			realLectList.add(map);
		}
		
		model.addAttribute("realLectList",realLectList);
		return "lecture/manageLectureOne";
	}
	
	// 교재등록 =  textbook insert
	// 과목등록 = textbook > subject에 귀속시킴
	// 두개를 한번에 만드는 controller제작 > 관리자만 가능
	// 영인 - get방식 subjectTextbookInsert Form
	@GetMapping("/loginCheck/subjectTextbookInsert")
	public String subjectTextbookInsert(Model model){
		
		Map<String, Object> getMap = youngInService.subjectTextbookInsertNeed();
		List<Textbook> bookList = (List<Textbook>) getMap.get("bookList");
		List<String> subjectList = (List<String>) getMap.get("subjectList");
		
		model.addAttribute("bookList", bookList);
		model.addAttribute("subjectList", subjectList);
		
		return "lecture/subjectTextbookInsert";
	}
	
	// 교재등록
	@PostMapping("/loginCheck/textbookInsert")
	public String textbookInsert(Textbook textbook){
		
		log.debug(CF.JYI+"YoungInController.textbookInsert.get textbook : "+textbook+CF.RS);
		youngInService.textbookInsert(textbook);
		
		
		return "redirect:/loginCheck/subjectTextbookInsert";
	}
	
	// 과목등록 = textbook > subject에 귀속시킴

	@PostMapping("/loginCheck/insertSubjectTextbook")
	public String insertSubjectTextbook(SubjectTextbook su){
		
		log.debug(CF.JYI+"YoungInController.insertSubjectTextbook.get SubjectTextbook : "+su+CF.RS);
		youngInService.insertSubjectTextbook(su);
		
		return "redirect:/loginCheck/subjectTextbookInsert";
	}
	
	//학생 - 책 수령 Get
	@GetMapping("/loginCheck/getBooks")
	public String getBooks(HttpSession session
							, Model model){
		
		String loginId = (String) session.getAttribute("sessionId"); // 학생 이름얻기
		log.debug(CF.JYI+"sessionId.sessionId.get sessionIdsessionIdsessionId : "+loginId+CF.RS);
		
		//이름으로 강의명 가져오기
		Map<String, Object> map = youngInService.selectRecordBook(loginId);
		
		if(map != null) {
			

		// 강의명 / 과목별 교재수령 / 강의에 귀속된 과목리스트
		String lectureName = (String) map.get("lectureName");
		List<HashMap<String, Object>> getBookList = (List<HashMap<String, Object>>) map.get("returnList");
		List<String> subjectName = (List<String>) map.get("subjectName");
		
		model.addAttribute("lectureName", lectureName);
		model.addAttribute("getBookList", getBookList);
		model.addAttribute("subjectName", subjectName);
		}
		
		return "lecture/getBooks";
	}
	
	// 학생 - 책 수령 싸인
	@GetMapping("/loginCheck/getBooksSign")
	public String getBooksSign(HttpSession session
				, Model model
				, @RequestParam(name = "subjectName") String subjectName){
	
	String loginId = (String) session.getAttribute("sessionId"); // 학생 이름얻기
	
	Map<String, Object> map = youngInService.getSignNeed(loginId, subjectName);
	 
	int eduNo = (int) map.get("eduNo");
	int lectureSubjectNo = (int) map.get("lecture_subject_no");
	
	model.addAttribute("eduNo", eduNo);
	model.addAttribute("lectureSubjectNo", lectureSubjectNo);
	
	return "lecture/getbooksSign";
	}
	
	//
	@PostMapping("/loginCheck/getBooksAction")
	public String getBooksAction(HttpSession session
			, Model model
			, TextbookRecord textbookRecord){

		log.debug(CF.JYI+"YoungInController.textbookRecord.get textbookRecord : "+textbookRecord+CF.RS);
		
		youngInService.getSignAction(textbookRecord);
	return "redirect:/loginCheck/getBooks";
	}
	//----------------------------------------커뮤니티------------------------------------------
	
	// 영인 - get방식 qnaListOne호출
	@GetMapping("/loginCheck/qnaListOne")
	public String qnaListOne(Model model
			, @RequestParam(name = "qnaNo") int qnaNo
			, HttpSession session) {
		
		Map<String, Object> map =  youngInService.selectQnaFileByQnaNo(qnaNo);
		
		//세션레벨
		int loginLv = (int)session.getAttribute("sessionLv");
		
		//사진 리스트
		List<String> fileList = (List<String>) map.get("fileList");
		
		//One 문의
		Qna qnaInquiry = (Qna) map.get("qnaInquiry");
		
		//One 답변
		Qna qnaAnswer = (Qna) map.get("qnaAnswer");
		log.debug(CF.JYI+"YoungInService.qnaListOne.get loginLv : "+loginLv+CF.RS);
		model.addAttribute("loginLv", loginLv);
		model.addAttribute("fileList", fileList);
		model.addAttribute("qnaInquiry", qnaInquiry);
		model.addAttribute("qnaAnswer", qnaAnswer);
		
		return "community/qnaListOne";
	}
	
	// 영인 - get방식 addQna호출
	@GetMapping("/loginCheck/addQna")
	public String addQna(Model model) {
		
		return "community/addQna";
	}
	
	// 영인 - post방식 addQna호출
	@PostMapping("/loginCheck/addQnaAction")
	public String addQnaAction(HttpServletRequest request
			, Model model
			, @RequestParam(name = "qnaFileList") List<MultipartFile> qnaFileList
			, Qna qna
			, HttpSession session
			) {
		
		if(qnaFileList.get(0).getSize() > 0) { // 하나 이상의 파일이 업로드 되면
	         for(MultipartFile mf : qnaFileList) {
	            log.debug(mf.getOriginalFilename());
	         }
	      }
		String path = request.getServletContext().getRealPath("/file/communityFile/");
		String loginId = (String) session.getAttribute("sessionId");
		
		youngInService.addQnaAction(path, qnaFileList, qna, loginId);
		
		return "redirect:/loginCheck/getQnaListByPage";
	}
	
	// 영인 - post방식 qnaListOne 답변
	@PostMapping("/loginCheck/qnaListOneAnswer")
	public String qnaListOneAnswer(HttpSession session
								, Model model
								, Qna qna){
		String loginId = (String) session.getAttribute("sessionId");
		qna.setLoginId(loginId);
		
		youngInService.insertQnaAnswer(qna);
		
		return "redirect:/loginCheck/qnaListOne?qnaNo="+qna.getQnaNo();
	}
	
	// 영인 - get방식 deleteByQnaNo 삭제
	@GetMapping("/loginCheck/deleteByQnaNo")
	public String deleteByQnaNo(HttpServletRequest request
								, @RequestParam(name = "qnaNo") int qnaNo
								, Qna qna){
		
		//삭제시 우선순위 : 물리(파일)삭제, db(qnaFile)날리기, 게시글(qna)날리기
		String path = request.getServletContext().getRealPath("/file/communityFile/");
		
		youngInService.deleteByQnaNo(qnaNo, path);
		
		return "redirect:/loginCheck/getQnaListByPage";
	}
	
	// ------------ 레스트컨트롤러--------------
	
	@GetMapping("/loginCheck/selectMilitaryStatus")
	public String selectMilitaryStatus() {
		
		return "stats/selectMilitaryStatus";
	}
}
