package kr.co.gdu.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.MemberMapper;
import kr.co.gdu.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class MemberService {
	@Autowired private MemberMapper memberMapper;
	
	// 학생정보 상세보기
	public Student getStudentOne(String loginId) {
		log.debug(CF.GDH+"MemberService.getStudentOne loginId : "+loginId+CF.RS);
		Student student = memberMapper.selectStudentOne(loginId);
		log.debug(CF.GDH+"MemberService.getStudentOne student : "+student+CF.RS);
		return student;
	}
	
	/* 학생정보 수정폼
	public Student modifyStudent(String loginId) {
	}
	
	// 학생정보 삭제하기
	public Student deleteStudent() {
		
	}
	*/
}
