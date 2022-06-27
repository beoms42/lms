package kr.co.gdu.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	public String getStudentList(Model model) {
		log.debug(CF.PSH + "LectureController.getStudentList :" + CF.RS);
		List<Student> studentlist = memberService.getStudentList();
		model.addAttribute("studentlist", studentlist);
		
		//이미 배정된 학생은 빼야함
		List<String> learningStudentList = youngInService.selectLearningStudentName();
		
		//근데 빼낼때 List의 높은 Index에서 낮은 index방향으로 순회해야 에러가 안남 관련페이지 : https://codechacha.com/ko/java-remove-from-list-while-iterating/
		for(String s : learningStudentList) {
			for(Student student : studentlist) {
				if(s.equals(student.getStudentName())) {
					studentlist.remove(student);
				}
			}
		}
		log.debug(CF.JYI+"LectureService.addStudentInLectureForm.get studentlist : "+studentlist+CF.RS);
		log.debug(CF.JYI+"LectureService.addStudentInLectureForm.get learningStudentList : "+learningStudentList+CF.RS);
		return "lecture/addStudentInLectureForm";
	}
}
