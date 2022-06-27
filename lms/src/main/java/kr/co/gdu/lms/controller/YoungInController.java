package kr.co.gdu.lms.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.LectureSerivce;
import kr.co.gdu.lms.service.MemberService;
import kr.co.gdu.lms.service.YoungInService;
import kr.co.gdu.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class YoungInController {
	@Autowired private YoungInService youngInService;
	@Autowired private LectureSerivce lectureService;
	@Autowired private MemberService memberService;
	
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
	
}
