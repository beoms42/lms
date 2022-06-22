package kr.co.gdu.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.ManagerMapper;
import kr.co.gdu.lms.mapper.MemberFileMapper;
import kr.co.gdu.lms.mapper.StudentMapper;
import kr.co.gdu.lms.mapper.TeacherMapper;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.MemberFile;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class MemberService {
	@Autowired private StudentMapper studentMapper;
	@Autowired private MemberFileMapper memberFileMapper;
	
	// 학생정보 상세보기
	public Map<String, Object> getStudentOne(String loginId) {
		// 로그인ID 디버깅
		log.debug(CF.GDH+"MemberService.getStudentOne loginId : "+loginId+CF.RS);
		
		// 학생정보 Mapper연결
		Student student = studentMapper.selectStudentOne(loginId);
		log.debug(CF.GDH+"MemberService.getStudentOne student : "+student+CF.RS);
		
		// 학생파일 Mapper연결
		List<MemberFile> memberFileList = memberFileMapper.selectMemberFileList(loginId);
		log.debug(CF.GDH+"MemberService.getStudentOne memberFileList : "+memberFileList+CF.RS);
		
		// 학생정보와 학생파일리스트 맵에 담기
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("student", student);
		returnMap.put("memberFileList", memberFileList);
		
		return returnMap;
	}
	
	// 학생정보 수정하기
	public int modifyStudent(Student student) {
		log.debug(CF.GDH+"MemberService.getStudentOne student : " + student + CF.RS);
		return studentMapper.updateStudent(student);
	}
	
	/* 학생정보 삭제하기
	public Student deleteStudent() {
		
	}
	*/

		
		@Autowired  private ManagerMapper managerMapper;
		@Autowired  private TeacherMapper teacherMapper;
		
		// managerList
		 public List<Manager> getManagerList() {
			 List<Manager> list = managerMapper.selectManagerList();
		 return list;   
		 }
		 
		// managerOne
		 public Manager getManagerOne(String loginId) {
			 	Manager manager = new Manager();
			 	manager = managerMapper.selectManagerOne(loginId);
				return manager;
			}
		 
		 // updateManager
		 
		 
		 
		 
		 // deleteManager
		 
		 
		 // teacherList
		 public List<Teacher> getTeacherList() {
			 List<Teacher>list = teacherMapper.selectTeacherList();
		 return list;
		 }
		 
		 
		// teacherOne
		 public Teacher getTeacherOne(String loginId) {
			 	Teacher teacher = new Teacher();
			 	teacher = teacherMapper.selectTeacherOne(loginId);
				return teacher;
			}
		 
		 
		 // updateteacher
		 
		 
		
		 
	}


