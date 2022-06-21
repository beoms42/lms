package kr.co.gdu.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.AssignmentService;
import kr.co.gdu.lms.vo.AssignmentExam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AssignmentController {
	@Autowired private AssignmentService assingmentservice;
	@GetMapping("/loginCheck/getAssignmentExam")
	public String getAssignment(Model model
								,@RequestParam (name="assignmentCurrentPage",defaultValue="1") int assignmentCurrentPage
								,@RequestParam (name="rowPerPage",defaultValue="10")int rowPerPage
								,@RequestParam (name="lecturName",defaultValue="자바") String lectureName) {
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
		
		
		
		return "/assignment/assignmentExam";
	}
	
	@PostMapping("/getAssignmentExam")
	public String addAssignmentExam(HttpServletRequest request
									,Model model
									,@RequestParam (name="assignmentExamNo") int assignmentExamNo) {
		HttpSession session = request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionId");
		int level = (int)session.getAttribute("sessionLv");
		
		log.debug(CF.GMC+"AssignmentController.addAssignmentExam assignmentExamNo : "+assignmentExamNo + CF.RS);
		model.addAttribute("assignmentExamNo",assignmentExamNo);
		model.addAttribute("level",level);
		return "/assignment/addAssignmentExam";
			
	}
	@PostMapping("/addAssignment")
	public String addAssignmentExam(HttpServletRequest request
									,AssignmentExam assignmentexam) {
		
		HttpSession session = request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionId");
	
		log.debug(CF.GMC+"AssignmentController.addAssignmentExam loginId:"+sessionMemberId + CF.GMC);
		
		assignmentexam.setLoginId(sessionMemberId);
		String path = request.getServletContext().getRealPath("/assignment/uploadAssignMent/");
		
		log.debug(CF.GMC+"AssignmentController.addAssignment path : "+path + CF.RS);
		log.debug(CF.GMC+"AssignmentController.addAssignment assignmentexam : "+assignmentexam + CF.RS);
		if( assignmentexam.getAssignmentFileList()!=null &&  assignmentexam.getAssignmentFileList().get(0).getSize() > 0) { // 하나 이상의 파일이 업로드 되면
			for(MultipartFile mf :  assignmentexam.getAssignmentFileList()) {
				log.debug(CF.GMC+"AssignmentController.addAssignment assignmentexam : "+mf.getOriginalFilename() + CF.RS);
			}
		}
		int row = assingmentservice.addAssignment(assignmentexam, path);
		log.debug("assingmentController.row : ", row);
		
		return "redirect:/loginCheck/getAssignmentExam";
	}
}


