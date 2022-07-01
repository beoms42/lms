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
	@Autowired
	private AssignmentService assingmentservice;

	@GetMapping("/loginCheck/getAssignmentExam")
	// 과제 리스트 출력
	public String getAssignment(HttpServletRequest request, Model model,
			@RequestParam(name = "assignmentCurrentPage", defaultValue = "1") int assignmentCurrentPage,
			@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");

		int edcuationNo = assingmentservice.getEducationNo(sessionMemberId);
		String lectureName = assingmentservice.getLectureName(sessionMemberId);
		log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + lectureName + CF.RS);
		if (request.getParameter("lectureName") != null) {
			log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + lectureName + CF.RS);
			lectureName = (String) request.getParameter("lectureName");
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("assignmentCurrentPage", assignmentCurrentPage);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("lectureName", lectureName);

		Map<String, Object> returnMap = assingmentservice.getAssignmentExam(paramMap);
		List<AssignmentExam> assignmentExamList = (List<AssignmentExam>) returnMap.get("assignmentExamList");
		int lastPage = (int) returnMap.get("lastPage");

		List<Lecture> lectureNameList = assingmentservice.getLectureNameList();

		model.addAttribute("lectureNameList", lectureNameList);
		model.addAttribute("lectureName", lectureName);
		model.addAttribute("assignmentExamList", assignmentExamList);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("assignmentCurrentPage", assignmentCurrentPage);
		model.addAttribute("level", level);

		return "/assignment/assignmentExam";
	}

	// 과제 제출 페이지
	@PostMapping("/loginCheck/getAssignmentExam")
	public String addAssignmentExam(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		String lectureName = assingmentservice.getLectureName(sessionMemberId);
		if (request.getParameter("lectureName") != null) {
			log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + lectureName + CF.RS);
			lectureName = (String) request.getParameter("lectureName");
		}
		model.addAttribute("lectureName", lectureName);
		return "/assignment/addAssignmentExam";

	}

	// 과제 입력
	@PostMapping("/loginCheck/addAssignment")
	public String addAssignmentExam(Model model, HttpServletRequest request, AssignmentAddForm assignmentAddForm,
			@RequestParam(name = "lectureName") String lectureName) {

		// 아이디 세션/레벨 받아오기
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");

		int edcuationNo = assingmentservice.getEducationNo(sessionMemberId);
		assignmentAddForm.setEducationNo(edcuationNo);
		// 아이디 세션 값 디버깅
		log.debug(CF.GMC + "AssignmentController.addAssignmentExam loginId:" + sessionMemberId + CF.GMC);
		// 경로 지정
		String path = request.getServletContext().getRealPath("/file/assignmentFile/");

		List<MultipartFile> assignmentFileList = assignmentAddForm.getAssignmentFileList();
		log.debug(CF.GMC + "AssignmentController.addAssignmentExam assignmentFileList:" + assignmentFileList + CF.GMC);
		if (assignmentFileList != null && assignmentFileList.get(0).getSize() > 0) { // 하나 이상의 파일이 업로드 되면
			for (MultipartFile mf : assignmentFileList) {
				log.debug(CF.GMC + "addAssignmentExam.assignmentFileList name : " + mf.getOriginalFilename());
			}
		}
		assignmentAddForm.setLoginId(sessionMemberId);

		log.debug(CF.GMC + "AssignmentController.addAssignment path : " + path + CF.RS);
		log.debug(CF.GMC + "AssignmentController.addAssignment assignmentexam : " + assignmentAddForm + CF.RS);
		int row;
		row = assingmentservice.addAssignment(assignmentAddForm, path);
		log.debug("assingmentController.row : ", row);

		model.addAttribute("lectureName", lectureName);
		model.addAttribute("level", level);
		return "redirect:/loginCheck/getAssignmentExam";
	}

	// 과제 제출 페이지
	@GetMapping("/loginCheck/getAssignmentOne")
	public String getAssignmentOne(Model model, HttpServletRequest request,
			@RequestParam(name = "assignmentExamNo") int assignmentExamNo,
			@RequestParam(name = "lectureName", defaultValue = "자바") String lectureName) {
		log.debug(CF.GMC + "AssignmentController.getAssignmentOne assignmentExamNo : " + assignmentExamNo + CF.RS);
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		// 과목명 받아오는 부분
		if (level < 3) {
			lectureName = assingmentservice.getLectureName(sessionMemberId);
		}
		int edcuationNo = assingmentservice.getEducationNo(sessionMemberId);
		if (request.getParameter("lectureName") != null) {
			log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + lectureName + CF.RS);
			lectureName = (String) request.getParameter("lectureName");
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lectureName", lectureName);
		paramMap.put("educationNo", edcuationNo);
		paramMap.put("loginId", sessionMemberId);
		paramMap.put("assignmentExamNo", assignmentExamNo);

		Map<String, Object> returnMap = assingmentservice.getAssignmentOne(paramMap);

		List<AssignmentFile> fileList = (List<AssignmentFile>) returnMap.get("assignmentListFile");
		for (AssignmentFile f : fileList) {
			log.debug(CF.GMC + f.getAssignmentExamNo() + CF.RS);
		}
		Map<String, Object> returnMap2 = assingmentservice.getAssignmentSubmit(paramMap);

		model.addAttribute("lectureName", lectureName);
		model.addAttribute("assignmentList", (List<AssignmentExam>) returnMap.get("assignmentList"));
		model.addAttribute("assignmentSubmitTeacher",
				(List<AssignmentSubmit>) returnMap2.get("assignmentSubmitTeacher"));
		model.addAttribute("fileList", fileList);
		model.addAttribute("assignmentSubmit", (List<AssignmentSubmit>) returnMap2.get("assignmentSubmit"));
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
		log.debug(CF.GMC + "AssignmentController.assignmentSubmit assignmentSubmitForm : " + assignmentSubmitForm
				+ CF.RS);
		assingmentservice.addAssignmentSubmitFile(assignmentSubmitForm, path, assignmentExamNo);

		model.addAttribute("assignmentSubmitContent", assignmentSubmitContent);
		model.addAttribute("level", level);
		model.addAttribute("assignmentExamNo", assignmentExamNo);
		return "submitSign";
	}

	// 과제 제출->
	@GetMapping("/loginCheck/addAssignmentSubmit")
	public String assignmentSubmit(Model model, HttpServletRequest request,
			@RequestParam(name = "ImageURL") String ImageURL,
			@RequestParam(name = "assignmentCurrentPage", defaultValue = "1") int assignmentCurrentPage,
			@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage,
			@RequestParam(name = "assignmentSubmitContent") String assignmentSubmitContent,
			@RequestParam(name = "assignmentExamNo") int assignmentExamNo) {
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");

		// 학생 과제 입력
		int educationNo = assingmentservice.getEducationNo(sessionMemberId);
		AssignmentSubmit assignmentSubmit = new AssignmentSubmit();
		assignmentSubmit.setAssignmentSubmitContent(assignmentSubmitContent);
		assignmentSubmit.setAssignmentExamNo(assignmentExamNo);
		assignmentSubmit.setAssignmentSignfileURL(ImageURL);
		assignmentSubmit.setEducationNo(educationNo);
		log.debug(CF.GMC
				+ "AssignmentController.assignmentSubmit assignmentSubmit.assignmentSubmit.getAssignmentSubmitContent() : "
				+ assignmentSubmit.getAssignmentSubmitContent() + CF.RS);
		log.debug(CF.GMC
				+ "AssignmentController.assignmentSubmit assignmentSubmit.setAssignmentExamNo(assignmentExamNo) : "
				+ assignmentSubmit.getAssignmentExamNo() + CF.RS);
		log.debug(CF.GMC
				+ "AssignmentController.assignmentSubmit assignmentSubmit.setAssignmentExamNo(assignmentExamNo) : "
				+ assignmentSubmit.getAssignmentSignfileURL() + CF.RS);
		log.debug(CF.GMC
				+ "AssignmentController.assignmentSubmit assignmentSubmit.setAssignmentExamNo(assignmentExamNo) : "
				+ assignmentSubmit.getEducationNo() + CF.RS);

		assingmentservice.addAssignmentSubmit(assignmentSubmit);

		int edcuationNo = assingmentservice.getEducationNo(sessionMemberId);
		String lectureName = assingmentservice.getLectureName(sessionMemberId);
		if (request.getParameter("lectureName") != null) {
			log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + lectureName + CF.RS);
			lectureName = (String) request.getParameter("lectureName");
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("assignmentCurrentPage", assignmentCurrentPage);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("lectureName", lectureName);

		Map<String, Object> returnMap = assingmentservice.getAssignmentExam(paramMap);
		List<AssignmentExam> assignmentExamList = (List<AssignmentExam>) returnMap.get("assignmentExamList");
		int lastPage = (int) returnMap.get("lastPage");

		log.debug(CF.GMC + "assignmentSubmit.ImageURL" + ImageURL + CF.RS);

		model.addAttribute("lectureName", lectureName);
		model.addAttribute("assignmentExamList", assignmentExamList);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("assignmentCurrentPage", assignmentCurrentPage);
		model.addAttribute("level", level);
		return "/assignment/assignmentExam";
	}

	// 점수 입력
	@GetMapping("/loginCheck/getScore")
	public String getScore(HttpServletRequest request, Model model,
			@RequestParam(name = "assignmentSubmitScore") int assignmentSubmitScore,
			@RequestParam(name = "educationNo") int educationNo,
			@RequestParam(name = "assignmentExamNo") int assignmentExamNo,
			@RequestParam(name = "lectureName") String lectureName, RedirectAttributes redirect) {
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("assignmentExamNo", assignmentExamNo);
		paramMap.put("educationNo", educationNo);
		paramMap.put("assignmentSubmitScore", assignmentSubmitScore);
		assingmentservice.modifyScore(paramMap);
		redirect.addAttribute("lectureName", lectureName);
		redirect.addAttribute("assignmentExamNo", assignmentExamNo);
		return "redirect:/loginCheck/getAssignmentOne";
	}

	// 수정 폼
	@GetMapping("/loginCheck/modifyAssignment")
	public String modifyAssignment(Model model, HttpServletRequest request,
			@RequestParam(name = "assignmentExamNo") int assignmentExamNo) {
		log.debug(CF.GMC + "AssignmentController.getAssignmentOne assignmentExamNo : " + assignmentExamNo + CF.RS);
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		String lectureName = assingmentservice.getLectureName(sessionMemberId);
		if (request.getParameter("lectureName") != null) {
			log.debug(CF.GMC + "AssignmentController.getAssignment lectureName" + lectureName + CF.RS);
			lectureName = (String) request.getParameter("lectureName");
		}

		int edcuationNo = assingmentservice.getEducationNo(sessionMemberId);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginId", sessionMemberId);
		paramMap.put("assignmentExamNo", assignmentExamNo);
		paramMap.put("educationNo", edcuationNo);
		Map<String, Object> returnMap = assingmentservice.getAssignmentOne(paramMap);
		Map<String, Object> returnMap2 = assingmentservice.getAssignmentSubmit(paramMap);

		model.addAttribute("lectureName", lectureName);
		model.addAttribute("assignmentList", (List<AssignmentExam>) returnMap.get("assignmentList"));
		model.addAttribute("assignmentSubmit", (List<AssignmentSubmit>) returnMap2.get("assignmentSubmit"));
		model.addAttribute("assignmentExamNo", assignmentExamNo);
		model.addAttribute("level", level);
		return "/assignment/modifiyAssignment";
	}

	// 수정 액션
	@PostMapping("/loginCheck/modifyAssignment")
	public String modifyAssignment(Model model, HttpServletRequest request,
			@RequestParam(name = "assignmentExamNo") int assignmentExamNo, AssignmentExam assignmentExam,
			@RequestParam(name = "lectureName") String lectureName) {
		assignmentExam.setAssignmentExamNo(assignmentExamNo);
		assingmentservice.modifiyAssignmentExam(assignmentExam);
		log.debug(CF.GMC + "AssignmentController.getAssignmentOne assignmentExamNo : " + assignmentExamNo + CF.RS);
		HttpSession session = request.getSession();
		String sessionMemberId = (String) session.getAttribute("sessionId");
		int level = (int) session.getAttribute("sessionLv");
		int edcuationNo = assingmentservice.getEducationNo(sessionMemberId);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("educationNo", edcuationNo);

		log.debug(CF.GMC + "AssignmentController.getAssignmentmodifty lectureName : " + lectureName + CF.RS);
		paramMap.put("loginId", sessionMemberId);
		paramMap.put("assignmentExamNo", assignmentExamNo);
		paramMap.put("lectureName", lectureName);
		Map<String, Object> returnMap = assingmentservice.getAssignmentOne(paramMap);
		List<AssignmentFile> fileList = (List<AssignmentFile>) returnMap.get("assignmentListFile");
		for (AssignmentFile f : fileList) {
			log.debug(CF.GMC + f.getAssignmentExamNo() + CF.RS);
		}
		Map<String, Object> returnMap2 = assingmentservice.getAssignmentSubmit(paramMap);

		model.addAttribute("assignmentSubmitTeacher",
				(List<AssignmentSubmit>) returnMap2.get("assignmentSubmitTeacher"));
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
		assingmentservice.deleteAssignment(assignmentExamNo);
		return "redirect:/loginCheck/getAssignmentExam";
	}

}
