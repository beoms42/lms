package kr.co.gdu.lms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.MemberService;
import kr.co.gdu.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberRestController {
	@Autowired private MemberService memberService;
	
	@PostMapping("/modifyEmployment")
	public int modifyEmployment(List<Student> student) {
		log.debug(CF.LCH+"MemberRestController.modifyEmployment.get student : " + student + CF.RS);
		int row = 0;
		for(Student s : student) {
			row = memberService.modifyEmploymentByStudent(s);
		}
		
		if(row == 1) {
			log.debug(CF.LCH+"MemberRestController.modifyEmployment.get 업데이트 성공"+ CF.RS);
		} else {
			log.debug(CF.LCH+"MemberRestController.modifyEmployment.get 업데이트 성공"+ CF.RS);
		}
		
		return row;
	}
}
