package kr.co.gdu.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.AssignmentService;
import kr.co.gdu.lms.vo.AssignmentAddForm;
import kr.co.gdu.lms.vo.AssignmentExam;
import kr.co.gdu.lms.vo.AssignmentFile;
import kr.co.gdu.lms.vo.AssignmentSubmit;
import kr.co.gdu.lms.vo.AssignmentSubmitForm;
import kr.co.gdu.lms.vo.Lecture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AssignmentController {
	@Autowired private AssignmentService assignmentservice;

	@GetMapping("/loginCheck/getAssignmentExam")
	// 과제 리스트 출력
	public String getAssignment(HttpServletRequest request
								, Model model) {
		
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		
		//educationNo,lectureName 받아오기
		Map<String,Object> educationMap = assignmentservice.getEducation(sessionMemberId);
		log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + educationMap.get("lectureName") + CF.RS);
		String lectureName = (String)educationMap.get("lectureName");
		if (request.getParameter("lectureName") != null) { // 관리자 및 매니저가 접속해서 임의로 값을 받아올경우
			log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + lectureName + CF.RS);
			lectureName = (String) request.getParameter("lectureName");
		}

		//과제 리스트 받아오기
		List<AssignmentExam> assignmentExamList = assignmentservice.getAssignmentExam(lectureName);
		//과목 리스트 받아오기
		List<Lecture> lectureNameList = assignmentservice.getLectureNameList();
		
		model.addAttribute("level",level);
		model.addAttribute("loginId",sessionMemberId);
		model.addAttribute("lectureNameList", lectureNameList);
		model.addAttribute("lectureName", lectureName);
		model.addAttribute("assignmentExamList", assignmentExamList);

		return "/assignment/assignmentExam";
	}

	// 과제 제출 페이지
	@PostMapping("/loginCheck/getAssignmentExam")
	public String addAssignmentExam(HttpServletRequest request
									, Model model) {
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		
		//과목 값 받아오기
		Map<String,Object> paramMap = assignmentservice.getEducation(sessionMemberId);
		String lectureName = (String)paramMap.get("lectureName");
		if (request.getParameter("lectureName") != null) {
			log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + lectureName + CF.RS);
			lectureName = (String) request.getParameter("lectureName");
		}
		
		model.addAttribute("level",level);
		model.addAttribute("loginId",sessionMemberId);
		model.addAttribute("lectureName", lectureName);
		return "/assignment/addAssignmentExam";

	}

	// 과제 입력
	@PostMapping("/loginCheck/addAssignment")
	public String addAssignmentExam(Model model
									, HttpServletRequest request
									, AssignmentAddForm assignmentAddForm
									, @RequestParam(name = "lectureName") String lectureName) {

		// 아이디 세션/레벨 받아오기
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		log.debug(CF.GMC + "AssignmentController.addAssignmentExam loginId:" + sessionMemberId + CF.GMC);
		int level = (int) session.getAttribute("sessionLv");

		//과목 값 받아오기
		Map<String,Object> paramMap = assignmentservice.getEducation(sessionMemberId);
		int educationNo = (int)paramMap.get("educationNo");
		assignmentAddForm.setEducationNo(educationNo);
		
		// 경로 지정
		String path = request.getServletContext().getRealPath("/file/assignmentFile/");

		List<MultipartFile> assignmentFileList = assignmentAddForm.getAssignmentFileList();
		if (assignmentFileList != null && assignmentFileList.get(0).getSize() > 0) { // 하나 이상의 파일이 업로드 되면
			for (MultipartFile mf : assignmentFileList) {
				log.debug(CF.GMC + "addAssignmentExam.assignmentFileList name : " + mf.getOriginalFilename());
			}
		}
		assignmentAddForm.setLoginId(sessionMemberId);

		log.debug(CF.GMC + "AssignmentController.addAssignment path : " + path + CF.RS);
		log.debug(CF.GMC + "AssignmentController.addAssignment assignmentexam : " + assignmentAddForm + CF.RS);
		int row;
		row = assignmentservice.addAssignment(assignmentAddForm, path);
		log.debug("assingmentController.row : ", row);

		
		model.addAttribute("loginId",sessionMemberId);
		model.addAttribute("lectureName", lectureName);
		model.addAttribute("level", level);
		return "redirect:/loginCheck/getAssignmentExam";
	}

	// 과제 제출 페이지
	@GetMapping("/loginCheck/getAssignmentOne")
	public String getAssignmentOne(Model model
								   , HttpServletRequest request
								   , @RequestParam(name = "assignmentExamNo") int assignmentExamNo
								   , @RequestParam(name = "lectureName", defaultValue = "자바") String lectureName) {
		log.debug(CF.GMC + "AssignmentController.getAssignmentOne assignmentExamNo : " + assignmentExamNo + CF.RS);
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		// 과목명 받아오는 부분
		Map<String,Object> educationMap = assignmentservice.getEducation(sessionMemberId);
		
		//학생 or 강사 일 경우 쿼리로 lectureName 찾아오기
		if (level < 3) {
			lectureName = (String)educationMap.get("lectureName");
		}
		//관리자의 경우 select창에서 value 값 받아오기
		if (request.getParameter("lectureName") != null) {
			log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + lectureName + CF.RS);
			lectureName = (String) request.getParameter("lectureName");
		}
		int educationNo = (int)educationMap.get("educationNo");
	
		//매개변수로 사용할 Map 세팅
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lectureName", lectureName);
		paramMap.put("educationNo", educationNo);
		paramMap.put("loginId", sessionMemberId);
		paramMap.put("assignmentExamNo", assignmentExamNo);
		Map<String, Object> returnMap = assignmentservice.getAssignmentOne(paramMap);
		
		//파일 리스트
		
		for (AssignmentFile f :  (List<AssignmentFile>) returnMap.get("assignmentListFile")) {
			log.debug(CF.GMC + f.getAssignmentExamNo() + CF.RS);
		}
		//상세보기에 필요한 과제 제출 리스트
		Map<String, Object> submitMap = assignmentservice.getAssignmentSubmit(paramMap);

		
		model.addAttribute("lectureName", lectureName);
		model.addAttribute("assignmentList", (List<AssignmentExam>) returnMap.get("assignmentList"));
		model.addAttribute("assignmentSubmitTeacher",(List<AssignmentSubmit>) submitMap.get("assignmentSubmitTeacher"));
		model.addAttribute("fileList", (List<AssignmentFile>) returnMap.get("assignmentListFile"));
		model.addAttribute("assignmentSubmit", (List<AssignmentSubmit>) submitMap.get("assignmentSubmit"));
		model.addAttribute("assignmentExamNo", assignmentExamNo);
		model.addAttribute("level", level);
		model.addAttribute("loginId", sessionMemberId);
		return "/assignment/assignmentOne";
	}

	@PostMapping("/loginCheck/getAssignmentOne")
	public String addAssignmentSubmit(AssignmentSubmitForm assignmentSubmitForm, HttpServletRequest request,
			Model model, @RequestParam(name = "assignmentExamNo") int assignmentExamNo) {
		// 학생 파일 제출
		// 아이디 세션/레벨 받아오기
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");

		// 아이디 세션 값 디버깅
		log.debug(CF.GMC + "AssignmentController.addAssignmentExam loginId:" + sessionMemberId + CF.GMC);
		assignmentSubmitForm.setLoginId(sessionMemberId);

		// 경로 지정
		String path = request.getServletContext().getRealPath("/file/assignmentSubmitFile/");

		List<MultipartFile> assignmentFileList = assignmentSubmitForm.getAssignmentSubmitFileList();
		assignmentSubmitForm.setLoginId(sessionMemberId);
		// 과제 제출 내용
		String assignmentSubmitContent = assignmentSubmitForm.getAssignmentSubmitContent();

		log.debug(CF.GMC + "AssignmentController.addAssignmentExam assignmentFileList:" + assignmentFileList + CF.GMC);
		if (assignmentFileList != null && assignmentFileList.get(0).getSize() > 0) { // 하나 이상의 파일이 업로드 되면
			for (MultipartFile mf : assignmentFileList) {
				log.debug(CF.GMC + "addAssignmentExam.assignmentFileList name : " + mf.getOriginalFilename());
			}
		}

		log.debug(CF.GMC + "AssignmentController.assignmentSubmit path : " + path + CF.RS);
		log.debug(CF.GMC + "AssignmentController.assignmentSubmit assignmentSubmitForm : " + assignmentSubmitForm + CF.RS);
		assignmentservice.addAssignmentSubmitFile(assignmentSubmitForm, path, assignmentExamNo);

		model.addAttribute("assignmentSubmitContent", assignmentSubmitContent);
		model.addAttribute("level", level);
		model.addAttribute("assignmentExamNo", assignmentExamNo);
		return "assignment/submitSign";
	}

	// 과제 제출->
	@GetMapping("/loginCheck/addAssignmentSubmit")
	public String assignmentSubmit(Model model
								   , HttpServletRequest request
								   , @RequestParam(name = "ImageURL") String ImageURL
								   , @RequestParam(name = "assignmentSubmitContent") String assignmentSubmitContent
								   , @RequestParam(name = "assignmentExamNo") int assignmentExamNo) {
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");

		// 학생 과제 입력
		//과목 번호, 과목 명 받아오기
		Map<String,Object> educationMap = assignmentservice.getEducation(sessionMemberId);
		int educationNo = (int)educationMap.get("educationNo");
		String lectureName = (String)educationMap.get("lectureName");
		if (request.getParameter("lectureName") != null) {
			log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + lectureName + CF.RS);
			lectureName = (String) request.getParameter("lectureName");
		}
		//과제 제출 테이블 세팅
		AssignmentSubmit assignmentSubmit = new AssignmentSubmit();
		assignmentSubmit.setAssignmentSubmitContent(assignmentSubmitContent);
		assignmentSubmit.setAssignmentExamNo(assignmentExamNo);
		assignmentSubmit.setAssignmentSignfileURL(ImageURL);
		assignmentSubmit.setEducationNo(educationNo);
		log.debug(CF.GMC + "AssignmentController.assignmentSubmit assignmentSubmit.assignmentSubmit.getAssignmentSubmitContent() : " + assignmentSubmit.getAssignmentSubmitContent() + CF.RS);
		log.debug(CF.GMC + "AssignmentController.assignmentSubmit assignmentSubmit.setAssignmentExamNo(assignmentExamNo) : " + assignmentSubmit.getAssignmentExamNo() + CF.RS);
		log.debug(CF.GMC + "AssignmentController.assignmentSubmit assignmentSubmit.setAssignmentExamNo(assignmentExamNo) : " + assignmentSubmit.getAssignmentSignfileURL() + CF.RS);
		log.debug(CF.GMC + "AssignmentController.assignmentSubmit assignmentSubmit.setAssignmentExamNo(assignmentExamNo) : " + assignmentSubmit.getEducationNo() + CF.RS);
		log.debug(CF.GMC + "AssignmentController.assignmentSubmit assignmentSubmit.setImageURL(ImageURL)" + ImageURL + CF.RS);
		//디버깅 후 과제 제출
		assignmentservice.addAssignmentSubmit(assignmentSubmit);
	
		//과제 리스트로 다시 돌려보냄
		List<AssignmentExam> assignmentExamList = assignmentservice.getAssignmentExam(lectureName);

		

		model.addAttribute("lectureName", lectureName);
		model.addAttribute("assignmentExamList", assignmentExamList);
		model.addAttribute("level", level);
		return "/assignment/assignmentExam";
	}

	// 점수 입력
	@GetMapping("/loginCheck/getScore")
	public String getScore(HttpServletRequest request
			 			   , Model model
						   , @RequestParam(name = "assignmentSubmitScore") int assignmentSubmitScore
						   , @RequestParam(name = "educationNo") int educationNo
						   , @RequestParam(name = "assignmentExamNo") int assignmentExamNo
						   , @RequestParam(name = "lectureName") String lectureName, RedirectAttributes redirect) {
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("assignmentExamNo", assignmentExamNo);
		paramMap.put("educationNo", educationNo);
		paramMap.put("assignmentSubmitScore", assignmentSubmitScore);
		assignmentservice.modifyScore(paramMap);
		redirect.addAttribute("lectureName", lectureName);
		redirect.addAttribute("assignmentExamNo", assignmentExamNo);
		return "redirect:/loginCheck/getAssignmentOne";
	}

	// 수정 폼
	@GetMapping("/loginCheck/modifyAssignment")
	public String modifyAssignment(Model model
								   ,HttpServletRequest request
								   ,@RequestParam(name = "assignmentExamNo") int assignmentExamNo) {
		log.debug(CF.GMC + "AssignmentController.getAssignmentOne assignmentExamNo : " + assignmentExamNo + CF.RS);
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		// 과목 값 받아오기
		Map<String,Object> educationMap = assignmentservice.getEducation(sessionMemberId);
		String lectureName = (String)educationMap.get("lectureName");
		int edcuationNo = (int)educationMap.get("educationNo");
		if (request.getParameter("lectureName") != null) {
			log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + lectureName + CF.RS);
			lectureName = (String) request.getParameter("lectureName");
		}

		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginId", sessionMemberId);
		paramMap.put("assignmentExamNo", assignmentExamNo);
		paramMap.put("educationNo", edcuationNo);
		// 학생 과제 제출 리스트, 과제 내용 받아오기
		Map<String, Object> assignmentExamMap = assignmentservice.getAssignmentOne(paramMap);
		Map<String, Object> assignmentSubmitMap = assignmentservice.getAssignmentSubmit(paramMap);

		model.addAttribute("lectureName", lectureName);
		model.addAttribute("assignmentList", (List<AssignmentExam>) assignmentExamMap.get("assignmentList"));
		model.addAttribute("assignmentSubmit", (List<AssignmentSubmit>) assignmentSubmitMap.get("assignmentSubmit"));
		model.addAttribute("assignmentExamNo", assignmentExamNo);
		model.addAttribute("level", level);
		return "/assignment/modifiyAssignment";
	}

	// 수정 액션
	@PostMapping("/loginCheck/modifyAssignment")
	public String modifyAssignment(Model model
								   , HttpServletRequest request
								   , @RequestParam(name = "assignmentExamNo") int assignmentExamNo
								   , @RequestParam(name = "lectureName") String lectureName
								   , AssignmentExam assignmentExam) {
		
		assignmentExam.setAssignmentExamNo(assignmentExamNo);
		assignmentservice.modifiyAssignmentExam(assignmentExam);
		log.debug(CF.GMC + "AssignmentController.getAssignmentOne assignmentExamNo : " + assignmentExamNo + CF.RS);
		
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		//과목 번호
		Map<String,Object> educationMap = assignmentservice.getEducation(sessionMemberId);
		int educationNo = (int)educationMap.get("educationNo");
		log.debug(CF.GMC + "AssignmentController.getAssignmentmodifty lectureName : " + lectureName + CF.RS);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("educationNo", educationNo);
		paramMap.put("loginId", sessionMemberId);
		paramMap.put("assignmentExamNo", assignmentExamNo);
		paramMap.put("lectureName", lectureName);
		Map<String, Object> returnMap = assignmentservice.getAssignmentOne(paramMap);
		List<AssignmentFile> fileList = (List<AssignmentFile>) returnMap.get("assignmentListFile");
		for (AssignmentFile f : fileList) {
			log.debug(CF.GMC + f.getAssignmentExamNo() + CF.RS);
		}
		Map<String, Object> returnMap2 = assignmentservice.getAssignmentSubmit(paramMap);

		model.addAttribute("assignmentSubmitTeacher", (List<AssignmentSubmit>) returnMap2.get("assignmentSubmitTeacher"));
		model.addAttribute("lectureName", lectureName);
		model.addAttribute("assignmentList", (List<AssignmentExam>) returnMap.get("assignmentList"));
		model.addAttribute("fileList", fileList);
		model.addAttribute("assignmentSubmit", (List<AssignmentSubmit>) returnMap2.get("assignmentSubmit"));
		model.addAttribute("assignmentExamNo", assignmentExamNo);
		model.addAttribute("level", level);
		return "/assignment/assignmentOne";
	}

	@GetMapping("/loginCheck/deleteAssignment")
	public String deleteAssignment(@RequestParam(name = "assignmentExamNo") int assignmentExamNo) {
		assignmentservice.deleteAssignment(assignmentExamNo);
		return "redirect:/loginCheck/getAssignmentExam";
	}

}
