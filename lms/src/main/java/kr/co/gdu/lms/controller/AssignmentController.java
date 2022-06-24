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

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.AssignmentService;
import kr.co.gdu.lms.vo.AssignmentAddForm;
import kr.co.gdu.lms.vo.AssignmentExam;
import kr.co.gdu.lms.vo.AssignmentFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AssignmentController {
	@Autowired private AssignmentService assingmentservice;
	@GetMapping("/loginCheck/getAssignmentExam")
	public String getAssignment(HttpServletRequest request
								,Model model
								,@RequestParam (name="assignmentCurrentPage",defaultValue="1") int assignmentCurrentPage
								,@RequestParam (name="rowPerPage",defaultValue="10")int rowPerPage
								,@RequestParam (name="lecturName",defaultValue="자바") String lectureName) {
		HttpSession session = request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionId");
		int level = (int)session.getAttribute("sessionLv");
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lectureName", lectureName);
		paramMap.put("assignmentCurrentPage", assignmentCurrentPage);
		paramMap.put("rowPerPage", rowPerPage);

		Map<String,Object> returnMap = assingmentservice.getAssignmentExam(paramMap);
		List<AssignmentExam> assignmentExamList = (List<AssignmentExam>)returnMap.get("assignmentExamList");
		int lastPage = (int)returnMap.get("lastPage");
		
		model.addAttribute("lectureName",lectureName);
		model.addAttribute("assignmentExamList",assignmentExamList);
		model.addAttribute("lastPage",lastPage);
		model.addAttribute("assignmentCurrentPage",assignmentCurrentPage);
		model.addAttribute("level",level);
		
		
		return "/assignment/assignmentExam";
	}
	
	@PostMapping("/loginCheck/getAssignmentExam")
	public String addAssignmentExam(HttpServletRequest request
									,Model model) {
		HttpSession session = request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionId");
		int level = (int)session.getAttribute("sessionLv");
		

		return "/assignment/addAssignmentExam";
			
	}
	@PostMapping("/loginCheck/addAssignment")
	public String addAssignmentExam(Model model
									,HttpServletRequest request
									,AssignmentAddForm assignmentAddForm) {
		
		//아이디 세션/레벨 받아오기
		HttpSession session = request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionId");
		int level = (int)session.getAttribute("sessionLv");
	
		//아이디 세션 값 디버깅
		log.debug(CF.GMC+"AssignmentController.addAssignmentExam loginId:"+sessionMemberId + CF.GMC);
		//경로 지정
		String path = request.getServletContext().getRealPath("/file/assignmentFile/");
		
		List<MultipartFile> assignmentFileList = assignmentAddForm.getAssignmentFileList();	
		log.debug(CF.GMC+"AssignmentController.addAssignmentExam assignmentFileList:"+assignmentFileList + CF.GMC);
		if(assignmentFileList!=null && assignmentFileList.get(0).getSize() > 0) { // 하나 이상의 파일이 업로드 되면
			for(MultipartFile mf : assignmentFileList) {
				log.debug(CF.GMC+"addAssignmentExam.assignmentFileList name : "+mf.getOriginalFilename());
			}
		}
		assignmentAddForm.setLoginId(sessionMemberId);
		
		
		log.debug(CF.GMC+"AssignmentController.addAssignment path : "+path + CF.RS);
		log.debug(CF.GMC+"AssignmentController.addAssignment assignmentexam : "+assignmentAddForm + CF.RS);
		int row;
		row = assingmentservice.addAssignment(assignmentAddForm, path);
		log.debug("assingmentController.row : ", row);
	
		model.addAttribute("level",level);
		return "redirect:/loginCheck/getAssignmentExam";
	}
	@GetMapping("/loginCheck/getAssignmentOne")
	public String getAssignmentOne(Model model
									,HttpServletRequest request
									,@RequestParam (name="assignmentExamNo") int assignmentExamNo) {
		log.debug(CF.GMC+"AssignmentController.getAssignmentOne assignmentExamNo : "+assignmentExamNo + CF.RS);
		HttpSession session = request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionId");
		int level = (int)session.getAttribute("sessionLv");
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginId", sessionMemberId);
		paramMap.put("assignmentExamNo", assignmentExamNo);
		Map<String,Object> returnMap = assingmentservice.getAssignmentOne(paramMap);
		List<AssignmentFile> fileList = (List<AssignmentFile>)returnMap.get("assignmentListFile");
		for(AssignmentFile f :fileList) {
			log.debug(CF.GMC+f.getAssignmentExamNo()+CF.RS);
		}

		
		model.addAttribute("assignmentList", (List<AssignmentExam>)returnMap.get("assignmentList"));
		model.addAttribute("fileList",fileList);
		return "/assignment/assignmentOne";
	}
	@GetMapping("/loginCheck/modifiyAssignment")
	public String modifyAssignment() {
		return "modifyAssingment";
	}
	
	
	@GetMapping("/loginCheck/saveImage")
	public String uploadSignFile() {
		return "uploadSign";
	}
	
	@PostMapping("/loginCheck/saveImage")
	public String uploadSigmFile(Model model
								,@RequestParam (name="ImageURL") String ImageURL) {
		log.debug(CF.GMC+"saveImage.ImageURL"+ImageURL+CF.RS);
		model.addAttribute("ImageURL",ImageURL);
		return "redirect:/loginCheck/getAssignmentExam";
	}




}


