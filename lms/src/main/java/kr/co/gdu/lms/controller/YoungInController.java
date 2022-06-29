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
		Map<String, Object> numberStudent = new HashMap<String, Object>();
		numberStudent = youngInService.selectStudentGroup();
		
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
	
	//----------------------------------------커뮤니티------------------------------------------
	
	// 영인 - get방식 qnaListOne호출
	@GetMapping("/loginCheck/qnaListOne")
	public String qnaListOne(Model model
			, @RequestParam(name = "qnaNo") int qnaNo) {
		
		Map<String, Object> map =  youngInService.selectQnaFileByQnaNo(qnaNo);
		
		//사진 리스트
		List<String> fileList = (List<String>) map.get("fileList");
		
		//One 문의
		Qna qnaInquiry = (Qna) map.get("qnaInquiry");
		
		//One 답변
		Qna qnaAnswer = (Qna) map.get("qnaAnswer");
		
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
	
}
